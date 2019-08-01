package com.mz.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;

@Named
public class JwtTokenFactory {
    private final JwtProperties jwtProperties;

    @Inject
    public JwtTokenFactory(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.secret);
        return JWT.create()
                .withIssuer(jwtProperties.issuer)
                .withIssuedAt(new Date())
                .withSubject(subject)
                .withExpiresAt(getExpiration())
                .sign(algorithm);
    }

    private Date getExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, (int) jwtProperties.expiration);
        return calendar.getTime();
    }
}
