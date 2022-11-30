package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

/**
 * DTO оценки (запрос)
 */
@Data
public class GradeRequestDTO {

    private int gradeScore;

    private int studentId;

    private int lessonId;
}
