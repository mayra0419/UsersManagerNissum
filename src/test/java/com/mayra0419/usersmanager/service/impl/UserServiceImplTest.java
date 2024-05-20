package com.mayra0419.usersmanager.service.impl;

import com.mayra0419.usersmanager.dto.CreateUserRequest;
import com.mayra0419.usersmanager.dto.PhoneDTO;
import com.mayra0419.usersmanager.exception.FieldValidationException;
import com.mayra0419.usersmanager.repository.UserRepository;
import com.mayra0419.usersmanager.service.EncryptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class UserServiceImplTest {

    private final EncryptionService MOCK_ENCRYPTION_SERVICE = mock(EncryptionService.class);
    private final UserRepository MOCK_USER_REPOSITORY = mock(UserRepository.class);
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        final String PASSWORD_REGEX_EXAMPLE = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$@#%&!]).{8,}$";
        userService = new UserServiceImpl(MOCK_ENCRYPTION_SERVICE, PASSWORD_REGEX_EXAMPLE, MOCK_USER_REPOSITORY);
    }

    @Test
    void test_GivenNullOrEmptyEmail_WhenCallCreateUser_ThenShouldThrowException() {
        //Null email
        CreateUserRequest request = buildValidRequest();
        request.setEmail(null);

        FieldValidationException validationException =
                assertThrows(FieldValidationException.class, () -> userService.createUser(request));
        assertEquals("Invalid email", validationException.getMessage());

        //Empty email
        request.setEmail(" ");

        validationException = assertThrows(FieldValidationException.class, () -> userService.createUser(request));
        assertEquals("Invalid email", validationException.getMessage());
    }

    @Test
    void test_GivenNullPassword_WhenCallCreateUser_ThenShouldThrowException() {
        CreateUserRequest request = buildValidRequest();
        request.setPassword(null);

        FieldValidationException validationException =
                assertThrows(FieldValidationException.class, () -> userService.createUser(request));
        assertEquals("Invalid password", validationException.getMessage());
    }

    @Test
    void test_GivenPasswordWithInvalidFormat_WhenCallCreateUser_ThenShouldThrowException() {
        CreateUserRequest request = buildValidRequest();
        request.setPassword("password");

        FieldValidationException validationException =
                assertThrows(FieldValidationException.class, () -> userService.createUser(request));
        assertEquals("Invalid password", validationException.getMessage());
    }

    @Test
    void test_GivenNullOrEmptyUserId_WhenCallGetUser_ThenShouldThrowException() {
        //Null userId
        FieldValidationException validationException =
                assertThrows(FieldValidationException.class, () -> userService.getUserById(null));
        assertEquals("User id required", validationException.getMessage());

        //Empty userId
        validationException = assertThrows(FieldValidationException.class, () -> userService.getUserById(" "));
        assertEquals("User id required", validationException.getMessage());
    }

    private static CreateUserRequest buildValidRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("pepe");
        request.setEmail("pepe@test.com");
        request.setPassword("Pepe1035#");

        PhoneDTO phoneDTO = new PhoneDTO("12983476", "34", "57");
        request.setPhones(List.of(phoneDTO));
        return request;
    }
}