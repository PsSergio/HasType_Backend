package com.hastype.api.models;

import java.util.UUID;

public class QuizPalavrasModel {

    private Integer idPalavra;

    private Boolean isCorrect;

    public Integer getIdPalavra() {
        return idPalavra;
    }

    public void setIdPalavra(Integer idPalavra) {
        this.idPalavra = idPalavra;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
