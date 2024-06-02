package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StartQuizRecordDto(@NotNull Integer pontuacao, @NotNull UUID userId) {
}
