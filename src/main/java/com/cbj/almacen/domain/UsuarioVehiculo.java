package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jolvera on 17/12/2014.
 */
@Entity
@Table(name = "usuario_vehiculo")
@NamedQueries({
        @NamedQuery(name="UsuarioVehiculo.findByUsuarioVehiculo",
                query="SELECT c FROM UsuarioVehiculo c where c.username = :username and c.idVehiculo = :idVehiculo"),
        @NamedQuery(name="UsuarioVehiculo.findByVehiculo",
                query="SELECT c FROM UsuarioVehiculo c where c.idVehiculo = :idVehiculo")})

public class UsuarioVehiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idUsuarioVehiculo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuarioVehiculo;
    private String username;
    private int idVehiculo;
    private boolean habilitado;
    private Date fechaRegistro;

    public int getIdUsuarioVehiculo() {
        return idUsuarioVehiculo;
    }

    public void setIdUsuarioVehiculo(int idUsuarioVehiculo) {
        this.idUsuarioVehiculo = idUsuarioVehiculo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
