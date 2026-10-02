package com.wzr26.onlineexam.service;

import com.wzr26.onlineexam.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final List<Question> questions = new ArrayList<>();

    public QuestionService() {

        questions.add(
                new Question(
                        1L,
                        1L,
                        "What is Java?",
                        "Programming Language",
                        "Animal",
                        "City",
                        "Food",
                        "Programming Language"
                )
        );

        questions.add(
                new Question(
                        2L,
                        1L,
                        "Which company created Java?",
                        "Google",
                        "Microsoft",
                        "Sun Microsystems",
                        "Apple",
                        "Sun Microsystems"
                )
        );
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public Question getQuestionById(Long id) {

        for (Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }

        return null;
    }

    public List<Question> getQuestionsByExamId(Long examId) {

        List<Question> result = new ArrayList<>();

        for (Question question : questions) {

            if (question.getExamId().equals(examId)) {
                result.add(question);
            }

        }

        return result;
    }

    public Question createQuestion(Question question) {

        Long newId = 1L;

        for (Question existingQuestion : questions) {

            if (existingQuestion.getId() >= newId) {
                newId = existingQuestion.getId() + 1;
            }

        }

        question.setId(newId);

        questions.add(question);

        return question;
    }

    public Question updateQuestion(
            Long id,
            Question updatedQuestion
    ) {

        Question existingQuestion =
                getQuestionById(id);

        if (existingQuestion == null) {
            return null;
        }

        existingQuestion.setExamId(
                updatedQuestion.getExamId()
        );

        existingQuestion.setContent(
                updatedQuestion.getContent()
        );

        existingQuestion.setOptionA(
                updatedQuestion.getOptionA()
        );

        existingQuestion.setOptionB(
                updatedQuestion.getOptionB()
        );

        existingQuestion.setOptionC(
                updatedQuestion.getOptionC()
        );

        existingQuestion.setOptionD(
                updatedQuestion.getOptionD()
        );

        existingQuestion.setCorrectAnswer(
                updatedQuestion.getCorrectAnswer()
        );

        return existingQuestion;
    }
    public boolean questionBelongsToExam(
        Long questionId,
        Long examId
    ) {
        Question question = getQuestionById(questionId);

    if (question == null) {
        return false;
    }

    return question.getExamId().equals(examId);
    }
    public boolean deleteQuestion(Long id) {

        Question question =
                getQuestionById(id);

        if (question == null) {
            return false;
        }

        questions.remove(question);

        return true;
    }
}
