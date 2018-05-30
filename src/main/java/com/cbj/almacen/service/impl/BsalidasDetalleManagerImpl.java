package com.cbj.almacen.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import com.cbj.almacen.domain.BsalidasDetalle;
import com.cbj.almacen.repository.BsalidasDetalleDao;
import com.cbj.almacen.service.BsalidasDetalleManager;

/**
 * Created by Ricardo on 18/01/2016.
 */
@Component
public class BsalidasDetalleManagerImpl implements BsalidasDetalleManager{
    @Autowired
    private BsalidasDetalleDao bsalidasDetalleDao;
    @Override
    public List<BsalidasDetalle> getFechasID(Date fechaini, Date fechafin, String idCliente){
        return bsalidasDetalleDao.getFechasID(fechaini, fechafin, idCliente);
    }
    @Override
    public List<BsalidasDetalle> getFolioSalida(String folioSalida){
        return bsalidasDetalleDao.getFolioSalida(folioSalida);
    }
}
