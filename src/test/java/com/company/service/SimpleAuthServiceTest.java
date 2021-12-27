package com.company.service;

import com.company.service.SimpleAuthService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleAuthServiceTest {

    private SimpleAuthService authService;

    @Before
    public void init() {
        authService = new SimpleAuthService();
    }

    @Test
    public void when_client_id_and_passkey_not_null_should_return_true() {
        String clientId = "testClientId";
        String passKey = "testPassKey";

        boolean actualResult = authService.isAuthorized(clientId, passKey);
        assertTrue(actualResult);
    }

    @Test
    public void when_client_id_null_should_return_false() {
        boolean actualResult = authService.isAuthorized(null, "testPassKey");
        assertFalse(actualResult);
    }

    @Test
    public void when_passkey_null_should_return_false() {
        boolean actualResult = authService.isAuthorized("testClientID", null);
        assertFalse(actualResult);
    }
}
