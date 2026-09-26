package com.wzr26.onlineexam.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @GetMapping("/profile")
    public String profilePage() {
        return "Trang profile";
    }

    @GetMapping("/api/profile")
    public String getProfile() {
        return "Thông tin profile";
    }
}
