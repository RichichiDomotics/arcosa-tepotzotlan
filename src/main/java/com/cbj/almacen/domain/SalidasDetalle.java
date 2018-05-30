package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jolvera on 02/06/2014.
 */
@Entity
@Table(name = "salidas_detalle")
@NamedQueries({
        @NamedQuery(name = "SalidasDetalle.getAll",
                query = "SELECT c FROM SalidasDetalle c"),
        @NamedQuery(name = "SalidasDetalle.getByConsecutivo",
                query = "SELECT c FROM SalidasDetalle c where c.consecutivo = :consecutivo"),
        @NamedQuery(name = "SalidasDetalle.getByConsecutivoFolioSalida",
                query = "SELECT c FROM SalidasDetalle c where c.consecutivo = :consecutivo and c.folioSalida = :folioSalida"),
        @NamedQuery(name = "SalidasDetalle.getByFolioSalida",
                query = "SELECT c FROM SalidasDetalle c where c.folioSalida = :folioSalida ORDER BY c.camara"),
        @NamedQuery(name = "SalidasDetalle.getByClienteConsecutivo",
                query = "SELECT c FROM SalidasDetalle c where c.idCliente = :idCliente and c.folioSalida = :folioSalida ORDER BY c.camara"),
        @NamedQuery(name = "SalidasDetalle.getFolioSalida",
                query = "SELECT c FROM SalidasDetalle c where c.folioSalida = :folioSalida ORDER BY c.camara"),
        @NamedQuery(name = "SalidasDetalle.getKilosByDay",
                query = "SELECT c.fechaSalida, SUM(c.pesou * c.cantidadSalida), SUM(c.cantidadSalida), c.consecutivo,c.idCliente,cl.NOM_CLIENTE, c.folioSalida  FROM SalidasDetalle c, Clientes cl where c.idCliente = cl.ID_CLIENTE and date(c.fechaSalida) = :fechaSalida"),
        @NamedQuery(name = "SalidasDetalle.getKilosByFechas",
                query = "SELECT c.fechaSalida, SUM(c.pesou * c.cantidadSalida), SUM(c.cantidadSalida), c.consecutivo,c.idCliente,cl.NOM_CLIENTE, c.folioSalida  FROM SalidasDetalle c, Clientes cl where c.idCliente = cl.ID_CLIENTE and date(c.fechaSalida)  >= :fechaInicio and date(c.fechaSalida)<= :fechaFin group by date(c.fechaSalida),c.folioSalida,c.consecutivo order by c.folioSalida,c.consecutivo"),
        @NamedQuery(name = "SalidasDetalle.getKilosByFechasConcentrado",
                query = "SELECT c.fechaSalida, SUM(c.pesou * c.cantidadSalida), SUM(c.cantidadSalida), c.consecutivo,c.idCliente,cl.NOM_CLIENTE, c.folioSalida  FROM SalidasDetalle c, Clientes cl where c.idCliente = cl.ID_CLIENTE and date(c.fechaSalida)  >= :fechaInicio and date(c.fechaSalida)<= :fechaFin group by date(c.fechaSalida)"),
        @NamedQuery(name = "SalidasDetalle.getKilosByFechasCliente",
                query = "SELECT c.fechaSalida, SUM(c.cantidadSalida), SUM(c.pesou * c.cantidadSalida), c.consecutivo, c.folioSalida FROM SalidasDetalle c where date(c.fechaSalida)>= :fechaInicio and date(c.fechaSalida)<= :fechaFin and c.idCliente = :idCliente group by date(c.fechaSalida),c.consecutivo ,c.folioSalida order by c.folioSalida,c.consecutivo"),
        @NamedQuery(name = "SalidasDetalle.getKilosByFechasClienteDetalle",
                query = "SELECT c.folioSalida , c.fechaSalida, c.consecutivo, c.renglon, c.camara, c.producto,c.caducidad,c.lote,c.marca, SUM(c.cantidadSalida), SUM(c.pesou) ,sum(c.cantidadSalida*c.pesou), c.folioSalida  FROM SalidasDetalle c where date(c.fechaSalida)>= :fechaInicio and date(c.fechaSalida)<= :fechaFin and c.idCliente = :idCliente group by date(c.fechaSalida), c.renglon"),
        @NamedQuery(name = "SalidasDetalle.getKilosByFechasClienteDetalle2",
                query = "SELECT c.folioSalida , c.fechaSalida, c.consecutivo, c.renglon, c.camara, c.producto,c.caducidad,c.lote,c.marca, c.cantidadSalida, c.pesou ,c.cantidadSalida*c.pesou, c.folioSalida  FROM SalidasDetalle c where date(c.fechaSalida)>= :fechaInicio and date(c.fechaSalida)<= :fechaFin and c.idCliente = :idCliente"),
        @NamedQuery(name = "SalidasDetalle.getKilosByFechasClienteDetalleTotal",
                query = "SELECT c.folioSalida , c.fechaSalida, c.consecutivo, c.renglon, c.camara, c.producto,c.caducidad,c.lote,c.marca, SUM(c.cantidadSalida), SUM(c.pesou) ,sum(c.cantidadSalida*c.pesou), c.folioSalida  FROM SalidasDetalle c where date(c.fechaSalida)>= :fechaInicio and date(c.fechaSalida)<= :fechaFin and c.idCliente = :idCliente"),
        @NamedQuery(name = "SalidasDetalle.getReporteByCamara",
                query = "SELECT c.idCliente, d.NOM_CLIENTE, c.consecutivo, SUM(c.cantidadSalida), SUM(c.cantidadSalida*c.pesou), c.folioSalida  FROM SalidasDetalle c , Clientes d where c.idCliente = d.ID_CLIENTE and date(c.fechaSalida)>= :fechaInicio and date(c.fechaSalida)<= :fechaFin and c.camara = :idCamara group by date(c.fechaSalida),c.consecutivo,c.folioSalida order by c.folioSalida,c.consecutivo"),
        @NamedQuery(name = "SalidasDetalle.getReporteByCamaraVacia",
                query = "SELECT c.idCliente, d.NOM_CLIENTE, c.consecutivo, SUM(c.cantidadSalida), SUM(c.cantidadSalida*c.pesou), c.folioSalida  FROM SalidasDetalle c , Clientes d where c.idCliente = d.ID_CLIENTE and date(c.fechaSalida)>= :fechaInicio and date(c.fechaSalida)<= :fechaFin group by c.fechaSalida,c.consecutivo,c.folioSalida order by c.folioSalida,c.consecutivo"),
        @NamedQuery(name = "SalidasDetalle.getArrastreSaldosDetalle",
        		query = "SELECT c.folioSalida, c.fechaSalida, c.renglon, c.descripcion,c.cantidadSalida, c.pesou , (c.pesou * c.cantidadSalida),c.idCliente, d.NOM_CLIENTE,c.marca,c.lote FROM SalidasDetalle c, Clientes d where c.consecutivo = :consecutivo AND d.ID_CLIENTE = c.idCliente order by c.renglon"),
        @NamedQuery(name = "SalidasDetalle.getArrastreSaldosDetalleAgrupado",
        		query = "SELECT c.renglon, SUM(c.cantidadSalida), SUM(c.cantidadSalida*c.pesou)  FROM SalidasDetalle c where c.consecutivo = :consecutivo group by c.renglon order by c.renglon")
})

public class    SalidasDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "folio")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer folio;
    private Integer folioSalida;
    private Integer consecutivo;
    @Min(0)
    private String idCliente;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaSalida;
    private String entregara;
    private String producto;
    private Double pesou;
    private String renglon;
    private String embalaje;
    private Double cantidadSalida;
    private String tipo;
    private String descripcion;
    private String lote;
    private String marca;
    private String camara;
    private String caducidad;
    private Double pesoNeto;
    

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

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(Double cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public Double getPesou() {
        return pesou;
    }

    public void setPesou(Double pesou) {
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

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getEntregara() {
        return entregara;
    }

    public void setEntregara(String entregara) {
        this.entregara = entregara;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public Double getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(Double pesoNeto) {
        this.pesoNeto = pesoNeto;
    }
}
