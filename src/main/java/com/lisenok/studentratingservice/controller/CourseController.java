package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.CourseRequestDTO;
import com.lisenok.studentratingservice.domain.dto.CourseResponseDTO;
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
    public ResponseEntity<CourseResponseDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(courseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> save(@RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok().body(courseService.save(courseRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(@RequestBody CourseRequestDTO courseRequestDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok().body(courseService.update(courseRequestDTO, id));
    }

    @PutMapping("/{course_id}/students/{student_id}")
    public ResponseEntity<CourseResponseDTO> addStudent(@PathVariable("course_id") int courseId, @PathVariable("student_id") int studentId) {
        return ResponseEntity.ok().body(courseService.addStudent(courseId, studentId));
    }

}
