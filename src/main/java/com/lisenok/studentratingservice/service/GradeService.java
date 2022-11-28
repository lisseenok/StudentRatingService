package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.GradeRequestDTO;
import com.lisenok.studentratingservice.domain.dto.GradeResponseDTO;
import com.lisenok.studentratingservice.domain.model.Grade;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.domain.model.Student;
import com.lisenok.studentratingservice.mapper.GradeMapper;
import com.lisenok.studentratingservice.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeMapper gradeMapper;

    private final GradeRepository gradeRepository;

    private final StudentService studentService;

    private final LessonService lessonService;

    public GradeResponseDTO save(GradeRequestDTO gradeRequestDTO) {
        Student student = studentService.getEntityById(gradeRequestDTO.getStudentId());
        Lesson lesson = lessonService.getEntityById(gradeRequestDTO.getLessonId());
        Grade grade = gradeMapper.toEntity(gradeRequestDTO);
        grade.setStudent(student);
        grade.setLesson(lesson);
        return gradeMapper.toDto(gradeRepository.save(grade));
    }

    public int getStudentScore(Student student, Lesson lesson) {
        return gradeRepository.getGradeByLessonAndStudent(lesson, student).getGradeScore();
    }
}
