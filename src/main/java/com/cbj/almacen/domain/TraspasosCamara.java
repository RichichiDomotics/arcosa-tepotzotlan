package com.cbj.almacen.domain;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by jolvera on 09/09/2014.
 */
@Entity
@Table(name = "traspasos_camara")
@NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "TraspasosCamara.getByRDFecha",
                query = "select c from TraspasosCamara c where c.consecutivo=:consecutivo"),
        @org.hibernate.annotations.NamedQuery(name = "TraspasosCamara.getByRDFecha2",
                query = "select c from TraspasosCamara c where (c.fechaCambio >=:fechaini and c.fechaCambio<=:fechafin)"),
        @org.hibernate.annotations.NamedQuery(name = "TraspasosCamara.getByRDFecha3",
                query = "select c from TraspasosCamara c where c.consecutivo=:consecutivo AND (c.fechaCambio >=:fechaini and c.fechaCambio<=:fechafin)")
})
public class TraspasosCamara {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String consecutivo;
    private String camaraInicial;
    private String camaraFinal;
    private Date fechaIngreso;
    private Date fechaCambio;
    private String motivo;
    private String realizadoPor;
    private String tunel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCamaraInicial() {
        return camaraInicial;
    }

    public void setCamaraInicial(String camaraInicial) {
        this.camaraInicial = camaraInicial;
    }

    public String getCamaraFinal() {
        return camaraFinal;
    }

    public void setCamaraFinal(String camaraFinal) {
        this.camaraFinal = camaraFinal;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(String realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public String getTunel() {
        return tunel;
    }

    public void setTunel(String tunel) {
        this.tunel = tunel;
    }

    @Override
    public String toString() {
        return "TraspasosCamara [id=" + id
                + ", consecutivo="+ consecutivo
                + "]";
    }
}
