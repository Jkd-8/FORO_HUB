package com.forohub.challenge.dtos;

import jakarta.validation.constraints.NotBlank;

public record DatosUsuario(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
