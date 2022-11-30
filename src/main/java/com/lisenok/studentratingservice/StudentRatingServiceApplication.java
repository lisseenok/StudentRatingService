package com.lisenok.studentratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentRatingServiceApplication.class, args);
    }

}
