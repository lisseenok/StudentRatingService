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

    /**
     * Метод получения модели занятия без связанных сущностей
     *
     * @param id - уникальный идентификатор занятия
     * @return модель занятия или null, если занятие не найдено
     */
    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    /**
     * Метод получения модели занятия со связанными оценками
     *
     * @param id - уникальный идентификатор занятия
     * @return модель занятия или null, если занятие не найдено
     */
    public Optional<Lesson> findWithGradesById(int id) {
        return lessonRepository.findWithGradesById(id);
    }

    // далее идут методы, которые, вызывая описанные выше методы, либо возвращают занятие (или DTO),
    // либо создают исключение если занятие не найдено
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

    /**
     * Метод получения всех моделей занятий курса со связанными оценками
     *
     * @param course - модель курса
     * @return модели занятия
     */
    public List<Lesson> getAllByCourse(Course course) {
        return lessonRepository.getAllByCourse(course);
    }
}
