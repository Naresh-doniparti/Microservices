package com.microservices.service.impl;

import com.microservices.exception.SecurityServiceException;
import com.microservices.model.User;
import com.microservices.repo.UserRepo;
import com.microservices.security.JwtTokenProvider;
import com.microservices.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class JwtLoginService implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepo userRepo;

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByEmail(username);
        if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
            throw new SecurityServiceException("Invalid username or password.",
                    HttpStatus.UNAUTHORIZED);
        }
        String token = jwtTokenProvider.createToken(username, user.getRole().stream()
                .map(role -> "ROLE_" + role.getRole()).collect(Collectors.toList()));
        return token;
    }
}
