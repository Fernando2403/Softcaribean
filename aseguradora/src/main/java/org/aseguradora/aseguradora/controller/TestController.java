package org.aseguradora.aseguradora.controller;

import org.aseguradora.aseguradora.mensaje.Mensaje;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-seguros")
public class TestController {

    @GetMapping("/test/getTest/{id}")
    public ResponseEntity<Mensaje> getTest(@PathVariable String id) {
        Mensaje mensajes = new Mensaje();
        mensajes.setMensaje("Esto es una prueba");
        return ResponseEntity.ok( mensajes );
    }

}