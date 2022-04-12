package net.javaguides.springboot.assigments.assigments.integration;

import net.javaguides.springboot.assigments.assigments.entity.Student;
import net.javaguides.springboot.assigments.assigments.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryIntegrationTests extends AbstractContainerBaseTest{

    @Autowired
    private StudentRepository repository;

    private Student givenStudent;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
        givenStudent = Student.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
    }

    @Test
    public void given_whenFindAll() {
        // given

        // when
        var student = repository.save(givenStudent);

        // then
        assertThat(student).isNotNull();
        assertThat(student.getId()).isGreaterThan(0);
    }

}
