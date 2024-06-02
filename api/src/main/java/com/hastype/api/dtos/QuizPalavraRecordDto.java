package com.hastype.api.dtos;

import com.hastype.api.models.PalavraModel;
import com.hastype.api.models.QuizModel;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record QuizPalavraRecordDto(@NotNull UUID idQuiz) {
}
