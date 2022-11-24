package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.CourseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO dto);
    CourseDTO toDto(Course entity);
}
