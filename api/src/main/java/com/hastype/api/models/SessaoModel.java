package com.hastype.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="sessao")
public class SessaoModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    private LocalDateTime initialSession;

    private LocalDateTime finalSession;

    private int sessionExpiration;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getInitialSession() {
        return initialSession;
    }

    public void setInitialSession(LocalDateTime initialSession) {
        this.initialSession = initialSession;
    }

    public LocalDateTime getFinalSession() {
        return finalSession;
    }

    public void setFinalSession(LocalDateTime finalSession) {
        this.finalSession = finalSession;
    }

    public int getSessionExpiration() {
        return sessionExpiration;
    }

    public void setSessionExpiration(int sessionExpiration) {
        this.sessionExpiration = sessionExpiration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void calculateFinalSession(int timeInSeconds){
        this.sessionExpiration = timeInSeconds;
        this.finalSession = LocalDateTime.now().plusSeconds(timeInSeconds);
    }

    public boolean validateSessionExpiration(){
        return LocalDateTime.now().isBefore(this.finalSession);
    }
}
