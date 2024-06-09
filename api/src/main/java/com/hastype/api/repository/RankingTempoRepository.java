package com.hastype.api.repository;

import com.hastype.api.models.RankingTempoModel;
import com.hastype.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RankingTempoRepository extends JpaRepository<RankingTempoModel, Integer> {

    List<RankingTempoModel> findAllByOrderByTempoAsc();

    Optional<RankingTempoModel> findByUserId(UUID userId);

}
