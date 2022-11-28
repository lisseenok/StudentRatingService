package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

@Data
public class RatingRequestDTO {

    private int studentId;

    private int courseId;
}
