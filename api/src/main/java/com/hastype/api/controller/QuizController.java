package com.hastype.api.controller;

import com.hastype.api.dtos.FinishQuizRecordDto;
import com.hastype.api.dtos.StartQuizRecordDto;
import com.hastype.api.models.QuizModel;
import com.hastype.api.repository.QuizRepository;
import com.hastype.api.services.QuizService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("quiz/")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("start")
    public ResponseEntity<QuizModel> iniciarQuiz(@RequestBody @Valid StartQuizRecordDto startQuizRecordDto){

//        var quiz = new QuizModel();
//        BeanUtils.copyProperties(quizRecordDto, quiz);
//        quiz.setTempoInicio(LocalTime.now());

        QuizModel quiz = quizService.startQuiz(startQuizRecordDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);

    }

    @PutMapping("finish/{id}")
    public ResponseEntity<Object> finalizarQuiz(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid FinishQuizRecordDto finishQuizRecordDto){

        return new ResponseEntity<Object>(quizService.finishQuiz(id, finishQuizRecordDto), HttpStatus.OK);


    }

    @GetMapping("all")
    public List<QuizModel> listarRegistros(){
        return quizService.listarTodos();
    }


}
