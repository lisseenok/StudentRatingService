package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

@Data
public class GradeRequestDTO {

    private int gradeScore;

    private int studentId;

    private int lessonId;
}
