package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @EntityGraph(value = "Student")
    Optional<Student> findById(int id);

    @EntityGraph(value = "Student.courses")
    Optional<Student> findWithCoursesById(int id);

    @EntityGraph(value = "Student.ratings")
    Optional<Student> findWithRatingsById(int id);

    @EntityGraph(value = "Student.courses-grades-ratings")
    Optional<Student> findFullById(int id);

}
