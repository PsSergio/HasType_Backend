package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record FinishQuizRecordDto(@NotNull Integer pontuacao) {
}
