package com.lisenok.studentratingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisenok.studentratingservice.mapper.CourseMapper;
import com.lisenok.studentratingservice.mapper.StudentMapper;
import com.lisenok.studentratingservice.repository.CourseRepository;
import com.lisenok.studentratingservice.repository.StudentRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractIntegrationTest {

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

    @Autowired
    protected ApplicationContext applicationContext;

    @Container
    public static PostgreSQLContainer<?> postgresSQLContainer = new PostgreSQLContainer<>("postgres:14.1");

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgresSQLContainer::getPassword);
    }
}
