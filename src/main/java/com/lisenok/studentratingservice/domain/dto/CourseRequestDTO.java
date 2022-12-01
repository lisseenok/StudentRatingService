package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO курса (запрос)
 */
@Data
@Builder
public class CourseRequestDTO {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isActive;
}
