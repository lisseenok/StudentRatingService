package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class StudentResponseDTO {
    private int id;

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;

    private Set<CourseResponseDTO> courses;

}
