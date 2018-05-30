package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.repository.SalidasDetalleDao;
import com.cbj.almacen.service.SalidasDetalleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
@Component
public class SalidasDetalleManagerImpl implements SalidasDetalleManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private SalidasDetalleDao salidasDetalleDao;


    @Override
    public int registraSalidaDetalle(final SalidasDetalle salidasDetalle) {
        return salidasDetalleDao.insertSalidasDetalle(salidasDetalle);
    }

    @Override
    public List<SalidasDetalle> findSalidaByClienteConsecutivo(final String idCliente, final int folioSalida) {
        return salidasDetalleDao.findSalidaByClienteConsecutivo(idCliente, folioSalida);
    }

    @Override
    public List<SalidasDetalle> findSalidaFolioSalida(final int folioSalida){
        return salidasDetalleDao.findSalidaFolioSalida(folioSalida);
    }

    @Override
     public List<SalidasDetalle> getSalidaByConsecutivo(final int consecutivo) {
        return salidasDetalleDao.findSalidaByConsecutivo(consecutivo);
    }

    @Override
    public List<SalidasDetalle> getSalidaByConsecutivoFolioSalida(final int consecutivo,final int folioSalida) {
        return salidasDetalleDao.findSalidaByConsecutivoFolioSalida(consecutivo, folioSalida);
    }

    @Override
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final String idCliente,final int consecutivo,final int folioSalida) {
        return salidasDetalleDao.findSalidaByConsecutivoFolioSalida(consecutivo,folioSalida);
    }

    @Override
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final int idCliente,final int folioSalida){
        return salidasDetalleDao.findSalidaByConsecutivoFolioSalida(idCliente,folioSalida);
    }

    @Override
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final String idCliente,final int folioSalida){
        return salidasDetalleDao.findSalidaByConsecutivoFolioSalida(Integer.parseInt(idCliente),folioSalida);
    }

    @Override
    public List<Object[]> getKilosByDay(final Date fechaSalida) {
        return salidasDetalleDao.getKilosByDay(fechaSalida);
    }

    @Override
    public List<Object[]> getKilosByFechas(final Date fechaInicio, final Date fechaFin) {
        return salidasDetalleDao.getKilosByFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin){
        return salidasDetalleDao.getKilosByFechasConcentrado(fechaInicio, fechaFin);
    }

    @Override
    public List<Object[]> getKilosByFechasCliente(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        return salidasDetalleDao.getKilosByFechasCliente(fechaInicio,fechaFin,idCliente);
    }

    @Override
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        return salidasDetalleDao.getKilosByFechasClienteDetalle(fechaInicio,fechaFin,idCliente);
    }

    @Override
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        return salidasDetalleDao.getKilosByFechasClienteDetalleTotal(fechaInicio,fechaFin,idCliente);
    }

    @Override
    public List<Object[]> getReporteByCamara(final Date fechaInicio, final Date fechaFin, final String idCamara) {
        return salidasDetalleDao.getReporteByCamara(fechaInicio,fechaFin,idCamara);
    }

    @Override
    public List<Object[]> getReporteByCamaraVacia(final Date fechaInicio, final Date fechaFin) {
        return salidasDetalleDao.getReporteByCamaraVacia(fechaInicio,fechaFin);
    }
    
    @Override
    public List<Object[]> getArrastreSaldosDetalle(final int consecutivo) {
        return salidasDetalleDao.getArrastreSaldosDetalle(consecutivo);
    }
    
    @Override
    public List<SalidasDetalle> getArrastreSaldosDetalleAgrupado(final int consecutivo) {
        return salidasDetalleDao.getArrastreSaldosDetalleAgrupado(consecutivo);
    }

    @Override
    public List<SalidasDetalle> findSalidaByFolioSalida(final int consecutivo){
        return salidasDetalleDao.findSalidaByFolioSalida(consecutivo);
    }
}
