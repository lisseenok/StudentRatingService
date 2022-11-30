package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Grade;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    /**
     * Метод получения оценки студента за занятие
     *
     * @param lesson  модель занятия
     * @param student модель студента
     * @return модель оценки или null, если ее не существует
     */
    Optional<Grade> getGradeByLessonAndStudent(Lesson lesson, Student student);
}
