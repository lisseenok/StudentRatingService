package com.lisenok.studentratingservice.mapper;

import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.domain.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface RatingMapper {

    /**
     * Метод маппинга модели рейтинга в DTO рейтинга (ответ)
     *
     * @param entity модель рейтинга
     * @return DTO рейтинга (ответ)
     */
    @Mapping(target = "courseResponseDTO", source = "course", qualifiedByName = "courseToCourseResponseDtoIgnoreLists")
    RatingResponseDTO toDto(Rating entity);

}
