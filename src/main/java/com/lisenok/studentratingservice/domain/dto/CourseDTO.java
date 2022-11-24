package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CourseDTO {
    private int id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isActive;

}
