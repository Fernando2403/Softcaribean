package org.aseguradora.aseguradora.controller;

import jakarta.validation.Valid;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mensaje.Mensaje;
import org.aseguradora.aseguradora.services.TomadorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLException.*;
import java.util.ArrayList;
import java.util.ArrayList.*;

@RestController
@RequestMapping("/api")
public class TomadorController {
    private TomadorServices tomadorServices = TomadorServices.getInstance();
@CrossOrigin(origins = "htpp://localhost:80")
    @PostMapping("/tomador/create")
    public ResponseEntity<Tomador> create(@Valid @RequestBody Tomador tomador) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            tomadorServices.create(tomador);
            mensajes.setId("0");
            mensajes.setMensaje("se creo el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setId("1");
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( tomador );
    }
    @CrossOrigin(origins = "htpp://localhost:80")
    @DeleteMapping("/tomador/{id}")
    public ResponseEntity<Mensaje> delete(@Valid @RequestBody int id) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            tomadorServices.delete(id);
            mensajes.setMensaje("se elimino el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }
    @CrossOrigin(origins = "htpp://localhost:80")
    @GetMapping("/tomador/mostrar")
    public ResponseEntity<Mensaje> getMostrar() throws  RuntimeException{
        ArrayList<Tomador> tomadores;
        Mensaje mensajes = new Mensaje();
        try{
           tomadores = tomadorServices.getAll();
            mensajes.setId("0");
           mensajes.setMensaje("se mostro el dato con éxito");
           mensajes.setDato(tomadores);
        }catch (ServicesException ex){
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            mensajes.setId("1");
            mensajes.setMensaje("fallo "+e.getMessage());
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok( mensajes );
    }
}
