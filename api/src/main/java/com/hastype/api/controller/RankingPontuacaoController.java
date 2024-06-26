package com.hastype.api.controller;

import com.hastype.api.models.RankingPontuacaoModel;
import com.hastype.api.models.RankingTempoModel;
import com.hastype.api.services.RankingPontuacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ranking/pontuacao/")
public class RankingPontuacaoController {

    private final RankingPontuacaoService rankingPontuacaoService;


    public RankingPontuacaoController(RankingPontuacaoService rankingPontuacaoService) {
        this.rankingPontuacaoService = rankingPontuacaoService;
    }

    @GetMapping("all")
    public ResponseEntity<List<RankingPontuacaoModel>> allRanking(){
        return ResponseEntity.status(HttpStatus.OK).body(rankingPontuacaoService.atualizaRanking());
    }
}
