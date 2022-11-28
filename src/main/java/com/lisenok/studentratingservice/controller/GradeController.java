package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.GradeRequestDTO;
import com.lisenok.studentratingservice.domain.dto.GradeResponseDTO;
import com.lisenok.studentratingservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeResponseDTO> save(@RequestBody GradeRequestDTO gradeRequestDTO) {
        return ResponseEntity.ok().body(gradeService.save(gradeRequestDTO));
    }
}
