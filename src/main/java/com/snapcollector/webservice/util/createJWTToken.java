package com.snapcollector.webservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class createJWTToken {

    public String createJWTToken(String email, String gender, String age) {
        String tokenSecret = "snapcollector";
        String token = null;

        try {
            Long EXPIRATION_TIME = 1000L * 60L * 10L;
            Date issuedAt = new Date();
            Date notBefore = new Date(issuedAt.getTime());
            Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(email)
                    .withClaim("gender", gender)
                    .withClaim("age", age)
                    .withNotBefore(notBefore)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (Exception e) {
            System.err.println("err: " + e);
        }
        return token;
    }
}
