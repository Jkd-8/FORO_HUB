package com.forohub.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elysia")
public class Ellie {
        @GetMapping
        public String helloElysia(){
            return "Hello Elysia my beloved, i love so much";
        }
}
