package com.mz.jwt;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JwtTokenValidatorTest {
    private static JwtProperties jwtProperties;

    @BeforeClass
    public static void setUp() {
        jwtProperties = new JwtProperties(
                "0pen-n4ven7-j085",
                1000,
                "on-jobs");
    }

    @Test
    public void validateTokenFailsBecauseTokenExpired() throws Exception {
        String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUT0NDTyIsImlzcyI6Im9uLWpvYnMiLCJleHAiOjE1MzcyODU1ODUsImlhdCI6MTUzNzI4NTU4NH0.6C_tzIU6FgAFKlzDIsLJf6dRUsPrstjIc4tOqSg6bGk";

        JwtTokenValidator jwtTokenValidator = new JwtTokenValidator(jwtProperties);
        assertFalse(jwtTokenValidator.validateToken(expiredToken));
    }

    @Test
    public void validateTokenFailsBecauseTokenWasAltered() {
        JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(jwtProperties);
        String token = jwtTokenFactory.generateToken("My-subject");
        String alteredToken = token.replace('e', 'E');
        JwtTokenValidator jwtTokenValidator = new JwtTokenValidator(jwtProperties);
        assertFalse(jwtTokenValidator.validateToken(alteredToken));
    }

    @Test
    public void validateTokenOk() {
        JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(jwtProperties);
        String token = jwtTokenFactory.generateToken("My-subject");
        JwtTokenValidator jwtTokenValidator = new JwtTokenValidator(jwtProperties);
        assertTrue(jwtTokenValidator.validateToken(token));
    }
}