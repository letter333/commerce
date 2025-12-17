package org.letter33.commerce.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter33.commerce.common.enumType.ErrorCode;
import org.letter33.commerce.common.exception.CustomException;
import org.letter33.commerce.config.JwtUtil;
import org.letter33.commerce.domain.user.dto.request.UserRequest;
import org.letter33.commerce.domain.user.dto.response.LoginResponse;
import org.letter33.commerce.domain.user.dto.response.SignUpResponse;
import org.letter33.commerce.domain.user.entity.User;
import org.letter33.commerce.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public SignUpResponse signUp(UserRequest.SignUp request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User user = request.toEntity(passwordEncoder);
        User savedUser = userRepository.save(user);

        log.info("New user registered: {}", savedUser.getEmail());

        return SignUpResponse.from(savedUser);
    }

    @Override
    public LoginResponse login(UserRequest.Login request) {
        UserDetails userDetails = loadUserByUsername(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        User user = findByEmail(request.getEmail());

        String accessToken = jwtUtil.generateToken(user);
        Long expiresIn = jwtUtil.getExpirationTime();

        log.info("User logged in: {}", user.getEmail());

        return LoginResponse.of(accessToken, expiresIn, user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
