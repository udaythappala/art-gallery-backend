package com.klu.controller;

import com.klu.security.JwtUtil;
import com.klu.dto.LoginRequest;
import com.klu.dto.RegisterRequest;
import com.klu.entity.User;
import com.klu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        // 🔥 DEBUG
        System.out.println("Register Role: " + request.getRole());

        return userService.registerUser(request);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        User user = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

        // 🔥 TOKEN (still email-based)
        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole()); // 🔥 VERY IMPORTANT

        // 🔥 DEBUG
        System.out.println("Login Role: " + user.getRole());

        return response;
    }
}