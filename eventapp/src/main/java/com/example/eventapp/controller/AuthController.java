package com.example.eventapp.controller;

import com.example.eventapp.dto.LoginRequest;
import com.example.eventapp.dto.RegisterRequest;
import com.example.eventapp.entity.User;
import com.example.eventapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public User login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {

        User user = userService.login(request);

        session.setAttribute(
                "userId",
                user.getId()
        );

        return user;
    }

    @PostMapping("/logout")
    public String logout(
            HttpSession session
    ) {

        session.invalidate();

        return "Çıkış başarılı";
    }
}
