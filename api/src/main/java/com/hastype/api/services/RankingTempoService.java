package com.hastype.api.services;

import com.hastype.api.models.QuizModel;
import com.hastype.api.models.RankingTempoModel;
import com.hastype.api.repository.RankingTempoRepository;
import com.hastype.api.repository.UserRepository;
import com.hastype.api.services.interfaces.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class RankingTempoService implements RankingService {

    private final RankingTempoRepository rankingTempoRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    public RankingTempoService(RankingTempoRepository rankingTempoRepository, UserService userService, UserRepository userRepository) {
        this.rankingTempoRepository = rankingTempoRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<RankingTempoModel> atualizaRanking(){

        return rankingTempoRepository.findAllByOrderByTempoAsc();

    }

    public Optional<RankingTempoModel> findUserInRanking(UUID userID){
        return rankingTempoRepository.findByUserId(userID);
    }

    public Integer calculaTempoTotal(QuizModel quiz){
        long tempo_final = SECONDS.between(quiz.getTempoInicio(), quiz.getTempoFinal());

        return (int) tempo_final;
    }

    public void validateUserToAddOrUpdate(QuizModel quizModel){
        if (findUserInRanking(quizModel.getUserId()).isEmpty()){
            addUserRanking(quizModel);
        }else{
            updateUserInRanking(quizModel);
        }
    }

    @Override
    public void addUserRanking(QuizModel quiz){

        var ranking = new RankingTempoModel();

        ranking.setQuizId(quiz.getId());
        ranking.setTempo(calculaTempoTotal(quiz));
        ranking.setUserId(quiz.getUserId());
        ranking.setUserName(userRepository.findById(quiz.getUserId()).get().getNome());

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

