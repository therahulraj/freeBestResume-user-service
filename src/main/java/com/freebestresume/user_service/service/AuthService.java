package com.freebestresume.user_service.service;

import com.freebestresume.user_service.dto.JwtAuthResponse;
import com.freebestresume.user_service.dto.UserDto;

public interface AuthService {

    JwtAuthResponse login(UserDto userDto);
    UserDto register(UserDto userDto);

}
