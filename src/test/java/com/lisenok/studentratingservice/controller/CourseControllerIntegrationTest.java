package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.AbstractIntegrationTest;
import com.lisenok.studentratingservice.TestFactory;
import com.lisenok.studentratingservice.domain.model.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@Transactional
@DisplayName("CourseController Integration tests")
class CourseControllerIntegrationTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("Getting course by id test")
    void getByMid() throws Exception {

        Course course = TestFactory.getCourse();

        courseRepository.save(course);

        String expectedContent = objectMapper.writeValueAsString(courseMapper.toDto(course));


        String actualContent = mockMvc.perform(get("/courses/" + course.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);


    }
}
