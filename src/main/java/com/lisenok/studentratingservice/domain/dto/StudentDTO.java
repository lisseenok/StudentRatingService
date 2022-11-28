package com.lisenok.studentratingservice.domain.dto;

import com.lisenok.studentratingservice.domain.model.Grade;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentDTO {
    private int id;

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;

    private List<CourseDTO> courses;

    private List<Grade> grades;

}
