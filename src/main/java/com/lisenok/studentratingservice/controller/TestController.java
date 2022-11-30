package com.lisenok.studentratingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер для проверки работы приложения
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello world";
    }
}
