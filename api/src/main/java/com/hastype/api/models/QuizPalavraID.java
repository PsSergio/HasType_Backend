package com.hastype.api.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class QuizPalavraID implements Serializable {

    private Integer idPalavra;

    private UUID quiz;

}
