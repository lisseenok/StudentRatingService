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

    public Optional<Course> findFullById(int id) {
        return courseRepository.findFullById(id);
    }
    public Optional<Course> findWithStudentsById(int id) {
        return courseRepository.findWithStudentsById(id);
    }

    public Optional<Course> findWithLessonsById(int id) {
        return courseRepository.findWithLessonsById(id);
    }

    public Course getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }
    public Course getFullEntityById(int id) {
        return findFullById(id)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }

    public Course getEntityWithStudentsById(int id) {
        return findWithStudentsById(id)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }

    public Course getEntityWithLessonsById(int id) {
        return findWithLessonsById(id)
                .orElseThrow(() -> new CourseNotFoundProblem(id));
    }

    public CourseResponseDTO getFullById(int id) {
        return courseMapper.toDto(getFullEntityById(id));
    }

    public CourseResponseDTO save(Course course) {
        return courseMapper.toDto(courseRepository.save(course));
    }

    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO) {
        return save(courseMapper.toEntity(courseRequestDTO));
    }

    public CourseResponseDTO update(CourseRequestDTO courseRequestDTO, int id) {
        getEntityById(id);
        Course updatedCourse = courseMapper.toEntity(courseRequestDTO);
        updatedCourse.setId(id);
        return save(updatedCourse);
    }

    public CourseResponseDTO addStudent(int courseId, int studentId) {
        Course course = getEntityWithStudentsById(courseId);
        Student student = studentService.getEntityById(studentId);
        course.addStudent(student);
        return save(course);
    }
}
