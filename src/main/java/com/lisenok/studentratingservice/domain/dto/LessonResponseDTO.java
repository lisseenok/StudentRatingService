package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO занятия (ответ)
 */
@Data
public class LessonResponseDTO {

    private int id;

    private String title;

    private LocalDateTime date;

    private int maxGrade;

    private CourseResponseDTO course;

    private Set<GradeResponseDTO> grades;
}
