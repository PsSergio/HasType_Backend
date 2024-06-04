package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

public record RespostaQuizRecordDto(@NotNull Integer palavraId, @NotNull String resposta) {
}
