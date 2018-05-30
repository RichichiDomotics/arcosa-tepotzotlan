package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.Inventarioview;
import com.cbj.almacen.repository.InventarioDao;
import com.cbj.almacen.service.InventarioManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
@Component
public class InventarioManagerImpl implements InventarioManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private InventarioDao inventarioDao;

    @Override
    public boolean setInventario(Inventario inventario) {
        return inventarioDao.insertInventario(inventario);
    }

    @Override
    public boolean updateInventario(Inventario inventario) {
        return inventarioDao.updateInventario(inventario);
    }

    @Override
    public List<Inventario> getInventarioByConsulta(String consecutivo, String idCliente, String descripcion, String caducidad) {
        return inventarioDao.findInventarioSalidas(consecutivo, idCliente, descripcion, caducidad);
    }

    @Override
    public Inventario getInventarioByClienteConsecutivo(String idCliente, int consecutivo) {
        return inventarioDao.findByClienteConsecutivo(idCliente, consecutivo);
    }

    @Override
    public Inventario getByIdInventario(int id) {
        return inventarioDao.findInventario(id);
    }

    @Override
    public List<Inventarioview> getInventarioReporte(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        return inventarioDao.findInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel);
    }

    @Override
    public List<Inventario> findInventarioReporte2(String consecutivo, String camara, String idCliente, String claveProducto, String tunel){
        return inventarioDao.findInventarioReporte2(consecutivo, camara, idCliente, claveProducto, tunel);
    }

    @Override
    public List<Integer> getInventarioReporteRD(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        return inventarioDao.findInventarioReporteRD(consecutivo, camara, idCliente, claveProducto, tunel);
    }

    @Override
    public List<Inventario> getInventarioByConsecutivo(int consecutivo) {
        return inventarioDao.findByConsecutivo(consecutivo);
    }
    
    @Override
    public List<Inventario> getArrastreSaldosInventarioAgrupado(final int consecutivo) {
        return inventarioDao.getArrastreSaldosInventarioAgrupado(consecutivo);
    }

    @Override
    public List<Object[]> getSaldoXCamara() {
        return inventarioDao.getSaldoXCamara();
    }

    @Override
    public List<Object[]> getSaldoXTunel() {
        return inventarioDao.getSaldoXTunel();
    }

    @Override
    public List<Object[]> getSaldoXCliente() {
        return inventarioDao.getSaldoXCliente();
    }

    @Override
    public List<Object[]> getTodasCamaras() {
        return inventarioDao.getTodasCamaras();
    }

    @Override
    public List<Inventario> getCitroFrut(String[] consecutivo){return inventarioDao.getCitroFrut(consecutivo);}

    @Override
    public List<Object> getSaldosTotales(){return inventarioDao.getSaldosTotales();}

    @Override
    public List<Object> getRDContinuidades(String idcliente, Date fechaini, Date fechafin){
        return inventarioDao.getRDContinuidades(idcliente, fechaini, fechafin);
    }

    @Override
    public List<Object> getInventarioTif(int idcliente){
        return inventarioDao.getInventarioTif(idcliente);
    }
}
