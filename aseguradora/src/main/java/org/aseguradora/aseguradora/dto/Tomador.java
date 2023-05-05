package org.aseguradora.aseguradora.dto;

import java.util.Date;

public class Tomador {
     private Integer nmid;
     private String   documento;
     private String tipo_doc;
     private String nombre;
     private String apellido;
     private String direccion;
     private String telefono;
     private String ocupacion;
     private String correo;
     private Date f_naci;

     public Integer getNmid() {
          return nmid;
     }

     public void setNmid(Integer nmid) {
          this.nmid = nmid;
     }

     public String getDocumento() {
          return documento;
     }

     public void setDocumento(String documento) {
          this.documento = documento;
     }

     public String getTipo_doc() {
          return tipo_doc;
     }

     public void setTipo_doc(String tipo_doc) {
          this.tipo_doc = tipo_doc;
     }

     public String getNombre() {
          return nombre;
     }

     public void setNombre(String nombre) {
          this.nombre = nombre;
     }

     public String getApellido() {
          return apellido;
     }

     public void setApellido(String apellido) {
          this.apellido = apellido;
     }

     public String getDireccion() {
          return direccion;
     }

     public void setDireccion(String direccion) {
          this.direccion = direccion;
     }

     public String getTelefono() {
          return telefono;
     }

     public void setTelefono(String telefono) {
          this.telefono = telefono;
     }

     public String getOcupacion() {
          return ocupacion;
     }

     public void setOcupacion(String ocupacion) {
          this.ocupacion = ocupacion;
     }

     public String getCorreo() {
          return correo;
     }

     public void setCorreo(String correo) {
          this.correo = correo;
     }

     public Date getF_naci() {
          return f_naci;
     }

     public void setF_naci(Date f_naci) {
          this.f_naci = f_naci;
     }
}
