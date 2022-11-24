package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.StudentDTO;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(studentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok().body(studentService.save(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO studentDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(studentService.update(studentDTO, id));
    }
}
