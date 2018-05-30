package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.Inventarioview;

import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
public interface InventarioDao {

    public boolean insertInventario(Inventario inventario);
    public boolean updateInventario(Inventario inventario);
    public boolean deleteInventario(int id);
    public Inventario findInventario(int id);
    List<Inventario> findInventarioSalidas(String consecutivo, String idCliente, String descripcion, String caducidad);
    List<Inventarioview> findInventarioReporte(String consecutivo, String camara, String idCliente, String claveProducto, String tunel);
    List<Inventario> findInventarioReporte2(String consecutivo, String camara, String idCliente, String claveProducto, String tunel);
    List<Integer> findInventarioReporteRD(String consecutivo, String camara, String idCliente, String claveProducto, String tunel);
    public Inventario findByClienteConsecutivo(String idCliente, int consecutivo);
    public Inventario findByClienteFolioSalida(String idCliente, int folioSalida);
    public List<Inventario> findByConsecutivo(int consecutivo);
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
