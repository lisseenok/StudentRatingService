package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentResponseDTO {
    private int id;

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;

    private List<CourseResponseDTO> courses;

}
