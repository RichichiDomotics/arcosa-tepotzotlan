package com.cbj.almacen.domain;

/**
 * Created by Ricardo on 29/03/2016.
 */
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.type.DoubleType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "continuidades")
@NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Continuidades.getAll",
                query="SELECT c.RD, c.INICIO from Continuidades c where c.INICIO >= :fechaini and c.INICIO <= :fechafin"),
        @org.hibernate.annotations.NamedQuery(name="Continuidades.getByConsecutivo",
                query="SELECT c from Continuidades c where RD = :consecutivo")
})

public class Continuidades implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int RD;
   // private String DIAREVISION;
    private String CLIENTE;
    private String INICIO;
    private String FIN;
    private double CUOTA;
    private int PERIODO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRD() {
        return RD;
    }

    public void setRD(int RD) {
        this.RD = RD;
    }

   /* public String getDIAREVISION() {
        return DIAREVISION;
    }*/

   /* public void setDIAREVISION(String DIAREVISION) {
        this.DIAREVISION = DIAREVISION;
    }*/

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getINICIO() {
        return INICIO;
    }

    public void setINICIO(String INICIO) {
        this.INICIO = INICIO;
    }

    public String getFIN() {
        return FIN;
    }

    public void setFIN(String FIN) {
        this.FIN = FIN;
    }

    public double getCUOTA() {
        return CUOTA;
    }

    public void setCUOTA(double CUOTA) {
        this.CUOTA = CUOTA;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }
}
