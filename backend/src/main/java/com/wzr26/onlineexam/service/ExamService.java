package com.wzr26.onlineexam.service;

import com.wzr26.onlineexam.model.Exam;
import com.wzr26.onlineexam.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
        seedDefaultExam("Java Midterm", 60);
        seedDefaultExam("OOP Quiz", 30);
    }

    private void seedDefaultExam(String title, Integer duration) {
        boolean exists = examRepository.findAll().stream()
                .anyMatch(exam -> exam.getTitle().equals(title));

        if (!exists) {
            examRepository.save(new Exam(null, title, duration));
        }
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    public Exam createExam(Exam exam) {
        exam.setId(null);
        return examRepository.save(exam);
    }

    public Exam updateExam(Long id, Exam updatedExam) {
        Exam existingExam = getExamById(id);
        if (existingExam == null) {
            return null;
        }

        existingExam.setTitle(updatedExam.getTitle());
        existingExam.setDuration(updatedExam.getDuration());

        return examRepository.save(existingExam);
    }

    public boolean deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            return false;
        }

        examRepository.deleteById(id);
        return true;
    }
}
