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

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }
    public Optional<Student> findFullById(int id) {
        return studentRepository.findFullById(id);
    }

    public Optional<Student> findWithRatingsById(int id) {
        return studentRepository.findWithRatingsById(id);
    }

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
    public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentRequestDTO)));
    }

    public StudentResponseDTO update(StudentRequestDTO studentRequestDTO, int id) {
        findById(id).map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
        return save(studentRequestDTO);
    }

    public Set<RatingResponseDTO> getRatings(int id) {
        Student student = getEntityWithRatingsById(id);
        return student.getRatings().stream().map(ratingMapper::toDto).collect(Collectors.toSet());
    }

}
