package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StartQuizRecordDto(@NotNull UUID userId, @NotNull int qtdPalavras) {
}
