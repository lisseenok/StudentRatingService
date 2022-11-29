package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.LessonRequestDTO;
import com.lisenok.studentratingservice.domain.dto.LessonResponseDTO;
import com.lisenok.studentratingservice.service.LessonService;
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
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(lessonService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LessonResponseDTO> save(@RequestBody LessonRequestDTO lessonRequestDTO) {
        return ResponseEntity.ok().body(lessonService.save(lessonRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> update(@RequestBody LessonRequestDTO lessonRequestDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(lessonService.update(lessonRequestDTO, id));
    }
}
