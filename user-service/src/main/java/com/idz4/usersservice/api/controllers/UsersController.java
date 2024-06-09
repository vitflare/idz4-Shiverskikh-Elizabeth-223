package com.idz4.usersservice.api.controllers;

import com.idz4.usersservice.api.requests.RegisterRequest;
import com.idz4.usersservice.core.entities.Sessions;
import com.idz4.usersservice.core.entities.Users;
import com.idz4.usersservice.core.entities.Validator;
import com.idz4.usersservice.api.requests.AuthorizeRequest;
import com.idz4.usersservice.core.interfaces.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        if (isBlank(request.getEmail()) || isBlank(request.getPassword()) || isBlank(request.getNickname())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("All fields must be filled");
        }
        if (usersService.checkIfUserExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User with this email already exists");
        }
        if (!Validator.isValidInformation(request.getEmail(), request.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The email must contain the characters “@”, “.”.\n" +
                    "The password must be at least 8 characters long and include both uppercase and lowercase letters, " +
                    "numbers, and at least one special character (@, $, !, %, *, ?, &).");
        }
        usersService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorizeUser(@RequestBody AuthorizeRequest request) {
        if (isBlank(request.getEmail()) || isBlank(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("All fields must be filled");
        }
        if (!usersService.checkIfUserExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with such email not found");
        }
        Users user = usersService.getUserByEmail(request.getEmail()).get();
        if (!Integer.toString(request.getPassword().hashCode()).equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Wrong password");
        }
        var response = usersService.createSession(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Users> me() {
        try {
            String token = (String) request.getAttribute("token");
            Sessions session = usersService.getSessionByToken(token).orElseThrow(() -> new IllegalArgumentException("No user with such token"));
            Date now = new Date();
            if (now.after(session.getExpires())) {
                throw new IllegalArgumentException("This token is expired");
            }
            Users user = usersService.getUserById(session.getUser_id()).orElseThrow(() -> new IllegalArgumentException("No user with such id"));
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}
