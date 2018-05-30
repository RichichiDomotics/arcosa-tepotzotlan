package com.cbj.almacen.domain;

/**
 * Created by Richard on 06/07/2015.
 */
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "observaciones_seguimiento")
        @org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="ObservacionesSeguimiento.getNumObservaciones",
                query="SELECT COUNT(c.id) FROM ObservacionesSeguimiento c WHERE c.idProspecto=:idProspecto AND c.idSeguimiento=:idSeguimiento"),
        @org.hibernate.annotations.NamedQuery(name="ObservacionesSeguimiento.getObservaciones",
                query="SELECT c FROM ObservacionesSeguimiento c WHERE c.idProspecto=:idProspecto AND c.idSeguimiento=:idSeguimiento")
})
public class ObservacionesSeguimiento {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  Integer id;
    private String claveUsuario;
    private String observacion;
    private String fecha;
    private Integer idSeguimiento;
    private Integer idProspecto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Integer idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Integer getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(Integer idProspecto) {
        this.idProspecto = idProspecto;
    }
}
