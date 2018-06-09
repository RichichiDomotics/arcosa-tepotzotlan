package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "regentradas")
@NamedQueries({
            @NamedQuery(name="RegEntradas.getAll",
                query="SELECT c FROM RegEntradas c"),
        @NamedQuery(name="RegEntradas.findByIdIngresoVehiculo",
                query="SELECT c FROM RegEntradas c where c.idIngresoVehiculo = :idIngresoVehiculo"),
        @NamedQuery(name="RegEntradas.findByConsecutivo",
                query="SELECT c FROM RegEntradas c where c.consecutivo = :consecutivo"),
        @NamedQuery(name="RegEntradas.findByConsecutivoImpresionEntrada",
                query="SELECT c.consecutivo, c.idCliente, cl.NOM_CLIENTE,d.camara,c.transporte FROM RegEntradas c, Clientes cl, DetallesRd d where c.consecutivo = :consecutivo AND c.idCliente = cl.ID_CLIENTE AND c.consecutivo = d.consecutivo group by c.consecutivo"),
        @NamedQuery(name="RegEntradas.getAllByActualDate",
        		query="SELECT c FROM RegEntradas c where date(c.fecha) = :fecha"),
        @NamedQuery(name="RegEntradas.getAllByActualDateEntrada",
            	query="SELECT c.consecutivo, c.idCliente, cl.NOM_CLIENTE,d.camara,c.transporte FROM RegEntradas c, Clientes cl, DetallesRd d where c.fecha = :fecha AND c.idCliente = cl.ID_CLIENTE AND c.consecutivo = d.consecutivo group by c.consecutivo"),
            //query="SELECT e FROM Salidas c ,SalidasDetalle d, DetallesRd e WHERE c.folioSalida = :folioSalida AND c.consecutivo = :consecutivo AND c.idCliente = :idCliente AND c.folioSalida = d.folioSalida AND (c.consecutivo = e.consecutivo AND  c.idCliente = e.idCliente) group by d.folio"),
        @NamedQuery(name="RegEntradas.getComparaCantidadesTonelaje",
        		query="SELECT cl.ID_CLIENTE,cl.NOM_CLIENTE,c.idIngresoVehiculo,v.idTipoRecibo,c.fecha,c.horas,c.anden,v.fechaEntrada, v.horaEntrada,v.nombreOperador, v.placasVehiculo,c.totalEntrada,v.peso FROM RegEntradas c ,Vehiculo v, Clientes cl WHERE c.idIngresoVehiculo=v.idIngresoVehiculo AND cl.ID_CLIENTE = c.idCliente AND c.totalEntrada is not NULL and v.fechaEntrada >= :fecini and v.fechaEntrada <= :fecfin"),
		@NamedQuery(name="RegEntradas.getCancelados",
				query="SELECT c FROM RegEntradas c WHERE c.observaciones='CANCELADO' order by c.consecutivo")
	})
