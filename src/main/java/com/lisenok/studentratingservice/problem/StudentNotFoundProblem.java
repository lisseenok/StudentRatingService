package com.lisenok.studentratingservice.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

/**
 * Сообщение с подбробными сведениями об ошибке: студент не найден
 */
public class StudentNotFoundProblem extends AbstractThrowableProblem {
    private static final URI TYPE
            = URI.create("https://localhost:8080/not-found");

    public StudentNotFoundProblem(int id) {
        super(
                TYPE,
                "Not found",
                Status.NOT_FOUND,
                String.format("Student '%s' not found", id));
    }
}
