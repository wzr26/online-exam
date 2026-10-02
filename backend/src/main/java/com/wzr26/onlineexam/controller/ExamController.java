package com.wzr26.onlineexam.controller;

import com.wzr26.onlineexam.model.Exam;
import com.wzr26.onlineexam.model.Question;
import com.wzr26.onlineexam.service.ExamService;
import com.wzr26.onlineexam.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;
    private final QuestionService questionService;

    public ExamController(
            ExamService examService,
            QuestionService questionService
    ) {
        this.examService = examService;
        this.questionService = questionService;
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{examId}")
    public Exam getExamById(
            @PathVariable Long examId
    ) {
        return examService.getExamById(examId);
    }

    @GetMapping("/{examId}/questions")
    public List<Question> getQuestionsByExamId(
            @PathVariable Long examId
    ) {
        return questionService.getQuestionsByExamId(examId);
    }

    @PostMapping("/{examId}/questions")
    public Question createQuestion(
            @PathVariable Long examId,
            @RequestBody Question question
    ) {
        question.setExamId(examId);

        return questionService.createQuestion(question);
    }

    @PutMapping("/{examId}/questions/{questionId}")
    public Question updateQuestion(
            @PathVariable Long examId,
            @PathVariable Long questionId,
            @RequestBody Question question
    ) {
        question.setExamId(examId);

        return questionService.updateQuestion(
                questionId,
                question
        );
    }

    @DeleteMapping("/{examId}/questions/{questionId}")
    public String deleteQuestion(
            @PathVariable Long questionId
    ) {

        boolean deleted =
                questionService.deleteQuestion(questionId);

        if (deleted) {
            return "Deleted successfully";
        }

        return "Question not found";
    }
}