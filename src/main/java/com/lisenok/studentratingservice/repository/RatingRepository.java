package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    /**
     * Метод получения всех моделей рейтингов
     *
     * @return список моделей рейтингов
     */
    List<Rating> findAll();
}
