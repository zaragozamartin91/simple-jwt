package com.mz.jwt;

public class JwtTokenDecoderException extends RuntimeException {
    public JwtTokenDecoderException(String message, Throwable cause) {
        super(message, cause);
    }
}
