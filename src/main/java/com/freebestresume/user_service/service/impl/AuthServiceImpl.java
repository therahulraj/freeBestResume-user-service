package com.freebestresume.user_service.service.impl;

import com.freebestresume.user_service.dto.JwtAuthResponse;
import com.freebestresume.user_service.dto.UserDto;
import com.freebestresume.user_service.entity.Role;
import com.freebestresume.user_service.entity.User;
import com.freebestresume.user_service.repository.RoleRepository;
import com.freebestresume.user_service.repository.UserRepository;
import com.freebestresume.user_service.service.AuthService;
import com.freebestresume.user_service.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

    private JwtTokenUtils jwtTokenUtils;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           AuthenticationManager authenticationManager,
                           JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public JwtAuthResponse login(UserDto userDto) {
        Authentication authentication =  authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmailId(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.jwtTokenUtils.generateToken(authentication);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(token);
        return jwtAuthResponse;
    }

    @Override
    public UserDto register(UserDto userDto) {
        Optional<User> optionalUser = this.userRepository.findByEmailId(userDto.getEmailId());
        if(optionalUser.isPresent()) {
            throw new RuntimeException("User already exits.");
        }
        Role role = this.roleRepository.findByName("ROLE_USER").get();
        User user = new User(userDto.getEmailId(), passwordEncoder.encode(userDto.getPassword()), Set.of(role));
        userRepository.save(user);
        return userDto;
    }
}
