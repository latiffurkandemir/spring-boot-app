package com.springboot.quizapp.controller;

import com.springboot.quizapp.model.Question;
import com.springboot.quizapp.service.QuestionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<Optional<Question>> getQuestionById(@RequestParam Integer id){
        return new ResponseEntity<>(questionService.getQuestionById(id),HttpStatus.OK);
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {//whenever we are passing a value in the path, that value will be assigned to this variable
        List<Question> questionListByCategory = questionService.getQuestionsByCategory(category);
        return new ResponseEntity<>(questionListByCategory, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) { // spring converts json to object, thats why parameter type is object. RequestBody(we get the data in http request body
        Question addedQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/remove/{id}")
    public  ResponseEntity<Response> deleteQuestionById(@PathVariable Integer id){
        questionService.deleteQuestionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
