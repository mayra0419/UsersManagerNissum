package com.mayra0419.usersmanager.dto.mapper;

import com.mayra0419.usersmanager.model.User;
import com.mayra0419.usersmanager.swagger.model.UserPost200Response;

public class UserMapper {

    public static UserPost200Response mapToCreateUserResponse(User user){
        return new UserPost200Response()
                .id(user.getId().toString())
                .created(user.getCreated().toString())
                .modified(user.getModified().toString())
                .token(user.getToken())
                .isActive(Boolean.toString(user.isActive()));
    }
}
