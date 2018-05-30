package com.cbj.almacen.service;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.Inventarioview;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
public interface InventarioManager extends Serializable {

    public boolean setInventario(Inventario inventario);
    public boolean updateInventario(Inventario inventario);
    public List<Inventario> getInventarioByConsulta(String numeroRdb, String idClienteb, String productob, String caducidadb);

    public Inventario getInventarioByClienteConsecutivo(String idCliente, int consecutivo);
    public List<Inventario> getInventarioByConsecutivo(int consecutivo);

    public Inventario getByIdInventario(int id);

    public List<Inventarioview> getInventarioReporte(String consecutivo,String camara,String idCliente,String claveProducto,String tunel);
    public List<Inventario> findInventarioReporte2(String consecutivo, String camara, String idCliente, String claveProducto, String tunel);
    public List<Integer> getInventarioReporteRD(String consecutivo,String camara,String idCliente,String claveProducto,String tunel);
    public List<Inventario> getArrastreSaldosInventarioAgrupado(final int consecutivo);

    public List<Object[]> getSaldoXCamara();
    public List<Object[]> getSaldoXTunel();
    public List<Object[]> getSaldoXCliente();
    public List<Object[]> getTodasCamaras();
    public List<Inventario> getCitroFrut(String[] consecutivo);

    public List<Object> getSaldosTotales();
    public List<Object> getRDContinuidades(String idcliente, Date fechaini, Date fechafin);
    public List<Object> getInventarioTif(int idcliente);
}
