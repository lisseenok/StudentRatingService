package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(int id);
}
