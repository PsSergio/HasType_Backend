package com.hastype.api.dtos;

import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotNull String email, @NotNull String nome, @NotNull String senha) {



}
