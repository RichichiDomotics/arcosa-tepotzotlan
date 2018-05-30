package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_general")
public class CatalogoGeneral implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idCatalogo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCatalogo;

    private String descripcion;
    private String tipo;
    private String estatus;
    
    public Integer getIdCatalogo() {
		return idCatalogo;
	}
    public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
    public String getDescripcion() {
		return descripcion;
	}
    public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    public String getTipo() {
		return tipo;
	}
    public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    public String getEstatus() {
		return estatus;
	}
    public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	@Override
	public String toString() {
		return "CatalogoGeneral [idCatalogo=" + idCatalogo + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", estatus=" + estatus + "]";
	}
    

}