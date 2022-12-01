package com.lisenok.studentratingservice;

import com.lisenok.studentratingservice.domain.dto.CourseRequestDTO;
import com.lisenok.studentratingservice.domain.dto.CourseResponseDTO;
import com.lisenok.studentratingservice.domain.dto.StudentRequestDTO;
import com.lisenok.studentratingservice.domain.dto.StudentResponseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Rating;
import com.lisenok.studentratingservice.domain.model.Student;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для статических методов создания объектов, используемых в тестах
 */
public class TestFactory {

    private static final LocalDateTime localDateTime1 = LocalDateTime.of(2022, 1, 1, 0, 0);
    private static final LocalDateTime localDateTime2 = LocalDateTime.of(2021, 1, 1, 0, 0);

    public static CourseRequestDTO getCourseRequestDto() {
        return CourseRequestDTO.builder()
                .title("test course")
                .startDate(localDateTime2)
                .endDate(localDateTime1)
                .isActive(true)
                .build();
    }

    public static StudentRequestDTO getStudentRequestDTO() {
        return StudentRequestDTO.builder()
                .name("test name")
                .lastName("test last name")
                .patronymicName("test patronymic name")
                .groupNumber("abc1")
                .isActive(true)
                .build();
    }

    public static Course getCourse() {
        return Course.builder()
                .id(1)
                .title("test course")
                .startDate(localDateTime2)
                .endDate(localDateTime1)
                .isActive(true)
                .build();
    }

    public static Student getStudent() {
        return Student.builder()
                .id(1)
                .name("test name")
                .lastName("test last name")
                .patronymicName("test patronymic name")
                .groupNumber("abc1")
                .isActive(true)
                .build();
    }

    public static Rating getRating() {
        return Rating.builder()
                .ratingScore(100)
                .isCredited(true)
                .build();
    }
}
