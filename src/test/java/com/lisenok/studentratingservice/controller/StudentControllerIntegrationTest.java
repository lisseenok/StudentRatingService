package com.lisenok.studentratingservice.controller;

import com.lisenok.studentratingservice.AbstractIntegrationTest;
import com.lisenok.studentratingservice.DbTestUtil;
import com.lisenok.studentratingservice.TestFactory;
import com.lisenok.studentratingservice.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@DisplayName("Student Controller Integration tests")
class StudentControllerIntegrationTest extends AbstractIntegrationTest {

    @BeforeEach
    public void setUp() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "student");
    }

    @Test
    @DisplayName("Getting student by id test")
    void getByMid() throws Exception {

        Student student = TestFactory.getStudent();
        studentRepository.save(student);
        String expectedContent = objectMapper.writeValueAsString(studentMapper.toDto(student));


        String actualContent = mockMvc.perform(get("/students/" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);


    }

    @Test
    @DisplayName("Saving student test")
    void save() throws Exception {

        Student student = TestFactory.getStudent();
        String expectedContent = objectMapper.writeValueAsString(studentMapper.toDto(student));

        String actualContent = mockMvc.perform(post("/students/")
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
    @DisplayName("Updating student test")
    void update() throws Exception {

        Student student = TestFactory.getStudent();
        studentRepository.save(student);
        Student updatedStudent = TestFactory.getStudent();
        updatedStudent.setName("updated student");

        String expectedContent = objectMapper.writeValueAsString(studentMapper.toDto(updatedStudent));

        String actualContent = mockMvc.perform(put("/students/" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals(expectedContent, actualContent);

    }
}
