package com.cbj.almacen.service;

import com.cbj.almacen.domain.SalidasAlmacen;
import com.cbj.almacen.domain.SalidasDetalle;

import java.io.Serializable;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SalidasAlmacenManager extends Serializable {

    public int registraSalidaAlmacen(SalidasAlmacen salidasAlmacen);
    public SalidasAlmacen getSalidaByClienteConsecutivo( String idCliente,int consecutivo);
    public SalidasAlmacen findSalidabyFolioSalida (String folioSalida);

}
