package com.mz.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtTokenDecoder {
    private DecodedJWT jwt;

    /**
     * Crea un decodificador de tokens JWT.
     *
     * @param token Token a decodificar.
     * @throws JwtTokenDecoderException Si el token no puede ser decodificado.
     */
    public JwtTokenDecoder(String token) {
        try {
            jwt = JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw new JwtTokenDecoderException("Error al decodificar token " + token, e);
        }
    }

    public static JwtTokenDecoder fromToken(String token) {
        return new JwtTokenDecoder(token);
    }

    public String getIssuer() {
        return jwt.getIssuer();
    }

    public String getSubject() {
        return jwt.getSubject();
    }

    public Date getExpiresAt() {
        return jwt.getExpiresAt();
    }

    public Date getIssuedAt() {
        return jwt.getIssuedAt();
    }
}
