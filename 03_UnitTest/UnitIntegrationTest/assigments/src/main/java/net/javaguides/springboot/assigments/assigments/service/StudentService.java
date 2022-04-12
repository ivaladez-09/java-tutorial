package net.javaguides.springboot.assigments.assigments.service;

import net.javaguides.springboot.assigments.assigments.entity.Student;

import java.util.List;

public interface StudentService {
    Student create(Student student);
    List<Student> findAll();

}
