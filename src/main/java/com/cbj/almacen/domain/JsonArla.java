package com.cbj.almacen.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class JsonArla implements Serializable {

    private static final long serialVersionUID = 1L;

    private String alturaTarima;
    private String camaraSelect;
    private String claveProducto;
    private String incidencia;
    private String nivelEstiba;
    private String numTarimas;
    private String rd;
    @JsonProperty("renglon")
    private List<JsonRenglon> renglon;
    private String temProd;
    private String temVehi;
    private String tipoalmacenado;
    private String valMerca;

    public String getAlturaTarima() {
        return alturaTarima;
    }

    public void setAlturaTarima(String alturaTarima) {
        this.alturaTarima = alturaTarima;
    }

    public String getCamaraSelect() {
        return camaraSelect;
    }

    public void setCamaraSelect(String camaraSelect) {
        this.camaraSelect = camaraSelect;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public String getNivelEstiba() {
        return nivelEstiba;
    }

    public void setNivelEstiba(String nivelEstiba) {
        this.nivelEstiba = nivelEstiba;
    }

    public String getNumTarimas() {
        return numTarimas;
    }

    public void setNumTarimas(String numTarimas) {
        this.numTarimas = numTarimas;
    }

    public String getTemProd() {
        return temProd;
    }

    public void setTemProd(String temProd) {
        this.temProd = temProd;
    }

    public String getTemVehi() {
        return temVehi;
    }

    public void setTemVehi(String temVehi) {
        this.temVehi = temVehi;
    }

    public String getTipoalmacenado() {
        return tipoalmacenado;
    }

    public void setTipoalmacenado(String tipoalmacenado) {
        this.tipoalmacenado = tipoalmacenado;
    }

    public String getValMerca() {
        return valMerca;
    }

    public void setValMerca(String valMerca) {
        this.valMerca = valMerca;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public List<JsonRenglon> getRenglon() {
        return renglon;
    }

    public void setRenglon(List<JsonRenglon> renglon) {
        this.renglon = renglon;
    }
}
