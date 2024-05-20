package com.mayra0419.usersmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @Schema(example = "12983476")
    private String number;
    @Schema(description = "city code identifier according to each country", example = "34")
    private String citycode;
    @Schema(description = "country code identifier without + sign", example = "57")
    private String countrycode;
}
