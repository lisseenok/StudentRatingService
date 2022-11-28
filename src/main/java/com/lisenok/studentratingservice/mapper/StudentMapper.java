package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.StudentDTO;
import com.lisenok.studentratingservice.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface StudentMapper {

    Student toEntity(StudentDTO dto);

    @Named("fromStudentToDto")
    @Mapping(target = "courses", source = "courses", qualifiedByName = "toDtoIgnoreLists")
    StudentDTO toDto(Student entity);

    @Mapping(target = "courses", source = "courses", ignore = true)
    @Named("toDtoIgnoreCourses")
    StudentDTO toDtoIgnoreCourses(Student entity);
}
