package com.cbj.almacen.repository;

/**
 * Created by Ricardo on 18/01/2016.
 */
import java.util.Date;
import java.util.List;
import com.cbj.almacen.domain.BsalidasDetalle;



public interface BsalidasDetalleDao {
    public List<BsalidasDetalle> getFechasID(Date fechaini, Date fechafin, String idCliente);
    public List<BsalidasDetalle> getFolioSalida(String folioSalida);
}
