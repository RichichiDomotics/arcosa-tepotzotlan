package com.cbj.almacen.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by jolvera on 01/09/2014.
 */
@Entity
@Table(name = "prospecto")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Prospecto.getAll",
                query="SELECT c FROM Prospecto c where c.activo IN ('0','1') AND idEjecutivo = :idEjecutivo ORDER BY c.razonSocial"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.getAllActive",
                query="SELECT c FROM Prospecto c where c.activo = '1' AND idEjecutivo = :idEjecutivo ORDER BY c.razonSocial"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.getAllFail",
                query="SELECT c FROM Prospecto c where c.idEtapa = :idEtapa"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByIdProspectoNombre",
                query="SELECT c FROM Prospecto c where c.idProspecto = :idProspecto and c.razonSocial = :nombre AND activo = '0' AND c.idEjecutivo = :idEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByIdProspecto",
                query="SELECT c FROM Prospecto c where c.idProspecto = :idProspecto AND idEjecutivo = :idEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByNombre",
                query="SELECT c FROM Prospecto c where (c.razonSocial like :nombre OR c.rfc like :nombre OR c.telefono like :nombre OR c.email like :nombre) AND c.idEjecutivo = :idEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByRazonSocial",
                query="SELECT c FROM Prospecto c where (c.razonSocial like :nombre)"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByActivo",
                query="SELECT c FROM Prospecto c where c.estatus=:estatus AND idEjecutivo = :idEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByIdProspecto2",
                query="SELECT c FROM Prospecto c where c.idProspecto=:idProspecto"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByFiltro",
                query="SELECT c FROM Prospecto c where (c.razonSocial like :nombre OR c.rfc like :nombre OR c.telefono like :nombre OR c.email like :nombre) AND c.estatus=:estatus AND idEjecutivo = :idEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.getAllJefe",
                query="SELECT c FROM Prospecto c, Ejecutivo e, JefeEjecutivo j where c.activo IN ('0','1') AND c.idEjecutivo = e.id AND e.idJefe = j.id AND j.id = :idJefe ORDER BY c.razonSocial"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.getAllJefeSinAsignar",
                query="SELECT c FROM Prospecto c, Ejecutivo e, JefeEjecutivo j where c.activo IN ('2') AND c.idEjecutivo = e.id AND e.idJefe = j.id AND j.id = :idJefe"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.getAllActiveJefe",
                query="SELECT c FROM Prospecto c, Ejecutivo e, JefeEjecutivo j where c.activo = '1' AND c.idEjecutivo = e.id AND e.idJefe = j.id AND j.id = :idJefe ORDER BY c.razonSocial"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByIdProspectoNombreJefe",
                query="SELECT c FROM Prospecto c where c.idProspecto = :idProspecto and c.razonSocial = :nombre AND activo = '0'"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByIdProspectoJefe",
                query="SELECT c FROM Prospecto c where c.idProspecto = :idProspecto AND idEjecutivo = :idEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByNombreJefe",
                query="SELECT c FROM Prospecto c where c.razonSocial like :nombre OR c.rfc like :nombre OR c.telefono like :nombre OR c.email like :nombre"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByActivoJefe",
                query="SELECT c FROM Prospecto c, Ejecutivo e, JefeEjecutivo j  where c.estatus=:estatus AND c.idEjecutivo = e.id AND e.idJefe = j.id AND j.id = :idJefe"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByFiltroJefe",
                query="SELECT c FROM Prospecto c, Ejecutivo e, JefeEjecutivo j where c.idEjecutivo = e.id AND e.idJefe = j.id AND (c.razonSocial like :nombre OR c.rfc like :nombre OR c.telefono like :nombre OR c.email like :nombre) AND c.estatus=:estatus AND j.id = :idJefe"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.findByFiltroJefeDesasignado",
                query="SELECT c FROM Prospecto c, Ejecutivo e, JefeEjecutivo j where c.idEjecutivo = e.id AND e.idJefe = j.id AND c.activo = '2' AND  (c.razonSocial like :nombre OR c.rfc like :nombre OR c.telefono like :nombre OR c.email like :nombre) AND j.id = :idJefe"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reporteEjecutivoEstatus",
                query="SELECT ejt.nombreEjecutivo, cep.descripcion_estatus, COUNT(p.idProspecto) FROM Prospecto p, CatEstatusprospecto cep, Ejecutivo ejt WHERE p.estatus = cep.idEstatus AND p.idEjecutivo = ejt.id AND p.activo='1' AND ejt.idJefe =:idJefe GROUP BY ejt.nombreEjecutivo, p.idEtapa ORDER BY ejt.nombreEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reporteEjecutivoEtapas",
                query="SELECT ejt.nombreEjecutivo, ep.nombreEtapa, COUNT(p.idProspecto) FROM Prospecto p, EtapaProspecto ep, Ejecutivo ejt WHERE p.idEtapa = ep.idEtapa AND p.idEjecutivo = ejt.id AND p.activo='1' AND ejt.idJefe =:idJefe GROUP BY ejt.nombreEjecutivo, p.idEtapa ORDER BY ejt.nombreEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reporteEjecutivoEstatusFE",
                query="SELECT ejt.nombreEjecutivo, cep.descripcion_estatus, COUNT(p.idProspecto) FROM Prospecto p, CatEstatusprospecto cep, Ejecutivo ejt WHERE p.estatus = cep.idEstatus AND p.idEjecutivo = ejt.id AND ejt.claveEjecutivo =:cveEjecutivo AND ejt.idJefe =:idJefe AND p.activo='1' GROUP BY ejt.nombreEjecutivo, p.estatus ORDER BY ejt.nombreEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reporteEjecutivoEtapasFE",
                query="SELECT ejt.nombreEjecutivo, ep.nombreEtapa, COUNT(p.idProspecto) FROM Prospecto p, EtapaProspecto ep, Ejecutivo ejt WHERE p.idEtapa = ep.idEtapa AND p.idEjecutivo = ejt.id AND ejt.claveEjecutivo =:cveEjecutivo AND ejt.idJefe =:idJefe  AND p.activo='1' GROUP BY ejt.nombreEjecutivo, p.idEtapa ORDER BY ejt.nombreEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reportePorEstatus",
                query="SELECT cep.descripcion_estatus, COUNT(p.idProspecto) FROM Prospecto p, CatEstatusprospecto cep, Ejecutivo ejt WHERE p.estatus = cep.idEstatus AND p.idEjecutivo = ejt.id AND ejt.idJefe =:idJefe  AND p.activo='1' GROUP BY p.estatus ORDER BY p.estatus"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reportePorEtapas",
                query="SELECT ep.nombreEtapa, COUNT(p.idProspecto) FROM Prospecto p, EtapaProspecto ep, Ejecutivo ejt WHERE p.idEtapa = ep.idEtapa AND p.idEjecutivo = ejt.id AND ejt.idJefe =:idJefe  AND p.activo='1' GROUP BY p.idEtapa ORDER BY p.idEtapa"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reporteHistorial",
                query="SELECT ah.idProspecto, p.razonSocial, ah.tipoNotificacion, ah.claveJefe, ah.claveEjecutivo, ah.visto, ah.fecha, ah.observacion FROM AvisosHistorial ah, Prospecto p WHERE ah.idProspecto = p.idProspecto AND ah.fecha>=:fechaIni AND ah.fecha<=:fechaFin AND ah.claveJefe =:claveJefe ORDER BY ah.idProspecto, ah.fecha"),
        @org.hibernate.annotations.NamedQuery(name="Prospecto.reporteHistorialIdProspecto",
                query="SELECT ah.idProspecto, p.razonSocial, ah.tipoNotificacion, ah.claveJefe, ah.claveEjecutivo, ah.visto, ah.fecha, ah.observacion FROM AvisosHistorial ah, Prospecto p WHERE ah.idProspecto = p.idProspecto AND ah.fecha>=:fechaIni AND ah.fecha<=:fechaFin AND ah.idProspecto =:idProspecto ORDER BY ah.idProspecto, ah.fecha")
})

