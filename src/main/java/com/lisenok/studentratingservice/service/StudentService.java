package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.StudentDTO;
import com.lisenok.studentratingservice.domain.model.Student;
import com.lisenok.studentratingservice.mapper.StudentMapper;
import com.lisenok.studentratingservice.problem.StudentNotFoundProblem;
import com.lisenok.studentratingservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    public StudentDTO getById(int id) {
        return findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
    }

    public StudentDTO save(StudentDTO studentDTO) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(studentDTO)));
    }

    public StudentDTO update(StudentDTO studentDTO) {
        int id = studentDTO.getId();
        findById(id).map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundProblem(id));
        return save(studentDTO);
    }

}
