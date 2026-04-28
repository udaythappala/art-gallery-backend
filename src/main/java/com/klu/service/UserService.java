package com.klu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klu.dto.RegisterRequest;
import com.klu.entity.Role;
import com.klu.entity.User;
import com.klu.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER (FIXED)
    public User registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 🔥 STRICT ROLE SETTING
        String roleInput = request.getRole().toUpperCase();

        System.out.println("Incoming Role: " + roleInput);

        if (roleInput.equals("ADMIN")) {
            user.setRole(Role.ADMIN);
        } else if (roleInput.equals("ARTIST")) {
            user.setRole(Role.ARTIST);
        } else {
            user.setRole(Role.VISITOR); // default safe
        }

        System.out.println("Saved Role: " + user.getRole());

        return userRepository.save(user);
    }

    // ✅ LOGIN
    public User loginUser(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // ✅ GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ UPDATE USER
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        // 🔥 SAFE ROLE UPDATE
        try {
            user.setRole(Role.valueOf(updatedUser.getRole().toString().toUpperCase()));
        } catch (Exception e) {
            user.setRole(Role.VISITOR);
        }

        return userRepository.save(user);
    }

    // ✅ DELETE USER
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}