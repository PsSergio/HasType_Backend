package com.hastype.api.services;

import com.hastype.api.dtos.QuizPalavraRecordDto;
import com.hastype.api.dtos.StartQuizRecordDto;
import com.hastype.api.models.PalavraModel;
import com.hastype.api.models.QuizModel;
import com.hastype.api.repository.PalavraRepository;
import com.hastype.api.repository.QuizRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class QuizPalavrasService {

    private final PalavraRepository palavraRepository;
    private final QuizRepository quizRepository;

    public QuizPalavrasService(PalavraRepository palavraRepository, QuizRepository quizRepository) {
        this.palavraRepository = palavraRepository;
        this.quizRepository = quizRepository;
    }

    public List<PalavraModel> sorteiaPalavras(int qtdPalavras){

        List<Integer> listaId = new PalavraModel().sorteiaNumeros((int)palavraRepository.count(), qtdPalavras);

        return palavraRepository.findAllById(listaId);

    }

//    public QuizPalavraRecordDto atribuirPalavraAoQuiz(StartQuizRecordDto startQuizRecordDto){
//
//        var quiz = new QuizModel();
//        BeanUtils.copyProperties(startQuizRecordDto, quiz);
//        quiz.setTempoInicio(LocalTime.now());
//
//
//
//    }

}
