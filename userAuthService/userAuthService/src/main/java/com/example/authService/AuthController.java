package com.example.authService;

import com.example.authService.Dto.AuthResponse;
import com.example.authService.Dto.LoginRequest;
import com.example.authService.Dto.RegisterRequest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}