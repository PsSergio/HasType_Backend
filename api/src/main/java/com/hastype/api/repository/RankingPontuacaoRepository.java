package com.hastype.api.repository;

import com.hastype.api.models.RankingPontuacaoModel;
import com.hastype.api.models.RankingTempoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RankingPontuacaoRepository extends JpaRepository<RankingPontuacaoModel, UUID> {

    List<RankingPontuacaoModel> findAllByOrderByPontuacaoDesc();

    Optional<RankingPontuacaoModel> findByUserId(UUID userId);

}
