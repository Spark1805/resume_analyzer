package com.resumeanalyzer.controller;

import com.resumeanalyzer.config.JwtUtil;
import com.resumeanalyzer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return jwtUtil.generateToken(user.getUsername());
    }
}
