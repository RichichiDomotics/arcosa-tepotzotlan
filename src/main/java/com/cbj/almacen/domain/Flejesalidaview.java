package com.cbj.almacen.domain;

/**
 * Created by Ricardo on 01/09/2015.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "flejesalidaview")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Flejesalidaview.getFlejeSalida",
                query="SELECT c FROM Flejesalidaview c")
})
public class Flejesalidaview implements  Serializable{
    @Id
    @Column(name = "folioSalida")
    private Integer folioSalida;
    private Integer idCliente;
    private String consecutivo;
    private String fleje;
    private String tipoFleje;
    private String fechaEntrada;
    private String horaEntrada;
    private String tipoRecibo;
    private String fechaSalidaVehiculo;
    private String fechaSalida_salidas;
    private Integer idIngresoVehiculo;

    public Integer getFolioSalida() {
        return folioSalida;
    }

    public void setFolioSalida(Integer folioSalida) {
        this.folioSalida = folioSalida;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getFleje() {
        return fleje;
    }

    public void setFleje(String fleje) {
        this.fleje = fleje;
    }

    public String getTipoFleje() {
        return tipoFleje;
    }

    public void setTipoFleje(String tipoFleje) {
        this.tipoFleje = tipoFleje;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(String tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public String getFechaSalidaVehiculo() {
        return fechaSalidaVehiculo;
    }

    public void setFechaSalidaVehiculo(String fechaSalidaVehiculo) {
        this.fechaSalidaVehiculo = fechaSalidaVehiculo;
    }

    public String getFechaSalida_salidas() {
        return fechaSalida_salidas;
    }

    public void setFechaSalida_salidas(String fechaSalida_salidas) {
        this.fechaSalida_salidas = fechaSalida_salidas;
    }

    public Integer getIdIngresoVehiculo() {
        return idIngresoVehiculo;
    }

    public void setIdIngresoVehiculo(Integer idIngresoVehiculo) {
        this.idIngresoVehiculo = idIngresoVehiculo;
    }
}
