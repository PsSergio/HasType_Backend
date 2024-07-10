package com.hastype.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name="Quiz")
public class QuizModel {

    private Integer pontuacao;

    private LocalTime tempoInicio;

    private LocalTime tempoFinal;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalTime getTempoInicio() {
        return tempoInicio;
    }

//    TODO: trocar localTime para LocalDateTime

    public void setTempoInicio(LocalTime tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public LocalTime getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(LocalTime tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
