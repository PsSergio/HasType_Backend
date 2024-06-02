package com.hastype.api.controller;

import com.hastype.api.dtos.FinishQuizRecordDto;
import com.hastype.api.dtos.StartQuizRecordDto;
import com.hastype.api.models.PalavraModel;
import com.hastype.api.models.QuizModel;
import com.hastype.api.models.QuizPalavrasModel;
import com.hastype.api.services.QuizPalavrasService;
import com.hastype.api.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("quiz/")
public class QuizController {

    private final QuizService quizService;
    private final QuizPalavrasService quizPalavrasService;



    @Autowired
    public QuizController(QuizService quizService, QuizPalavrasService quizPalavrasService) {
        this.quizService = quizService;
        this.quizPalavrasService = quizPalavrasService;
    }


    @PostMapping("start")
    public ResponseEntity<List<Object>> iniciarQuiz(@RequestBody @Valid StartQuizRecordDto startQuizRecordDto){

        QuizModel quiz = quizService.startQuiz(startQuizRecordDto);
        List<QuizPalavrasModel> quizPalavrasModel = quizPalavrasService.atribuirPalavraAoQuiz(startQuizRecordDto.qtdPalavras(), quiz.getId());

//        List<PalavraModel> palavraModels = new ArrayList<>(quizPalavrasModel.size());
//        for(int i = 0; i < quizPalavrasModel.size(); i++){
//            palavraModels.add(new )
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Arrays.asList(quiz, quizPalavrasModel));

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
