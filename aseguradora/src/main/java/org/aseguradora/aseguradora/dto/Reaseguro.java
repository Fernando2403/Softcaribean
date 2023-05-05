package org.aseguradora.aseguradora.dto;

public class Reaseguro {
    private Integer nmid;
    private String nit;
    private String razon_social;
    private Double monto_seguro;
    private Double porcentaje_cober;

    public Integer getNmid() {
        return nmid;
    }

    public void setNmid(Integer nmid) {
        this.nmid = nmid;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public Double getMonto_seguro() {
        return monto_seguro;
    }

    public void setMonto_seguro(Double monto_seguro) {
        this.monto_seguro = monto_seguro;
    }

    public Double getPorcentaje_cober() {
        return porcentaje_cober;
    }

    public void setPorcentaje_cober(Double porcentaje_cober) {
        this.porcentaje_cober = porcentaje_cober;
    }
}
