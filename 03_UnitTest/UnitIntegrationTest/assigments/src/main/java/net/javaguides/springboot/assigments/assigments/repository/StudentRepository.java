package net.javaguides.springboot.assigments.assigments.repository;

import net.javaguides.springboot.assigments.assigments.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student, Long> {

}
