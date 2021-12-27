package com.company.controller;

import java.util.Collection;

class UnprocessableEntityResponse extends BaseResponse {
    public UnprocessableEntityResponse(Collection<Error> errors) {
        super(errors);
    }
}
