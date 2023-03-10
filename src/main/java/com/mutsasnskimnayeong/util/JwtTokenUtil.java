package com.mutsasnskimnayeong.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    public static Claims openToken(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody();
    }

    public static boolean isExpired(String token, String key) {
        return openToken(token, key)
                .getExpiration()
                .before(new Date());
    }

    public static String getUserName(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody()
                .get("userName",String.class);
    }


    public static String createToken(String userName, String key, Long expiredUntilMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredUntilMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
