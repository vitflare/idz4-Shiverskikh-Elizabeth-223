package com.idz4.usersservice.core.services;

import com.idz4.usersservice.api.requests.RegisterRequest;
import com.idz4.usersservice.core.entities.Sessions;
import com.idz4.usersservice.core.entities.Users;
import com.idz4.usersservice.core.interfaces.IJwtService;
import com.idz4.usersservice.core.interfaces.IUsersService;
import com.idz4.usersservice.repository.UsersRepository;
import com.idz4.usersservice.api.responses.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idz4.usersservice.repository.SessionRepository;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UsersService implements IUsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private IJwtService jwtService;

    public void registerUser(RegisterRequest request) {
        Users users = new Users();
        users.setEmail(request.getEmail());
        users.setNickname(request.getNickname());
        users.setPassword(Integer.toString(request.getPassword().hashCode()));
        usersRepository.save(users);
    }

    public TokenResponse createSession(Users user) {
        Sessions session = new Sessions();
        session.setUser_id(user.getId());
        session.setToken(jwtService.generateToken(user));
        Date now = new Date();
        session.setExpires(new Date(now.getTime() + Sessions.EXPIRATION_TIME));
        addUserToSessions(session);
        var response = new TokenResponse();
        response.token = session.getToken();
        return response;
    }

    public Boolean checkIfUserExists(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Optional<Users> getUserByNickname(String nickname) {
        return usersRepository.findByNickname(nickname);
    }

    public void addUserToSessions(Sessions session) {
        sessionRepository.save(session);
    }

    public Optional<Sessions> getSessionByToken(String token) {
        return sessionRepository.findByToken(token);
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }
}
