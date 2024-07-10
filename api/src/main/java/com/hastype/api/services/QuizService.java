package com.hastype.api.services;

import com.hastype.api.dtos.FinishQuizRecordDto;
import com.hastype.api.dtos.StartQuizRecordDto;
import com.hastype.api.models.QuizModel;
import com.hastype.api.repository.PalavraRepository;
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
    private final PalavraRepository palavraRepository;

    private final QuizPalavrasService quizPalavrasService;

    private final RankingTempoService rankingTempoService;

    private final RankingPontuacaoService rankingPontuacaoService;

    private final SessaoService sessaoService;

    public QuizService(QuizRepository quizRepository, PalavraRepository palavraRepository, QuizPalavrasService quizPalavrasService, RankingTempoService rankingTempoService, RankingPontuacaoService rankingPontuacaoService, SessaoService sessaoService) {
        this.quizRepository = quizRepository;
        this.palavraRepository = palavraRepository;
        this.quizPalavrasService = quizPalavrasService;
        this.rankingTempoService = rankingTempoService;
        this.rankingPontuacaoService = rankingPontuacaoService;
        this.sessaoService = sessaoService;
    }

    public QuizModel startQuiz(StartQuizRecordDto quizRecordDto){

        var quiz = new QuizModel();
        BeanUtils.copyProperties(quizRecordDto, quiz);
        quiz.setTempoInicio(LocalTime.now());
        quiz.setPontuacao(0);

        return quizRepository.save(quiz);

    }

    public Integer countQuizScore(QuizModel quiz, FinishQuizRecordDto finishQuizRecordDto){
        Integer _pontuacao = 0;
        for(int i = 0; i < finishQuizRecordDto.respostas().size(); i++){

            if(quizPalavrasService.isRespostaCorrect(finishQuizRecordDto.respostas().get(i))){
                _pontuacao++;
            }
        }
        return _pontuacao;
    }

    public ResponseEntity<Object> finishQuiz (UUID id, FinishQuizRecordDto finishQuizRecordDto){

        Optional<QuizModel> quizFinalizado = quizRepository.findById(id);

        if(quizFinalizado.isPresent()){

            var quiz = quizFinalizado.get();

            BeanUtils.copyProperties(finishQuizRecordDto, quiz);

            quiz.setTempoFinal(LocalTime.now());

            quiz.setPontuacao(countQuizScore(quiz, finishQuizRecordDto));

            rankingTempoService.validateUserToAddOrUpdate(quiz);
            rankingPontuacaoService.validateUserToAddOrUpdate(quiz);

            return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);

        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

    }

    public List<QuizModel> listarTodos (){
        return quizRepository.findAll();
    }

}
