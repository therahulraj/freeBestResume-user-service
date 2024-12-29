package com.freebestresume.user_service.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtils {

    @Value("${app.jwt-secret}")
    private String jwtSecret;


    @Value("${app-jwt-expiration-period-milliseconds}")
    private long jwtExpirationPeriod;



    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationPeriod);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .signWith(key())
                .compact();
        return token;
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Key key() {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        System.out.print(key);
        return key;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException malformedJwtException) {
            throw new RuntimeException("Invalid JWT token");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new RuntimeException("Expired JWT token");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new UnsupportedJwtException("Expired JWT token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new UnsupportedJwtException("JWT claims string is null or empty");
        }
    }

}
