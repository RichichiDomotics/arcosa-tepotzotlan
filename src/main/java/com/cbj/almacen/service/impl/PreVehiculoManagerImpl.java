package com.cbj.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.PreVehiculo;
import com.cbj.almacen.repository.PreVehiculoDao;
import com.cbj.almacen.service.PreVehiculoManager;

@Component
public class PreVehiculoManagerImpl implements PreVehiculoManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PreVehiculoDao preVehiculoDao;
    
    
    public boolean setIngresaVehiculo(PreVehiculo vehiculo) {
    	return preVehiculoDao.insertVehiculo(vehiculo);
        }

    public PreVehiculo getIngresaVehiculoById(Integer id) {
    	return preVehiculoDao.findVehiculoById(id);
        }

    public boolean updateIngresaVehiculo(PreVehiculo vehiculo) {
    	return preVehiculoDao.updateVehiculo(vehiculo);
        }

    @Override
    public List<PreVehiculo> getVehiculosPorDia() {
        return preVehiculoDao.findVehiculosPorDia();
    }

    @Override
    public List<PreVehiculo> getVehiculosPorDiaCliente(String idCliente) {
        return preVehiculoDao.findVehiculosPorDiaCliente(idCliente);
    }

    public List<PreVehiculo> getVehiculoByCliente(int idCliente) {
        return preVehiculoDao.getVehiculoByCliente(idCliente);
    }
    
    public List<PreVehiculo> getInOutVehiculo() {
        return preVehiculoDao.getInOutVehiculo();
    }
    

}
