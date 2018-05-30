package com.cbj.almacen.repository;

import com.cbj.almacen.domain.AutPuertas;

import java.util.List;

/**
 * Created by jolvera on 12/11/2014.
 */
public interface AutPuertasDao {

    public List<AutPuertas> getPuertaByClienteVehiculo(int idCliente,int idTipoVehiculo);
}
