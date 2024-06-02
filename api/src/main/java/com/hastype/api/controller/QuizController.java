package com.hastype.api.controller;

import com.hastype.api.dtos.QuizRecordDto;
import com.hastype.api.models.QuizModel;
import com.hastype.api.repository.QuizRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("quiz/")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping("start")
    public ResponseEntity<QuizModel> iniciarQuiz(@RequestBody @Valid QuizRecordDto quizRecordDto){

        var quiz = new QuizModel();
        BeanUtils.copyProperties(quizRecordDto, quiz);
        quiz.setTempoInicio(LocalTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(quizRepository.save(quiz));

    }

}
