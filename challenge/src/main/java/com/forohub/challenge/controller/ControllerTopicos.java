package com.forohub.challenge.controller;

import com.forohub.challenge.dtos.ActualizarTemas;
import com.forohub.challenge.dtos.RegistrarTemas;
import com.forohub.challenge.dtos.TemaDto;
import com.forohub.challenge.models.Topic;
import com.forohub.challenge.repositorios.TopicRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class ControllerTopicos {


    @Autowired
    TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopicos(@RequestBody @Valid RegistrarTemas registrarTemas, UriComponentsBuilder uriComponentsBuilder){
        var topico = new Topic(registrarTemas);
        topicRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegistrarTemas(topico.getTitulo(), topico.getMensaje(),topico.getCurso()));
    }

    @GetMapping("/{id}")
    public ResponseEntity temaDto(@PathVariable Long id){
        var topicc = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new TemaDto(topicc));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTemas( @PathVariable Long id, @RequestBody @Valid ActualizarTemas actualizarTemas){
        var topic = topicRepository.getReferenceById(id);
        topic.actualizarTemas(actualizarTemas);
        return ResponseEntity.ok(new TemaDto(topic));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTema(@PathVariable Long id ){
        Topic topic = topicRepository.findById(id).orElse(null);
        if (topic == null){
            return ResponseEntity.noContent().build();
        }else {
            topicRepository.delete(topic);
            return ResponseEntity.status(HttpStatus.OK).body("El topico ya fue eliminado ");
        }

    }

}
