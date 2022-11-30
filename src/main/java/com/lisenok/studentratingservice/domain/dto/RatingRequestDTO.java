package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

/**
 * DTO рейтинга (запрос)
 */
@Data
public class RatingRequestDTO {

    private int studentId;

    private int courseId;
}
