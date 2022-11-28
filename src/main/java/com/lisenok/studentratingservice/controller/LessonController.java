package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.LessonDTO;
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
    public ResponseEntity<LessonDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(lessonService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LessonDTO> save(@RequestBody LessonDTO lessonDTO) {
        return ResponseEntity.ok().body(lessonService.save(lessonDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> update(@RequestBody LessonDTO lessonDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(lessonService.update(lessonDTO, id));
    }
}
