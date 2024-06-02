package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.UUID;

public record QuizRecordDto(@NotNull Integer pontuacao, @NotNull UUID userId) {
}
