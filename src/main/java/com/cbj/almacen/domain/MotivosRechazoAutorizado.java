package com.cbj.almacen.domain;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;
/**
 * Created by Richard on 6/1/2015.
 */

@Entity
@Table(name = "motivos_rechazo_autorizado")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Motivo.getAll",
                query="SELECT COUNT(*) FROM MotivosRechazoAutorizado  where idProspecto=:idProspecto AND solicitudMotivo=1")
})

public class MotivosRechazoAutorizado {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int idProspecto;
    private String claveUsuario;
    private String tipoEvento;
    private String fecha;
    private String motivo;
    private int solicitudMotivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(int idProspecto) {
        this.idProspecto = idProspecto;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getSolicitudMotivo() {
        return solicitudMotivo;
    }

    public void setSolicitudMotivo(int solicitudMotivo) {
        this.solicitudMotivo = solicitudMotivo;
    }
}
