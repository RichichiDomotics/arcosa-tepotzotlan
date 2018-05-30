package com.cbj.almacen.domain;

/**
 * Created by Ricardo on 14/01/2016.
 */
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BSALIDASDETALLE")
@NamedQueries({
        @NamedQuery(name = "BsalidasDetalle.getFechasID",
                query = "select c from BsalidasDetalle c where (date(c.FECHASALIDA) >= :fechaini and date(c.FECHASALIDA) <= :fechafin) and c.ID_CLIENTE = :idCliente group by c.CONSECUTIVO"),
        @NamedQuery(name = "BsalidasDetalle.getFolioSalida",
                query = "select c from BsalidasDetalle c where c.FOLIOSALIDA = :foliosalida order by c.CONSECUTIVO, c.RENGLON")

})

public class BsalidasDetalle implements  Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FOLIO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer FOLIO;
    private String FOLIOSALIDA;
    private String ID_CLIENTE;
    private String FECHASALIDA;
    private String PRODUCTO;
    private String CANTIDAD_SALIDA;
    private String PESOU;
    private String CONSECUTIVO;
    private String EMBALAJE;
    private String RENGLON;
    private String ENTREGARA;
    private String TIPO;
    private String DESCRIPCION;
    private String LOTE;
    private String MARCA;
    private String CAMARA;
    private String CADUCIDAD;
    private String FBACHOCO;
    private String DESTINO;
    private String T;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFOLIO() {
        return FOLIO;
    }

    public void setFOLIO(Integer FOLIO) {
        this.FOLIO = FOLIO;
    }

    public String getFOLIOSALIDA() {
        return FOLIOSALIDA;
    }

    public void setFOLIOSALIDA(String FOLIOSALIDA) {
        this.FOLIOSALIDA = FOLIOSALIDA;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getFECHASALIDA() {
        return FECHASALIDA;
    }

    public void setFECHASALIDA(String FECHASALIDA) {
        this.FECHASALIDA = FECHASALIDA;
    }

    public String getPRODUCTO() {
        return PRODUCTO;
    }

    public void setPRODUCTO(String PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    public String getCANTIDAD_SALIDA() {
        return CANTIDAD_SALIDA;
    }

    public void setCANTIDAD_SALIDA(String CANTIDAD_SALIDA) {
        this.CANTIDAD_SALIDA = CANTIDAD_SALIDA;
    }

    public String getPESOU() {
        return PESOU;
    }

    public void setPESOU(String PESOU) {
        this.PESOU = PESOU;
    }

    public String getCONSECUTIVO() {
        return CONSECUTIVO;
    }

    public void setCONSECUTIVO(String CONSECUTIVO) {
        this.CONSECUTIVO = CONSECUTIVO;
    }

    public String getEMBALAJE() {
        return EMBALAJE;
    }

    public void setEMBALAJE(String EMBALAJE) {
        this.EMBALAJE = EMBALAJE;
    }

    public String getRENGLON() {
        return RENGLON;
    }

    public void setRENGLON(String RENGLON) {
        this.RENGLON = RENGLON;
    }

    public String getENTREGARA() {
        return ENTREGARA;
    }

    public void setENTREGARA(String ENTREGARA) {
        this.ENTREGARA = ENTREGARA;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getLOTE() {
        return LOTE;
    }

    public void setLOTE(String LOTE) {
        this.LOTE = LOTE;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getCAMARA() {
        return CAMARA;
    }

    public void setCAMARA(String CAMARA) {
        this.CAMARA = CAMARA;
    }

    public String getCADUCIDAD() {
        return CADUCIDAD;
    }

    public void setCADUCIDAD(String CADUCIDAD) {
        this.CADUCIDAD = CADUCIDAD;
    }

    public String getFBACHOCO() {
        return FBACHOCO;
    }

    public void setFBACHOCO(String FBACHOCO) {
        this.FBACHOCO = FBACHOCO;
    }

    public String getDESTINO() {
        return DESTINO;
    }

    public void setDESTINO(String DESTINO) {
        this.DESTINO = DESTINO;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }
}
