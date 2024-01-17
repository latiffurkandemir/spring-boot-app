package com.springboot.quizapp.service;

import com.springboot.quizapp.exception.BadRequestException;
import com.springboot.quizapp.model.Question;
import com.springboot.quizapp.exception.CategoryNotFoundException;
import com.springboot.quizapp.exception.QuestionNotFoundException;
import com.springboot.quizapp.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        try { // I can do exception handling easily without creating GlobalExceptionHandler class but I don't want service to return response directly
            return questionRepository.findAll(); //findAll() coming from JPA that returns list of objects
        } catch (Exception e) {
            throw new BadRequestException("Error getting question" + e);
        }
    }


    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);
        if (questions.isEmpty()) {
            throw new CategoryNotFoundException("Category not found: " + category);
        }
        return questions;
    }

    public Question addQuestion(Question question) {
        try {
            return questionRepository.save(question);
        } catch (Exception e) {
            throw new BadRequestException("Error adding question", e);
        }
    }

    public Optional<Question> getQuestionById(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            throw new QuestionNotFoundException("Question not found with ID: " + id);
        }

        return optionalQuestion;
    }

    public void deleteQuestionById(Integer id) {
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException("Question not found with ID: " + id);
        }

        questionRepository.deleteById(id);
    }
}
