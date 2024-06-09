package com.hastype.api.controller;

import com.hastype.api.services.RankingTempoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ranking/tempo/")
public class RankingTempoController {


    private final RankingTempoService rankingTempoService;


    public RankingTempoController(RankingTempoService rankingTempoService) {
        this.rankingTempoService = rankingTempoService;
    }

    @PostMapping("addUser/{id}")
    public ResponseEntity<List<Object>> addUser(@PathVariable("id") UUID quizId){
        boolean serviceStatus = rankingTempoService.addUserRanking(quizId);

        if(serviceStatus){
            return ResponseEntity.status(HttpStatus.CREATED).body(Arrays.asList(rankingTempoService.atualizaRanking()));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Arrays.asList("Error"));
        }
    }
}
