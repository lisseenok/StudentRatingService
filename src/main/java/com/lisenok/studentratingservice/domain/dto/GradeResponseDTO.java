package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

/**
 * DTO оценки (ответ)
 */
@Data
public class GradeResponseDTO {

    private int id;

    private int gradeScore;

    private StudentResponseDTO studentResponseDTO;

    private LessonResponseDTO lessonResponseDTO;
}
