package com.cbj.almacen.repository;

import com.cbj.almacen.domain.UsuarioVehiculo;

/**
 * Created by jolvera on 18/12/2014.
 */
public interface UsuarioVehiculoDao {

    public UsuarioVehiculo getByUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo);
    public UsuarioVehiculo getByVehiculo(UsuarioVehiculo usuarioVehiculo);
    public UsuarioVehiculo registraSeguimiento(UsuarioVehiculo usuarioVehiculo);
}
