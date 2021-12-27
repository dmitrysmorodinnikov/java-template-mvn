package com.company.controller;

import com.company.exceptions.MyAppException;
import org.assertj.core.util.diff.myers.MyersDiff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyAppExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAppExceptionHandler.class);

    ResponseEntity handleMyAppException(MyAppException e) {
        LOGGER.error(e.getMessage(), e);
        List<Error> errors = Collections.singletonList(new Error(e.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(errors);
    }
}
