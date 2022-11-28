package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    Optional<Lesson> findById(int id);
}
