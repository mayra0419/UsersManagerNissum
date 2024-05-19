package com.mayra0419.usersmanager.controller;

import com.mayra0419.usersmanager.service.UserService;
import com.mayra0419.usersmanager.swagger.UserApi;
import com.mayra0419.usersmanager.swagger.model.UserPost200Response;
import com.mayra0419.usersmanager.swagger.model.UserPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<UserPost200Response> userPost(UserPostRequest userPostRequest) {
        UserPost200Response response = userService.createUser(userPostRequest);
        URI userLocation = URI.create(String.format("/user/%s", response.getId()));
        return ResponseEntity.created(userLocation).body(response);
    }
}
