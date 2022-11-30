package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.CourseRequestDTO;
import com.lisenok.studentratingservice.domain.dto.CourseResponseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface CourseMapper {

    @Named("courseRequestDtoToCourse")
    @Mapping(target = "isActive", expression = "java((dto.isActive())?dto.isActive():false)")
    Course toEntity(CourseRequestDTO dto);

    @Named("courseToCourseResponseDto")
    @Mapping(target = "students", source = "students", qualifiedByName = "studentToStudentResponseDtoIgnoreLists")
    @Mapping(target = "lessons", source = "lessons", qualifiedByName = "lessonToLessonResponseDtoIgnoreLists")
    @Mapping(target = "isActive", expression = "java((entity.isActive())?entity.isActive():false)")
    CourseResponseDTO toDto(Course entity);

    @Named("courseToCourseResponseDtoIgnoreLists")
    @Mapping(target = "students", source = "students", ignore = true)
    @Mapping(target = "lessons", source = "lessons", ignore = true)
    @Mapping(target = "isActive", expression = "java((entity.isActive())?entity.isActive():false)")
    CourseResponseDTO toDtoIgnoreLists(Course entity);
}
