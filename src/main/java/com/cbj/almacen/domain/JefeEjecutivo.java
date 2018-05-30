package com.cbj.almacen.domain;

import javax.persistence.*;

/**
 * Created by Richard on 5/11/2015.
 */

@Entity
@Table(name = "jefe_ejecutivo")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="JefeEjecutivo.getJefeEjecutivo",
                query="SELECT c FROM JefeEjecutivo c where c.id = :idJefeEjecutivo"),
        @org.hibernate.annotations.NamedQuery(name="JefeEjecutivo.getJefeEjecutivoUser",
                query="SELECT c FROM JefeEjecutivo c where c.usuario = :userJefe")
})

public class JefeEjecutivo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombreJefe   ;
    private int activo;
    private String claveJefe;
    private String usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getClaveJefe() {
        return claveJefe;
    }

    public void setClaveJefe(String claveJefe) {
        this.claveJefe = claveJefe;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
