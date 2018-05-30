package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jolvera on 12/11/2014.
 */

@Entity
@Table(name = "aut_puertas")
@NamedQueries({
        @NamedQuery(name="AutPuertas.getByClienteTipoVehiculo",
                query="SELECT c FROM AutPuertas c where c.idCliente = :idCliente and c.idTipoVehiculo = :idTipoVehiculo"),
})
public class AutPuertas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idautPuertas")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idautPuertas;
    private Integer idCliente;
    private Integer idTipoVehiculo;
    private String puerta;
    private String planta;

    public Integer getIdautPuertas() {
        return idautPuertas;
    }

    public void setIdautPuertas(Integer idautPuertas) {
        this.idautPuertas = idautPuertas;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }
}
