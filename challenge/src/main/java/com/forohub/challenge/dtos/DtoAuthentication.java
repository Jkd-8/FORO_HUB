package com.forohub.challenge.dtos;

import jakarta.validation.constraints.NotNull;

public record DtoAuthentication(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
