package com.mayra0419.usersmanager.dto.mapper;

import com.mayra0419.usersmanager.dto.CreateUserResponse;
import com.mayra0419.usersmanager.model.User;

public class UserMapper {

    public static CreateUserResponse mapToCreateUserResponse(User user){
        return CreateUserResponse.builder()
                .id(user.getId().toString())
                .created(user.getCreated())
                .modified(user.getModified())
                .token(user.getToken())
                .active(user.isActive())
                .build();
    }
}
