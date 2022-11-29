package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

@Data
public class RatingResponseDTO {

    private double ratingScore;

    private boolean isCredited;

    private CourseResponseDTO courseResponseDTO;
}
