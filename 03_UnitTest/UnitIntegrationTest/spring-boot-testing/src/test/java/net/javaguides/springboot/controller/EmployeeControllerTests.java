package net.javaguides.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class EmployeeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void givenEmployee_whenCreate_thenReturnEmployee() throws Exception {
        // given
        Employee givenEmployee = Employee.builder()
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        given(service.save(any())).willAnswer((invocation) -> invocation.getArgument(0));

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
        given(service.getAll()).willReturn(employees);

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
                .id(1L)
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        given(service.getById(1L)).willReturn(Optional.ofNullable(givenEmployee));

        // when
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", 1L));

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
                .id(1L)
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        given(service.getById(1L)).willReturn(Optional.empty());

        // when
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", 1L));

        // then
        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenEmployeeIdAndEmployee_whenUpdate_thenReturnUpdatedEmployee() throws Exception {
        // given
        Employee savedEmployee = Employee.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        Employee updatedEmployee = Employee.builder()
                .id(1L)
                .firstName("Aaron")
                .lastName("Ruiz")
                .email("aruiz@gmail.com")
                .build();
        given(service.getById(1L)).willReturn(Optional.ofNullable(savedEmployee));
        given(service.save(any())).willAnswer((invocation) -> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", 1L)
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
                .id(1L)
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        Employee updatedEmployee = Employee.builder()
                .id(1L)
                .firstName("Aaron")
                .lastName("Ruiz")
                .email("aruiz@gmail.com")
                .build();
        given(service.getById(1L)).willReturn(Optional.empty());

        // when
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", 1L)
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
                .id(1L)
                .firstName("Ivan")
                .lastName("Valadez")
                .email("ivaladez@gmail.com")
                .build();
        willDoNothing().given(service).deleteById(anyLong());

        // when
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", 1L));

        // then
        response.andDo(print())
                .andExpect(status().isOk());
    }
}
