package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contadores")
@NamedQueries({
		@org.hibernate.annotations.NamedQuery(name="Contadores.getXTipo",
				query="SELECT c FROM Contadores c where tipoContador = :tipoContador")
})

public class Contadores implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idContador")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idContador;

    private String tipoContador;
	private String contador;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Contadores [idContador=" + idContador + ", tipoContador=" + tipoContador + ", contador=" + contador
				+ "]";
	}

	public Integer getIdContador() {
		return idContador;
	}

	public void setIdContador(Integer idContador) {
		this.idContador = idContador;
	}
	public String getTipoContador() {
		return tipoContador;
	}

	public void setTipoContador(String tipoContador) {
		this.tipoContador = tipoContador;
	}

	public String getContador() {
		return contador;
	}

	public void setContador(String contador) {
		this.contador = contador;
	}

}