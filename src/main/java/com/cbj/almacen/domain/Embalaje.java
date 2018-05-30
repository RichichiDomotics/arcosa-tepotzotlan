package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "embalaje")
public class Embalaje implements Serializable {

    //private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String idEmbalaje;
    private String descripcion;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}

    public String getIdEmbalaje() {
		return idEmbalaje;
	}
    public void setIdEmbalaje(String idEmbalaje) {
		this.idEmbalaje = idEmbalaje;
	}
    public String getDescripcion() {
		return descripcion;
	}
    public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Embalajes [id=" + id + "idEmbalaje=" + idEmbalaje + ", descripcion="
				+ descripcion + "]";
	}


}