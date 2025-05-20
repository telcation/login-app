package com.example.loginapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder; // ←追加
import org.springframework.stereotype.Component;

import com.example.loginapp.entity.User;
import com.example.loginapp.repository.UserRepository;

@Component
public class InitUserConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // ←追加

    public InitUserConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // ←追加
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsById("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("pass")); // ←ここでハッシュ化
            user.setRoles("ADMIN");
            userRepository.save(user);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
