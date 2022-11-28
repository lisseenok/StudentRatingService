package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.domain.dto.StudentDTO;
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

    public StudentDTO getById(int id) {
        return findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public Student getEntityById(int id) {
        return findById(id)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public StudentDTO save(StudentDTO studentDTO) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentDTO)));
    }

    public StudentDTO update(StudentDTO studentDTO, int id) {
        findById(id).map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
        return save(studentDTO);
    }

    public List<RatingResponseDTO> getRatings(int id) {
        Student student = getEntityById(id);
        return student.getRatings().stream().map(ratingMapper::toDto).collect(Collectors.toList());
    }

}
