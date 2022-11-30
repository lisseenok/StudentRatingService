package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.LessonRequestDTO;
import com.lisenok.studentratingservice.domain.dto.LessonResponseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.mapper.LessonMapper;
import com.lisenok.studentratingservice.problem.LessonNotFoundProblem;
import com.lisenok.studentratingservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;

    private final CourseService courseService;

    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    public Optional<Lesson> findWithGradesById(int id) {
        return lessonRepository.findWithGradesById(id);
    }

    public Lesson getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new LessonNotFoundProblem(id));
    }

    public Lesson getFullEntityById(int id) {
        return findWithGradesById(id)
                .orElseThrow(() -> new LessonNotFoundProblem(id));
    }

    public LessonResponseDTO getById(int id) {
        return lessonMapper.toDto(getFullEntityById(id));
    }

    public LessonResponseDTO save(Lesson lesson) {
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public LessonResponseDTO save(LessonRequestDTO lessonRequestDTO) {
        Course course = courseService.getEntityById(lessonRequestDTO.getCourseId());
        Lesson lesson = lessonMapper.toEntity(lessonRequestDTO);
        lesson.setCourse(course);
        return save(lesson);
    }

    public LessonResponseDTO update(LessonRequestDTO lessonRequestDTO, int id) {
        getEntityById(id);
        Lesson updatedLesson = lessonMapper.toEntity(lessonRequestDTO);
        updatedLesson.setId(id);
        return save(updatedLesson);
    }

    public List<Lesson> getAllByCourse(Course course) {
        return lessonRepository.getAllByCourse(course);
    }
}
