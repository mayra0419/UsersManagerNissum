package com.mayra0419.usersmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreateUserResponse {

    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String token;
    @JsonProperty("isActive")
    private boolean active;
}
