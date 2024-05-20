package com.mayra0419.usersmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldValidationException extends RuntimeException{

    private String message;
}
