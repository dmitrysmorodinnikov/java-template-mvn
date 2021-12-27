package com.company.controller;

import java.util.Collection;

public class ForbiddenResponse extends BaseResponse {
    public ForbiddenResponse(Collection<Error> errors) {
        super(errors);
    }
}
