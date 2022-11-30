package com.lisenok.studentratingservice.service;

import com.lisenok.studentratingservice.domain.dto.RatingRequestDTO;
import com.lisenok.studentratingservice.domain.dto.RatingResponseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Lesson;
import com.lisenok.studentratingservice.domain.model.Rating;
import com.lisenok.studentratingservice.domain.model.Student;
import com.lisenok.studentratingservice.mapper.RatingMapper;
import com.lisenok.studentratingservice.problem.ZeroDivisionProblem;
import com.lisenok.studentratingservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final CourseService courseService;

    private final StudentService studentService;

    private final GradeService gradeService;

    private final RatingMapper ratingMapper;

    private final RatingRepository ratingRepository;

    public Rating getRatingFromRequest(RatingRequestDTO ratingRequestDTO) {
        Course course = courseService.getFullEntityById(ratingRequestDTO.getCourseId());
        Student student = studentService.getEntityById(ratingRequestDTO.getStudentId());
        Rating rating = new Rating();
        rating.setStudent(student);
        rating.setCourse(course);
        return rating;
    }

    public RatingResponseDTO countRating(RatingRequestDTO ratingRequestDTO) {

        Rating rating = getRatingFromRequest(ratingRequestDTO);

        int sumRating = 0;
        int sumMaxGrades = 0;
        double resultRating;
        boolean isCredited = true;
        int currentScore;
        int currentMaxScore;

        // получаем список занятий курса, котрые были раньше текущего времени
        Set<Lesson> courseLessons = rating.getCourse().getLessons().stream()
                .filter(a -> a.getDate().isBefore(LocalDateTime.now())).collect(Collectors.toSet());

        // дял каждого занятия ссумируем оценки студента и проверяем набрано ли не менее 70% от максимального балла
        for (Lesson lesson : courseLessons){
            currentScore = gradeService.getStudentScore(rating.getStudent(), lesson);
            currentMaxScore = lesson.getMaxGrade();
            sumRating += currentScore;
            sumMaxGrades += currentMaxScore;
            if (0.7 * currentMaxScore > currentScore) isCredited = false;
        }
        if (sumMaxGrades == 0) throw new ZeroDivisionProblem();
        else {
            resultRating = (double) sumRating / sumMaxGrades;
            // округление до 3 знаков после запятой
            BigDecimal result = BigDecimal.valueOf(resultRating);
            result = result.setScale(3, RoundingMode.DOWN);
            rating.setRatingScore(result.doubleValue());
        }

        rating.setCredited(isCredited && resultRating >= 0.7);

        return ratingMapper.toDto(ratingRepository.save(rating));

    }
}
