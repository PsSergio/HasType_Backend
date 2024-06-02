package com.hastype.api.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "QuizPalavra")
public class QuizPalavrasModel {

    @EmbeddedId
    private QuizPalavraID quizPalavraID;

    private Boolean isCorrect;

    public QuizPalavraID getQuizPalavraID() {
        return quizPalavraID;
    }

    public void setQuizPalavraID(QuizPalavraID quizPalavraID) {
        this.quizPalavraID = quizPalavraID;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
