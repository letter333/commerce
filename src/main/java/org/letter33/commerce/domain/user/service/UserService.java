package org.letter33.commerce.domain.user.service;

import org.letter33.commerce.domain.user.dto.request.UserRequest;
import org.letter33.commerce.domain.user.dto.response.LoginResponse;
import org.letter33.commerce.domain.user.dto.response.SignUpResponse;
import org.letter33.commerce.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    SignUpResponse signUp(UserRequest.SignUp request);
    LoginResponse login(UserRequest.Login request);
    User findByEmail(String email);
}

