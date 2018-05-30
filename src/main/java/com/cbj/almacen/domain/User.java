package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jolvera on 11/05/2014.
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="user.findByUserName",
                query="SELECT c FROM User c where username = :username"),
        @org.hibernate.annotations.NamedQuery(name="user.findByIdEjecutivo",
                query="SELECT c FROM User c where idEjecutivo = :idEjecutivo")
})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String password;
    private String idEjecutivo;
    private String PERMISOS;
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getIdEjecutivo() {
        return idEjecutivo;
    }

    public void setIdEjecutivo(String idEjecutivo) {
        this.idEjecutivo = idEjecutivo;
    }

    public String getPERMISOS() {
        return PERMISOS;
    }

    public void setPERMISOS(String PERMISOS) {
        this.PERMISOS = PERMISOS;
    }
}
