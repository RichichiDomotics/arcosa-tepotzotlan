package com.cbj.almacen.service;

import com.cbj.almacen.domain.AutPuertas;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 12/11/2014.
 */
public interface AutPuertasManager extends Serializable {

    public List<AutPuertas> getPuertaByClienteVehiculo(int idCliente, int idTipoVehiculo);
}
