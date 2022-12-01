package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO студента (запрос)
 */
@Data
@Builder
public class StudentRequestDTO {

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;
}
