package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.GradeRequestDTO;
import com.lisenok.studentratingservice.domain.dto.GradeResponseDTO;
import com.lisenok.studentratingservice.domain.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface GradeMapper {

    /**
     * Метод маппинга DTO оценки (запрос) в модель оценки
     *
     * @param dto DTO оценки (запрос)
     * @return модель оценки
     */
    @Named("gradeRequestDtoToGrade")
    @Mapping(target = "student", source = "studentId", ignore = true)
    @Mapping(target = "lesson", source = "lessonId", ignore = true)
    Grade toEntity(GradeRequestDTO dto);

    /**
     * Метод маппинга модели оценки в DTO оценки (ответ)
     *
     * @param entity модель оценки
     * @return DTO оценки (ответ)
     */
    @Named("gradeToGradeResponseDto")
    @Mapping(target = "studentResponseDTO", source = "student", qualifiedByName = "studentToStudentResponseDtoIgnoreLists")
    @Mapping(target = "lessonResponseDTO", source = "lesson", qualifiedByName = "lessonToLessonResponseDtoIgnoreLists")
    GradeResponseDTO toDto(Grade entity);

}
