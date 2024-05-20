package com.mayra0419.usersmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserResponse {
    @Schema(description = "user identifier in uuid format", example = "8c163949-ab69-4ef8-bc79-d2553f235163")
    private String id;
    @Schema(description = "user creation date in ISO format", example = "2024-05-19T19:56:14.651093")
    private LocalDateTime created;
    @Schema(description = "user last modification date in ISO format", example = "2024-05-19T19:56:14.651093")
    private LocalDateTime modified;
    @Schema(description = "user token (jwt format)")
    private String token;
    @Schema(description = "boolean indicating user state", example = "true|false")
    @JsonProperty("isActive")
    private boolean active;
    @Schema(description = "user last login date in ISO format", example = "2024-05-19T19:56:14.651093")
    private LocalDateTime lastLogin;
    @Schema(description = "User phones list")
    private List<PhoneDTO> phones;
}
