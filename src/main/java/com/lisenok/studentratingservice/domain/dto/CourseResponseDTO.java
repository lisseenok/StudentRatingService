package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CourseResponseDTO {
    private int id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isActive;

    private List<StudentResponseDTO> students;

    private List<LessonResponseDTO> lessons;

}
