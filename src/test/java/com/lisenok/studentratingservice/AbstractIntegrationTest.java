package com.lisenok.studentratingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisenok.studentratingservice.mapper.CourseMapper;
import com.lisenok.studentratingservice.mapper.StudentMapper;
import com.lisenok.studentratingservice.repository.CourseRepository;
import com.lisenok.studentratingservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;

import java.util.stream.Stream;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest
public class AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected StudentMapper studentMapper;

    @Autowired
    protected CourseMapper courseMapper;

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    protected StudentRepository studentRepository;

    @Container
    public static PostgreSQLContainer<?> postgresSQLContainer = new PostgreSQLContainer<>("postgres:14.1");


    static {
        Startables.deepStart(Stream.of(postgresSQLContainer)).join();
        System.setProperty("spring.datasource.url", postgresSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresSQLContainer.getPassword());

    }
}
