package org.aseguradora.aseguradora.dto;

import java.util.Date;

public class Beneficiario {
    private Tomador nmid_tomador;
    private Integer nmid;
    private String tipo_doc;
    private String documento;
    private String nombre;
    private String apellido;
    private Date f_naci;
    private String parentezco;
    private String ocupacion;
    private String direccion;
    private String telefono;
    private String correo;
    private Double porcentaje_afi;
    private String nombre_banco;
    private Integer numero_cuenta;

    public Tomador getNmid_tomador() {
        return nmid_tomador;
    }

    public void setNmid_tomador(Tomador nmid_tomador) {
        this.nmid_tomador = nmid_tomador;
    }

    public Integer getNmid() {
        return nmid;
    }

    public void setNmid(Integer nmid) {
        this.nmid = nmid;
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

    public Date getF_naci() {
        return f_naci;
    }

    public void setF_naci(Date f_naci) {
        this.f_naci = f_naci;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getPorcentaje_afi() {
        return porcentaje_afi;
    }

    public void setPorcentaje_afi(Double porcentaje_afi) {
        this.porcentaje_afi = porcentaje_afi;
    }

    public String getNombre_banco() {
        return nombre_banco;
    }

    public void setNombre_banco(String nombre_banco) {
        this.nombre_banco = nombre_banco;
    }

    public Integer getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(Integer numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
