package com.mz.jwt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class JwtProperties {
    private static JwtProperties DEFAULT;

    public final String secret;
    public final long expiration;
    public final String issuer;
    public final String header;

    /**
     * Crea un conjunto de propiedades de JSON WEB TOKEN
     *
     * @param secret     Clave o secreto usado para firmar el token.
     * @param expiration Tiempo de expiracion en SEGUNDOS.
     * @param issuer     Emisor del token.
     */
    public JwtProperties(String secret, long expiration, String issuer) {
        this.secret = secret;
        this.expiration = expiration;
        this.issuer = issuer;
        header = "Authorization";
    }

    /**
     * Crea un conjunto de propiedades de JSON WEB TOKEN
     *
     * @param secret     Clave o secreto usado para firmar el token.
     * @param expiration Tiempo de expiracion en SEGUNDOS.
     * @param issuer     Emisor del token.
     * @param header     Encabezado del token en HTTP.
     */
    public JwtProperties(String secret, long expiration, String issuer, String header) {
        this.secret = secret;
        this.expiration = expiration;
        this.issuer = issuer;
        this.header = header;
    }

    /**
     * Obtiene las configuraciones de JWT establecidas en un archivo del classpath jwt.properties.
     *
     * @return Configuraciones JWT de classpath:/jwt.properties.
     * @throws IllegalStateException Si no se encontro el archivo jwt.properties en el classpath.
     */
    public synchronized static JwtProperties getDefault() {
        DEFAULT = Optional.ofNullable(DEFAULT).orElseGet(() -> {
            try {
                Properties props = new Properties();
                InputStream jwtPropsResource = JwtProperties.class.getResourceAsStream("/jwt.properties");
                props.load(jwtPropsResource);
                return new JwtProperties(
                        props.getProperty("jwt.secret"),
                        Long.valueOf(props.getProperty("jwt.expiration")),
                        props.getProperty("jwt.issuer"),
                        props.getProperty("jwt.header"));
            } catch (IOException e) {
                throw new IllegalStateException("Archivo jwt.properties no encontrado en el classpath");
            }
        });
        return DEFAULT;
    }
}
