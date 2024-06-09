package com.hastype.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "quiz_palavra")
@IdClass(QuizPalavraID.class)
public class QuizPalavrasModel {

    @Id
    private Integer palavraId;

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID quizId;

    public Integer getPalavraId() {
        return palavraId;
    }

    public void setPalavraId(Integer palavraId) {
        this.palavraId = palavraId;
    }

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }
}
