package com.idz4.ordersservice.core.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    Claims extractAllClaims(String token);
    boolean isValid(String token, UserDetails user);
}
