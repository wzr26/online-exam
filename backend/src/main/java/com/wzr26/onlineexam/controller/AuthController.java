package com.wzr26.onlineexam.controller;

import com.wzr26.onlineexam.model.LoginRequest;
import com.wzr26.onlineexam.model.User;
import com.wzr26.onlineexam.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "Trang chủ";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        User user = userService.login(
                request.getUsername(),
                request.getPassword()
        );

        if (user == null) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/register")
    public String registerPage() {
        return "Trang đăng ký";
    }
}