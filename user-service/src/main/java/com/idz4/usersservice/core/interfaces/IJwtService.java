package com.idz4.usersservice.core.interfaces;

import com.idz4.usersservice.core.entities.Users;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    Claims extractAllClaims(String token);
    boolean isValid(String token, UserDetails user);
    String generateToken(Users user);
}
