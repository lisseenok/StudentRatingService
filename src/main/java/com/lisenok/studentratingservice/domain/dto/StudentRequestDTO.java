package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

@Data
public class StudentRequestDTO {

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;
}
