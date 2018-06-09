package com.cbj.almacen.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonArla {
    private String sku;
    private String descripcion;
    private String marca;
    private String pesoXpieza;
    private String pesoNeto;
    private String piezasXempaque;
    private String lote;
    private String cantidad;
    private String presoReal;
    private String fechaCad;
    private boolean editado;
    private String rd;
    private String claveProducto;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca2) {
        marca = marca2;
    }

    public String getPesoXpieza() {
        return pesoXpieza;
    }

    public void setPesoXpieza(String pesoXpieza) {
        this.pesoXpieza = pesoXpieza;
    }

    public String getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(String pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public String getPiezasXempaque() {
        return piezasXempaque;
    }

    public void setPiezasXempaque(String piezasXempaque) {
        this.piezasXempaque = piezasXempaque;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPresoReal() {
        return presoReal;
    }

    public void setPresoReal(String presoReal) {
        this.presoReal = presoReal;
    }

    public String getFechaCad() {
        return fechaCad;
    }

    public void setFechaCad(String fechaCad) {
        this.fechaCad = fechaCad;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }
}
