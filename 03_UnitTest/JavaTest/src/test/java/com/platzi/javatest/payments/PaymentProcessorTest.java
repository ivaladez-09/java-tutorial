package com.platzi.javatest.payments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    PaymentGateway paymentGateway;
    PaymentProcessor paymentProcessor;

    @Before
    public void setup(){
        PaymentGateway paymentGateway = Mockito.mock(PaymentGateway.class);
        PaymentProcessor paymentProcessor = new PaymentProcessor(paymentGateway);
    }

    @Test
    public void payment_is_correct() {
        Mockito.when(paymentGateway.requestPayment(Mockito.any())).
                thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK));

        assertTrue(paymentProcessor.makePayment(1000));
    }

    @Test
    public void payment_is_incorrect() {
        Mockito.when(paymentGateway.requestPayment(Mockito.any())).
                thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR));

        assertFalse(paymentProcessor.makePayment(1000));
    }
}