package com.example.authService;

import com.example.authService.Dto.AuthResponse;
import com.example.authService.Dto.LoginRequest;
import com.example.authService.Dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token);
    }
}