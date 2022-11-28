package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.GradeRequestDTO;
import com.lisenok.studentratingservice.domain.dto.GradeResponseDTO;
import com.lisenok.studentratingservice.domain.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface GradeMapper {

    @Mapping(target = "student", source = "studentId", ignore = true)
    @Mapping(target = "lesson", source = "lessonId", ignore = true)
    Grade toEntity(GradeRequestDTO dto);

    @Mapping(target = "studentDTO", source = "student", qualifiedByName = "fromStudentToDto")
    @Mapping(target = "lessonDTO", source = "lesson", qualifiedByName = "fromLessonToDto")
    GradeResponseDTO toDto(Grade entity);

}
