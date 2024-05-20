package com.mayra0419.usersmanager.service;

import com.mayra0419.usersmanager.dto.CreateUserRequest;
import com.mayra0419.usersmanager.dto.CreateUserResponse;
import com.mayra0419.usersmanager.dto.UserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(String userId);
}
