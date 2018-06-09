package com.cbj.almacen.domain;

import com.cbj.almacen.Utils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "consultaingreso")
@NamedQueries({
        @NamedQuery(name="VistaIngreso.findByStatusIngreso",
                query="SELECT c FROM VistaIngreso c where c.status = :status and c.tipoMovimiento='"+ Utils.TIPO_MOVIMIENTO_ENTRADA+"' and c.idCliente not in (550, 551, 552, 606, 650, 1550, 1551, 1650, 1651, 2550)"),
        @NamedQuery(name="VistaIngreso.findByStatusSalida",
                query="SELECT c FROM VistaIngreso c where c.status = :status and c.tipoMovimiento='"+ Utils.TIPO_MOVIMIENTO_SALIDA+"'")
})
public class VistaIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="idIngresoVehiculo")
    private Integer idIngresoVehiculo;
    private String fechaEntrada;
    private String horaEntrada;
    private String numRecibo;
    private Integer idCliente;
    private String nombreCliente;
    private String nombrePlanta;
    private String nombrePuerta;
    private String tipoMovimiento;
    private String status;
	
    public Integer getIdIngresoVehiculo() {
		return idIngresoVehiculo;
	}
	public void setIdIngresoVehiculo(Integer idIngresoVehiculo) {
		this.idIngresoVehiculo = idIngresoVehiculo;
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
	public String getNumRecibo() {
		return numRecibo;
	}
	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNombrePlanta() {
		return nombrePlanta;
	}
	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}
	public String getNombrePuerta() {
		return nombrePuerta;
	}
	public void setNombrePuerta(String nombrePuerta) {
		this.nombrePuerta = nombrePuerta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Cliente [idIngresoVehiculo=" + idIngresoVehiculo + ", fechaEntrada="
				+ fechaEntrada + ", horaEntrada=" + horaEntrada
				+ ", numRecibo=" + numRecibo + ", nombreCliente="
				+ nombreCliente + ", nombrePlanta=" + nombrePlanta
				+ ", nombrePuerta=" + nombrePuerta + "]";
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}