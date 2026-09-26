package com.wzr26.onlineexam.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "Trang đăng nhập";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        return "Đã nhận dữ liệu đăng nhập";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "Trang đăng ký";
    }
}
