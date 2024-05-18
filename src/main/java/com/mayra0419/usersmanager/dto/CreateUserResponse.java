package com.mayra0419.usersmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {

    private String id;

    private String created;

    private String modified;

    private String token;

    private String isactive;
}
