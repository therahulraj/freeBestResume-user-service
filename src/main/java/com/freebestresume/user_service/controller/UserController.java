package com.freebestresume.user_service.controller;

import com.freebestresume.user_service.dto.JwtAuthResponse;
import com.freebestresume.user_service.dto.UserDto;
import com.freebestresume.user_service.repository.RoleRepository;
import com.freebestresume.user_service.service.AuthService;
import com.freebestresume.user_service.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserDto userDto) {
        JwtAuthResponse jwtAuthResponse = authService.login(userDto);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
//        roleRepository.save(new Role(Long.getLong("1"), "ROLE_USER"));
//        roleRepository.save(new Role(Long.getLong("2"), "ROLE_ADMIN"));
//        jwtTokenUtils.key();
        UserDto userDto1 = authService.register(userDto);
        System.out.println("afd");
//        return ResponseEntity.ok(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
}
