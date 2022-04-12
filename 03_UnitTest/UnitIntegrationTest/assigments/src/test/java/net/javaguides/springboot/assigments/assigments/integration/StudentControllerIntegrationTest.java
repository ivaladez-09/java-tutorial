package net.javaguides.springboot.assigments.assigments.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.assigments.assigments.entity.Student;
import net.javaguides.springboot.assigments.assigments.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerIntegrationTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StudentRepository repository;

    @BeforeEach
    public void setup() {
        System.out.println(MY_SQL_CONTAINER.getUsername());
        System.out.println(MY_SQL_CONTAINER.getPassword());
        System.out.println(MY_SQL_CONTAINER.getDatabaseName());
        System.out.println(MY_SQL_CONTAINER.getJdbcUrl());
        repository.deleteAll();
    }

    @Test
    public void given_whenGetAll_thenReturnAllStudents() throws Exception {
        // given
        List<Student> students = new ArrayList<>();
        students.add(Student.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build());
        students.add(Student.builder()
                .firstName("Aaron")
                .lastName("Ruiz")
                .email("aruiz@gmail.com")
                .build());
        repository.saveAll(students);

        // when
        ResultActions response = mockMvc.perform(get("/api/students"));

        //then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(students.size())));
    }
}
