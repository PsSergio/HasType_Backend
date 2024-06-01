package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

public record PalavraRecordDto(@NotNull Integer id, @NotNull String palavraNormal, @NotNull String palavraTraduzida) {
}
