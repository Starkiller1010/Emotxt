package com.revature.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.revature.dtos.Principal;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
    
    public static String createJwt(Principal subject) {
        SignatureAlgorithm sigAlg = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setId(Integer.toString(subject.getId()))
                .setSubject(subject.getUsername())
                .setIssuer("revature")
                .claim("role", subject.getRole().toString())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + JwtConfig.EXPIRATION))
                .signWith(sigAlg, JwtConfig.SECRET.getBytes());

        return builder.compact();
    }

}
