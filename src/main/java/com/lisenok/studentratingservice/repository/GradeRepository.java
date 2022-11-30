package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Grade;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Optional<Grade> getGradeByLessonAndStudent(Lesson lesson, Student student);
}
