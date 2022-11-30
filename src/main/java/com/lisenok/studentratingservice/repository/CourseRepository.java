package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    /**
     * Метод получения курса без связанных сущностей
     * @param id - уникальный идентификатор курса
     * @return курс или null
     */
    @EntityGraph(value = "Course")
    Optional<Course> findById(int id);

    /**
     * Метод получения курса с связанными студентами и занятиями
     * @param id - уникальный идентификатор курса
     * @return курс или null
     */
    @EntityGraph(value = "Course.students-lessons")
    Optional<Course> findFullById(int id);

    /**
     * Метод получения курса с связанными студентами
     * @param id - уникальный идентификатор курса
     * @return курс или null
     */
    @EntityGraph(value = "Course.students")
    Optional<Course> findWithStudentsById(int id);

    /**
     * Метод получения курса с связанными занятиями
     *
     * @param id - уникальный идентификатор курса
     * @return курс или null
     */
    @EntityGraph(value = "Course.lessons")
    Optional<Course> findWithLessonsById(int id);
}
