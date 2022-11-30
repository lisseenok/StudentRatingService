package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.domain.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface RatingMapper {

    @Mapping(target = "courseResponseDTO", source = "course", qualifiedByName = "courseToCourseResponseDtoIgnoreLists")
    RatingResponseDTO toDto(Rating entity);

}
