package com.mayra0419.usersmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserResponse {
    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String token;
    @JsonProperty("isActive")
    private boolean active;
    private LocalDateTime lastLogin;
    private List<PhoneDTO> phones;
}
