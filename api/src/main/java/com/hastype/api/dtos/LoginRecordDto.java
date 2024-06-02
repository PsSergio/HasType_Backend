package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

public record LoginRecordDto(@NotNull String email, @NotNull String senha) {
}
