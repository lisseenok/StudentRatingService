package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.LessonDTO;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.mapper.LessonMapper;
import com.lisenok.studentratingservice.problem.LessonNotFoundProblem;
import com.lisenok.studentratingservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;


    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    public LessonDTO getById(int id) {
        return findById(id)
                .map(lessonMapper::toDto)
                .orElseThrow(() -> new LessonNotFoundProblem(id));
    }

    public Lesson getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new LessonNotFoundProblem(id));
    }

    public LessonDTO save(Lesson lesson) {
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public LessonDTO save(LessonDTO lessonDTO) {
        return save(lessonMapper.toEntity(lessonDTO));
    }

    public LessonDTO update(LessonDTO lessonDTO, int id) {
        findById(id).map(lessonMapper::toDto)
                .orElseThrow(() -> new LessonNotFoundProblem(id));
        return save(lessonDTO);
    }
}
