package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.UsuarioVehiculo;
import com.cbj.almacen.repository.UsuarioVehiculoDao;
import com.cbj.almacen.service.UsuarioVehiculoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jolvera on 17/12/2014.
 */
@Component
public class UsuarioVehiculoManagerImpl implements UsuarioVehiculoManager{

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioVehiculoDao usuarioVehiculoDao;

    @Override
    public UsuarioVehiculo getByUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo) {
        return usuarioVehiculoDao.getByUsuarioVehiculo(usuarioVehiculo);
    }

    @Override
    public UsuarioVehiculo getByVehiculo(UsuarioVehiculo usuarioVehiculo) {
        return usuarioVehiculoDao.getByVehiculo(usuarioVehiculo);
    }

    @Override
    public UsuarioVehiculo registraSeguimiento(UsuarioVehiculo usuarioVehiculo) {
        return usuarioVehiculoDao.registraSeguimiento(usuarioVehiculo);
    }
}
