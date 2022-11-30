package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO занятия (запрос)
 */
@Data
public class LessonRequestDTO {

    private String title;

    private LocalDateTime date;

    private int maxGrade;

    private int courseId;
}
