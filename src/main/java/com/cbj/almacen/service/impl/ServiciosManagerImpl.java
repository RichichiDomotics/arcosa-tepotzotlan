package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Servicios;
import com.cbj.almacen.repository.ServiciosDao;
import com.cbj.almacen.service.ServiciosManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by colvera on 10/06/2014.
 */
@Component
public class ServiciosManagerImpl implements ServiciosManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ServiciosDao serviciosDao;

    @Override
    public List<Servicios> getByActivo() {
        return serviciosDao.getByActivo();
    }
    
    @Override
    public List<Servicios> getByActivoByIdCliente(String idCliente) {
    	return serviciosDao.getByActivoByIdCliente(idCliente);
    }

    @Override
    public List<Object[]> getCboServicios() {
        return serviciosDao.getCboServicios();
    }

    @Override
    public Servicios getByActivoByIdClienteClave(String idCliente, String clave) {
        return serviciosDao.getByActivoByIdClienteClave(idCliente,clave);
    }

    public boolean insertServicio(Servicios servicio) {
        return serviciosDao.insertServicio(servicio);
    }
}
