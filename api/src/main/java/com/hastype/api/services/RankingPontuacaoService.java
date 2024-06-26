package com.hastype.api.services;

import com.hastype.api.models.QuizModel;
import com.hastype.api.models.RankingPontuacaoModel;
import com.hastype.api.repository.RankingPontuacaoRepository;
import com.hastype.api.repository.UserRepository;
import com.hastype.api.services.interfaces.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RankingPontuacaoService implements RankingService {


    private final RankingPontuacaoRepository rankingPontuacaoRepository;

    private final UserRepository userRepository;

    public RankingPontuacaoService(RankingPontuacaoRepository rankingPontuacaoRepository, UserRepository userRepository) {
        this.rankingPontuacaoRepository = rankingPontuacaoRepository;
        this.userRepository = userRepository;
    }

    public Optional<RankingPontuacaoModel> findUserInRanking(UUID userID){
        return rankingPontuacaoRepository.findByUserId(userID);
    }

    public List<RankingPontuacaoModel> atualizaRanking(){

        return rankingPontuacaoRepository.findAllByOrderByPontuacaoDesc();

    }

    public void validateUserToAddOrUpdate(QuizModel quizModel){
        if (findUserInRanking(quizModel.getUserId()).isEmpty()){
            addUserRanking(quizModel);
        }else{
            updateUserInRanking(quizModel);
        }
    }

    @Override
    public void addUserRanking(QuizModel quiz) {

        var ranking = new RankingPontuacaoModel();

        ranking.setUserId(quiz.getUserId());
        ranking.setQuizId(quiz.getId());
        ranking.setPontuacao(quiz.getPontuacao());
        ranking.setUserName(userRepository.findById(quiz.getUserId()).get().getNome());

        rankingPontuacaoRepository.save(ranking);


    }

    @Override
    public void updateUserInRanking(QuizModel quiz) {

        var oldRanking = rankingPontuacaoRepository.findByUserId(quiz.getUserId());

        if (oldRanking.get().getPontuacao() < quiz.getPontuacao()) {

            oldRanking.get().setQuizId(quiz.getId());
            oldRanking.get().setPontuacao(quiz.getPontuacao());

            rankingPontuacaoRepository.save(oldRanking.get());

        }

    }
}
