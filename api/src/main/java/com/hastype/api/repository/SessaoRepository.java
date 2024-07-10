package com.hastype.api.repository;

import com.hastype.api.models.SessaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessaoRepository extends JpaRepository<SessaoModel, UUID> {

    Optional<SessaoModel> findByUserId (UUID userId);

}
