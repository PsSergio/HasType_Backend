package com.hastype.api.dtos;

import com.hastype.api.models.PalavraModel;
import com.hastype.api.models.QuizModel;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StartQuizResponseDto (@NotNull QuizModel quiz, @NotNull List<PalavraModel> palavras){
}
