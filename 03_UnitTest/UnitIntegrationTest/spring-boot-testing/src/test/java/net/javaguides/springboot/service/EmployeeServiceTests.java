package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import static org.assertj.core.api.Assertions.*;

import net.javaguides.springboot.service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private EmployeeServiceImpl service;
    private Employee givenEmployee;

    @BeforeEach
    public void setup() {
        givenEmployee = Employee.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
    }

    @Test
    public void givenEmployee_whenSave_thenReturnEmployee() {
        // given
        given(repository.findByEmail(any())).willReturn(Optional.empty());
        given(repository.save(any())).willReturn(givenEmployee);

        // when
        var savedEmployee = service.save(givenEmployee);

        // then
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void givenExistingEmail_whenSave_thenThrowsException() {
        // given
        given(repository.findByEmail(any())).willReturn(Optional.ofNullable(givenEmployee));

        // when
        Executable executable = () -> service.save(givenEmployee);

        // then
        assertThrows(ResourceNotFoundException.class, executable);
    }

    @Test
    public void givenEmployees_whenGetAll_thenReturnEmployees() {
        // given
        var secondGivenEmployee = Employee.builder()
                .id(2L)
                .firstName("Emmanuel")
                .lastName("Garcia")
                .email("egarcia@gmail.com")
                .build();
        given(repository.findAll()).willReturn(List.of(givenEmployee, secondGivenEmployee));

        // when
        var employees = service.getAll();

        // then
        assertThat(employees).isNotNull().hasSize(2);
    }

    @Test
    public void givenEmptyEmployees_whenGetAll_thenReturnEmptyEmployees() {
        // given
        var secondGivenEmployee = Employee.builder()
                .id(2L)
                .firstName("Emmanuel")
                .lastName("Garcia")
                .email("egarcia@gmail.com")
                .build();
        given(repository.findAll()).willReturn(Collections.emptyList());

        // when
        var employees = service.getAll();

        // then
        assertThat(employees).isEmpty();
        assertThat(employees.size()).isEqualTo(0);
    }

    @Test
    public void givenEmployeeId_whenGetById_thenReturnEmployee() {
        // given
        given(repository.findById(any())).willReturn(Optional.ofNullable(givenEmployee));

        // when
        var employee = service.getById(givenEmployee.getId());

        // then
        assertThat(employee).isNotEmpty();
        assertThat(employee.get().getId()).isEqualTo(givenEmployee.getId());
    }

    @Test
    public void givenEmployee_whenUpdate_thenReturnEmployee() {
        // given
        given(repository.save(any())).willReturn(givenEmployee);
        givenEmployee.setFirstName("Aaron");

        // when
        var updatedEmployee = service.update(givenEmployee);

        // then
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Aaron");
    }

    @Test
    public void givenEmployeeId_whenDelete_thenReturnVoid() {
        // given
        willDoNothing().given(repository).deleteById(any());

        // when
        service.deleteById(givenEmployee.getId());

        // then
        verify(repository, times(1)).deleteById(givenEmployee.getId());
    }
}
