package com.hastype.api.repository;

import com.hastype.api.models.QuizPalavraID;
import com.hastype.api.models.QuizPalavrasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizPalavraRepository extends JpaRepository<QuizPalavrasModel, QuizPalavraID> {

}
