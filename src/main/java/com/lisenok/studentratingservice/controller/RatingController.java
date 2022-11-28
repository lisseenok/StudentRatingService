package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.domain.dto.RatingRequestDTO;
import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingResponseDTO> countRating(@RequestBody RatingRequestDTO ratingRequestDTO) {
        return ResponseEntity.ok().body(ratingService.countRating(ratingRequestDTO));
    }
}
