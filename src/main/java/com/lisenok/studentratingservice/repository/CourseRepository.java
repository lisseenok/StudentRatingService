package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findById(int id);
}
