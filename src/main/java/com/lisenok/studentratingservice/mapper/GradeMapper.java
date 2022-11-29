package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.GradeRequestDTO;
import com.lisenok.studentratingservice.domain.dto.GradeResponseDTO;
import com.lisenok.studentratingservice.domain.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface GradeMapper {

    @Named("gradeRequestDtoToGrade")
    @Mapping(target = "student", source = "studentId", ignore = true)
    @Mapping(target = "lesson", source = "lessonId", ignore = true)
    Grade toEntity(GradeRequestDTO dto);

    @Named("gradeToGradeResponseDto")
    @Mapping(target = "studentResponseDTO", source = "student", qualifiedByName = "studentToStudentResponseDto")
    @Mapping(target = "lessonResponseDTO", source = "lesson", qualifiedByName = "lessonToLessonResponseDto")
    GradeResponseDTO toDto(Grade entity);

}
