package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonDTO {

    private int id;

    private String title;

    private LocalDateTime date;

    private int maxGrade;

    private CourseDTO course;
}
