package com.cbj.almacen.domain;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class JsonRenglon implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cantidad;
    private String descripcion;
    private boolean editado;
    private String fechaCad;
    private String lote;
    private String marca;
    private String obsercacionesMerca;
    private String pesoBruto;
    private float pesoNeto;
    private float pesoReal;
    private float pesoXpieza;
    private int piezaXempaque;
    private String presentacion;
    private String sku;
    private String terminado;
    private int idCliente;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public String getFechaCad() {
        return fechaCad;
    }

    public void setFechaCad(String fechaCad) {
        this.fechaCad = fechaCad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getObsercacionesMerca() {
        return obsercacionesMerca;
    }

    public void setObsercacionesMerca(String obsercacionesMerca) {
        this.obsercacionesMerca = obsercacionesMerca;
    }

    public String getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(String pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public float getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(float pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public float getPesoReal() {
        return pesoReal;
    }

    public void setPesoReal(float pesoReal) {
        this.pesoReal = pesoReal;
    }

    public float getPesoXpieza() {
        return pesoXpieza;
    }

    public void setPesoXpieza(float pesoXpieza) {
        this.pesoXpieza = pesoXpieza;
    }

    public int getPiezaXempaque() {
        return piezaXempaque;
    }

    public void setPiezaXempaque(int piezaXempaque) {
        this.piezaXempaque = piezaXempaque;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTerminado() {
        return terminado;
    }

    public void setTerminado(String terminado) {
        this.terminado = terminado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
