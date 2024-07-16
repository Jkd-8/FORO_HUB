package com.forohub.challenge.models;

import com.forohub.challenge.dtos.ActualizarTemas;
import com.forohub.challenge.dtos.RegistrarTemas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topic(RegistrarTemas registrarTemas) {

        this.titulo = registrarTemas.titulo();
        this.mensaje =  registrarTemas.mensaje();

        this.curso = registrarTemas.curso();
    }

    public void actualizarTemas(ActualizarTemas actualizarTemas){
        if (actualizarTemas.titulo()!= null){
            this.titulo = actualizarTemas.titulo();
        }

        if (actualizarTemas.mensaje()!= null){
            this.mensaje = actualizarTemas.mensaje();
        }
    }

}
