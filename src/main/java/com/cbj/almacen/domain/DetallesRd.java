package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "detalles_rd")
@NamedQueries({
        @NamedQuery(name="DetallesRd.getAll",
                query="SELECT c FROM DetallesRd c"),
        @NamedQuery(name="DetallesRd.getAllByConsecutivo",
                query="SELECT c FROM DetallesRd c where c.consecutivo = :consecutivo order by c.renglon"),
        @NamedQuery(name="DetallesRd.getAllByCliente",
                query="SELECT c FROM DetallesRd c where c.idCliente = :idCliente"),        
        @NamedQuery(name="DetallesRd.getAllByFolioSalida",
        		query="SELECT e FROM Salidas c ,SalidasDetalle d, DetallesRd e WHERE c.folioSalida = :folioSalida AND c.consecutivo = :consecutivo AND c.idCliente = :idCliente AND c.folioSalida = d.folioSalida AND (c.consecutivo = e.consecutivo AND  c.idCliente = e.idCliente) group by d.folio"),
        @NamedQuery(name="DetallesRd.getKilosByDay",
                query="SELECT c.fechaCaptura, SUM(c.pesou * c.cantidad),SUM(c.cantidad),c.consecutivo, c.idCliente,cl.NOM_CLIENTE, c.descripcion, c.camara, c.observaciones, c.posiciones FROM DetallesRd c, Clientes cl where c.idCliente = cl.ID_CLIENTE  and date(c.fechaCaptura) like :fechaSalida group by date(c.fechaCaptura),c.consecutivo order by c.consecutivo+0"),
        @NamedQuery(name="DetallesRd.getKilosByFechas",
                query="SELECT c.fechaCaptura, SUM(c.pesou * c.cantidad), SUM(c.cantidad),c.consecutivo,c.idCliente,cl.NOM_CLIENTE FROM DetallesRd c, Clientes cl where c.idCliente = cl.ID_CLIENTE and date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin group by date(c.fechaCaptura),c.consecutivo"),
        @NamedQuery(name="DetallesRd.getKilosByFechasConcentrado",
                query="SELECT c.fechaCaptura, SUM(c.pesou * c.cantidad), SUM(c.cantidad),c.consecutivo,c.idCliente,cl.NOM_CLIENTE FROM DetallesRd c, Clientes cl where c.idCliente = cl.ID_CLIENTE and date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin group by date(c.fechaCaptura)"),
        @NamedQuery(name="DetallesRd.getKilosByFechasCliente",
                query="SELECT c.fechaCaptura, SUM(c.cantidad), SUM(c.pesou * c.cantidad),c.consecutivo FROM DetallesRd c where date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin and c.idCliente = :idCliente group by date(c.fechaCaptura),c.consecutivo"),
        @NamedQuery(name="DetallesRd.getKilosByFechasClienteDetalle",
                query="SELECT c.fechaCaptura, c.consecutivo, c.renglon, c.camara, c.descripcion,c.caducidad,c.lote,c.marca, c.cantidad, c.pesou ,c.cantidad*c.pesou FROM DetallesRd c where date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin and c.idCliente = :idCliente"),
        @NamedQuery(name="DetallesRd.getKilosByFechasClienteDetalleTotal",
                query="SELECT c.fechaCaptura, c.consecutivo, c.renglon, c.camara, c.claveProducto,c.caducidad,c.lote,c.marca, SUM(c.cantidad), SUM(c.pesou) ,sum(c.cantidad*c.pesou) FROM DetallesRd c where date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin and c.idCliente = :idCliente"),
        @NamedQuery(name="DetallesRd.getReporteByCamara",
                query="SELECT c.idCliente, d.NOM_CLIENTE, c.consecutivo, SUM(c.cantidad), sum(c.cantidad*c.pesou), c.camara  FROM DetallesRd c , Clientes d where c.idCliente = d.ID_CLIENTE and date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin and c.camara = :idCamara group by date(c.fechaCaptura),c.consecutivo"),
        @NamedQuery(name="DetallesRd.getReporteByCamaraVacia",
                query="SELECT c.idCliente, d.NOM_CLIENTE, c.consecutivo, SUM(c.cantidad), sum(c.cantidad*c.pesou), c.camara  FROM DetallesRd c , Clientes d where c.idCliente = d.ID_CLIENTE and date(c.fechaCaptura)>= :fechaInicio and date(c.fechaCaptura)<= :fechaFin group by date(c.fechaCaptura),c.consecutivo"),
		@NamedQuery(name = "DetallesRd.getArrastreSaldosDetalleRdAgrupado",
		        query = "SELECT c.renglon, SUM(c.cantidad), SUM(c.cantidad*c.pesou), l.ID_CLIENTE,l.NOM_CLIENTE  FROM DetallesRd c, Clientes l where c.consecutivo = :consecutivo and c.idCliente = l.ID_CLIENTE group by c.renglon order by c.renglon"),
        @NamedQuery(name = "DetallesRd.getRDAlmacenajesNuevos",
                query = "SELECT d.idCliente, d.consecutivo, date_format(d.fechaCaptura, '%Y-%m-%d'),date(d.fechaCaptura +  (c.periodo))  , d.camara, round(sum(d.cantidad * d.pesou),2),'ALM',c.cuotaAlmacenaje, round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje,round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje, '',curdate() FROM DetallesRd d, Convenios c WHERE c.claveProducto=d.claveProducto and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin and d.idCliente= :idcliente GROUP BY d.consecutivo")
        

})
public class DetallesRd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idDetalle")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDetalle;
    private Integer idIngresoVehiculo;
    @Min(0)
	private String idCliente;
    @NotNull (message = "Valor requerido")
    private Integer consecutivo;
    @NotNull (message = "Valor requerido")
    private Double pesou;
    @NotNull (message = "Valor requerido")
    private Double cantidad;
    private Double pesoTotal;
    private Double valorTotal;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaCaptura;
    private String horaCaptura;
    private String tunel;
    private String camara;
    private String diasTunel;
    private String claveProducto;
    @NotNull
    @Size(min = 1, message = "Valor requerido")
    private String marca;
    @NotNull (message = "Valor requerido")
    private Double renglon;
    @NotNull
    @Size(min = 1, message = "Valor requerido")
    private String thermoking;
    @NotNull
    @Size(min = 1, message = "Valor requerido")
    private String temperaturaAnden;
    @NotNull
    @Size(min = 1, message = "Valor requerido")
    private String embalaje;
    private String lote;
    private Double bultos;
    private String clienteCantidad;
    private String clienteKilos;
    private String capturista;
    private String observaciones;
    private String anden;
    private String capturado;
    private String almacenista;
    private String validador;
    private String descripcion;
    private String servicio;
    private String plaga;
    private String libreVidrios;
    private String vehiculoLimpio;
    private String vehiculoBuenEstado;
    @NotNull
    @Size(min = 1, message = "Valor requerido")
    private String caducidad;
    private String fechaA;
    private Double pesoBruto;
    private String apertura;
    private String cierre;
    private String lote2;
    private String lote3;
    private String lote4;
    private String lote5;
    private String lote6;
    private String lote7;
    private String lote8;
    private Double aplicado;
    private String tarimas;
    private String estibas;
    private String altura;
    private String almaen;
    private String posiciones;
    private String calle;
    private String fila;
    private String status;
    private String incidenciaAnden;
    private String impedimento;

	@Override
	public String toString() {
		return "DetallesRd [idIngresoVehiculo=" + getIdIngresoVehiculo() + ", idDetalle=" + getIdDetalle() +", CONSECUTIVO=" + getConsecutivo() + ", ID_CLIENTE="
				+ getIdCliente() + ", PESOU=" + getPesou() +", CANTIDAD="
                + getCantidad()
				+ ", PESO_TOTAL=" + pesoTotal + ", VALOR_TOTAL="
				+ valorTotal + ", FECHACAPTURA=" + fechaCaptura + ", HORACAPTURA="
				+ horaCaptura + ", TUNEL=" + tunel
				+ ", CAMARA=" + camara + ", DIASTUNEL="
				+ diasTunel + ", CLAVE_PRODUCTO=" + claveProducto + " , marca=" + marca
				+ ", RENGLON=" + renglon
				+ ", THERMOKING=" + thermoking + ", TEMPERATURA_ANDEN=" + temperaturaAnden
				+ ", EMBALAJE=" + embalaje + ", LOTE=" + lote
				+ ", BULTOS=" + bultos + ", CLIENTE_CANTIDAD=" + clienteCantidad
				+ ", CLIENTE_KILOS=" + clienteKilos + ", CAPTURISTA=" + capturista
				+ ", OBSERVACIONES=" + observaciones + ", ANDEN=" + anden
				+ ", CAPTURADO=" + capturado + ", ALMACENISTA=" + almacenista
				+ ", VALIDADOR=" + validador+ ", DESCRIPCION=" + descripcion
				+ ", SERVICIO=" + servicio + ", CADUCIDAD=" + caducidad
				+ ", FECHAA=" + fechaA + ", PESOBRUTO=" + pesoBruto
				+ ", APERTURA=" + apertura + ", CIERRE=" + cierre
				+ ", LOTE2=" + lote2 + ", LOTE3=" + lote3
				+ ", LOTE4=" + lote4 + ", LOTE5=" + lote5
				+ ", LOTE6=" + lote6 + ", LOTE7=" + lote7
				+ ", LOTE8=" + lote8 + ", APLICADO=" + aplicado
				+ ", TARIMAS=" + tarimas + ", ESTIBAS=" + estibas
				+ ", ALTURA=" + altura + ", ALMAEN=" + almaen+ ", posiciones=" + posiciones+", status=" + status+", calle=" + calle+", fila=" + fila
				+", incidenciaAnden=" + incidenciaAnden
				+", impedimento=" + impedimento
				+ "]";
	}


    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdIngresoVehiculo() {
        return idIngresoVehiculo;
    }

    public void setIdIngresoVehiculo(Integer idIngresoVehiculo) {
        this.idIngresoVehiculo = idIngresoVehiculo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Double getPesou() {
        return pesou;
    }

    public void setPesou(Double pesou) {
        this.pesou = pesou;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getHoraCaptura() {
        return horaCaptura;
    }

    public void setHoraCaptura(String horaCaptura) {
        this.horaCaptura = horaCaptura;
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

    public String getDiasTunel() {
        return diasTunel;
    }

    public void setDiasTunel(String diasTunel) {
        this.diasTunel = diasTunel;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getRenglon() {
        return renglon;
    }

    public void setRenglon(Double renglon) {
        this.renglon = renglon;
    }

    public String getThermoking() {
        return thermoking;
    }

    public void setThermoking(String thermoking) {
        this.thermoking = thermoking;
    }

    public String getTemperaturaAnden() {
        return temperaturaAnden;
    }

    public void setTemperaturaAnden(String temperaturaAnden) {
        this.temperaturaAnden = temperaturaAnden;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
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

    public String getClienteCantidad() {
        return clienteCantidad;
    }

    public void setClienteCantidad(String clienteCantidad) {
        this.clienteCantidad = clienteCantidad;
    }

    public String getClienteKilos() {
        return clienteKilos;
    }

    public void setClienteKilos(String clienteKilos) {
        this.clienteKilos = clienteKilos;
    }

    public String getCapturista() {
        return capturista;
    }

    public void setCapturista(String capturista) {
        this.capturista = capturista;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAnden() {
        return anden;
    }

    public void setAnden(String anden) {
        this.anden = anden;
    }

    public String getCapturado() {
        return capturado;
    }

    public void setCapturado(String capturado) {
        this.capturado = capturado;
    }

    public String getAlmacenista() {
        return almacenista;
    }

    public void setAlmacenista(String almacenista) {
        this.almacenista = almacenista;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
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

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getFechaA() {
        return fechaA;
    }

    public void setFechaA(String fechaA) {
        this.fechaA = fechaA;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
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

    public Double getAplicado() {
        return aplicado;
    }

    public void setAplicado(Double aplicado) {
        this.aplicado = aplicado;
    }

    public String getTarimas() {
        return tarimas;
    }

    public void setTarimas(String tarimas) {
        this.tarimas = tarimas;
    }

    public String getEstibas() {
        return estibas;
    }

    public void setEstibas(String estibas) {
        this.estibas = estibas;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getAlmaen() {
        return almaen;
    }

    public void setAlmaen(String almaen) {
        this.almaen = almaen;
    }

    public String getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(String posiciones) {
        this.posiciones = posiciones;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaga() {
        return plaga;
    }

    public void setPlaga(String plaga) {
        this.plaga = plaga;
    }

    public String getLibreVidrios() {
        return libreVidrios;
    }

    public void setLibreVidrios(String libreVidrios) {
        this.libreVidrios = libreVidrios;
    }

    public String getVehiculoLimpio() {
        return vehiculoLimpio;
    }

    public void setVehiculoLimpio(String vehiculoLimpio) {
        this.vehiculoLimpio = vehiculoLimpio;
    }

    public String getVehiculoBuenEstado() {
        return vehiculoBuenEstado;
    }

    public void setVehiculoBuenEstado(String vehiculoBuenEstado) {
        this.vehiculoBuenEstado = vehiculoBuenEstado;
    }
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }
    
    public String getIncidenciaAnden() {
        return incidenciaAnden;
    }

    public void setIncidenciaAnden(String incidenciaAnden) {
        this.incidenciaAnden = incidenciaAnden;
    }
    
    public String getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(String impedimento) {
        this.impedimento = impedimento;
    }
}