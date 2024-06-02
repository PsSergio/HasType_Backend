package com.hastype.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
public class QuizPalavraID implements Serializable {

    private Integer palavraId;

    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID quizId;

    public QuizPalavraID(Integer palavraId, UUID quizId) {
        this.palavraId = palavraId;
        this.quizId = quizId;
    }
}
