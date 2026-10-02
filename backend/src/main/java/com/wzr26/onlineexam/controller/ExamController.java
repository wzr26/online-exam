package com.wzr26.onlineexam.controller;

import com.wzr26.onlineexam.model.Exam;
import com.wzr26.onlineexam.model.Question;
import com.wzr26.onlineexam.service.ExamService;
import com.wzr26.onlineexam.service.QuestionService;
import org.springframework.http.ResponseEntity;
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

    // EXAMS

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(
            @PathVariable Long examId
    ) {
        Exam exam = examService.getExamById(examId);

        if (exam == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exam);
    }

    // QUESTIONS

    @GetMapping("/{examId}/questions")
    public ResponseEntity<List<Question>> getQuestionsByExamId(
            @PathVariable Long examId
    ) {
        Exam exam = examService.getExamById(examId);

        if (exam == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                questionService.getQuestionsByExamId(examId)
        );
    }

    @PostMapping("/{examId}/questions")
    public ResponseEntity<Question> createQuestion(
            @PathVariable Long examId,
            @RequestBody Question question
    ) {
        Exam exam = examService.getExamById(examId);

        if (exam == null) {
            return ResponseEntity.notFound().build();
        }

        question.setExamId(examId);

        Question newQuestion =
                questionService.createQuestion(question);

        return ResponseEntity.ok(newQuestion);
    }

    @PutMapping("/{examId}/questions/{questionId}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Long examId,
            @PathVariable Long questionId,
            @RequestBody Question question
    ) {

        // Check exam exists
        Exam exam = examService.getExamById(examId);

        if (exam == null) {
            return ResponseEntity.notFound().build();
        }

        // Check question belongs to this exam
        if (!questionService.questionBelongsToExam(
                questionId,
                examId
        )) {
            return ResponseEntity.notFound().build();
        }

        question.setExamId(examId);

        Question updatedQuestion =
                questionService.updateQuestion(
                        questionId,
                        question
                );

        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{examId}/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long examId,
            @PathVariable Long questionId
    ) {

        // Check exam exists
        Exam exam = examService.getExamById(examId);

        if (exam == null) {
            return ResponseEntity.notFound().build();
        }

        // Check question belongs to this exam
        if (!questionService.questionBelongsToExam(
                questionId,
                examId
        )) {
            return ResponseEntity.notFound().build();
        }

        questionService.deleteQuestion(questionId);

        return ResponseEntity.noContent().build();
    }
}