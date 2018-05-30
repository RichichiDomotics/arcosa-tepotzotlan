package com.cbj.almacen.domain;

/**
 * Created by Richard on 21/07/2015.
 */

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "modificaciones")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Modificacion.getAll",
                query="SELECT c FROM Modificaciones c where estatus=0"),
        @org.hibernate.annotations.NamedQuery(name="Modificacion.getFolio",
                query="SELECT MAX(c.folio) FROM Modificaciones c where tipo='SOLICITUD'"),
        @org.hibernate.annotations.NamedQuery(name="Modificacion.getModificacionById",
                query="SELECT c FROM Modificaciones c where idModificacion=:idModificacion"),
        @org.hibernate.annotations.NamedQuery(name="Modificacion.getModificacionByFecha",
                query="SELECT c FROM Modificaciones c where (c.fecha_captura >= :fechaini and c.fecha_captura<= :fechafin) and c.estatus = 1")

})

public class Modificaciones {

    @Id
    @Column(name = "idModificacion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idModificacion;
    private String area_solicitante;
    private String nombre_solicitante;
    private String fecha_captura;
    private String hora_captura;
    private Integer idCliente;
    private String cambio_solicitado;
    private String causa;
    private String rd_afectado;
    private String salida_afectada;
    private int estatus;
    private int folio;
    //private String nombre_autoriza;
    private String tipo;
    private String atendidopor;
    private String fechaatencion;
    private String horaatencion;
    private String solucion;
    private String tipomodificacion;


    public Integer getIdModificacion() {
        return idModificacion;
    }

    public void setIdModificacion(Integer idModificacion) {
        this.idModificacion = idModificacion;
    }

    public String getArea_solicitante() {
        return area_solicitante;
    }

    public void setArea_solicitante(String area_solicitante) {
        this.area_solicitante = area_solicitante;
    }

    public String getNombre_solicitante() {
        return nombre_solicitante;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getFecha_captura() {
        return fecha_captura;
    }

    public void setFecha_captura(String fecha_captura) {
        this.fecha_captura = fecha_captura;
    }

    public String getHora_captura() {
        return hora_captura;
    }

    public void setHora_captura(String hora_captura) {
        this.hora_captura = hora_captura;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCambio_solicitado() {
        return cambio_solicitado;
    }

    public void setCambio_solicitado(String cambio_solicitado) {
        this.cambio_solicitado = cambio_solicitado;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getRd_afectado() {
        return rd_afectado;
    }

    public void setRd_afectado(String rd_afectado) {
        this.rd_afectado = rd_afectado;
    }

    public String getSalida_afectada() {
        return salida_afectada;
    }

    public void setSalida_afectada(String salida_afectada) {
        this.salida_afectada = salida_afectada;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }



    /*public String getNombre_autoriza() {
        return nombre_autoriza;
    }

    public void setNombre_autoriza(String nombre_autorizo) {
        this.nombre_autoriza = nombre_autorizo;
    }*/

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtendidopor() {
        return atendidopor;
    }

    public void setAtendidopor(String atendidopor) {
        this.atendidopor = atendidopor;
    }

    public String getFechaatencion() {
        return fechaatencion;
    }

    public void setFechaatencion(String fechaatencion) {
        this.fechaatencion = fechaatencion;
    }

    public String getHoraatencion() {
        return horaatencion;
    }

    public void setHoraatencion(String horaatencion) {
        this.horaatencion = horaatencion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getTipomodificacion() {
        return tipomodificacion;
    }

    public void setTipomodificacion(String tipomodificacion) {
        this.tipomodificacion = tipomodificacion;
    }
}
