package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LessonResponseDTO {

    private int id;

    private String title;

    private LocalDateTime date;

    private int maxGrade;

    private CourseResponseDTO course;

    private List<GradeResponseDTO> grades;
}
