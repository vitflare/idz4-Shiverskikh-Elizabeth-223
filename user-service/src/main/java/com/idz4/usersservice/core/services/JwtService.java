package com.idz4.usersservice.core.services;

import com.idz4.usersservice.core.entities.Users;
import com.idz4.usersservice.core.interfaces.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService implements IJwtService {
    private final String secretKey = "my0320character0ultra0secure0and0ultra0long0secret";

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValid(String token, UserDetails user) {
        Claims claims = extractAllClaims(token);
        return claims.get("nickname", String.class).equals(user.getUsername()) && !claims.getExpiration().before(new Date());
    }

    public String generateToken(Users user) {
        return Jwts.builder()
                .setClaims(Map.of("nickname", user.getNickname()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(signingKey())
                .compact();
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
