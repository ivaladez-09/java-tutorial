package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@DataJpaTest // Auto-configure in-memory DB (H2) instead of real DB (MySQL, PostgresSQL)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository repository;

    private Employee givenEmployee;

    /*@Test
    public void given_when_then() {
        // given
        // when
        // then
    }*/

    @BeforeEach
    public void setup() {
        givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
    }

    // JUinit test for saving employee
    @Test
    //@DisplayName("Test description")
    public void givenEmployee_whenSave_thenReturnSavedEmployee() {
        // given

        // when
        var savedEmployee = repository.save(givenEmployee);

        // then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployees_whenFindAll_thenReturnAllEmployees() {
        // given
        var givenEmployees = List.of(
                new Employee("Ivan", "Valadez", "ivan@gmail.com"),
                new Employee("Aaron", "Ruiz", "aaron@gmail.com"),
                new Employee("Susana", "Perez", "susana@gmail.com")
        );
        repository.saveAll(givenEmployees);

        // when
        var allEmployees = repository.findAll();

        // then
        assertThat(allEmployees).isNotNull().hasSize(3);
    }

    @Test
    public void givenEmployeeId_whenFindById_thenReturnEmployee() {
        // given
        repository.save(givenEmployee);

        // when
        var employee = repository.findById(givenEmployee.getId())
                .orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(employee.getId()).isEqualTo(givenEmployee.getId());
    }

    @Test
    public void givenEmployee_whenFindByEmail_thenReturnEmployee() {
        // given
        repository.save(givenEmployee);

        // when
        var employee = repository.findByEmail(givenEmployee.getEmail())
                .orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(employee).isNotNull();
        assertThat(employee.getEmail()).isEqualTo(givenEmployee.getEmail());
    }

    @Test
    public void givenEmployee_whenUpdate_thenReturnUpdatedEmployee() {
        // given
        repository.save(givenEmployee);

        // when
        var savedEmployee = repository.findById(givenEmployee.getId())
                .orElseThrow(EntityNotFoundException::new);
        savedEmployee.setFirstName("Ramesh");
        var updatedEmployee = repository.save(savedEmployee);

        // then
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ramesh");
    }

    @Test
    public void givenEmployee_whenDelete_thenReturnRemoveEmployee() {
        // given
        repository.save(givenEmployee);

        // when
        repository.delete(givenEmployee);
        var deletedEmployee = repository.findById(givenEmployee.getId());

        // then
        assertThat(deletedEmployee).isEmpty();
    }

    @Test
    public void givenEmployee_whenFindByFirstNameAndLastName_thenReturnEmployee() {
        // given
        var firstName = "Ivan";
        var lastName = "Valadez";
        repository.save(givenEmployee);

        // when
        var employee = repository.
                findByJPQL(givenEmployee.getFirstName(), givenEmployee.getLastName());

        // then
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo(firstName);
        assertThat(employee.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void givenEmployee_whenFindByFirstNameAndLastNamedParams_thenReturnEmployee() {
        // given
        var firstName = "Ivan";
        var lastName = "Valadez";
        repository.save(givenEmployee);

        // when
        var employee = repository.
                findByJPQLNamedParams(givenEmployee.getFirstName(), givenEmployee.getLastName());

        // then
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo(firstName);
        assertThat(employee.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void givenEmployee_whenFindByFirstNameAndLastNameSQL_thenReturnEmployee() {
        // given
        var firstName = "Ivan";
        var lastName = "Valadez";
        repository.save(givenEmployee);

        // when
        var employee = repository.
                findByNativeSQL(givenEmployee.getFirstName(), givenEmployee.getLastName());

        // then
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo(firstName);
        assertThat(employee.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void givenEmployee_whenFindByFirstNameAndLastNameSQLNamedParams_thenReturnEmployee() {
        // given
        var firstName = "Ivan";
        var lastName = "Valadez";
        repository.save(givenEmployee);

        // when
        var employee = repository.
                findByNativeSQLNamedParams(givenEmployee.getFirstName(), givenEmployee.getLastName());

        // then
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo(firstName);
        assertThat(employee.getLastName()).isEqualTo(lastName);
    }

}
