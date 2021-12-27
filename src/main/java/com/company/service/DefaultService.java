package com.company.service;

public class DefaultService implements AuthService {

    @Override
    public boolean isAuthorized(String clientId, String passKey) {
        return false;
    }
}
