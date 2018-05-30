package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "form_entrada")
public class FormEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idFormEntrada")
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer idFormEntrada;
    @Min(0)
	private Integer idCliente;
	private String fechaEntrada;
    private String horaEntrada;
    private Integer idTipoRecibo;
    private Integer idPlanta;
    private Integer idPuerta;
    private String nombreOperador;
    private Integer idManiobra;
    private String placasVehiculo;
    private Integer idFleje;
    private String fleje;
    private Integer idVehiculo;
    private Integer idTransporte;
    private String nombreCiaTransporte;
    private String numRecibo;
    private String permisos;
    private String status;
    
    
    public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getFleje() {
		return fleje;
	}



	public void setFleje(String fleje) {
		this.fleje = fleje;
	}

	public String getPermisos() {
		return permisos;
	}



	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}



	public Integer getIdFormEntrada() {
		return idFormEntrada;
	}



	public void setIdFormEntrada(Integer idFormEntrada) {
		this.idFormEntrada = idFormEntrada;
	}



	public Integer getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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



	public Integer getIdTipoRecibo() {
		return idTipoRecibo;
	}



	public void setIdTipoRecibo(Integer idTipoRecibo) {
		this.idTipoRecibo = idTipoRecibo;
	}



	public Integer getIdPlanta() {
		return idPlanta;
	}



	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}



	public Integer getIdPuerta() {
		return idPuerta;
	}



	public void setIdPuerta(Integer idPuerta) {
		this.idPuerta = idPuerta;
	}



	public String getNombreOperador() {
		return nombreOperador;
	}



	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}



	public Integer getIdManiobra() {
		return idManiobra;
	}



	public void setIdManiobra(Integer idManiobra) {
		this.idManiobra = idManiobra;
	}



	public String getPlacasVehiculo() {
		return placasVehiculo;
	}



	public void setPlacasVehiculo(String placasVehiculo) {
		this.placasVehiculo = placasVehiculo;
	}



	public Integer getIdFleje() {
		return idFleje;
	}



	public void setIdFleje(Integer idFleje) {
		this.idFleje = idFleje;
	}



	public Integer getIdVehiculo() {
		return idVehiculo;
	}



	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}



	public Integer getIdTransporte() {
		return idTransporte;
	}



	public void setIdTransporte(Integer idTransporte) {
		this.idTransporte = idTransporte;
	}



	public String getNombreCiaTransporte() {
		return nombreCiaTransporte;
	}



	public void setNombreCiaTransporte(String nombreCiaTransporte) {
		this.nombreCiaTransporte = nombreCiaTransporte;
	}



	public String getNumRecibo() {
		return numRecibo;
	}



	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "FormEntrada [idFormEntrada=" + idFormEntrada + ", idCliente="
				+ idCliente + ", fechaEntrada=" + fechaEntrada
				+ ", horaEntrada=" + horaEntrada + ", idTipoRecibo="
				+ idTipoRecibo + ", idPlanta=" + idPlanta + ", idPuerta="
				+ idPuerta + ", nombreOperador=" + nombreOperador
				+ ", idManiobra=" + idManiobra + ", placasVehiculo="
				+ placasVehiculo + ", idFleje=" + idFleje + ", fleje=" + fleje
				+ ", idVehiculo=" + idVehiculo + ", idTransporte="
				+ idTransporte + ", nombreCiaTransporte=" + nombreCiaTransporte
				+ ", numRecibo=" + numRecibo + ", permisos=" + permisos
				+ ", status=" + status + "]";
	}



	

   
}