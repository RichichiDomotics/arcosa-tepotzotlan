package com.cbj.almacen.domain;

/**
 * Created by Richard on 07/07/2015.
 */

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "notas")
@NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Notas.getNotasCredito",
                query="SELECT SUM(c.SUBTOTAL) AS tnotas,cl.NOM_EJECUTIVO FROM Notas c, Clientes cl WHERE c.ID_CLIENTE = cl.ID_CLIENTE AND (c.FECHANOTA >= :fechainicio AND c.FECHANOTA <= :fechafin) GROUP BY cl.NOM_EJECUTIVO"),
        @org.hibernate.annotations.NamedQuery(name="Notas.getDetalleNotasCredito",
                query="SELECT SUM(c.SUBTOTAL) AS Ntotal, c.NNOTA, c.FECHANOTA, c.ID_CLIENTE, c.FACTURAA, c.FACTURAS, c.AUTORIZA, cl.NOM_CLIENTE, c.MOTIVO, cl.NOM_EJECUTIVO FROM Notas c, Clientes cl WHERE c.ID_CLIENTE = cl.ID_CLIENTE AND (c.FECHANOTA >= :fechainicio AND c.FECHANOTA <= :fechafin) GROUP BY c.NNOTA, c.FECHANOTA, c.ID_CLIENTE, c.FACTURAA, c.FACTURAS, c.AUTORIZA, cl.NOM_CLIENTE, c.MOTIVO, cl.NOM_EJECUTIVO ORDER BY cl.NOM_EJECUTIVO"),
        @org.hibernate.annotations.NamedQuery(name="Notas.getNotasCreditoTotal",
                query="SELECT SUM(c.SUBTOTAL) AS tnotas FROM Notas c WHERE (c.FECHANOTA >= :fechainicio AND c.FECHANOTA <= :fechafin)"),
        @org.hibernate.annotations.NamedQuery(name="Notas.getNotasCreditoId",
                query="SELECT SUM(c.SUBTOTAL) AS tnotas,cl.NOM_EJECUTIVO FROM Notas c, Clientes cl WHERE c.ID_CLIENTE = cl.ID_CLIENTE AND (c.FECHANOTA >= :fechainicio AND c.FECHANOTA <= :fechafin) and (cl.ID_EJECUTIVO= :idEjecutivo) GROUP BY cl.NOM_EJECUTIVO"),
        @org.hibernate.annotations.NamedQuery(name="Notas.getDetalleNotasCreditoId",
                query="SELECT SUM(c.SUBTOTAL) AS Ntotal, c.NNOTA, c.FECHANOTA, c.ID_CLIENTE, c.FACTURAA, c.FACTURAS, c.AUTORIZA, cl.NOM_CLIENTE, c.MOTIVO, cl.NOM_EJECUTIVO FROM Notas c, Clientes cl WHERE c.ID_CLIENTE = cl.ID_CLIENTE AND (c.FECHANOTA >= :fechainicio AND c.FECHANOTA <= :fechafin) and (cl.ID_EJECUTIVO= :idEjecutivo) GROUP BY c.NNOTA, c.FECHANOTA, c.ID_CLIENTE, c.FACTURAA, c.FACTURAS, c.AUTORIZA, cl.NOM_CLIENTE, c.MOTIVO, cl.NOM_EJECUTIVO ORDER BY cl.NOM_EJECUTIVO"),
        @org.hibernate.annotations.NamedQuery(name="Notas.getNotasCreditoTotalId",
                query="SELECT SUM(c.SUBTOTAL) AS tnotas FROM Notas c, Clientes cl WHERE c.ID_CLIENTE = cl.ID_CLIENTE AND (c.FECHANOTA >= :fechainicio AND c.FECHANOTA <= :fechafin) and (cl.ID_EJECUTIVO= :idEjecutivo)"),
        @NamedQuery(name="Notas.getMaxId",
                query="SELECT MAX(c.FOLIO) FROM Notas c "),
        @NamedQuery(name="Notas.getByFolio",
                query="SELECT c FROM Notas c where FOLIO=:folio")

})
public class Notas  {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer ID;
    private int FOLIO;
    private int NNOTA;
    private String FECHANOTA;
    private Integer ID_CLIENTE;
    private String FACTURAA;
    private String FACTURAS;
    private String CONCEPTO;
    private String MOTIVO;
    private double SUBTOTAL;
    private String AUTORIZA;
    private String REALIZO;
    private String OBSERVACIONES;
    private String FECHAA;
    private String FECHAS;
    private String CAMARA;



    public int getFOLIO() {
        return FOLIO;
    }

    public void setFOLIO(int FOLIO) {
        this.FOLIO = FOLIO;
    }

    public int getNNOTA() {
        return NNOTA;
    }

    public void setNNOTA(int NNOTA) {
        this.NNOTA = NNOTA;
    }

    public String getFECHANOTA() {
        return FECHANOTA;
    }

    public void setFECHANOTA(String FECHANOTA) {
        this.FECHANOTA = FECHANOTA;
    }

    public Integer getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(Integer ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getFACTURAA() {
        return FACTURAA;
    }

    public void setFACTURAA(String FACTURAA) {
        this.FACTURAA = FACTURAA;
    }

    public String getFACTURAS() {
        return FACTURAS;
    }

    public void setFACTURAS(String FACTURAS) {
        this.FACTURAS = FACTURAS;
    }

    public String getCONCEPTO() {
        return CONCEPTO;
    }

    public void setCONCEPTO(String CONCEPTO) {
        this.CONCEPTO = CONCEPTO;
    }

    public String getMOTIVO() {
        return MOTIVO;
    }

    public void setMOTIVO(String MOTIVO) {
        this.MOTIVO = MOTIVO;
    }

    public double getSUBTOTAL() {
        return SUBTOTAL;
    }

    public void setSUBTOTAL(double SUBTOTAL) {
        this.SUBTOTAL = SUBTOTAL;
    }

    public String getAUTORIZA() {
        return AUTORIZA;
    }

    public void setAUTORIZA(String AUTORIZA) {
        this.AUTORIZA = AUTORIZA;
    }

    public String getREALIZO() {
        return REALIZO;
    }

    public void setREALIZO(String REALIZO) {
        this.REALIZO = REALIZO;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public String getFECHAA() {
        return FECHAA;
    }

    public void setFECHAA(String FECHAA) {
        this.FECHAA = FECHAA;
    }

    public String getFECHAS() {
        return FECHAS;
    }

    public void setFECHAS(String FECHAS) {
        this.FECHAS = FECHAS;
    }

    public String getCAMARA() {
        return CAMARA;
    }

    public void setCAMARA(String CAMARA) {
        this.CAMARA = CAMARA;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
