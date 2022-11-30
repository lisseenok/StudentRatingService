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
import lombok.var;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final CourseService courseService;

    private final StudentService studentService;

    private final GradeService gradeService;

    private final LessonService lessonService;

    private final RatingMapper ratingMapper;

    private final RatingRepository ratingRepository;

    /**
     * Метод маппинга DTO рейтинга (запрос) в модель рейтинга
     * Написан в сервисе, а не в маппере, поскольку DTO содержит id курса и студента,
     * и необходима проверка существования этих объектов
     *
     * @param ratingRequestDTO DTO рейтинга (запрос)
     * @return модель рейтинга
     */
    public Rating getRatingFromRequest(RatingRequestDTO ratingRequestDTO) {
        Course course = courseService.getFullEntityById(ratingRequestDTO.getCourseId());
        Student student = studentService.getEntityById(ratingRequestDTO.getStudentId());
        Rating rating = new Rating();
        rating.setStudent(student);
        rating.setCourse(course);
        return rating;
    }

    /**
     * Метод подсчета рейтинга студента
     *
     * @param rating модель рейтинга
     * @return модель рейтинга (уже посчитанного)
     */
    public Rating countRating(Rating rating) {

        int sumRating = 0;
        int sumMaxGrades = 0;
        double resultRating;
        boolean isCredited = true;
        int currentScore;
        int currentMaxScore;

        // получаем список занятий курса, котрые были раньше текущего времени
        List<Lesson> courseLessons = lessonService.getAllByCourse(rating.getCourse()).stream()
                .filter(a -> a.getDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());

        // дял каждого занятия ссумируем оценки студента и проверяем набрано ли не менее 70% от максимального балла
        for (Lesson lesson : courseLessons) {
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

        return rating;
    }

    /**
     * Метод подсчета и сохранения рейтинга
     *
     * @param rating модель рейтинга
     */
    public void countAndSaveRating(Rating rating) {
        ratingRepository.save(countRating(rating));
    }

    /**
     * Метод подсчета рейтинга (для контроллера)
     *
     * @param ratingRequestDTO DTO рейтинга (запрос)
     * @return DTO рейтинга (ответ)
     */
    public RatingResponseDTO countRating(RatingRequestDTO ratingRequestDTO) {
        Rating rating = getRatingFromRequest(ratingRequestDTO);
        return ratingMapper.toDto(ratingRepository.save(countRating(rating)));
    }

    /**
     * Метод актуализации рейтинга (актуализирует записи в таблице рейтингов с частотой, указанной в fixedDelay)
     */
//    @Scheduled(fixedDelay = 2 * 3600000)
    @Scheduled(fixedDelay = 5000)
    public void actualizeRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        for (var rating : ratings) {
            countAndSaveRating(rating);
        }
    }
}
