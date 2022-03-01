package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatcher.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest01 {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Spy
    private BookingDAO bookingDAOMock;
    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;

    /* -- All way

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;
    private ArgumentCaptor<Double> doubleCaptor;

    @BeforeEach
    void setup(){
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingService = mock(BookingService.class);
        mailSenderMock = mock(MailSender.class);
        bookingDAOMock = spy(BookingDAO.class); //Call the actual method/class - Partial mock

        bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

        doubleCaptor = ArgumentCaptor.forClass(Double.class);
    }*/

    @Test
    void should_CalculateCorrectPrice_When_CorrectInput() {
        BookingRequest bookingRequest = new BookingRequest("id",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                false);
        double expected = 4 * 2 * 50.0;

        double actual = bookingService.calculatePrice(bookingRequest);

        assertEquals(expected, actual);
    }

    @Test
    void should_CountAvailablePlaces_When_OneRoomAvailable(){
        when(roomServiceMock.getAvailableRooms()).thenReturn(
                Collections.singletonList(new Room("Room 1", 2))
        );
        int expected = 2;

        int actual = bookingService.getAvailablePlaceCount();

        assertEquals(expected, actual);
    }

    @Test
    void should_CountAvailablePlaces_When_MultipleRoomsAvailable(){
        List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 5));
        when(roomServiceMock.getAvailableRooms()).thenReturn(rooms);
        int expected = 7;

        int actual = bookingService.getAvailablePlaceCount();

        assertEquals(expected, actual);
    }

    @Test // Multiple thenReturns calls
    void should_CountAvailablePlaces_When_CalledMultipleTimes(){
        when(roomServiceMock.getAvailableRooms()).
                thenReturn(Collections.singletonList(new Room("Room 1", 5))).
                thenReturn(Collections.emptyList());
        int expectedFirstCall = 5;
        int expectedSecondCall = 0;

        int actualFirstCall = bookingService.getAvailablePlaceCount();
        int actualSecondCall = bookingService.getAvailablePlaceCount();

        assertAll(
                () -> assertEquals(expectedFirstCall, actualFirstCall),
                () -> assertEquals(expectedSecondCall, actualSecondCall)
        );
    }

    @Test
    void should_ThrowException_When_NoRoomAvailable(){
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                false);
        when(roomServiceMock.findAvailableRoomId(bookingRequest)).
                thenThrow(BusinessException.class);

        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        assertThrows(BusinessException.class, executable);
    }

    @Test
    void should_NotCompleteBooking_When_PriceTooHigh(){
        BookingRequest bookingRequest = new BookingRequest("2",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                true);
        when(paymentServiceMock.pay(any(), anyDouble())).thenThrow(BusinessException.class);

        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        assertThrows(BusinessException.class, executable);
    }

    @Test
    void should_InvokePayment_When_Prepaid() {
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                true);

        bookingService.makeBooking(bookingRequest);

        verify(paymentServiceMock, times(1)).pay(bookingRequest, 400.0);
        verifyNoMoreInteractions(paymentServiceMock);

    }

    @Test
    void should_InvokePayment_When_NotPrepaid() {
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                false);

        bookingService.makeBooking(bookingRequest);

        verify(paymentServiceMock, never()).pay(any(), anyDouble());
        verifyNoMoreInteractions(paymentServiceMock);

    }


    @Test
    void should_MakeBooking_When_InputOk() {
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                false);

        String bookingId = bookingService.makeBooking(bookingRequest);

        verify(bookingDAOMock).save(bookingRequest);
        System.out.println("bookingId=" + bookingId);
    }

    @Test
    void should_CancelBooking_When_InputOK(){
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";
        // This is because we are using the actual implementation of BookingDAO as spy()
        doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);

        bookingService.cancelBooking(bookingId);
    }

    @Test
    void should_ThrowException_When_MailNotReady(){
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                false);
        // "when()" does not work with methods that returns "void", so, we use "doThrow().when()"
        doThrow(BusinessException.class).when(mailSenderMock).sendBookingConfirmation(any());

        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        assertThrows(BusinessException.class, executable);
    }

    @Test
    void should_NotThrowException_When_MailNotReady(){
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                false);
        //doNothing().when(mailSenderMock).sendBookingConfirmation(any());

        bookingService.makeBooking(bookingRequest);
    }

    @Test
    void should_PayCorrectPrice_When_InputOK() {
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                true);

        bookingService.makeBooking(bookingRequest);

        verify(paymentServiceMock).pay(eq(bookingRequest), doubleCaptor.capture());
        double capturedArgument = doubleCaptor.getValue();
        assertEquals(400.0, capturedArgument);
    }

    @Test
    void should_PayCorrectPrice_When_MultipleCalls() {
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                true);
        BookingRequest bookingRequest2 = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 2),
                2,
                true);
        List<Double> expectedValues = Arrays.asList(400.0, 100.0);

        bookingService.makeBooking(bookingRequest);
        bookingService.makeBooking(bookingRequest2);

        verify(paymentServiceMock, times(2)).pay(any(), doubleCaptor.capture());
        List<Double> capturedArguments = doubleCaptor.getAllValues();
        assertEquals(expectedValues, capturedArguments);
    }

    @Test
    void should_CountAvailablePlaces_When_OneRoomAvailable_BDD(){

        // Follow BDD test design
        // given() = when() and willReturn() = thenReturn()
        given(roomServiceMock.getAvailableRooms()).willReturn(
                Collections.singletonList(new Room("Room 1", 2))
        );
        int expected = 2;

        int actual = bookingService.getAvailablePlaceCount();

        assertEquals(expected, actual);
    }

    @Test
    void should_InvokePayment_When_Prepaid_BDD() {
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 5),
                2,
                true);

        bookingService.makeBooking(bookingRequest);

        then(paymentServiceMock).should(times(1)).pay(bookingRequest, 400.0);
        verifyNoMoreInteractions(paymentServiceMock);

    }

    @Test // Mocking STATIC methods
    void should_CalculateCorrectPrice(){
        try(MockedStatic<CurrencyConverter> mockedConverted = mockStatic(CurrencyConverter.class)){
            BookingRequest bookingRequest = new BookingRequest("1",
                    LocalDate.of(2020, 1, 1),
                    LocalDate.of(2020, 1, 5),
                    2,
                    true);
            double expected = 400.0;
            mockedConverted.when(() -> CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);

            double actual = bookingService.calculatePriceEuro(bookingRequest);

            assertEquals(expected, actual);
        }
    }

    @Test // Mocking STATIC methods with Answers
    void should_CalculateCorrectPrice_2(){
        try(MockedStatic<CurrencyConverter> mockedConverted = mockStatic(CurrencyConverter.class)){
            BookingRequest bookingRequest = new BookingRequest("1",
                    LocalDate.of(2020, 1, 1),
                    LocalDate.of(2020, 1, 5),
                    2,
                    true);
            double expected = 400.0;
            mockedConverted.when(() -> CurrencyConverter.toEuro(anyDouble())).
                    thenAnswer(inv -> (double) inv.getArgument(0) * 0.8);

            double actual = bookingService.calculatePriceEuro(bookingRequest);

            assertEquals(expected, actual);
        }
    }
}