package com.springboot.quizapp.service;

import com.springboot.quizapp.model.Question;
import com.springboot.quizapp.model.QuestionWrapper;
import com.springboot.quizapp.model.Quiz;
import com.springboot.quizapp.model.Reply;
import com.springboot.quizapp.repository.QuestionRepository;
import com.springboot.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public void createQuiz(String category, Integer numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
    }

    public List<QuestionWrapper> getQuizQuestionById(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();//fetch the question
        List<QuestionWrapper> questionForUser = new ArrayList<>();//queston list(quiz questions) to return user
        for (Question q : questionsFromDB) {//filling the question list
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return questionForUser;
    }

    public Integer calculateResult(Integer id, List<Reply> answerList) {
        Quiz quiz = quizRepository.findById(id).get();//it is not optional because user will submit the quiz with its id.
        List<Question> questions = quiz.getQuestions();
        int score = 0;
        int i=0;
        for (Reply reply : answerList) {
            if(answerList.get(i).getAnswer().equals(questions.get(i).getRightAnswer()))
                score++;
            i++;
        }
        return score;
    }
}
