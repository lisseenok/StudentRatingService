package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.domain.dto.StudentRequestDTO;
import com.lisenok.studentratingservice.domain.dto.StudentResponseDTO;
import com.lisenok.studentratingservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(studentService.getFullById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> save(@RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok().body(studentService.save(studentRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(studentService.update(studentRequestDTO, id));
    }

    /**
     * Метод получения рейтингов по всем курсам студента
     *
     * @param id - уникальный идентификатор студента
     * @return Set<RatingResponseDTO>
     */
    @GetMapping("/{id}/ratings")
    public ResponseEntity<Set<RatingResponseDTO>> getRatings(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(studentService.getRatings(id));
    }
}
