package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.StudentRequestDTO;
import com.lisenok.studentratingservice.domain.dto.StudentResponseDTO;
import com.lisenok.studentratingservice.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface StudentMapper {

    /**
     * Метод маппинга DTO студента (запрос) в модель студента
     *
     * @param dto DTO студента (запрос)
     * @return модель студента
     */
    @Named("studentRequestDtoToStudent")
    @Mapping(target = "isActive", expression = "java((dto.isActive())?dto.isActive():false)")
    Student toEntity(StudentRequestDTO dto);

    /**
     * Метод маппинга модели студента в DTO студента (ответ)
     *
     * @param entity модель студента
     * @return DTO студента (ответ)
     */
    @Named("studentToStudentResponseDto")
    @Mapping(target = "isActive", expression = "java((entity.isActive())?entity.isActive():false)")
    @Mapping(target = "courses", source = "courses", qualifiedByName = "courseToCourseResponseDtoIgnoreLists")
    StudentResponseDTO toDto(Student entity);

    /**
     * Метод маппинга модели студента в DTO студента (ответ) без связанных сущностей
     *
     * @param entity модель студента
     * @return DTO студента (ответ) без связанных сущностей
     */
    @Named("studentToStudentResponseDtoIgnoreLists")
    @Mapping(target = "isActive", expression = "java((entity.isActive())?entity.isActive():false)")
    @Mapping(target = "courses", source = "courses", ignore = true)
    StudentResponseDTO toDtoIgnoreCourses(Student entity);
}
