package org.aseguradora.aseguradora.dto;

import java.util.Date;

public class Seguro {
    private Tomador tom;
    private Reaseguro re;
    private Integer nmid;
    private  String tipo_seguro;
    private Double valor;
    private String descripcion;
    private Date f_inicial;

    public Tomador getTom() {
        return tom;
    }

    public void setTom(Tomador tom) {
        this.tom = tom;
    }

    public Reaseguro getRe() {
        return re;
    }

    public void setRe(Reaseguro re) {
        this.re = re;
    }

    public Integer getNmid() {
        return nmid;
    }

    public void setNmid(Integer nmid) {
        this.nmid = nmid;
    }

    public String getTipo_seguro() {
        return tipo_seguro;
    }

    public void setTipo_seguro(String tipo_seguro) {
        this.tipo_seguro = tipo_seguro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getF_inicial() {
        return f_inicial;
    }

    public void setF_inicial(Date f_inicial) {
        this.f_inicial = f_inicial;
    }

    public Date getF_final() {
        return f_final;
    }

    public void setF_final(Date f_final) {
        this.f_final = f_final;
    }

    private Date f_final;


}
