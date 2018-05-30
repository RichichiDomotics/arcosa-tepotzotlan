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
import javax.validation.constraints.Min;

@Entity
@Table(name = "preVehiculo")
@NamedQueries({
        @NamedQuery(name="PreVehiculo.getPorDia",
                query="SELECT c,l FROM PreVehiculo c, Clientes l where c.fechaEntrada = CURRENT_DATE and l.ID_CLIENTE = c.idCliente and c.estatus=1"),
        @NamedQuery(name="preVehiculo.getPorDiaCliente",
                query="SELECT c,l FROM PreVehiculo c, Clientes l where c.fechaEntrada = CURRENT_DATE and l.ID_CLIENTE = c.idCliente and c.idCliente like :idCliente"),
        @NamedQuery(name="PreVehiculo.getVehiculoByCliente",
        		query="SELECT c FROM PreVehiculo c where c.idCliente = :idCliente order by c.idPreVehiculo ASC"),
        @NamedQuery(name="PreVehiculo.getInOutVehiculo",
				query="SELECT cl.ID_CLIENTE,cl.NOM_CLIENTE,v.nombreOperador, v.placasVehiculo, v.fechaEntrada, v.horaEntrada FROM PreVehiculo v, Clientes cl WHERE cl.ID_CLIENTE = v.idCliente AND fechaEntrada is not null")
})
public class PreVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idPreVehiculo")
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer idPreVehiculo;
    @Min(0)
	private String idCliente;
	private String fechaEntrada;
    private String horaEntrada;
    private String nombreOperador;
    private String placasVehiculo;
    private String tipoProducto;
    private Double peso;
    private String maniobraCargaDescarga;
	private String fechaSalida;
	private String horaSalida;
	private int estatus;
    
	public Integer getIdPreVehiculo() {
		return idPreVehiculo;
	}



	public void setIdPreVehiculo(Integer idPreVehiculo) {
		this.idPreVehiculo = idPreVehiculo;
	}



	public String getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(String idCliente) {
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

	public String getNombreOperador() {
		return nombreOperador;
	}



	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}


	public String getPlacasVehiculo() {
		return placasVehiculo;
	}



	public void setPlacasVehiculo(String placasVehiculo) {
		this.placasVehiculo = placasVehiculo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    public String getManiobraCargaDescarga() {
        return maniobraCargaDescarga;
    }

    public void setManiobraCargaDescarga(String maniobraCargaDescarga) {
        this.maniobraCargaDescarga = maniobraCargaDescarga;
    }

	@Override
	public String toString() {
		return "PreVehiculo [idPreVehiculo=" + idPreVehiculo + ", idCliente="
				+ idCliente + ", fechaEntrada=" + fechaEntrada
				+ ", horaEntrada=" + horaEntrada + ", nombreOperador=" + nombreOperador
				+ ", placasVehiculo="
				+ placasVehiculo 
				+ ", tipoProducto=" + tipoProducto 
				+ ", peso=" + peso 
				+ ", maniobraCargaDescarga=" + maniobraCargaDescarga 
				+ "]";
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
}