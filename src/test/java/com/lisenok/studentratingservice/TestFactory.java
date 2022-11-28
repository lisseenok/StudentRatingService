package com.lisenok.studentratingservice;

import com.lisenok.studentratingservice.domain.dto.CourseDTO;
import com.lisenok.studentratingservice.domain.dto.StudentDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Student;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для статических методов создания объектов, используемых в тестах
 */
public class TestFactory {

    private static final LocalDateTime time = LocalDateTime.of(2022, 1, 1, 0, 0);

    public static Course getCourse() {
        return Course.builder()
                .id(1)
                .title("test course")
                .startDate(time)
                .endDate(time)
                .isActive(true)
                .build();
    }

    public static Course getCourseWithStudents() {
        Student student = getStudent();
        Student student2 = getStudent();
        student2.setName("testName 2");
        return Course.builder()
                .id(1)
                .title("test course")
                .startDate(time)
                .endDate(time)
                .isActive(true)
                .students(List.of(student, student2))
                .build();
    }

    public static CourseDTO getCourseDto() {
        return CourseDTO.builder()
                .id(1)
                .title("test course")
                .startDate(time)
                .endDate(time)
                .isActive(true)
                .build();
    }

    public static Student getStudent() {
        return Student.builder()
                .id(1)
                .name("testName")
                .lastName("testLastName")
                .patronymicName("testPatronymicName")
                .groupNumber("abcd1")
                .isActive(true)
                .build();
    }

    public static StudentDTO getStudentDto() {
        return StudentDTO.builder()
                .id(1)
                .name("testName")
                .lastName("testLastName")
                .patronymicName("testPatronymicName")
                .groupNumber("abcd1")
                .isActive(true)
                .build();
    }
}
