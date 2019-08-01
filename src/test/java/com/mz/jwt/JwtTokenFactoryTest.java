package com.mz.jwt;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JwtTokenFactoryTest {
    @Test
    public void generateToken() throws Exception {
        JwtProperties jwtProperties = new JwtProperties(
                "0pen-n4ven7-j085",
                60,
                "on-jobs"
        );

        JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(jwtProperties);
        String token = jwtTokenFactory.generateToken("TOCO");

        System.out.println(token);
        assertNotNull(token);
    }

}