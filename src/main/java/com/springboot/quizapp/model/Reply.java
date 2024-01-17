package com.springboot.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Reply {//response coming from the user.
    private Integer id;
    private String answer;
}
