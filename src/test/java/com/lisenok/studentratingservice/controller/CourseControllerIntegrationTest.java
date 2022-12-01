package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.AbstractIntegrationTest;
import com.lisenok.studentratingservice.DbTestUtil;
import com.lisenok.studentratingservice.TestFactory;
import com.lisenok.studentratingservice.domain.dto.CourseRequestDTO;
import com.lisenok.studentratingservice.domain.dto.CourseResponseDTO;
import com.lisenok.studentratingservice.domain.model.Course;
import com.lisenok.studentratingservice.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("CourseController Integration tests")
@Transactional
class CourseControllerIntegrationTest extends AbstractIntegrationTest {

    /**
     * Метод выполняется перед каждым тестом для рестарта sequence в таблице
     * примерчание: сброс sequence можно сделать с помощью одной аннотации над данным классом:
     * "@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD), однако это существенно снижает
     * производительность, поскольку перед каждым тестом создается новый контекст. Поэтому более верным решением
     * является написание методов сбрасывающих sequence sql запросом
     * @throws SQLException
     */
    @BeforeEach
    public void setUp() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "course");
    }

    @Test
    @DisplayName("Getting course by id test")
    void getByMid() throws Exception {

        Course course = TestFactory.getCourse();
        courseRepository.save(course);

        CourseResponseDTO courseResponseDTO = courseMapper.toDto(course);
        String expectedContent = objectMapper.writeValueAsString(courseResponseDTO);


        String actualContent = mockMvc.perform(get("/courses/" + course.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);

    }

    @Test
    @DisplayName("Saving course test")
    void save() throws Exception {

        CourseRequestDTO courseRequestDTO = TestFactory.getCourseRequestDto();
        Course course = courseMapper.toEntity(courseRequestDTO);
        course.setId(1);
        String expectedContent = objectMapper.writeValueAsString(courseMapper.toDto(course));

        String actualContent = mockMvc.perform(post("/courses/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    @DisplayName("Updating course test")
    void update() throws Exception {

        Course course = TestFactory.getCourse();
        courseRepository.save(course);

        CourseRequestDTO courseRequestDTOUpdated = TestFactory.getCourseRequestDto();
        courseRequestDTOUpdated.setTitle("updated course");

        course.setTitle("updated course");

        String expectedContent = objectMapper.writeValueAsString(courseMapper.toDto(course));

        String actualContent = mockMvc.perform(put("/courses/" + course.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDTOUpdated)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);

    }

    @Test
    @DisplayName("Adding student to course test")
    void addStudent() throws Exception {
        Course course = TestFactory.getCourse();
        Student student = TestFactory.getStudent();

        courseRepository.save(course);
        studentRepository.save(student);

        course.addStudent(student);

        String expectedContent = objectMapper.writeValueAsString(courseMapper.toDto(course));

        String actualContent = mockMvc.perform(put("/courses/" + course.getId() + "/students/" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);
    }
}
