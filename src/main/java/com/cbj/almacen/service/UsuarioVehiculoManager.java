package com.cbj.almacen.service;

import com.cbj.almacen.domain.UsuarioVehiculo;

import java.io.Serializable;

/**
 * Created by jolvera on 17/12/2014.
 */
public interface UsuarioVehiculoManager extends Serializable {
    public UsuarioVehiculo getByUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo);
    public UsuarioVehiculo getByVehiculo(UsuarioVehiculo usuarioVehiculo);
    public UsuarioVehiculo registraSeguimiento(UsuarioVehiculo usuarioVehiculo);
}