public class RegEntradas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "consecutivo")
    private Integer consecutivo;
    @Min(0)
	private String idCliente;
    private String nomChofer;
    private String transporte;
    private String retraso;
    private String docFaltante;
    private String fecha;
    private String horas;
    private String vehiculo;
    private String placas;
    private String movimiento;
    private String anden;
    private String entroComo;
    private Float clienteKilos;
    private Float clienteCantidad;
    private Float clienteValor;
    private String clienteTemperatura;
    private String observaciones;
    private String impresion;
    private String capturado;
    private String horaSalida;
    private String horasAte2;
    private String pedimento;
    private String planta;
    private String causas;
    private String respon;
    private Integer idIngresoVehiculo;
    private Double totalEntrada;
    //private String incidenciaAnden;


	
	/*public String getIncidenciaAnden() {
		return incidenciaAnden;
	}
	public void setIncidenciaAnden(String incidenciaAnden) {
		this.incidenciaAnden = incidenciaAnden;
	}*/
	@Override
	public String toString() {
		return "RegEntradas [consecutivo=" + getConsecutivo() + ", idCliente="
				+ getIdCliente() + ", nomChofer=" + getNomChofer() + ", transporte="
				+ getTransporte() + ", retraso=" + getRetraso() + ", docFaltante="
				+ getDocFaltante() + ", fecha=" + getFecha() + ", horas=" + getHoras()
				+ ", vehiculo=" + getVehiculo() + ", placas=" + getPlacas()
				+ ", movimiento=" + getMovimiento() + ", anden=" + getAnden()
				+ ", entroComo=" + getEntroComo() + ", clienteKilos=" + getClienteKilos()
				+ ", clienteCantidad=" + getClienteCantidad() + ", clienteValor="
				+ getClienteValor() + ", clienteTemperatura=" + getClienteTemperatura()
				+ ", observaciones=" + getObservaciones() + ", impresion="
				+ getImpresion() + ", capturado=" + getCapturado() + ", horaSalida="
				+ getHoraSalida() + ", horasAte2=" + getHorasAte2() + ", pedimento="
				+ getPedimento() + ", planta=" + getPlanta() + ", causas=" + getCausas()
				+ ", respon=" + getRespon() + ", idIngresoVehiculo=" + getIdIngresoVehiculo()
				//+ ", incidenciaAnden=" + incidenciaAnden
				+ "]";
	}


    public Double getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(Double totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomChofer() {
		return nomChofer;
	}

	public void setNomChofer(String nomChofer) {
		this.nomChofer = nomChofer;
	}

	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}

	public String getRetraso() {
		return retraso;
	}

	public void setRetraso(String retraso) {
		this.retraso = retraso;
	}

	public String getDocFaltante() {
		return docFaltante;
	}

	public void setDocFaltante(String docFaltante) {
		this.docFaltante = docFaltante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getPlacas() {
		return placas;
	}

	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public String getAnden() {
		return anden;
	}

	public void setAnden(String anden) {
		this.anden = anden;
	}

	public String getEntroComo() {
		return entroComo;
	}

	public void setEntroComo(String entroComo) {
		this.entroComo = entroComo;
	}

	public Float getClienteKilos() {
		return clienteKilos;
	}

	public void setClienteKilos(Float clienteKilos) {
		this.clienteKilos = clienteKilos;
	}

	public Float getClienteCantidad() {
		return clienteCantidad;
	}

	public void setClienteCantidad(Float clienteCantidad) {
		this.clienteCantidad = clienteCantidad;
	}

	public Float getClienteValor() {
		return clienteValor;
	}

	public void setClienteValor(Float clienteValor) {
		this.clienteValor = clienteValor;
	}

	public String getClienteTemperatura() {
		return clienteTemperatura;
	}

	public void setClienteTemperatura(String clienteTemperatura) {
		this.clienteTemperatura = clienteTemperatura;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getImpresion() {
		return impresion;
	}

	public void setImpresion(String impresion) {
		this.impresion = impresion;
	}

	public String getCapturado() {
		return capturado;
	}

	public void setCapturado(String capturado) {
		this.capturado = capturado;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getHorasAte2() {
		return horasAte2;
	}

	public void setHorasAte2(String horasAte2) {
		this.horasAte2 = horasAte2;
	}

	public String getPedimento() {
		return pedimento;
	}

	public void setPedimento(String pedimento) {
		this.pedimento = pedimento;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public String getCausas() {
		return causas;
	}

	public void setCausas(String causas) {
		this.causas = causas;
	}

	public String getRespon() {
		return respon;
	}

	public void setRespon(String respon) {
		this.respon = respon;
	}

	public Integer getIdIngresoVehiculo() {
		return idIngresoVehiculo;
	}

	public void setIdIngresoVehiculo(Integer idIngresoVehiculo) {
		this.idIngresoVehiculo = idIngresoVehiculo;
	}
}