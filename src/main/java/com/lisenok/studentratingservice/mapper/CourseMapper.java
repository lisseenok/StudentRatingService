package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.CourseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface CourseMapper {

    @Named("toEntity")
    Course toEntity(CourseDTO dto);

    @Named("toDto")
    @Mapping(target = "students", source = "students", qualifiedByName = "toDtoIgnoreCourses")
    CourseDTO toDto(Course entity);

    @Mapping(target = "students", source = "students", ignore = true)
    @Named("toDtoIgnoreLists")
    CourseDTO toDtoIgnoreLists(Course entity);
}
