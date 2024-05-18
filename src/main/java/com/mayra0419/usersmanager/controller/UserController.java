package com.mayra0419.usersmanager.controller;

import com.mayra0419.usersmanager.dto.CreateUserRequest;
import com.mayra0419.usersmanager.dto.CreateUserResponse;
import com.mayra0419.usersmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        //URI userLocation = URI.create(String.format("/user/%s", response.getId()));
        //return ResponseEntity.created(userLocation).body(response);
        return ResponseEntity.ok(response);
    }
}
