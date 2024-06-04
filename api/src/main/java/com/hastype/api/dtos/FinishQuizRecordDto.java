package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public record FinishQuizRecordDto(@NotNull List<RespostaQuizRecordDto> respostas) {
}
