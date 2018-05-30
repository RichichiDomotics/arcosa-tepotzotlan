package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.DetalleFacturacionCopia;
import com.cbj.almacen.repository.DetalleFacturacionCopiaDao;
import com.cbj.almacen.service.DetalleFacturacionCopiaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class DetalleFacturacionCopiaManagerImpl implements DetalleFacturacionCopiaManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private DetalleFacturacionCopiaDao detalleFacturacionCopiaDao;

    @Transactional(readOnly = false)
    public DetalleFacturacionCopia insertDetalleFacturacion(DetalleFacturacionCopia detalleFacturacion) {
        return detalleFacturacionCopiaDao.insertDetalleFacturacion(detalleFacturacion);
    }

    @Override
    public List<Object> getIdClienteDetalle(Integer idCliente) {
        return detalleFacturacionCopiaDao.getIdClienteDetalle(idCliente);
    }

    @Override
    public List<Object> getIdDetalleFactura(String nofactura) {
        return detalleFacturacionCopiaDao.getIdDetalleFactura(nofactura);
    }

    @Override
    public List<Object> getVentasEjecutivo(String fechainicio, String fechafin){
        return detalleFacturacionCopiaDao.getVentasEjecutivo(fechainicio, fechafin);
    }

    @Override
    public List<Object> getVentasNetasEjecutivo(String fechainicio, String fechafin){
        return detalleFacturacionCopiaDao.getVentasNetasEjecutivo(fechainicio, fechafin);
    }
    @Override
    public double getVentasTotal(String fechainicio, String fechafin){
        return detalleFacturacionCopiaDao.getVentasTotal(fechainicio, fechafin);
    }
    @Override
    public double getVentasNetasEjecutivoTotal(String fechainicio, String fechafin){
        return detalleFacturacionCopiaDao.getVentasNetasEjecutivoTotal(fechainicio, fechafin);
    }
    @Override
    public List<Object> getVentasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo){
        return detalleFacturacionCopiaDao.getVentasEjecutivoId(fechainicio, fechafin, idEjecutivo);
    }
    @Override
    public List<Object> getVentasNetasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo){
        return detalleFacturacionCopiaDao.getVentasNetasEjecutivoId(fechainicio, fechafin, idEjecutivo);
    }
    @Override
    public double getVentasNetasEjecutivoTotalID(String fechainicio, String fechafin, String idEjecutivo){
        return detalleFacturacionCopiaDao.getVentasNetasEjecutivoTotalID(fechainicio, fechafin, idEjecutivo);
    }
}
