package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "consultaingresodet")
public class VistaIngresoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="idIngresoVehiculo")
    private Integer idIngresoVehiculo;
    private String fechaEntrada;
    private String horaEntrada;
    private String numRecibo;
    private String idCliente;
    private String nombreCliente;
    private String nombrePlanta;
    private String nombrePuerta;
    private String nombreOperador;
    private String placasVehiculo;
    private String nombreCiaTransporte;
    private String permisos;
    private String maniobra;
    private String tipofleje;
    private String fleje;
    private String vehiculo;
    private String transporte;
    private String tipoRecibo;
    private String incidenciaIngreso;
	private String entregadoA;
    
	public String getManiobra() {
		return maniobra;
	}
	public void setManiobra(String maniobra) {
		this.maniobra = maniobra;
	}
	public String getTipofleje() {
		return tipofleje;
	}
	public void setTipofleje(String tipofleje) {
		this.tipofleje = tipofleje;
	}
	public String getFleje() {
		return fleje;
	}
	public void setFleje(String fleje) {
		this.fleje = fleje;
	}
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getTransporte() {
		return transporte;
	}
	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}
	
    public String getPlacasVehiculo() {
		return placasVehiculo;
	}
	public void setPlacasVehiculo(String placasVehiculo) {
		this.placasVehiculo = placasVehiculo;
	}
	public String getNombreCiaTransporte() {
		return nombreCiaTransporte;
	}
	public void setNombreCiaTransporte(String nombreCiaTransporte) {
		this.nombreCiaTransporte = nombreCiaTransporte;
	}
	public String getNombreOperador() {
		return nombreOperador;
	}
	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}
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
	
    
	public String getPermisos() {
		return permisos;
	}
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}
	
	
	public String getTipoRecibo() {
		return tipoRecibo;
	}
	public void setTipoRecibo(String tipoRecibo) {
		this.tipoRecibo = tipoRecibo;
	}
	
	public String getIncidenciaIngreso() {
		return incidenciaIngreso;
	}
	public void setIncidenciaIngreso(String incidenciaIngreso) {
		this.incidenciaIngreso = incidenciaIngreso;
	}
	@Override
	public String toString() {
		return "VistaIngresoDetalle [idIngresoVehiculo=" + idIngresoVehiculo
				+ ", fechaEntrada=" + fechaEntrada + ", horaEntrada="
				+ horaEntrada + ", numRecibo=" + ", idCliente=" + idCliente + numRecibo + ", nombreCliente="
				+ nombreCliente + ", nombrePlanta=" + nombrePlanta
				+ ", nombrePuerta=" + nombrePuerta + ", nombreOperador="
				+ nombreOperador + ", placasVehiculo=" + placasVehiculo
				+ ", nombreCiaTransporte=" + nombreCiaTransporte
				+ ", permisos=" + permisos + ", maniobra=" + maniobra
				+ ", tipofleje=" + tipofleje
				+ ", fleje=" + fleje + ", vehiculo=" + vehiculo
				+ ", transporte=" + transporte + ", tipoRecibo=" + tipoRecibo
				+ "]";
	}


	public String getEntregadoA() {
		return entregadoA;
	}

	public void setEntregadoA(String entregadoA) {
		this.entregadoA = entregadoA;
	}
}