public class Prospecto {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idProspecto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProspecto;

    private String razonSocial;
    private String rfc;
    private String callenum;
    private String colonia;
    private String ciudad;
    private String estado;
    private String cp;
    private String email;
    private String telefono;
    private String extension;
    private String contacto1 ;
    private String cargo1;
    private String telefono1;
    private String celular1;
    private String activo;
    private int idEjecutivo;
    private String estatus;
    private int idEtapa;
    private String divisionNegocio;
    private String producto;
    private Integer numConteo;


    public Integer getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(Integer idProspecto) {
        this.idProspecto = idProspecto;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCallenum() {
        return callenum;
    }

    public void setCallenum(String callenum) {
        this.callenum = callenum;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContacto1() {
        return contacto1;
    }

    public void setContacto1(String contacto1) {
        this.contacto1 = contacto1;
    }

    public String getCargo1() {
        return cargo1;
    }

    public void setCargo1(String cargo1) {
        this.cargo1 = cargo1;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }



    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus(){return estatus;}

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public int getIdEjecutivo() {
        return idEjecutivo;
    }

    public void setIdEjecutivo(int idEjecutivo) {
        this.idEjecutivo = idEjecutivo;
    }

    public String getDivisionNegocio() {
        return divisionNegocio;
    }

    public void setDivisionNegocio(String divisionNegocio) {
        this.divisionNegocio = divisionNegocio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getNumConteo() {
        return numConteo;
    }

    public void setNumConteo(Integer numConteo) {
        this.numConteo = numConteo;
    }

   /* public String getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }*/
}
