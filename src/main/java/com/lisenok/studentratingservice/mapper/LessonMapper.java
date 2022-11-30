package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.LessonRequestDTO;
import com.lisenok.studentratingservice.domain.dto.LessonResponseDTO;
import com.lisenok.studentratingservice.domain.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, GradeMapper.class})
public interface LessonMapper {

    /**
     * Метод маппинга DTO занятия (запрос) в модель занятия
     *
     * @param dto DTO занятия (запрос)
     * @return модель занятия
     */
    @Named("lessonRequestDtoToLesson")
    @Mapping(target = "course", source = "courseId", ignore = true)
    Lesson toEntity(LessonRequestDTO dto);

    /**
     * Метод маппинга модели занятия в DTO занятия (ответ)
     *
     * @param entity модель занятия
     * @return DTO занятия (ответ)
     */
    @Named("lessonToLessonResponseDto")
    @Mapping(target = "course", source = "course", qualifiedByName = "courseToCourseResponseDtoIgnoreLists")
    @Mapping(target = "grades", source = "grades", qualifiedByName = "gradeToGradeResponseDto")
    LessonResponseDTO toDto(Lesson entity);

    /**
     * Метод маппинга модели занятия в DTO занятия (ответ) без связанных сущностей
     *
     * @param entity модель занятия
     * @return DTO занятия (ответ) без связанных сущностей
     */
    @Named("lessonToLessonResponseDtoIgnoreLists")
    @Mapping(target = "course", source = "course", ignore = true)
    @Mapping(target = "grades", source = "grades", ignore = true)
    LessonResponseDTO toDtoIgnoreLists(Lesson entity);
}
