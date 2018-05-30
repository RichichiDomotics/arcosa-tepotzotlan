package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_planta")
public class Planta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idPlanta")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPlanta;

    private String descripcion;

	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Planta [idPlanta=" + idPlanta + ", descripcion=" + descripcion
				+ "]";
	}
   
}