package com.cbj.almacen.service;

import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.domain.Vehiculo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
public interface DetalleRdManager extends Serializable {

    public boolean setRegistroEntrada(DetallesRd detallesRd);
    public boolean updateRegistroEntrada(DetallesRd detallesRd);
    public List<DetallesRd> getDetallesRdSalida(String consecutivo, String idCliente, String descripcion, String caducidad);
    public List<DetallesRd> getAll();
    public List<DetallesRd> getAllByConsecutivo(int consecutivo);
    public List<DetallesRd> getAllByCliente(String idCliente);
    public List<DetallesRd> getAllByFolioSalida(int folioSalida, int consecutivo, String idCliente);
    public DetallesRd getByIdDetalle(int idDetalle);
    public List<Object[]> getKilosByDay(Date fechaSalida);
    public List<Object[]> getKilosByFechas(Date fechaInicio,Date fechaFin);
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin);
    public List<Object[]> getKilosByFechasCliente(Date fechaInicio,Date fechaFin, String idCliente);
    public List<Object[]> getKilosByFechasClienteDetalle(Date fechaInicio,Date fechaFin, String idCliente);
    public Object getKilosByFechasClienteDetalleTotal(Date fechaInicio,Date fechaFin, String idCliente);
    public List<Object[]> getReporteByCamara(Date fechaInicio,Date fechaFin, String idCamara);
    public List<Object[]> getReporteByCamaraVacia(Date fechaInicio,Date fechaFin);
    public List<DetallesRd> getArrastreSaldosDetalleRdAgrupado(final int consecutivo);
    public List<Object> getRDAlmacenajesNuevos(String idcliente, Date fechaini, Date fechafin);
    public List<Object> getRDTunelesNuevos(String idcliente, Date fechaini, Date fechafin);
    public List<Object> getRDMEYS(String idcliente, Date fechaini, Date fechafin);
    public List<Object[]> getRendimientos(Date fechaini, Date fechafin);

}
