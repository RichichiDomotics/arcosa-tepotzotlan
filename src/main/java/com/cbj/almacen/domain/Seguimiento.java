package com.cbj.almacen.domain;

/**
 * Created by Richard on 5/6/2015.
 */
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "seguimiento")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Seguimiento.getAll",
                query="SELECT c FROM Seguimiento c"),
        @org.hibernate.annotations.NamedQuery(name="Seguimiento.getSeguimiento",
                query="SELECT c FROM Seguimiento c where c.idProspecto=:idProspecto")
})


public class Seguimiento {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String fecha;
    private String etapaSeguimiento;
    private String seguimiento;
    private String tipoSeguimiento;
    private int idProspecto;
    private Integer numeroObservaciones;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEtapaSeguimiento() {
        return etapaSeguimiento;
    }

    public void setEtapaSeguimiento(String etapaSeguimiento) {
        this.etapaSeguimiento = etapaSeguimiento;
    }

    public String getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    public String getTipoSeguimiento() {
        return tipoSeguimiento;
    }

    public void setTipoSeguimiento(String tipoSeguimiento) {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    public int getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(int idProspecto) {
        this.idProspecto = idProspecto;
    }

    public Integer getNumeroObservaciones() {
        return numeroObservaciones;
    }

    public void setNumeroObservaciones(Integer numeroObservaciones) {
        this.numeroObservaciones = numeroObservaciones;
    }
}
