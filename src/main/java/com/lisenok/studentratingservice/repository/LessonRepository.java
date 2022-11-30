package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Lesson;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    @EntityGraph(value = "Lesson")
    Optional<Lesson> findById(int id);

    @EntityGraph(value = "Lesson.grades")
    Optional<Lesson> findWithGradesById(int id);

    @EntityGraph(value = "Lesson.grades")
    List<Lesson> getAllByCourse(Course course);
}
