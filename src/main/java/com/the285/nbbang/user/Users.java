package com.the285.nbbang.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class Users {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    // Using BCryptPasswordEncoder here does not mean your passwords are safe,
    // as decompilation of the class will reveal those password strings.
    // But since this is just for testing, that is not a problem here.

    public static final String ADMIN_EMAIL = "1234@gmail.com";
    public static final String ADMIN_PASSWORD = "admin";

    public static final String GENERAL_EMAIL = "5678@gmail.com";
    public static final String GENERAL_PASSWORD = "general";

    private static User ADMIN = User.createAdmin(newRandomId(), ADMIN_EMAIL, PASSWORD_ENCODER.encode(ADMIN_PASSWORD));
    private static User GENERAL = User.createAdmin(newRandomId(), GENERAL_EMAIL, PASSWORD_ENCODER.encode(GENERAL_PASSWORD));

    public static UserId newRandomId(){
        return new UserId(UUID.randomUUID());
    }

    public static User newRandomAdmin(){
        return newRandomAdmin(newRandomId());
    }

    public static User newRandomAdmin(UserId userId){
        String uniqueId = userId.asString().substring(0,5);
        return User.createAdmin(userId, "admin-" + uniqueId + "@google.com", PASSWORD_ENCODER.encode("admin"));
    }

    public static User admin(){
        return ADMIN;
    }

    public static User general(){
        return GENERAL;
    }

    public static User newGeneral(String email, String password){
        return User.createGeneral(newRandomId(), email, PASSWORD_ENCODER.encode(password));
    }

    private Users(){

    }
}
