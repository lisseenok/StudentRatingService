package com.lisenok.studentratingservice.repository;

import com.lisenok.studentratingservice.domain.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
