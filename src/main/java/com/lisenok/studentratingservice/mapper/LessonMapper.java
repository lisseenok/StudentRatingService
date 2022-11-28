package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.LessonDTO;
import com.lisenok.studentratingservice.domain.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface LessonMapper {

    @Mapping(target = "course", source = "course", qualifiedByName = "toEntity")
    Lesson toEntity(LessonDTO dto);

    @Named("fromLessonToDto")
    @Mapping(target = "course", source = "course", qualifiedByName = "toDto")
    LessonDTO toDto(Lesson entity);
}
