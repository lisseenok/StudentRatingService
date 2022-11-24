package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.CourseDTO;
import com.lisenok.studentratingservice.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(courseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok().body(courseService.save(courseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO courseDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(courseService.update(courseDTO, id));
    }
}
