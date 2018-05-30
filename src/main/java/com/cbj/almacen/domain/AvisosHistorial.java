package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "avisos_historial")
@NamedQueries({
		@NamedQuery(name="AvisosHistorial.getXJefe",
				query="SELECT c FROM AvisosHistorial c where claveJefe = :claveJefe"),
		@NamedQuery(name="AvisosHistorial.getTotalAvisos",
				query="SELECT COUNT(c.idAviso) FROM AvisosHistorial c where c.visto = 0 AND claveJefe = :claveJefe GROUP BY c.visto"),
		@NamedQuery(name="AvisosHistorial.getAvisosLista",
				query="SELECT COUNT(c.idAviso), c.tipoNotificacion, c.idProspecto FROM AvisosHistorial c where c.visto = 0 AND claveJefe = :claveJefe GROUP BY c.tipoNotificacion"),
		@NamedQuery(name="AvisosHistorial.getAvisosListaDetalle",
				query="SELECT c.idProspecto,d.razonSocial,c.tipoNotificacion,c.fecha FROM AvisosHistorial c, Prospecto d  WHERE c.idProspecto = d.idProspecto AND c.visto = 0 AND  claveJefe = :claveJefe"),
		@NamedQuery(name="AvisosHistorial.updateAvisosHistorialXNotificacion",
				query="UPDATE AvisosHistorial c SET c.visto = 1 WHERE c.visto = 0 AND  c.tipoNotificacion = :tipoNotificacion")
})

public class AvisosHistorial implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idAviso")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAviso;

    private Integer idProspecto;
	private String tipoNotificacion;
	private String claveJefe;
	private String claveEjecutivo;
	private Integer visto;
	private String fecha;
	private String observacion;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AvisosHistorial [idAviso=" + getIdAviso() + ", idProspecto=" + getIdProspecto() + ", tipoNotificacion=" + getTipoNotificacion()
				+ ", claveJefe=" + getClaveJefe() + ", claveEjecutivo=" + getClaveEjecutivo() + ", visto=" + getVisto()
				+ ", fecha=" + getFecha()
				+ "]";
	}


	public Integer getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}

	public Integer getIdProspecto() {
		return idProspecto;
	}

	public void setIdProspecto(Integer idProspecto) {
		this.idProspecto = idProspecto;
	}

	public String getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public String getClaveJefe() {
		return claveJefe;
	}

	public void setClaveJefe(String claveJefe) {
		this.claveJefe = claveJefe;
	}

	public String getClaveEjecutivo() {
		return claveEjecutivo;
	}

	public void setClaveEjecutivo(String claveEjecutivo) {
		this.claveEjecutivo = claveEjecutivo;
	}

	public Integer getVisto() {
		return visto;
	}

	public void setVisto(Integer visto) {
		this.visto = visto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}