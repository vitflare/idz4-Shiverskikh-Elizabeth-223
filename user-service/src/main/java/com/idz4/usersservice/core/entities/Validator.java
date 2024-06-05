package com.idz4.usersservice.core.entities;

import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static boolean isValidInformation(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (!isValidEmail(email)) {
            return false;
        }
        if (!isValidPassword(password)) {
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
