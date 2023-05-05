package org.aseguradora.aseguradora.controller;

import jakarta.validation.Valid;
import org.aseguradora.aseguradora.dto.Pagos;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mensaje.Mensaje;
import org.aseguradora.aseguradora.services.PagosServices;
import org.aseguradora.aseguradora.services.TomadorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
@RestController
@RequestMapping("/api-seguros")
public class PagosController {

    private PagosServices pagosServices = PagosServices.getInstance();

    @PostMapping("/pagos/create")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody Pagos pagos) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            pagosServices.CreatePagos(pagos);
            mensajes.setId("0");
            mensajes.setMensaje("se creo el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setId("1");
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }

    @DeleteMapping("/pagos/delete")
    public ResponseEntity<Mensaje> delete(@Valid @RequestBody Pagos pagos) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            pagosServices.delete(pagos.getNmid());
            mensajes.setMensaje("se elimino el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }

    @GetMapping("/pagos")
    public ResponseEntity<ArrayList<Pagos>> getMostrar() throws  RuntimeException{
        ArrayList<Pagos> pagos;
        try{
            pagos = pagosServices.getAll();
        }catch (ServicesException ex){
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok( pagos );
    }
}
