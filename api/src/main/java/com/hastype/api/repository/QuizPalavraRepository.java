package com.hastype.api.repository;

import com.hastype.api.models.QuizPalavraID;
import com.hastype.api.models.QuizPalavrasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizPalavraRepository extends JpaRepository<QuizPalavrasModel, QuizPalavraID> {

    List<QuizPalavrasModel> findAllByQuizId(UUID quizId);

}
