package com.chatop.rentalapp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    @Value("${security.jwtSecret")
    private String jwtSecret;

    @Value("${security.jwtExpiration}")
    private int jwtExpiration;

    public String generateTokenFromEmail(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plus(this.jwtExpiration, ChronoUnit.MILLIS)))
                .sign(Algorithm.HMAC256(this.jwtSecret));
    }

    public String getUserEmailFromJwtToken(String jwt) {
        if (jwt == null){
            throw new JWTVerificationException("token is null");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.jwtSecret)).build();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        return decodedJWT.getSubject();
    }

    public boolean validateJwtToken(String jwt) {
        if (jwt == null){
            return false;
        }

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.jwtSecret)).build();
            verifier.verify(jwt);
        }
        catch (JWTVerificationException e){
            return false;
        }

        return true;
    }
}
