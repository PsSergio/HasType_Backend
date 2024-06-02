package com.hastype.api.services;

import com.google.protobuf.Any;
import com.hastype.api.dtos.FinishQuizRecordDto;
import com.hastype.api.dtos.StartQuizRecordDto;
import com.hastype.api.models.QuizModel;
import com.hastype.api.repository.QuizRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizModel startQuiz(StartQuizRecordDto quizRecordDto){

        var quiz = new QuizModel();
        BeanUtils.copyProperties(quizRecordDto, quiz);
        quiz.setTempoInicio(LocalTime.now());

        return quizRepository.save(quiz);

    }

    public ResponseEntity<Object> finishQuiz (UUID id, FinishQuizRecordDto finishQuizRecordDto){

        Optional<QuizModel> quizFinalizado = quizRepository.findById(id);

        if(quizFinalizado.isPresent()){
            var quiz = quizFinalizado.get();
            BeanUtils.copyProperties(finishQuizRecordDto, quiz);
            quiz.setTempoFinal(LocalTime.now());
            return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);

        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

    }

    public List<QuizModel> listarTodos (){
        return quizRepository.findAll();
    }

}
