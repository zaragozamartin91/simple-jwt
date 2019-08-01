package com.mz.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JwtTokenValidator {
    private final JWTVerifier verifier;

    @Inject public JwtTokenValidator(JwtProperties jwtProperties) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.secret);
        verifier = JWT.require(algorithm)
                .withIssuer(jwtProperties.issuer)
                .build(); //Reusable verifier instance
    }

    /**
     * Valida la estructura de un token y su validez en el tiempo.
     *
     * @param token Token a validar.
     * @return True si el token es valido (no fue alterado y sigue vigente) , false en caso contrario.
     */
    public boolean validateToken(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
