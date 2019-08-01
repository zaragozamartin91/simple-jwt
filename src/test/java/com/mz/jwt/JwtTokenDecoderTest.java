package com.mz.jwt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JwtTokenDecoderTest {
    @Test
    public void getSubject() throws Exception {
        JwtProperties jwtProperties = new JwtProperties(
                "0pen-n4ven7-j085",
                60,
                "on-jobs"
        );

        JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(jwtProperties);
        String expectedSubject = "TOCO";
        String token = jwtTokenFactory.generateToken(expectedSubject);

        JwtTokenDecoder jwtTokenDecoder = JwtTokenDecoder.fromToken(token);
        String subject = jwtTokenDecoder.getSubject();

        assertEquals(expectedSubject, subject);
        assertEquals(jwtProperties.issuer, jwtTokenDecoder.getIssuer());
    }

    @Test(expected = JwtTokenDecoderException.class)
    public void failToGetSubjectOnInvalidToken() {
        JwtProperties jwtProperties = new JwtProperties(
                "0pen-n4ven7-j085",
                60,
                "on-jobs"
        );

        JwtTokenFactory jwtTokenFactory = new JwtTokenFactory(jwtProperties);
        String expectedSubject = "TOCO";
        String token = jwtTokenFactory.generateToken(expectedSubject);

        String invalidToken = token.substring(1);
        JwtTokenDecoder.fromToken(invalidToken);
    }
}