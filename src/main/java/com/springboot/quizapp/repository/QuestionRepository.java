package com.springboot.quizapp.repository;

//service is going to get the data from dao package

import com.springboot.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> { //type of table (table), type of primary key

    List<Question> findByCategory(String category);//jpa understands we try to get data by category and handles it
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer numQ);
}
