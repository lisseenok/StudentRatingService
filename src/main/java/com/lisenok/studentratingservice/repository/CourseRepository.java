package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @EntityGraph(value = "Course")
    Optional<Course> findById(int id);
    @EntityGraph(value = "Course.students-lessons")
    Optional<Course> findFullById(int id);

    @EntityGraph(value = "Course.students")
    Optional<Course> findWithStudentsById(int id);

    @EntityGraph(value = "Course.lessons")
    Optional<Course> findWithLessonsById(int id);
}
