package org.aseguradora.aseguradora.dto;

import java.util.Date;

public class Siniestro {
    private Tomador to;
    private Integer nmid;
    private String tipo_siniestro;
    private Date f_siniestro;
    private String lugar;

    public Tomador getTo() {
        return to;
    }

    public void setTo(Tomador to) {
        this.to = to;
    }

    public Integer getNmid() {
        return nmid;
    }

    public void setNmid(Integer nmid) {
        this.nmid = nmid;
    }

    public String getTipo_siniestro() {
        return tipo_siniestro;
    }

    public void setTipo_siniestro(String tipo_siniestro) {
        this.tipo_siniestro = tipo_siniestro;
    }

    public Date getF_siniestro() {
        return f_siniestro;
    }

    public void setF_siniestro(Date f_siniestro) {
        this.f_siniestro = f_siniestro;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
