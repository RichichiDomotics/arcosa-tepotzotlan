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
@Table(name = "vehiculo")
@NamedQueries({
        @NamedQuery(name="Vehiculo.getPorSalir",
                query="SELECT c,d,l FROM Vehiculo c, CatalogoGeneral d, Clientes l where c.status IN (2) and c.fechaEntrada = CURRENT_DATE and c.idVehiculo = d.idCatalogo and l.ID_CLIENTE = c.idCliente"),
        @NamedQuery(name="Vehiculo.getVehiculoByCliente",
        		query="SELECT c FROM Vehiculo c where c.idCliente = :idCliente AND c.status='1' AND idTipoRecibo='1112' order by c.idIngresoVehiculo ASC"),
        @NamedQuery(name="Vehiculo.getInOutVehiculo",
				query="SELECT cl.ID_CLIENTE,cl.NOM_CLIENTE,v.nombreOperador, v.placasVehiculo, v.fleje, v.fechaEntrada, v.horaEntrada, v.fechaSalida, v.horaSalida, TIMEDIFF(v.horaSalida, v.horaEntrada) as atencion, v.idTipoRecibo FROM Vehiculo v, Clientes cl WHERE cl.ID_CLIENTE = v.idCliente AND fechaEntrada is not null AND fechaSalida is not null AND fechaSalida != '' and fechaEntrada >= :fecini and fechaEntrada <= :fecfin"),
		@NamedQuery(name = "Vehiculo.getIdIngresoVehiculo",
				query="SELECT v FROM Vehiculo v where v.idIngresoVehiculo = :idIngresoVehiculo"),
		@NamedQuery(name = "Vehiculo.getAll",
				query="SELECT v FROM Vehiculo v")
})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idIngresoVehiculo")
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer idIngresoVehiculo;
    @Min(0)
	private Integer idCliente;
	private String fechaEntrada;
    private String horaEntrada;
    private Integer idTipoRecibo;
    private Integer idPlanta;
    private Integer idPuerta;
    private String nombreOperador;
    private Integer idManiobra;
    private String placasVehiculo;
    private Integer idFleje;
    private String fleje;
    private Integer idVehiculo;
    private Integer idTransporte;
    private String nombreCiaTransporte;
    private String numRecibo;
    private String permisos;
    private String status;
    private String incidenciaIngreso;
    private String fechaSalida;
    private String horaSalida;
    private String tipoProducto;
    private Double peso;
	private String entregadoA;
	private String ordenSalidaCliente;
    
    public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getFleje() {
		return fleje;
	}



	public void setFleje(String fleje) {
		this.fleje = fleje;
	}

	public String getPermisos() {
		return permisos;
	}



	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}



	public Integer getIdIngresoVehiculo() {
		return idIngresoVehiculo;
	}



	public void setIdIngresoVehiculo(Integer idIngresoVehiculo) {
		this.idIngresoVehiculo = idIngresoVehiculo;
	}



	public Integer getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(Integer idCliente) {
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



	public Integer getIdTipoRecibo() {
		return idTipoRecibo;
	}



	public void setIdTipoRecibo(Integer idTipoRecibo) {
		this.idTipoRecibo = idTipoRecibo;
	}



	public Integer getIdPlanta() {
		return idPlanta;
	}



	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}



	public Integer getIdPuerta() {
		return idPuerta;
	}



	public void setIdPuerta(Integer idPuerta) {
		this.idPuerta = idPuerta;
	}



	public String getNombreOperador() {
		return nombreOperador;
	}



	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}



	public Integer getIdManiobra() {
		return idManiobra;
	}



	public void setIdManiobra(Integer idManiobra) {
		this.idManiobra = idManiobra;
	}



	public String getPlacasVehiculo() {
		return placasVehiculo;
	}



	public void setPlacasVehiculo(String placasVehiculo) {
		this.placasVehiculo = placasVehiculo;
	}



	public Integer getIdFleje() {
		return idFleje;
	}



	public void setIdFleje(Integer idFleje) {
		this.idFleje = idFleje;
	}



	public Integer getIdVehiculo() {
		return idVehiculo;
	}



	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}



	public Integer getIdTransporte() {
		return idTransporte;
	}



	public void setIdTransporte(Integer idTransporte) {
		this.idTransporte = idTransporte;
	}



	public String getNombreCiaTransporte() {
		return nombreCiaTransporte;
	}



	public void setNombreCiaTransporte(String nombreCiaTransporte) {
		this.nombreCiaTransporte = nombreCiaTransporte;
	}



	public String getNumRecibo() {
		return numRecibo;
	}



	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getIncidenciaIngreso() {
		return incidenciaIngreso;
	}



	public void setIncidenciaIngreso(String incidenciaIngreso) {
		this.incidenciaIngreso = incidenciaIngreso;
	}
	
	public String getTipoProducto() {
		return tipoProducto;
	}



	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	@Override
	public String toString() {
		return "Vehiculo [idIngresoVehiculo=" + idIngresoVehiculo + ", idCliente="
				+ idCliente + ", fechaEntrada=" + fechaEntrada
				+ ", horaEntrada=" + horaEntrada + ", idTipoRecibo="
				+ idTipoRecibo + ", idPlanta=" + idPlanta + ", idPuerta="
				+ idPuerta + ", nombreOperador=" + nombreOperador
				+ ", idManiobra=" + idManiobra + ", placasVehiculo="
				+ placasVehiculo + ", idFleje=" + idFleje + ", fleje=" + fleje
				+ ", idVehiculo=" + idVehiculo + ", idTransporte="
				+ idTransporte + ", nombreCiaTransporte=" + nombreCiaTransporte
				+ ", numRecibo=" + numRecibo + ", permisos=" + permisos
				+ ", status=" + status + ", incidenciaIngreso =" + incidenciaIngreso 
				+ ", tipoProducto=" + tipoProducto 
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

	public String getEntregadoA() {
		return entregadoA;
	}

	public void setEntregadoA(String entregadoA) {
		this.entregadoA = entregadoA;
	}

	public String getOrdenSalidaCliente() {
		return ordenSalidaCliente;
	}

	public void setOrdenSalidaCliente(String ordenSalidaCliente) {
		this.ordenSalidaCliente = ordenSalidaCliente;
	}
}