package com.mayra0419.usersmanager.service;

import com.mayra0419.usersmanager.model.User;

public interface EncryptionService {

    String cipherPassword(String rawPassword);
    String generateJWT(User user);
}
