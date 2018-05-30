package com.cbj.almacen.domain;

/**
 * Created by Richard on 6/26/2015.
 */
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "cat_estatusprospecto")
public class CatEstatusprospecto {
    @Id
    @Column(name = "idEstatus")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEstatus;
    private String descripcion_estatus;



    public String getDescripcion_estatus() {
        return descripcion_estatus;
    }

    public void setDescripcion_estatus(String descripcion_estatus) {
        this.descripcion_estatus = descripcion_estatus;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }
}
