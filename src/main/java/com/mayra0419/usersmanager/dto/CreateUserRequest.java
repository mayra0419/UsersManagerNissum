package com.mayra0419.usersmanager.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {

    @Schema(description = "user name", example = "Robert Martin")
    private String name;
    @Schema(description = "user email (must be unique)", example = "rmartin@java.com")
    private String email;
    @Schema(description = "password (see guidelines in the documentation)", example = "strongPass1#")
    private String password;
    private List<PhoneDTO> phones;
}
