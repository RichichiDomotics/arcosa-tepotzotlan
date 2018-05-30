package com.cbj.almacen.service;

import com.cbj.almacen.domain.BsalidasDetalle;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Ricardo on 18/01/2016.
 */
public interface BsalidasDetalleManager extends Serializable{
    public List<BsalidasDetalle> getFechasID(Date fechaini, Date fechafin, String idCliente);
    public List<BsalidasDetalle> getFolioSalida(String folioSalida);
}
