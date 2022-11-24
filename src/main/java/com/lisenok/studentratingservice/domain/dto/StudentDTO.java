package com.lisenok.studentratingservice.domain.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;

}
