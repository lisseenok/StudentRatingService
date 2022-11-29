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
    Course toEntity(CourseRequestDTO dto);

    @Named("courseToCourseResponseDto")
    @Mapping(target = "students", source = "students", qualifiedByName = "studentToStudentResponseDtoIgnoreLists")
    @Mapping(target = "lessons", source = "lessons", qualifiedByName = "lessonToLessonResponseDtoIgnoreLists")
    CourseResponseDTO toDto(Course entity);

    @Named("courseToCourseResponseDtoIgnoreLists")
    @Mapping(target = "students", source = "students", ignore = true)
    @Mapping(target = "lessons", source = "lessons", ignore = true)
    CourseResponseDTO toDtoIgnoreLists(Course entity);
}
