package com.hastype.api.services;

import com.hastype.api.models.QuizModel;
import com.hastype.api.models.RankingTempoModel;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.QuizRepository;
import com.hastype.api.repository.RankingTempoRepository;
import com.hastype.api.services.interfaces.RankingService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class RankingTempoService implements RankingService {

    private final RankingTempoRepository rankingTempoRepository;

    private final UserService userService;

    private final QuizRepository quizRepository;

    public RankingTempoService(RankingTempoRepository rankingTempoRepository, UserService userService, QuizRepository quizRepository) {
        this.rankingTempoRepository = rankingTempoRepository;
        this.userService = userService;
        this.quizRepository = quizRepository;
    }

    @Override
    public List<RankingTempoModel> atualizaRanking(){

        return rankingTempoRepository.findAllByOrderByTempoAsc();

    }

    @Override
    public Optional<RankingTempoModel> findUserInRanking(UUID userID){
        Optional<RankingTempoModel> userInRanking = rankingTempoRepository.findByUserId(userID);

        return userInRanking;
    }

    public Integer calculaTempoTotal(QuizModel quiz){
        long tempo_final = SECONDS.between(quiz.getTempoInicio(), quiz.getTempoFinal());

        return (int) tempo_final;
    }

    @Override
    public void addUserRanking(QuizModel quiz){

        var ranking = new RankingTempoModel();

        ranking.setQuizId(quiz.getId());
        ranking.setTempo(calculaTempoTotal(quiz));
        ranking.setUserId(quiz.getUserId());

        rankingTempoRepository.save(ranking);


    }

    @Override
    public void updateUserInRanking(QuizModel quiz){

        var oldRanking = rankingTempoRepository.findByUserId(quiz.getUserId());

        Integer newTempo = calculaTempoTotal(quiz);

        if (oldRanking.get().getTempo() > newTempo) {

            oldRanking.get().setQuizId(quiz.getId());
            oldRanking.get().setTempo(calculaTempoTotal(quiz));

            rankingTempoRepository.save(oldRanking.get());

        }

    }

}

