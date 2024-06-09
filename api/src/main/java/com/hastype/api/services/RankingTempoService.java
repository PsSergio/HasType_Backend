package com.hastype.api.services;

import com.hastype.api.models.QuizModel;
import com.hastype.api.models.RankingTempoModel;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.QuizRepository;
import com.hastype.api.repository.RankingTempoRepository;
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
public class RankingTempoService {

    private final RankingTempoRepository rankingTempoRepository;

    private final UserService userService;

    private final QuizRepository quizRepository;

    public RankingTempoService(RankingTempoRepository rankingTempoRepository, UserService userService, QuizRepository quizRepository) {
        this.rankingTempoRepository = rankingTempoRepository;
        this.userService = userService;
        this.quizRepository = quizRepository;
    }

    public List<RankingTempoModel> atualizaRanking(){

        return rankingTempoRepository.findAllByOrderByTempoAsc();

    }

    public boolean findUserInRanking(UUID userID){
        Optional<RankingTempoModel> user = rankingTempoRepository.findByUserId(userID);

        return user.isPresent();
    }

    public Integer calculaTempoTotal(Optional<QuizModel> quiz){
        long tempo_final = SECONDS.between(quiz.get().getTempoInicio(), quiz.get().getTempoFinal());

        return (int) tempo_final;
    }



    public boolean addUserRanking(UUID quizId){

        var ranking = new RankingTempoModel();
        ranking.setQuizId(quizId);

        Optional<QuizModel> quiz = quizRepository.findById(ranking.getQuizId());

        if(quiz.isPresent()) {
            boolean userFound = findUserInRanking(quiz.get().getUserId());

            if(userFound){
                return false;
//                return ResponseEntity.status(HttpStatus.FOUND).body("User can't be added");
            }else{
                ranking.setTempo(calculaTempoTotal(quiz));
                ranking.setUserId(quiz.get().getUserId());
                rankingTempoRepository.save(ranking);
                return true;
            }

        }
        return false;
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id Quiz not found");

    }




//    public ResponseEntity<RankingTempoModel> userNoRanking(RankingTempoRecordDto rankingTempoRecordDto){
//
//        var ranking = new RankingTempoModel();
//        BeanUtils.copyProperties(rankingTempoRecordDto, ranking);
//
//        Optional<QuizModel> quiz = quizRepository.findById(ranking.getQuizId());
//
//        if(quiz.isPresent()){
//
//            boolean userFound = findUserInRanking(quiz.get().getUserId());
//
//            if(userFound){
////                 atualiza pelo usuario
////                return ResponseEntity.status(HttpStatus.OK).body(rankingTempoRepository.save(ranking));
//
//
//            }else{
//                // add usuario
//                ranking.setUserId(quiz.get().getUserId());
//                return ResponseEntity.status(HttpStatus.OK).body(rankingTempoRepository.save(ranking));
//            }
//
//        }
//
//    }

}

