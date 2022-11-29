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

import java.util.List;
import java.util.Optional;
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

    public StudentResponseDTO getById(int id) {
        return findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public Student getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentRequestDTO)));
    }

    public StudentResponseDTO update(StudentRequestDTO studentRequestDTO, int id) {
        findById(id).map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
        return save(studentRequestDTO);
    }

    public List<RatingResponseDTO> getRatings(int id) {
        Student student = getEntityById(id);
        return student.getRatings().stream().map(ratingMapper::toDto).collect(Collectors.toList());
    }

}
