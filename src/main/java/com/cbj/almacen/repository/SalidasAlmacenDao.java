package com.cbj.almacen.repository;


import com.cbj.almacen.domain.SalidasAlmacen;
import com.cbj.almacen.domain.SalidasDetalle;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SalidasAlmacenDao {

    public int insertSalidasAlmacen(SalidasAlmacen salidasAlmacen);
    public SalidasAlmacen findSalidaByClienteConsecutivo(String cliente, int consecutivo);
    public SalidasAlmacen findSalidabyFolioSalida (String folioSalida);
}
