package com.mayra0419.usersmanager.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {

    @NotEmpty(message = "Name required")
    private String name;

    @NotEmpty(message = "Email required")
    private String email;

    private String password;

    private List<PhoneDTO> phones;
}
