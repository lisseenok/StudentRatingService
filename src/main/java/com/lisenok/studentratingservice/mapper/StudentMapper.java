package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.StudentDTO;
import com.lisenok.studentratingservice.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentDTO dto);

    StudentDTO toDto(Student entity);
}
