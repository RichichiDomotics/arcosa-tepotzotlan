package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by jolvera on 02/06/2014.
 */
@Entity
@Table(name = "salidas")
@NamedQueries({
        @NamedQuery(name="Salidas.getAll",
                query="SELECT c FROM Salidas c"),
        @NamedQuery(name="Salidas.getByClienteConsecutivo",
                //query="SELECT c FROM Salidas c where c.idCliente = :idCliente and c.consecutivo = :consecutivo order by c.folioSalida ASC "),
                query="SELECT c FROM Salidas c where c.idCliente = :idCliente and (c.estatusSalida is null OR c.estatusSalida = '0') order by c.folioSalida ASC "),
        @NamedQuery(name="Salidas.getByCliente",
                query="SELECT c FROM Salidas c where c.idCliente = :idCliente and (c.estatusSalida is null OR c.estatusSalida = '0') order by c.folioSalida ASC "),
        @NamedQuery(name="Salidas.getByConsecutivo",
                query="SELECT c FROM Salidas c where c.consecutivo = :consecutivo and fechaSalida is null"),
                @NamedQuery(name="Salidas.getByFolioSalida",
                query="SELECT c FROM Salidas c where c.folioSalida = :folioSalida"),
        @NamedQuery(name="Salidas.getAllFechaSalidaNull",
                query="SELECT c FROM Salidas c where date(c.fechaSalida) = current_date()"),
        @NamedQuery(name="Salidas.getKilosByFechaSalida",
        query="SELECT SUM(c.pesou) FROM Salidas c where date(c.fechaSalida)= :fechaSalida")
})

public class Salidas implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "folioSalida")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer folioSalida;
    private Integer consecutivo;
    @Min(0)
    private String idCliente;
    private String nomCliente;
    private String fechaSalida;
    private String solicitante;
    private String cantidadSalida;
    private String entregadoA;
    private String placas;
    private String documentacion;
    private String observaciones;
    private String vehiculo;
    private String transporte;
    private String claveCliente;
    private String hora;
    private String anden;
    private String pesou;
    private String producto;
    private String renglon;
    private String embalaje;
    private String cantidadKilosalida;
    private String autorizacion;
    private Integer consecutivoSalida;
    private String estatusSalida;

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getFolioSalida() {
        return folioSalida;
    }

    public void setFolioSalida(Integer folioSalida) {
        this.folioSalida = folioSalida;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(String cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public String getEntregadoA() {
        return entregadoA;
    }

    public void setEntregadoA(String entregadoA) {
        this.entregadoA = entregadoA;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getClaveCliente() {
        return claveCliente;
    }

    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAnden() {
        return anden;
    }

    public void setAnden(String anden) {
        this.anden = anden;
    }

    public String getPesou() {
        return pesou;
    }

    public void setPesou(String pesou) {
        this.pesou = pesou;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
    }

    public String getCantidadKilosalida() {
        return cantidadKilosalida;
    }

    public void setCantidadKilosalida(String cantidadKilosalida) {
        this.cantidadKilosalida = cantidadKilosalida;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Integer getConsecutivoSalida() {
        return consecutivoSalida;
    }

    public void setConsecutivoSalida(Integer consecutivoSalida) {
        this.consecutivoSalida = consecutivoSalida;
    }

    public String getEstatusSalida() {
        return estatusSalida;
    }

    public void setEstatusSalida(String estatusSalida) {
        this.estatusSalida = estatusSalida;
    }
}
