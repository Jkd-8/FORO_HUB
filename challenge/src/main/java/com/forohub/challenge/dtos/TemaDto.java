package com.forohub.challenge.dtos;

import com.forohub.challenge.models.Curso;
import com.forohub.challenge.models.Topic;

import java.time.LocalDateTime;

public record TemaDto(String titulo, String mensaje, LocalDateTime fechaDeCreacion,Curso curso) {
    public TemaDto(Topic topicc) {
              this(topicc.getTitulo(),topicc.getMensaje(),topicc.getFechaDeCreacion(),topicc.getCurso());
    }
}
