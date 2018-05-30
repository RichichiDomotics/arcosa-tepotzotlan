package com.cbj.almacen.domain;

import com.cbj.almacen.service.ClienteManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jolvera on 17/05/2014.
 */
@Entity
@Table(name = "inventarioview")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name="Inventarioview.getByClienteConsecutivo",
                query="SELECT c FROM Inventarioview c where c.idCliente = :idCliente and c.consecutivo = :consecutivo")
})

public class Inventarioview implements Serializable {

    private static final Logger logger = LoggerFactory
            .getLogger(Inventarioview.class);

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idInventario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInventario;

    private Integer consecutivo;

    private String idCliente;
    private String fechaSalida;
    private Double renglon;
    private String claveProducto;
    private Double pesou;
    private BigDecimal cantidadInventario;
    private String cantidadSalida;
    private String hora;
    private String embalaje;
    private String marca;
    private String lote;
    private Double bultos;
    private Double valorTotal;
    private String tunel;
    private String camara;
    private String fechaCaptura;
    private String diasTunel;
    private String horaCaptura;
    private String descripcion;
    private String servicio;
    private String aplicado;
    private String maniobras;
    private String caducidad;
    private String pesoBruto;
    private String lote2;
    private String lote3;
    private String lote4;
    private String lote5;
    private String lote6;
    private String lote7;
    private String lote8;
    private String tarimas;
    private String tAnden;
    private String calle;
    private String no;
    private String altura;
    private Double disp;
    private String nomCliente;
    private String observaciones;
    private String pedimento;

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getRenglon() {
        return renglon;
    }

    public void setRenglon(Double renglon) {
        this.renglon = renglon;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public Double getPesou() {
        return pesou;
    }

    public void setPesou(Double pesou) {
        this.pesou = pesou;
    }

    public BigDecimal getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(BigDecimal cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public String getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(String cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getBultos() {
        return bultos;
    }

    public void setBultos(Double bultos) {
        this.bultos = bultos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTunel() {
        return tunel;
    }

    public void setTunel(String tunel) {
        this.tunel = tunel;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public String getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(String fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getDiasTunel() {
        return diasTunel;
    }

    public void setDiasTunel(String diasTunel) {
        this.diasTunel = diasTunel;
    }

    public String getHoraCaptura() {
        return horaCaptura;
    }

    public void setHoraCaptura(String horaCaptura) {
        this.horaCaptura = horaCaptura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getAplicado() {
        return aplicado;
    }

    public void setAplicado(String aplicado) {
        this.aplicado = aplicado;
    }

    public String getManiobras() {
        return maniobras;
    }

    public void setManiobras(String maniobras) {
        this.maniobras = maniobras;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(String pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getLote2() {
        return lote2;
    }

    public void setLote2(String lote2) {
        this.lote2 = lote2;
    }

    public String getLote3() {
        return lote3;
    }

    public void setLote3(String lote3) {
        this.lote3 = lote3;
    }

    public String getLote4() {
        return lote4;
    }

    public void setLote4(String lote4) {
        this.lote4 = lote4;
    }

    public String getLote5() {
        return lote5;
    }

    public void setLote5(String lote5) {
        this.lote5 = lote5;
    }

    public String getLote6() {
        return lote6;
    }

    public void setLote6(String lote6) {
        this.lote6 = lote6;
    }

    public String getLote7() {
        return lote7;
    }

    public void setLote7(String lote7) {
        this.lote7 = lote7;
    }

    public String getLote8() {
        return lote8;
    }

    public void setLote8(String lote8) {
        this.lote8 = lote8;
    }

    public String getTarimas() {
        return tarimas;
    }

    public void setTarimas(String tarimas) {
        this.tarimas = tarimas;
    }

    public String gettAnden() {
        return tAnden;
    }

    public void settAnden(String tAnden) {
        this.tAnden = tAnden;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Double getDisp() {
        return disp;
    }

    public void setDisp(Double disp) {
        this.disp = disp;
    }

    @Override
    public String toString() {
        return "Inventario [consecutivo=" + getConsecutivo()+"]";
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPedimento() {
        return pedimento;
    }

    public void setPedimento(String pedimento) {
        this.pedimento = pedimento;
    }
}
