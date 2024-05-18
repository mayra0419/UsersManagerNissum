package com.mayra0419.usersmanager.service;


import com.mayra0419.usersmanager.Constants;
import com.mayra0419.usersmanager.dto.CreateUserRequest;
import com.mayra0419.usersmanager.dto.CreateUserResponse;
import com.mayra0419.usersmanager.exception.FieldValidationException;
import com.mayra0419.usersmanager.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile(Constants.EMAIL_VALIDATION_REGEX);
    private final Pattern PASSWORD_VALIDATION_PATTERN;

    public UserService(@Value("${validations.password.regex}") String passwordValidationRegex) {
        PASSWORD_VALIDATION_PATTERN = Pattern.compile(passwordValidationRegex);
    }

    public CreateUserResponse createUser(CreateUserRequest request){
        //Fields validation
        checkEmail(request.getEmail());
        checkPassword(request.getPassword());


        return null;
    }

    public void checkEmail(String email) {
        if(email == null){
            throw new FieldValidationException("Invalid email");
        }
        Matcher matcher = EMAIL_VALIDATION_PATTERN.matcher(email);
        if(!matcher.matches()){
            throw new FieldValidationException("Invalid email");
        }
    }

    public void checkPassword(String password) {
        if(password == null){
            throw new FieldValidationException("Invalid password");
        }
        Matcher matcher = PASSWORD_VALIDATION_PATTERN.matcher(password);
        if(!matcher.matches()){
            throw new FieldValidationException("Invalid password");
        }
    }

    private User buildUser(CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return user;
    }
}
