package com.cbj.almacen.domain;

/**
 * Created by Richard on 5/5/2015.
 */

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "etapa_prospecto")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="EtapaProspecto.getEtapa",
                query="SELECT c.nombreEtapa FROM EtapaProspecto c where idEtapa=:idEtapa")
})

public class EtapaProspecto {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idEtapa")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idEtapa;
    private String nombreEtapa;


    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getNombreEtapa() {
        return nombreEtapa;
    }

    public void setNombreEtapa(String nombreEtapa) {
        this.nombreEtapa = nombreEtapa;
    }
}
