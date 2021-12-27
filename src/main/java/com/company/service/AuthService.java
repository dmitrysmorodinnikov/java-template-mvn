package com.company.service;

public interface AuthService {
    boolean isAuthorized(String clientId, String passKey);
}
