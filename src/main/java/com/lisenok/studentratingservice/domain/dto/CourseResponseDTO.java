package com.lisenok.studentratingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO курса (ответ)
 */
@Data
@Builder
public class CourseResponseDTO {
    private int id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isActive;

    private Set<StudentResponseDTO> students;

    private Set<LessonResponseDTO> lessons;

}
