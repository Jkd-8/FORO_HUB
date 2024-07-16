package com.forohub.challenge.controller;

import com.forohub.challenge.dtos.DatosUsuario;
import com.forohub.challenge.models.User;
import com.forohub.challenge.repositorios.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsers {
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public void registrarUsuario(@RequestBody @Valid DatosUsuario datosUsuario){
        String encodedPassword = new BCryptPasswordEncoder().encode(datosUsuario.password());
        User user = new User(new DatosUsuario(datosUsuario.username(),encodedPassword));
        userRepository.save(user);
    }
}
