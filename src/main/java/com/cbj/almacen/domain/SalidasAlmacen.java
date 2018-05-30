package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by jolvera on 02/06/2014.
 */
@Entity
@Table(name = "salidas_almacen")
@NamedQueries({
        @NamedQuery(name="SalidasAlmacen.getAll",
                query="SELECT c FROM SalidasAlmacen c"),
        @NamedQuery(name="SalidasAlmacen.getByClienteConsecutivo",
                query="SELECT c FROM SalidasAlmacen c where c.idCliente = :idCliente and c.consecutivo = :consecutivo order by c.folioSalida ASC"),
        @NamedQuery(name="SalidasAlmacen.getByFolioSalida",
                query="SELECT c FROM SalidasAlmacen c where c.folioSalida = :folioSalida")
})

public class SalidasAlmacen implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer folioSalida;
    private Integer consecutivo;
    @Min(0)
    private String idCliente;
    private String fechaSalida;
    private String entregara;
    private String observaciones;
    private String camara;
    private String solicitante;
    private String transporte;
    private String vehiculo;
    private String hora;
    private String documentacion;
    private String placas;
    private String autorizacion;


    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getFolioSalida() {
        return folioSalida;
    }

    public void setFolioSalida(Integer folioSalida) {
        this.folioSalida = folioSalida;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEntregara() {
        return entregara;
    }

    public void setEntregara(String entregara) {
        this.entregara = entregara;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }
}
