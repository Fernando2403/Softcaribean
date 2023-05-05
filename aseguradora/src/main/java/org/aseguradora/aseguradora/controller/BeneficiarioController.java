package org.aseguradora.aseguradora.controller;

import jakarta.validation.Valid;
import org.aseguradora.aseguradora.dto.Beneficiario;
import org.aseguradora.aseguradora.dto.Tomador;
import org.aseguradora.aseguradora.exception.ServicesException;
import org.aseguradora.aseguradora.mensaje.Mensaje;
import org.aseguradora.aseguradora.services.BeneficiarioServices;
import org.aseguradora.aseguradora.services.TomadorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api-seguros")
public class BeneficiarioController {

    private BeneficiarioServices beneficiarioServices = BeneficiarioServices.getInstance();

    @PostMapping("/beneficiario/create")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody Beneficiario beneficiario) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            beneficiarioServices.create(beneficiario);
            mensajes.setId("0");
            mensajes.setMensaje("se creo el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setId("1");
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }

    @DeleteMapping("/beneficiario/delete")
    public ResponseEntity<Mensaje> delete(@Valid @RequestBody Beneficiario beneficiario) throws  RuntimeException{
        Mensaje mensajes = new Mensaje();
        try{
            beneficiarioServices.delete(beneficiario.getNmid());
            mensajes.setMensaje("se elimino el dato con éxito");
        }catch (ServicesException ex){
            mensajes.setMensaje("fallo "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok( mensajes );
    }

    @GetMapping("/beneficiario")
    public ResponseEntity<ArrayList<Beneficiario>> getMostrar() throws  RuntimeException{
        ArrayList<Beneficiario> beneficiarios;
        try{
            beneficiarios = beneficiarioServices.getAll();
        }catch (ServicesException ex){
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok( beneficiarios );
    }
}
