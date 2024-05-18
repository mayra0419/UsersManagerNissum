package com.mayra0419.usersmanager.service;


import com.mayra0419.usersmanager.Constants;
import com.mayra0419.usersmanager.dto.CreateUserRequest;
import com.mayra0419.usersmanager.dto.CreateUserResponse;
import com.mayra0419.usersmanager.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
class UserService {
    private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile(Constants.EMAIL_VALIDATION_REGEX);

    public CreateUserResponse createUser(CreateUserRequest request){
        checkEmail(request.getEmail());
        return null;
    }


    public static void checkEmail(String email) {
        Matcher matcher = EMAIL_VALIDATION_PATTERN.matcher(email);
        if(matcher.matches()){
            throw new ValidationException("Invalid email");
        }
    }
}
