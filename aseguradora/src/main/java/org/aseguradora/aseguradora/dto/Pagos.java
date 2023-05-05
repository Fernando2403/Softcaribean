package org.aseguradora.aseguradora.dto;

import java.util.Date;

public class Pagos {
  private Seguro se;
  private Integer nmid;
  private Date f_pago;
  private Integer cuotas;
  private Double valor_cmes;

    public Seguro getSe() {
        return se;
    }

    public void setSe(Seguro se) {
        this.se = se;
    }

    public Integer getNmid() {
        return nmid;
    }

    public void setNmid(Integer nmid) {
        this.nmid = nmid;
    }

    public Date getF_pago() {
        return f_pago;
    }

    public void setF_pago(Date f_pago) {
        this.f_pago = f_pago;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Double getValor_cmes() {
        return valor_cmes;
    }

    public void setValor_cmes(Double valor_cmes) {
        this.valor_cmes = valor_cmes;
    }


}
