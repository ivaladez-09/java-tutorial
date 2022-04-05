package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee employee);
    List<Employee> getAll();
    Optional<Employee> getById(long id);
    Employee update(Employee employee);
    void deleteById(long id);
}
