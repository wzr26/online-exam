package com.wzr26.onlineexam.service;

import com.wzr26.onlineexam.model.Exam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    private final List<Exam> exams = new ArrayList<>();

    public ExamService() {
        exams.add(new Exam(1L, "Java Midterm", 60));
        exams.add(new Exam(2L, "OOP Quiz", 30));
    }

    public List<Exam> getAllExams() {
        return exams;
    }

    public Exam getExamById(Long id) {

        for (Exam exam : exams) {
            if (exam.getId().equals(id)) {
                return exam;
            }
        }

        return null;
    }

    public Exam createExam(Exam exam) {

        Long newId = 1L;

        for (Exam existingExam : exams) {
            if (existingExam.getId() >= newId) {
                newId = existingExam.getId() + 1;
            }
        }

        exam.setId(newId);
        exams.add(exam);

        return exam;
    }

    public Exam updateExam(Long id, Exam updatedExam) {

        Exam existingExam = getExamById(id);

        if (existingExam == null) {
            return null;
        }

        existingExam.setTitle(updatedExam.getTitle());
        existingExam.setDuration(updatedExam.getDuration());

        return existingExam;
    }

    public boolean deleteExam(Long id) {

        Exam exam = getExamById(id);

        if (exam == null) {
            return false;
        }

        exams.remove(exam);

        return true;
    }
}