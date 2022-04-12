package net.javaguides.springboot.assigments.assigments.service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.assigments.assigments.entity.Student;
import net.javaguides.springboot.assigments.assigments.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public Student create(Student student) {
        return repository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }
}
