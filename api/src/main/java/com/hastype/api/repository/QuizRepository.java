package com.hastype.api.repository;

import com.hastype.api.models.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, UUID> {
}
