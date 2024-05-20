package com.mayra0419.usersmanager.dto.mapper;

import com.mayra0419.usersmanager.dto.PhoneDTO;
import com.mayra0419.usersmanager.model.Phone;

public class PhoneMapper {

    public static PhoneDTO mapToPhoneDto(Phone phone){
        return new PhoneDTO(phone.getNumber(), phone.getCitycode(), phone.getCitycode());
    }
}
