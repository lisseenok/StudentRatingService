package com.lisenok.studentratingservice.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CourseNotFoundProblem extends AbstractThrowableProblem {

    private static final URI TYPE
            = URI.create("https://localhost:8080/not-found");

    public CourseNotFoundProblem(int id) {
        super(
                TYPE,
                "Not found",
                Status.NOT_FOUND,
                String.format("Course '%s' not found", id));
    }
}
