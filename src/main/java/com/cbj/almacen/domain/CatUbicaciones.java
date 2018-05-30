package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "catUbicaciones")
@NamedQueries({
		@NamedQuery(name="CatUbicaciones.getCamaras",
				query="SELECT DISTINCT(c.camara) FROM CatUbicaciones c GROUP BY c.camara"),
		@NamedQuery(name="CatUbicaciones.getPuertas",
				query="SELECT DISTINCT(c.puerta) FROM CatUbicaciones c WHERE c.camara = :camara GROUP BY c.puerta"),
		@NamedQuery(name="CatUbicaciones.getPasillos",
				query="SELECT DISTINCT(c.pasillo) FROM CatUbicaciones c WHERE c.puerta = :puerta AND c.camara = :camara GROUP BY c.pasillo"),
		@NamedQuery(name="CatUbicaciones.getFilas",
				query="SELECT DISTINCT(c.filaCalle) FROM CatUbicaciones c WHERE c.pasillo = :pasillo AND c.puerta = :puerta AND c.camara = :camara GROUP BY c.filaCalle"),
		@NamedQuery(name="CatUbicaciones.getPosiciones",
				query="SELECT DISTINCT(c.posiciones) FROM CatUbicaciones c WHERE c.pasillo = :pasillo AND c.puerta = :puerta AND c.camara = :camara AND c.filaCalle = :fila GROUP BY c.posiciones"),
		@NamedQuery(name="CatUbicaciones.getNiveles",
				query="SELECT DISTINCT(c.niveles) FROM CatUbicaciones c WHERE c.pasillo = :pasillo AND c.puerta = :puerta AND c.camara = :camara AND c.filaCalle = :fila GROUP BY c.niveles"),
})
public class CatUbicaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idUbicacion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUbicacion;

    private String camara;
    private String puerta;
    private String pasillo;
	private Integer totalFilas;
	private String filaCalle;
	private Integer posiciones;
	private Integer niveles;

	@Override
	public String toString() {
		return "CatUbicaciones [idUbicacion=" + getIdUbicacion() + ", camara="
				+ getCamara() + ", puerta=" + getPuerta() + ", pasillo=" + getPasillo() + "totalFilas="+ getTotalFilas() +
				", filaCalle="+ getFilaCalle() + ", posiciones="+ getPosiciones() +
				", niveles=" + getNiveles() + "]";
	}


	public Integer getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Integer idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getCamara() {
		return camara;
	}

	public void setCamara(String camara) {
		this.camara = camara;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getPasillo() {
		return pasillo;
	}

	public void setPasillo(String pasillo) {
		this.pasillo = pasillo;
	}

	public Integer getTotalFilas() {
		return totalFilas;
	}

	public void setTotalFilas(Integer totalFilas) {
		this.totalFilas = totalFilas;
	}

	public String getFilaCalle() {
		return filaCalle;
	}

	public void setFilaCalle(String filaCalle) {
		this.filaCalle = filaCalle;
	}

	public Integer getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(Integer posiciones) {
		this.posiciones = posiciones;
	}

	public Integer getNiveles() {
		return niveles;
	}

	public void setNiveles(Integer niveles) {
		this.niveles = niveles;
	}
}