package net.javaguides.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerIT extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        System.out.println(MY_SQL_CONTAINER.getUsername());
        System.out.println(MY_SQL_CONTAINER.getPassword());
        System.out.println(MY_SQL_CONTAINER.getDatabaseName());
        System.out.println(MY_SQL_CONTAINER.getJdbcUrl());
        repository.deleteAll();
    }

    @Test
    public void givenEmployee_whenCreate_thenReturnEmployee() throws Exception {
        // given
        Employee givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();

        // when
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(givenEmployee)));

        // then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(givenEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(givenEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(givenEmployee.getEmail())));
    }

    @Test
    public void givenEmployees_whenGetAll_thenReturnEmployees() throws Exception {
        // given
        List<Employee> employees = new ArrayList<>();
        Employee givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        Employee givenEmployee2 = Employee.builder()
                .firstName("Aaron")
                .lastName("Ruiz")
                .email("aruiz@gmail.com")
                .build();
        employees.add(givenEmployee);
        employees.add(givenEmployee2);
        repository.saveAll(employees);

        // when
        ResultActions response = mockMvc.perform(get("/api/employees"));

        // then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(employees.size())));
    }

    @Test
    public void givenEmployeeId_whenGetById_thenReturnEmployee() throws Exception {
        // given
        Employee givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        repository.save(givenEmployee);

        // when
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", givenEmployee.getId()));

        // then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(givenEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(givenEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(givenEmployee.getEmail())));
    }

    @Test
    public void givenEmployeeId_whenGetByIdDoesNotExist_thenReturn404() throws Exception {
        // given
        Employee givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        repository.save(givenEmployee);

        // when
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", givenEmployee.getId() + 1));

        // then
        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenEmployeeIdAndEmployee_whenUpdate_thenReturnUpdatedEmployee() throws Exception {
        // given
        Employee savedEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        repository.save(savedEmployee);

        Employee updatedEmployee = Employee.builder()
                .firstName("Aaron")
                .lastName("Ruiz")
                .email("aruiz@gmail.com")
                .build();

        // when
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedEmployee)));

        // then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
    }

    @Test
    public void givenEmployeeIdAndEmployee_whenUpdateAndEmployeeIdDoesNotExist_thenReturn404() throws Exception {
        // given
        Employee savedEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        repository.save(savedEmployee);

        Employee updatedEmployee = Employee.builder()
                .firstName("Aaron")
                .lastName("Ruiz")
                .email("aruiz@gmail.com")
                .build();

        // when
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", savedEmployee.getId() + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedEmployee)));

        // then
        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenEmployeeId_whenDelete_thenReturn200() throws Exception {
        // given
        Employee givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        repository.save(givenEmployee);

        // when
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", givenEmployee.getId()));
        ResultActions getByIdResponse = mockMvc.perform(get("/api/employees/{id}", givenEmployee.getId()));

        // then
        response.andDo(print())
                .andExpect(status().isOk());
        getByIdResponse.andDo(print())
                .andExpect(status().isNotFound());
    }
}
