package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

@Data
public class GradeResponseDTO {

    private int id;

    private int gradeScore;

    private StudentResponseDTO studentResponseDTO;

    private LessonResponseDTO lessonResponseDTO;
}
