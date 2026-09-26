package com.wzr26.onlineexam.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ExamController {

    @GetMapping("/exams")
    public String getExams() {
        return "Danh sách bài thi";
    }

    @GetMapping("/exams/{id}")
    public String getExamById(@PathVariable Long id) {
        return "Thông tin bài thi có ID: " + id;
    }
}
