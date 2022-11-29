package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.CourseRequestDTO;
import com.lisenok.studentratingservice.domain.dto.CourseResponseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Student;
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

    private final StudentService studentService;

    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    public CourseResponseDTO getById(int id) {
        return findById(id)
                .map(courseMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }

    public Course getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }

    public CourseResponseDTO save(Course course) {
        return courseMapper.toDto(courseRepository.save(course));
    }

    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO) {
        return save(courseMapper.toEntity(courseRequestDTO));
    }

    public CourseResponseDTO update(CourseRequestDTO courseRequestDTO, int id) {
        findById(id).map(courseMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
        return save(courseRequestDTO);
    }

    public CourseResponseDTO addStudent(int courseId, int studentId) {
        Course course = getEntityById(courseId);
        Student student = studentService.getEntityById(studentId);
        course.addStudent(student);
        return save(course);
    }
}
