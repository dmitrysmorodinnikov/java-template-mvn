package com.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthController checks the health of the application
 */
@RestController
public class HealthController {
    private static final String MESSAGE = "pong";

    @RequestMapping("ping")
    public String ping() {
        return MESSAGE;
    }
}
