package com.cbj.almacen.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jolvera on 22/07/2014.
 */
@Entity
@Table(name = "controltunel")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="ControlTunel.getAll",
                query="SELECT c FROM ControlTunel c"),
        @org.hibernate.annotations.NamedQuery(name="ControlTunel.getIdFolio",
                query="SELECT c FROM ControlTunel c where c.folioAsignado= :folioAsignado")
})
public class ControlTunel  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "folioAsignado")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer folioAsignado;
    private String fecha;
    private String periodo;
    private String idCliente;
    private String nombreCliente;
    private float kilos;
    private String solicitante;
    private String observaciones;
    private String fechaCapturada;

    public Integer getFolioAsignado() {
        return folioAsignado;
    }

    public void setFolioAsignado(Integer folioAsignado) {
        this.folioAsignado = folioAsignado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public float getKilos() {
        return kilos;
    }

    public void setKilos(float kilos) {
        this.kilos = kilos;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaCapturada() {
        return fechaCapturada;
    }

    public void setFechaCapturada(String fechaCapturada) {
        this.fechaCapturada = fechaCapturada;
    }
}
