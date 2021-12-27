package com.company.service;

public class SimpleAuthService implements AuthService{
    @Override
    public boolean isAuthorized(String clientId, String passKey) {
        return clientId != null && passKey != null;
    }
}
