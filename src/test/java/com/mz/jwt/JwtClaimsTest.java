package com.mz.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Test;

import java.util.Date;


public class JwtClaimsTest {
    @Test
    public void testCustomClaims() {
        /*
        * jwt.expiration = 604800
            jwt.header = Authorization
            jwt.secret = 0pen-n4ven7-j085
            jwt.issuer = on-jobs
        * */

        String token = JWT.create()
                .withIssuer("on-jobs")
                .withIssuedAt(new Date())
                .withClaim("INT", "tocco")
                .withClaim("ROLE", "writer")
                .sign(Algorithm.HMAC256("0pen-n4ven7-j085"));

        System.out.println(token);

    }
}
