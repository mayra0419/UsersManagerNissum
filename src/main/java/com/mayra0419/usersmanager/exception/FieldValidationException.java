package com.mayra0419.usersmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldValidationException extends RuntimeException{

    private String message;
}
