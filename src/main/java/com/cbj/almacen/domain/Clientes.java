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
@Table(name = "clientes")
@NamedQueries({
		@org.hibernate.annotations.NamedQuery(name="Clientes.getAll",
				query="SELECT c FROM Clientes c where ESTATUS = 'ACTIVO' order by ID_CLIENTE+0"),
		@org.hibernate.annotations.NamedQuery(name="Clientes.getFormatosUnicos",
				query="SELECT c FROM Clientes c where ESTATUS = 'ACTIVO' and ID_CLIENTE in (179,186,187,207,258,326,333,341,497,526,531,538,550,551,552,556,595,606,650,704,1550,1551,1650,1651)"),
		@org.hibernate.annotations.NamedQuery(name="Clientes.getAllIdEjecutivo",
				query="SELECT c FROM Clientes c where c.ESTATUS = 'ACTIVO' and c.ID_EJECUTIVO = :idEjecutivo"),
        @NamedQuery(name="Clientes.findByIdClienteNombre",
                query="SELECT c FROM Clientes c where c.ID_CLIENTE = :idCliente and c.NOM_CLIENTE = :nombre"),
        @NamedQuery(name="Clientes.findByIdCliente",
                query="SELECT c FROM Clientes c where c.ID_CLIENTE = :idCliente"),
        @NamedQuery(name="Clientes.findByNombre",
                query="SELECT c FROM Clientes c where c.NOM_CLIENTE = :nombre"),
		@org.hibernate.annotations.NamedQuery(name="Clientes.getByEstatusSalida",
				query="SELECT c FROM Clientes c where SALIDA_PRODUCTO = 'DENEGADA' ORDER BY ID_CLIENTE")
})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Integer ID_CLIENTE;
	private String ID_EJECUTIVO;
    private String NOM_CLIENTE;
    private String CALLE;
    private String COLONIA;
    private String CIUDAD;
    private String ESTADO;
    private String CP;
    private String TELEFONO1;
    private String CONTACTO;
    private String RFC;
    private String DIAS_REVISION;
    private String DIAS_PAGO;
    private String HORARIO; 
    private String TELEFONO2;
    private String FAX;
    private String CONTACTO2;
    private String EMAIL;
    private String CONTACTOPAGO_NOMBRE;
    private String CONTACTOPAGO_TELEFONO;
    private String CONTACTOPAGO_CARGO;
    private String WWW;
    private String OBSERVACIONES;
    private String ESTATUS;
    private String DIAS_CREDITO;
    private String TIPO_FACTURACION;
    private String FACTURACION;
    private String FINGRESO;
    private String CAPTURADOPOR;
    private String CONTRATO;
    private String EXTENSION;
    private String SALIDA_PRODUCTO;
    private String NOM_EJECUTIVO;
   
    
	public Integer getIdCliente() {
		return ID_CLIENTE;
	}



	public void setIdCliente(Integer ID_CLIENTE) {
		this.ID_CLIENTE = ID_CLIENTE;
	}



	public String getIdEjecutivo() {
		return ID_EJECUTIVO;
	}



	public void setIdEjecutivo(String ID_EJECUTIVO) {
		this.ID_EJECUTIVO = ID_EJECUTIVO;
	}



	public String getNombreCliente() {
		return NOM_CLIENTE;
	}



	public void setNombreCliente(String NOM_CLIENTE) {
		this.NOM_CLIENTE = NOM_CLIENTE;
	}



	public String getCalle() {
		return CALLE;
	}



	public void setCalle(String CALLE) {
		this.CALLE = CALLE;
	}



	public String getColonia() {
		return COLONIA;
	}



	public void setColonia(String COLONIA) {
		this.COLONIA = COLONIA;
	}



	public String getCiudad() {
		return CIUDAD;
	}



	public void setCiudad(String CIUDAD) {
		this.CIUDAD = CIUDAD;
	}



	public String getIdEstado() {
		return ESTADO;
	}



	public void setIdEstado(String ESTADO) {
		this.ESTADO = ESTADO;
	}



	public String getCp() {
		return CP;
	}



	public void setCp(String CP) {
		this.CP = CP;
	}



	public String getTelefono1() {
		return TELEFONO1;
	}



	public void setTelefono1(String TELEFONO1) {
		this.TELEFONO1 = TELEFONO1;
	}



	public String getContacto() {
		return CONTACTO;
	}



	public void setContacto(String CONTACTO) {
		this.CONTACTO = CONTACTO;
	}
	
	public String getRfc() {
		return RFC;
	}



	public void setRfc(String RFC) {
		this.RFC = RFC;
	}
	
	public String getDiasRevision() {
		return DIAS_REVISION;
	}



	public void setDiasRevision(String DIAS_REVISION) {
		this.DIAS_REVISION = DIAS_REVISION;
	}
	
	public String getDiasPago() {
		return DIAS_PAGO;
	}



	public void setDiasPago(String DIAS_PAGO) {
		this.DIAS_PAGO = DIAS_PAGO;
	}
	
	public String getHorario() {
		return HORARIO;
	}



	public void setHorario(String HORARIO) {
		this.HORARIO = HORARIO;
	}



	public String getTelefono2() {
		return TELEFONO2;
	}



	public void setTelefono2(String TELEFONO2) {
		this.TELEFONO2 = TELEFONO2;
	}



	public String getFax() {
		return FAX;
	}



	public void setFax(String FAX) {
		this.FAX = FAX;
	}

	public String getContacto2() {
		return CONTACTO2;
	}



	public void setContacto2(String CONTACTO2) {
		this.CONTACTO2 = CONTACTO2;
	}

	public String getEmail() {
		return EMAIL;
	}



	public void setEmail(String EMAIL) {
		this.EMAIL = EMAIL;
	}



	public String getContactoPagoNombre() {
		return CONTACTOPAGO_NOMBRE;
	}



	public void setContactoPagoNombre(String CONTACTOPAGO_NOMBRE) {
		this.CONTACTOPAGO_NOMBRE = CONTACTOPAGO_NOMBRE;
	}



	public String getContactoPagoTelefono() {
		return CONTACTOPAGO_TELEFONO;
	}



	public void setContactoPagoTelefono(String CONTACTOPAGO_TELEFONO) {
		this.CONTACTOPAGO_TELEFONO = CONTACTOPAGO_TELEFONO;
	}



	public String getContactoPagoCargo() {
		return CONTACTOPAGO_CARGO;
	}



	public void setContactoPagoCargo(String CONTACTOPAGO_CARGO) {
		this.CONTACTOPAGO_CARGO = CONTACTOPAGO_CARGO;
	}



	public String getWww() {
		return WWW;
	}



	public void setWww(String WWW) {
		this.WWW = WWW;
	}



	public String getObservaciones() {
		return OBSERVACIONES;
	}



	public void setObservaciones(String OBSERVACIONES) {
		this.OBSERVACIONES = OBSERVACIONES;
	}



	public String getEstatus() {
		return ESTATUS;
	}



	public void setEstatus(String ESTATUS) {
		this.ESTATUS = ESTATUS;
	}



	public String getDiasCredito() {
		return DIAS_CREDITO;
	}



	public void setDiasCredito(String DIAS_CREDITO) {
		this.DIAS_CREDITO = DIAS_CREDITO;
	}



	public String getTipoFacturacion() {
		return TIPO_FACTURACION;
	}



	public void setTipoFacturacion(String TIPO_FACTURACION) {
		this.TIPO_FACTURACION = TIPO_FACTURACION;
	}



	public String getFacturacion() {
		return FACTURACION;
	}



	public void setFacturacion(String FACTURACION) {
		this.FACTURACION = FACTURACION;
	}



	public String getFingreso() {
		return FINGRESO;
	}



	public void setFingreso(String FINGRESO) {
		this.FINGRESO = FINGRESO;
	}



	public String getCapturadoPor() {
		return CAPTURADOPOR;
	}



	public void setCapturadoPor(String CAPTURADOPOR) {
		this.CAPTURADOPOR = CAPTURADOPOR;
	}



	public String getContrato() {
		return CONTRATO;
	}



	public void setContrato(String CONTRATO) {
		this.CONTRATO = CONTRATO;
	}



	public String getExtension() {
		return EXTENSION;
	}



	public void setExtension(String EXTENSION) {
		this.EXTENSION = EXTENSION;
	}



	public String getSalidaProducto() {
		return SALIDA_PRODUCTO;
	}



	public void setSalidaProducto(String SALIDA_PRODUCTO) {
		this.SALIDA_PRODUCTO = SALIDA_PRODUCTO;
	}



	public String getNomEjecutivo() {
		return NOM_EJECUTIVO;
	}
	public void setNomEjecutivo(String NOM_EJECUTIVO) {
		this.NOM_EJECUTIVO = NOM_EJECUTIVO;
	}



	public void setIdEstatus(String NOM_EJECUTIVO) {
		this.NOM_EJECUTIVO = NOM_EJECUTIVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Cliente [ID_CLIENTE=" + ID_CLIENTE + ", ID_EJECUTIVO="
				+ ID_EJECUTIVO + ", NOM_CLIENTE=" + NOM_CLIENTE + ", CALLE="
				+ CALLE + ", COLONIA=" + COLONIA + ", CIUDAD=" + CIUDAD
				+ ", ESTADO=" + ESTADO + ", CP=" + CP + ", TELEFONO1="
				+ TELEFONO1 + ", CONTACTO=" + CONTACTO + ", RFC="
				+ RFC + ", DIAS_REVISION=" + DIAS_REVISION + ", DIAS_PAGO=" + DIAS_PAGO
				+ ", HORARIO=" + HORARIO + ", TELEFONO2=" + TELEFONO2 + ", FAX="
				+ FAX + ", CONTACTO2=" + CONTACTO2 + ", EMAIL=" + EMAIL
				+ ", CONTACTOPAGO_NOMBRE=" + CONTACTOPAGO_NOMBRE + ", CONTACTOPAGO_TELEFONO=" + CONTACTOPAGO_TELEFONO
				+ ", CONTACTOPAGO_CARGO=" + CONTACTOPAGO_CARGO + ", WWW="
				+ WWW + ", OBSERVACIONES="
				+ OBSERVACIONES + ", ESTATUS="
				+ ESTATUS + ", DIAS_CREDITO=" + DIAS_CREDITO
				+ ", TIPO_FACTURACION=" + TIPO_FACTURACION + ", FACTURACION=" + FACTURACION
				+ ", FINGRESO=" + FINGRESO + ", CAPTURADOPOR="
				+ CAPTURADOPOR + ", CONTRATO=" + CONTRATO
				+ ", EXTENSION=" + EXTENSION + ", SALIDA_PRODUCTO=" + SALIDA_PRODUCTO
				+ ", NOM_EJECUTIVO=" + NOM_EJECUTIVO + "]";
	}
    
}