package net.javaguides.springboot.assigments.assigments.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.assigments.assigments.entity.Student;
import net.javaguides.springboot.assigments.assigments.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(service.create(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
