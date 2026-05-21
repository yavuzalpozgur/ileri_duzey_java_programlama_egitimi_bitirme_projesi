package com.example.eventapp.service;

import com.example.eventapp.dto.LoginRequest;
import com.example.eventapp.dto.RegisterRequest;
import com.example.eventapp.entity.User;
import com.example.eventapp.exception.BadRequestException;
import com.example.eventapp.exception.UnauthorizedException;
import com.example.eventapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {

        boolean exists =
                userRepository.findByEmail(
                        request.getEmail()
                ).isPresent();

        if (exists) {

            throw new BadRequestException("Bu email zaten kayıtlı");
        }

        User user = new User();

        user.setFullName(request.getFullName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new UnauthorizedException("Email veya şifre yanlış")
        );

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!matches) {

            throw new UnauthorizedException("Email veya şifre yanlış");
        }

        return user;
    }
}
