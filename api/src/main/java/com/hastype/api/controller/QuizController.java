package com.hastype.api.controller;

import com.hastype.api.dtos.FinishQuizRecordDto;
import com.hastype.api.dtos.StartQuizRecordDto;
import com.hastype.api.models.QuizModel;
import com.hastype.api.repository.QuizRepository;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("quiz/")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping("start")
    public ResponseEntity<QuizModel> iniciarQuiz(@RequestBody @Valid StartQuizRecordDto quizRecordDto){

        var quiz = new QuizModel();
        BeanUtils.copyProperties(quizRecordDto, quiz);
        quiz.setTempoInicio(LocalTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(quizRepository.save(quiz));

    }

    @PutMapping("finish/{id}")
    public ResponseEntity<Object> finalizarQuiz(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid FinishQuizRecordDto finishQuizRecordDto){

        Optional<QuizModel> quizFinalizado = quizRepository.findById(id);

        if(quizFinalizado.isPresent()){
            var quiz = quizFinalizado.get();
            BeanUtils.copyProperties(finishQuizRecordDto, quiz);
            quiz.setTempoFinal(LocalTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(quizRepository.save(quiz));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

    }


}
