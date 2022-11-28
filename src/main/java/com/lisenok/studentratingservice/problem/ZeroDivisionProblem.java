package com.lisenok.studentratingservice.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ZeroDivisionProblem extends AbstractThrowableProblem {
    private static final URI TYPE
            = URI.create("https://localhost:8080/division_by_zero");

    public ZeroDivisionProblem() {
        super(
                TYPE,
                "Division by zero",
                Status.BAD_REQUEST
        );
    }
}
