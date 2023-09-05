package com.jwt.securityPractice.service;

import com.jwt.securityPractice.auth.AuthenticationRequest;
import com.jwt.securityPractice.auth.AuthenticationResponse;
import com.jwt.securityPractice.auth.RegisterRequest;
import com.jwt.securityPractice.repository.UserRepository;
import com.jwt.securityPractice.user.Role;
import com.jwt.securityPractice.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = User.builder()

                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        return null;
    }
}
