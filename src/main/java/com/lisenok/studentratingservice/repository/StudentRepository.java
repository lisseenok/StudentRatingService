package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * Метод получения модели студента без связанных сущностей
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    @EntityGraph(value = "Student")
    Optional<Student> findById(int id);

    /**
     * Метод получения модели студента со связанными курсами
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    @EntityGraph(value = "Student.courses")
    Optional<Student> findWithCoursesById(int id);

    /**
     * Метод получения модели студента со связанными рейтингами
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    @EntityGraph(value = "Student.ratings")
    Optional<Student> findWithRatingsById(int id);

    /**
     * Метод получения модели студента со связанными курсами, рейтингами и оценками
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    @EntityGraph(value = "Student.courses-grades-ratings")
    Optional<Student> findFullById(int id);

}
