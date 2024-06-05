package com.idz4.usersservice.core.interfaces;

import com.idz4.usersservice.api.requests.RegisterRequest;
import com.idz4.usersservice.api.responses.TokenResponse;
import com.idz4.usersservice.core.entities.Sessions;
import com.idz4.usersservice.core.entities.Users;

import java.util.Optional;

public interface IUsersService {
    void registerUser(RegisterRequest users);
    void addUserToSessions(Sessions session);
    TokenResponse createSession(Users user);
    Boolean checkIfUserExists(String email);
    Optional<Users> getUserByEmail(String email);
    Optional<Users> getUserByNickname(String nickname);
    Optional<Sessions> getSessionByToken(String token);
    Optional<Users> getUserById(Long id);
}
