package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.domain.dto.StudentRequestDTO;
import com.lisenok.studentratingservice.domain.dto.StudentResponseDTO;
import com.lisenok.studentratingservice.domain.model.Student;
import com.lisenok.studentratingservice.mapper.RatingMapper;
import com.lisenok.studentratingservice.mapper.StudentMapper;
import com.lisenok.studentratingservice.problem.StudentNotFoundProblem;
import com.lisenok.studentratingservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final RatingMapper ratingMapper;

    /**
     * Метод получения модели студента без связанных сущностей
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    /**
     * Метод получения модели студента со связанными курсами, рейтингами и оценками
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    public Optional<Student> findFullById(int id) {
        return studentRepository.findFullById(id);
    }

    /**
     * Метод получения модели студента со связанными рейтингами
     *
     * @param id уникальный идентификатор студента
     * @return модель студента или null, если такой студент не найден
     */
    public Optional<Student> findWithRatingsById(int id) {
        return studentRepository.findWithRatingsById(id);
    }

    // далее идут методы, которые, вызывая описанные выше методы, либо возвращают студента,
    // либо создают исключение если студент не найден

    public Student getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public Student getFullEntityById(int id) {
        return findFullById(id)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public Student getEntityWithRatingsById(int id) {
        return findWithRatingsById(id)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public StudentResponseDTO getFullById(int id) {
        return studentMapper.toDto(getFullEntityById(id));
    }

    public StudentResponseDTO save(Student student) {
        return studentMapper.toDto(studentRepository.save(student));
    }

    public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentRequestDTO)));
    }

    public StudentResponseDTO update(StudentRequestDTO studentRequestDTO, int id) {
        getEntityById(id);
        Student updatedStudent = studentMapper.toEntity(studentRequestDTO);
        updatedStudent.setId(id);
        return save(updatedStudent);
    }

    /**
     * Метод получения всех рейтингов студента
     *
     * @param id уникальный идентификатор студента
     * @return список DTO рейтингов (ответ)
     */
    public Set<RatingResponseDTO> getRatings(int id) {
        Student student = getEntityWithRatingsById(id);
        return student.getRatings().stream().map(ratingMapper::toDto).collect(Collectors.toSet());
    }

}
