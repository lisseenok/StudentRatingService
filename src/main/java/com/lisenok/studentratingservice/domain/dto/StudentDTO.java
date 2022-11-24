package com.lisenok.studentratingservice.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {
    private int id;

    private String name;

    private String lastName;

    private String patronymicName;

    private String groupNumber;

    private boolean isActive;

}
