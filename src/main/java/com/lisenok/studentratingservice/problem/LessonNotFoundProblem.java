package com.lisenok.studentratingservice.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class LessonNotFoundProblem extends AbstractThrowableProblem {

    private static final URI TYPE
            = URI.create("https://localhost:8080/not-found");

    public LessonNotFoundProblem(int id) {
        super(
                TYPE,
                "Not found",
                Status.NOT_FOUND,
                String.format("Lesson '%s' not found", id));
    }
}
