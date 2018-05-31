package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@XmlRootElement
@Entity
@Table(name = "convenios")
@NamedQueries({
		@NamedQuery(name="Convenios.getByClientes",
            query="SELECT c FROM Convenios c where c.idCliente = :idCliente"),
		@NamedQuery(name="Convenios.getByCliente",
				query="SELECT c FROM Convenios c where c.idCliente = :idCliente"),
		@NamedQuery(name="Convenios.getAll",
				query="SELECT c FROM Convenios c"),
		@NamedQuery(name="Convenios.getByClaveProducto",
				query="SELECT c FROM Convenios c where c.claveProducto = :claveProducto"),
		@NamedQuery(name = "Convenios.getArla",
			query = "Select c from Convenios c where c.idCliente in ( :clientes )")
})
public class Convenios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idConvenio")
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Integer idConvenio;
    private Integer idCliente;
	private String claveProducto;
    private String producto;
    private String tipo;
    private int valor;
    private String cuotaAlmacenaje;
    private String periodo;
    private String minimo;
    private String congelacion;
    private float mentysal;
    private float mespeciales;
    private float partidaMinima;
    private String activa;

	public Integer getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Integer idConvenio) {
		this.idConvenio = idConvenio;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getClaveProducto() {
		return claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getCuotaAlmacenaje() {
		return cuotaAlmacenaje;
	}

	public void setCuotaAlmacenaje(String cuotaAlmacenaje) {
		this.cuotaAlmacenaje = cuotaAlmacenaje;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getMinimo() {
		return minimo;
	}

	public void setMinimo(String minimo) {
		this.minimo = minimo;
	}

	public String getCongelacion() {
		return congelacion;
	}

	public void setCongelacion(String congelacion) {
		this.congelacion = congelacion;
	}

	public float getMentysal() {
		return mentysal;
	}

	public void setMentysal(float mentysal) {
		this.mentysal = mentysal;
	}

	public float getMespeciales() {
		return mespeciales;
	}

	public void setMespeciales(float mespeciales) {
		this.mespeciales = mespeciales;
	}

	public float getPartidaMinima() {
		return partidaMinima;
	}

	public void setPartidaMinima(float partidaMinima) {
		this.partidaMinima = partidaMinima;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	@Override
	public String toString() {
		return "Convenio [idConvenio=" + getIdConvenio() + "idCliente=" + getIdCliente()    
				+ ", claveProducto=" + getClaveProducto() + ", producto="
				+ getProducto() + ", tipo="
				+ getTipo() + ", valor="
				+ getValor() + ", cuotaAlmacenaje=" + getCuotaAlmacenaje()
				+ ", periodo=" + getPeriodo() + ", minimo=" + getMinimo()
				+ ", congelacion=" + getCongelacion() + ", mentysal="
				+ getMentysal() + ", mespeciales=" + getMespeciales()
				+ ", partidaMinima=" + getPartidaMinima()
				+ ", activa=" + getActiva() + "]";
	}
}