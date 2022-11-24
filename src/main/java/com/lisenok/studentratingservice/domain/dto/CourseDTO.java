package com.lisenok.studentratingservice.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

@Data
@Schema
public class CourseDTO {
    private int id;

    private String title;

    private Instant startDate;

    private Instant endDate;

    private boolean isActive;

}
