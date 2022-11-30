package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Lesson;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    /**
     * Метод получения модели занятия без связанных сущностей
     * @param id - уникальный идентификатор занятия
     * @return модель занятия или null, если занятие не найдено
     */
    @EntityGraph(value = "Lesson")
    Optional<Lesson> findById(int id);

    /**
     * Метод получения модели занятия со связанными оценками
     * @param id - уникальный идентификатор занятия
     * @return модель занятия или null, если занятие не найдено
     */
    @EntityGraph(value = "Lesson.grades")
    Optional<Lesson> findWithGradesById(int id);

    /**
     * Метод получения всех моделей занятий курса со связанными оценками
     *
     * @param course - модель курса
     * @return модели занятия
     */
    @EntityGraph(value = "Lesson.grades")
    List<Lesson> getAllByCourse(Course course);
}
