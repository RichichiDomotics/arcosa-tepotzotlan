package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ubicaciones")
@NamedQueries({
		@NamedQuery(name="Ubicaciones.getRevisionUbicacion",
				query="SELECT c FROM Ubicaciones c WHERE c.camara = :camara AND c.puerta = :puerta AND c.pasillo = :pasillo AND c.filaCalle = :filaCalle AND c.posicion = :posicion AND c.nivel = :nivel AND c.tipoTarima = :tipoTarima AND c.consecutivo = :consecutivo "),
		@NamedQuery(name="Ubicaciones.recUbicacion",
				//query="SELECT c.camara,c.puerta,c.pasillo, c.filaCalle,c.posicion,c.nivel, c.tipoTarima, c.consecutivo, SUM(d.cantidadInventario) , SUM(d.pesou),c.idUbica  FROM Ubicaciones c , Inventario d WHERE c.consecutivo <> d.consecutivo AND c.camara like :camara AND c.consecutivo is not('0') AND c.consecutivo like :consecutivo AND d.cantidadInventario > 0 GROUP BY c.consecutivo, c.camara ,c.posicion,c.nivel ORDER BY c.camara, c.consecutivo"),
		        query="SELECT c.camara,c.puerta,c.pasillo, c.filaCalle,c.posicion,c.nivel, c.tipoTarima, c.consecutivo, c.idUbica,c.idUbica ,c.idUbica  FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo is not('0') AND c.consecutivo like :consecutivo ORDER BY c.camara, c.consecutivo"),
		@NamedQuery(name="Ubicaciones.recUbicacionVacia",
				//query="SELECT c.camara,c.puerta,c.pasillo, c.filaCalle,c.posicion,c.nivel, c.tipoTarima, c.consecutivo, c.idUbica FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo = '0' GROUP BY c.camara ,c.posicion,c.nivel ORDER BY c.camara")
		        query="SELECT c.camara,c.puerta,c.pasillo, c.filaCalle,c.posicion,c.nivel, c.tipoTarima, c.consecutivo, c.idUbica FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo = '0' ORDER BY c.camara"),
		@NamedQuery(name="Ubicaciones.recConsolidados",
				query="SELECT c.camara, c.consecutivo , SUM(c.posicion) FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo>0 GROUP BY c.camara UNION SELECT c.camara, c.consecutivo, COUNT(c.camara) FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo=0 GROUP BY c.camaraORDER BY camara, consecutivo"),
		@NamedQuery(name="Ubicaciones.recConsolidadosVacio",
		        query="SELECT c.camara, c.consecutivo, SUM(c.posicion) FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo=0 GROUP BY c.camara UNION SELECT c.camara, c.consecutivo , COUNT(c.camara) FROM Ubicaciones c WHERE c.camara like :camara AND c.consecutivo>0 GROUP BY c.camara ORDER BY camara, consecutivo")
})

public class Ubicaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idUbica")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUbica;

    private String camara;
    private String puerta;
    private String pasillo;
	private String filaCalle;
	private Integer posicion;
	private Integer nivel;
	private String tipoTarima;
	private String consecutivo;

	@Override
	public String toString() {
		return "Ubicaciones [idUbica=" + getIdUbica() + ", camara="
				+ getCamara() + ", puerta=" + getPuerta() + ", pasillo=" + getPasillo() +
				", filaCalle="+ getFilaCalle() + ", posicion="+ getPosicion() +
				", nivel=" + getNivel() + ", tipoTarima=" + getTipoTarima() +
				", consecutivo=" + getConsecutivo() + "]";
	}


	public Integer getIdUbica() {
		return idUbica;
	}

	public void setIdUbica(Integer idUbica) {
		this.idUbica = idUbica;
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

	public String getFilaCalle() {
		return filaCalle;
	}

	public void setFilaCalle(String filaCalle) {
		this.filaCalle = filaCalle;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getTipoTarima() {
		return tipoTarima;
	}

	public void setTipoTarima(String tipoTarima) {
		this.tipoTarima = tipoTarima;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
}