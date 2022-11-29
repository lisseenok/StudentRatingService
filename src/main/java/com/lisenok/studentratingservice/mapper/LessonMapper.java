package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.LessonRequestDTO;
import com.lisenok.studentratingservice.domain.dto.LessonResponseDTO;
import com.lisenok.studentratingservice.domain.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, GradeMapper.class})
public interface LessonMapper {

    @Named("lessonRequestDtoToLesson")
    @Mapping(target = "course", source = "courseId", ignore = true)
    Lesson toEntity(LessonRequestDTO dto);

    @Named("lessonToLessonResponseDto")
    @Mapping(target = "course", source = "course", qualifiedByName = "courseToCourseResponseDto")
    @Mapping(target = "grades", source = "grades", qualifiedByName = "gradeToGradeResponseDto")
    LessonResponseDTO toDto(Lesson entity);

    @Named("lessonToLessonResponseDtoIgnoreLists")
    @Mapping(target = "course", source = "course", ignore = true)
    @Mapping(target = "grades", source = "grades", ignore = true)
    LessonResponseDTO toDtoIgnoreLists(Lesson entity);
}
