package com.cbj.almacen.domain;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Richard on 5/11/2015.
 */

@Entity
@Table(name = "ejecutivo")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Ejecutivo.getAll",
                query="SELECT c FROM Ejecutivo c where c.activo = 1"),
        @org.hibernate.annotations.NamedQuery(name="Ejecutivo.getAllJefe",
                query="SELECT c FROM Ejecutivo c where c.activo = 1 AND c.idJefe =:idJefe"),
        @org.hibernate.annotations.NamedQuery(name="Ejecutivo.getEjecutivo",
                query="SELECT c FROM Ejecutivo c where c.claveEjecutivo  = :claveEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="Ejecutivo.getEjecutivoId",
                query="SELECT c FROM Ejecutivo c where c.id  = :idEjecutivo")
})

public class Ejecutivo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombreEjecutivo;
    private int activo;
    private String claveEjecutivo;
    private int idJefe;
    private String usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEjecutivo() {
        return nombreEjecutivo;
    }

    public void setNombreEjecutivo(String nombreEjecutivo) {
        this.nombreEjecutivo = nombreEjecutivo;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getClaveEjecutivo() {
        return claveEjecutivo;
    }

    public void setClaveEjecutivo(String claveEjecutivo) {
        this.claveEjecutivo = claveEjecutivo;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }
}
