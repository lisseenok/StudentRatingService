package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.CourseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.mapper.CourseMapper;
import com.lisenok.studentratingservice.problem.CourseNotFoundProblem;
import com.lisenok.studentratingservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    public CourseDTO getById(int id) {
        return findById(id)
                .map(courseMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }

    public CourseDTO save(CourseDTO courseDTO) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(courseDTO)));
    }

    public CourseDTO update(CourseDTO courseDTO) {
        int id = courseDTO.getId();
        findById(id).map(courseMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
        return save(courseDTO);
    }
}
