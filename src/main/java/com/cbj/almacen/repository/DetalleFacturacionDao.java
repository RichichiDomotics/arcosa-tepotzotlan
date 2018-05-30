package com.cbj.almacen.repository;

import com.cbj.almacen.domain.DetalleFacturacion;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface DetalleFacturacionDao {

   public DetalleFacturacion insertDetalleFacturacion(DetalleFacturacion detalleFacturacion);
   public List<Object> getIdClienteDetalle(Integer idCliente);
   public List<Object> getIdDetalleFactura(String nofactura);
   public List<Object> getVentasEjecutivo(String fechainicio, String fechafin);
   public List<Object> getVentasNetasEjecutivo(String fechainicio, String fechafin);
   public double getVentasTotal(String fechainicio, String fechafin);
   public double getVentasNetasEjecutivoTotal(String fechainicio, String fechafin);
   public List<Object> getVentasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo);
   public List<Object> getVentasNetasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo);
   public double getVentasNetasEjecutivoTotalID(String fechainicio, String fechafin, String idEjecutivo);
   public List<Object> getFacturasByRD(String consecutivo);
   public List<Object> getFacturasByFecha(String fecha);
   public Double getFacturasNetas(String fechaini, String fechafin);
   public Double getFacturasImpresas(String fechaini, String fechafin);
   public Double getFacturasTotal(String fechaini, String fechafin);
   public List<Object> getPendientes(String fechaini, String fechafin);
   public List<Object> getFacturasConcepto(String fechaini, String fechafin);
   public List<Object> getFacturasClienteRubro(String fechaini, String fechafin);
   public List<Object> getFacturasClienteRubroDetalle(String fechaini, String fechafin);
   public List<Object> getAll(String fechaini, String fechafin);
   public List<Object> getPreFacturas(String fechaini, String fechafin, String idcliente);
}
