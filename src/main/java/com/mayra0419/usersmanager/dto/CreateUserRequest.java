package com.mayra0419.usersmanager.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
