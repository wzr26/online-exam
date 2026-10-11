
package com.wzr26.onlineexam.controller;

import com.wzr26.onlineexam.model.LoginRequest;
import com.wzr26.onlineexam.model.LoginResponse;
import com.wzr26.onlineexam.model.User;
import com.wzr26.onlineexam.service.JwtService;
import com.wzr26.onlineexam.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public String home() {
        return "Trang chủ";
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {
        User user = userService.login(
                request.getUsername(),
                request.getPassword()
        );

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        LoginResponse response = new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                "Login successful",
                token
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Trang đăng nhập";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "Trang đăng ký";
    }
}
