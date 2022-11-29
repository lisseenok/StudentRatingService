package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.StudentRequestDTO;
import com.lisenok.studentratingservice.domain.dto.StudentResponseDTO;
import com.lisenok.studentratingservice.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface StudentMapper {

    @Named("studentRequestDtoToStudent")
    Student toEntity(StudentRequestDTO dto);

    @Named("studentToStudentResponseDto")
    @Mapping(target = "courses", source = "courses", qualifiedByName = "courseToCourseResponseDtoIgnoreLists")
    StudentResponseDTO toDto(Student entity);

    @Named("studentToStudentResponseDtoIgnoreLists")
    @Mapping(target = "courses", source = "courses", ignore = true)
    StudentResponseDTO toDtoIgnoreCourses(Student entity);
}
