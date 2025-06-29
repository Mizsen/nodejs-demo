package com.example.prescription.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 使用安全的密钥生成方式
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 24小时

    /**
     * 生成JWT Token
     */
    // public static String generateToken(String username) {
    //     return Jwts.builder()
    //             .setSubject(username)
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
    //             .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
    //             .compact();
    // }

    /**
     * 生成带角色的JWT Token
     */
    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从token获取用户名
     */
    public static String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 从token获取角色
     */
    public static String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Object role = claims.get("role");
        return role == null ? null : role.toString();
    }

    /**
     * 校验token有效性
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token过期时间（时间戳，毫秒）
     */
    public static long getExpireTime(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().getTime();
    }

    /**
     * 从HttpServletRequest获取token
     * Java 21建议由Controller层传递header字符串，不直接依赖servlet
     */
    public static String resolveToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}