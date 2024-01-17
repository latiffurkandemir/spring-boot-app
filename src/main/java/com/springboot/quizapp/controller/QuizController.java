package com.springboot.quizapp.controller;


import com.springboot.quizapp.model.QuestionWrapper;
import com.springboot.quizapp.service.QuizService;
import com.springboot.quizapp.model.Reply;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Response> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title) {
        quizService.createQuiz(category, numQ, title);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get questions for user
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id){
        List<QuestionWrapper> questions = quizService.getQuizQuestionById(id);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    //calculate user score for particular quiz
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Reply> answerList){
        //answer list does involve id of questions in that particular quiz, and the user answer of that questions
        Integer score = quizService.calculateResult(id,answerList);
        return new ResponseEntity<>(score,HttpStatus.OK);
    }

}
