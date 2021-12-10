package com.example.bootweb.service;

import com.example.bootweb.dto.RegistrationRequestDto;
import com.example.bootweb.dto.RegistrationResponseDto;
import com.example.bootweb.support.security.XTokenService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, XTokenService {
    RegistrationResponseDto register(RegistrationRequestDto requestDto);
}
