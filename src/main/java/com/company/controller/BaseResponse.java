package com.company.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
abstract class BaseResponse {
    Collection<Error> errors;
}
