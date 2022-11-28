package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Grade;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Grade getGradeByLessonAndStudent(Lesson lesson, Student student);
}
