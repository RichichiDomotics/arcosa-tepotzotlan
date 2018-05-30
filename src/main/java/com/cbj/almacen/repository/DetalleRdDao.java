package com.cbj.almacen.repository;

import java.util.Date;
import java.util.List;

import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.domain.Vehiculo;

/**
 * Created by jolvera on 17/05/2014.
 */
public interface DetalleRdDao {

    public boolean insertRegistroDetallesRd(DetallesRd detalles);
    public boolean updateRegistroDetallesRd(DetallesRd detalles);
    public boolean deleteRegistroDetallesRd(int id);
    public DetallesRd findRegistroDetallesRd(int id);
    public List<DetallesRd> findDetallesSalidas(String consecutivo, String idCliente, String descripcion, String caducidad);
    public List<DetallesRd> getAll();
    public List<DetallesRd> getAllByConsecutivo(int consecutivo);
    public List<DetallesRd> getAllByCliente(String idCliente);
    public List<DetallesRd> getAllByFolioSalida(int folioSalida, int consecutivo, String idCliente);
    public List<Object[]> getKilosByDay(final Date fechaSalida);
    public List<Object[]> getKilosByFechas(final Date fechaInicio, final Date fechaFin);
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin);
    public List<Object[]> getKilosByFechasCliente(final Date fechaInicio, final Date fechaFin, final String idCliente);
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio, final Date fechaFin, final String idCliente);
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio, final Date fechaFin, final String idCliente);
    public List<Object[]> getReporteByCamara(final Date fechaInicio, final Date fechaFin, final String idCamara);
    public List<Object[]> getReporteByCamaraVacia(final Date fechaInicio, final Date fechaFin);
    public List<DetallesRd> getArrastreSaldosDetalleRdAgrupado(final int consecutivo);
    public List<Object> getRDAlmacenajesNuevos(String idcliente, Date fechaini, Date fechafin);
    public List<Object> getRDTunelesNuevos(String idcliente, Date fechaini, Date fechafin);
    public List<Object> getRDMEYS(String idcliente, Date fechaini, Date fechafin);
    public List<Object[]> getRendimientos(Date fechaini, Date fechafin);

}
