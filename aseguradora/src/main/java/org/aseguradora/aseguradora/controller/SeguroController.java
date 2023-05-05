package org.aseguradora.aseguradora.controller;

import jakarta.validation.Valid;
import org.aseguradora.aseguradora.dto.Seguro;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mensaje.Mensaje;
import org.aseguradora.aseguradora.services.SeguroServices;
import org.aseguradora.aseguradora.services.TomadorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeguroController {
    private SeguroServices seguroServices = SeguroServices.getInstance();

    @PostMapping("/seguro/create")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody Seguro seguro) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            seguroServices.create(seguro);
            mensajes.setId("0");
            mensajes.setMensaje("se creo el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setId("1");
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }

    @DeleteMapping("/seguro/delete")
    public ResponseEntity<Mensaje> delete(@Valid @RequestBody Seguro seguro) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            seguroServices.delete(seguro.getNmid());
            mensajes.setMensaje("se elimino el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }

    @GetMapping("/seguro")
    public ResponseEntity<ArrayList<Seguro>> getMostrar() throws  RuntimeException{
        ArrayList<Seguro> seguros;
        try{
            seguros = seguroServices.getAll();
        }catch (ServicesException ex){
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok( seguros );
    }
}
