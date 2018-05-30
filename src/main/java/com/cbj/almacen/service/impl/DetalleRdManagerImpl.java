package com.cbj.almacen.service.impl;

import java.util.Date;
import java.util.List;

import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.repository.DetalleRdDao;
import com.cbj.almacen.service.DetalleRdManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jolvera on 17/05/2014.
 */
@Component
public class DetalleRdManagerImpl implements DetalleRdManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private DetalleRdDao detalleRdDao;

    @Override
    public boolean setRegistroEntrada(DetallesRd detallesRd) {
        return detalleRdDao.insertRegistroDetallesRd(detallesRd);
    }

    @Override
    public boolean updateRegistroEntrada(DetallesRd detallesRd) {
        return detalleRdDao.updateRegistroDetallesRd(detallesRd);
    }

    public List<DetallesRd> getDetallesRdSalida(String consecutivo, String idCliente, String descripcion, String caducidad) {
    	return detalleRdDao.findDetallesSalidas(consecutivo, idCliente, descripcion, caducidad);
        }

    public List<DetallesRd> getAll() {
        return detalleRdDao.getAll();
    }

    public List<DetallesRd> getAllByConsecutivo(int consecutivo) {
        return detalleRdDao.getAllByConsecutivo(consecutivo);
    }
    
    public List<DetallesRd> getAllByCliente(String idCliente) {
        return detalleRdDao.getAllByCliente(idCliente);
    }
    
    public List<DetallesRd> getAllByFolioSalida(int folioSalida, int consecutivo, String idCliente) {
        return detalleRdDao.getAllByFolioSalida(folioSalida, consecutivo, idCliente);
    }

    @Override
    public DetallesRd getByIdDetalle(int idDetalle) {
        return detalleRdDao.findRegistroDetallesRd(idDetalle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> getKilosByDay(final Date fechaSalida) {
        return detalleRdDao.getKilosByDay(fechaSalida);
    }

    @Override
    public List<Object[]> getKilosByFechas(final Date fechaInicio, final Date fechaFin) {
        return detalleRdDao.getKilosByFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin){
        return detalleRdDao.getKilosByFechasConcentrado(fechaInicio, fechaFin);
    }

    @Override
    public List<Object[]> getKilosByFechasCliente(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        return detalleRdDao.getKilosByFechasCliente(fechaInicio,fechaFin,idCliente);
    }

    @Override
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        return detalleRdDao.getKilosByFechasClienteDetalle(fechaInicio,fechaFin,idCliente);
    }

    @Override
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        return detalleRdDao.getKilosByFechasClienteDetalleTotal(fechaInicio,fechaFin,idCliente);
    }

    @Override
    public List<Object[]> getReporteByCamara(final Date fechaInicio, final Date fechaFin, final String idCamara) {
        return detalleRdDao.getReporteByCamara(fechaInicio,fechaFin,idCamara);
    }

    @Override
    public List<Object[]> getReporteByCamaraVacia(final Date fechaInicio, final Date fechaFin) {
        return detalleRdDao.getReporteByCamaraVacia(fechaInicio,fechaFin);
    }
    
    @Override
    public List<DetallesRd> getArrastreSaldosDetalleRdAgrupado(final int consecutivo) {
        return detalleRdDao.getArrastreSaldosDetalleRdAgrupado(consecutivo);
    }

    @Override
    public List<Object> getRDAlmacenajesNuevos(String idcliente, Date fechaini, Date fechafin){
        return detalleRdDao.getRDAlmacenajesNuevos(idcliente, fechaini, fechafin);
    }
    @Override
    public List<Object> getRDTunelesNuevos(String idcliente, Date fechaini, Date fechafin){
        return detalleRdDao.getRDTunelesNuevos(idcliente, fechaini, fechafin);
    }
    @Override
    public List<Object> getRDMEYS(String idcliente, Date fechaini, Date fechafin){
        return detalleRdDao.getRDMEYS(idcliente, fechaini, fechafin);
    }

    @Override
    public List<Object[]> getRendimientos(Date fechaini, Date fechafin){
        return detalleRdDao.getRendimientos(fechaini, fechafin);
    }

}
