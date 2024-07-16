package com.forohub.challenge.controller;


import com.forohub.challenge.dtos.DtoAuthentication;
import com.forohub.challenge.dtos.DtoToken;
import com.forohub.challenge.infra.seguridad.SecurityConfigurations;
import com.forohub.challenge.infra.seguridad.TokenService;
import com.forohub.challenge.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/logins")
public class AuthenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DtoAuthentication dtoAuthentication){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dtoAuthentication.username(),dtoAuthentication.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("Manda  generar un Token");
        var tokenJWT = tokenService.generarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DtoToken(tokenJWT));
    }

}
