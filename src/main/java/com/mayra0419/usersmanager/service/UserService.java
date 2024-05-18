package com.mayra0419.usersmanager.service;


import com.mayra0419.usersmanager.Constants;
import com.mayra0419.usersmanager.dto.CreateUserRequest;
import com.mayra0419.usersmanager.dto.CreateUserResponse;
import com.mayra0419.usersmanager.dto.PhoneDTO;
import com.mayra0419.usersmanager.dto.mapper.UserMapper;
import com.mayra0419.usersmanager.exception.FieldValidationException;
import com.mayra0419.usersmanager.model.Phone;
import com.mayra0419.usersmanager.model.User;
import com.mayra0419.usersmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile(Constants.EMAIL_VALIDATION_REGEX);
    private final EncryptionService encryptionService;
    private final Pattern PASSWORD_VALIDATION_PATTERN;

    private final UserRepository userRepository;

    public UserService(EncryptionService encryptionService,
                       @Value("${validations.password.regex}") String passwordValidationRegex,
                       UserRepository userRepository) {
        this.encryptionService = encryptionService;
        this.userRepository = userRepository;
        PASSWORD_VALIDATION_PATTERN = Pattern.compile(passwordValidationRegex);
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        //Fields validation
        checkEmail(request.getEmail());
        checkPassword(request.getPassword());

        User newUser = buildUser(request);
        newUser = userRepository.save(newUser);

        return UserMapper.mapToCreateUserResponse(newUser);
    }

    public void checkEmail(String email) {
        if (email == null) {
            throw new FieldValidationException("Invalid email");
        }
        Matcher matcher = EMAIL_VALIDATION_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new FieldValidationException("Invalid email");
        }
    }

    public void checkPassword(String password) {
        if (password == null) {
            throw new FieldValidationException("Invalid password");
        }
        Matcher matcher = PASSWORD_VALIDATION_PATTERN.matcher(password);
        if (!matcher.matches()) {
            throw new FieldValidationException("Invalid password");
        }
    }

    private User buildUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encryptionService.cipherPassword(request.getPassword()));
        user.setPhones(buildPhones(request.getPhones()));
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);

        String token = encryptionService.generateJWT(user);
        user.setToken(token);

        return user;
    }

    private List<Phone> buildPhones(List<PhoneDTO> phonesDto) {
        List<Phone> phones = new ArrayList<>();
        if (phonesDto != null && !phonesDto.isEmpty()) {
            phones.addAll(
                    phonesDto.stream()
                            .map((PhoneDTO phoneDto) -> new Phone(phoneDto.getNumber(), phoneDto.getCitycode(), phoneDto.getCountrycode()))
                            .toList());

        }
        return phones;
    }
}
