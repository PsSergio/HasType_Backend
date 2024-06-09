package com.hastype.api.services;

import com.hastype.api.dtos.QuizPalavraRecordDto;
import com.hastype.api.dtos.RespostaQuizRecordDto;
import com.hastype.api.models.PalavraModel;
import com.hastype.api.models.QuizPalavrasModel;
import com.hastype.api.repository.PalavraRepository;
import com.hastype.api.repository.QuizPalavraRepository;
import com.hastype.api.repository.QuizRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizPalavrasService {

    private final PalavraRepository palavraRepository;

    private final QuizRepository quizRepository;

    private final QuizPalavraRepository quizPalavraRepository;

    public QuizPalavrasService(PalavraRepository palavraRepository, QuizRepository quizRepository, QuizPalavraRepository quizPalavraRepository) {
        this.palavraRepository = palavraRepository;
        this.quizRepository = quizRepository;
        this.quizPalavraRepository = quizPalavraRepository;
    }

    public List<Integer> sorteiaIds(int qtdPalavras){

        return new PalavraModel().sorteiaNumeros((int)palavraRepository.count(), qtdPalavras);

//        return palavraRepository.findAllById(listaId);

    }

    public List<PalavraModel> atribuirPalavraAoQuiz(int qtdPalavras, UUID idQuiz){

        List<Integer> idsSorteados = sorteiaIds(qtdPalavras);
        System.out.println("ids: " + idsSorteados);

        List<QuizPalavrasModel> listQuizPalavras = new ArrayList<>(qtdPalavras);

        for (int i = 0; i < qtdPalavras; i++){
            listQuizPalavras.add(new QuizPalavrasModel());

            listQuizPalavras.get(i).setPalavraId(idsSorteados.get(i));

            listQuizPalavras.get(i).setQuizId(idQuiz);
        }

        quizPalavraRepository.saveAll(listQuizPalavras);

        return palavraRepository.findAllById(idsSorteados);

    }

    public Boolean isRespostaCorrect (RespostaQuizRecordDto resposta){

        Optional<PalavraModel> palavraModel = palavraRepository.findById(resposta.palavraId());

        if(palavraModel.isPresent()){
            String respostaCorreta = palavraModel.get().getPalavraTraduzida();

            return Objects.equals(resposta.resposta(), respostaCorreta);
        }

        return false;


    }

}
