package com.forohub.challenge.infra.seguridad;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forohub.challenge.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String secret;

    public String generarToken(User user) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("foro_hub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Error al generar el token", jwtCreationException);
         }
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("foro_hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTVerificationException jwtVerificationException){
            throw new RuntimeException("Token JWT no disponible");
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-06:00"));
    }
}
