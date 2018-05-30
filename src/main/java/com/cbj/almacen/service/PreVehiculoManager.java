package com.cbj.almacen.service;

import java.io.Serializable;
import java.util.List;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.PreVehiculo;


public interface PreVehiculoManager extends Serializable {

    public boolean setIngresaVehiculo(PreVehiculo vehiculo);
    public PreVehiculo getIngresaVehiculoById(Integer id);
    public boolean updateIngresaVehiculo(PreVehiculo vehiculo);
    public List<PreVehiculo> getVehiculosPorDia();
    public List<PreVehiculo> getVehiculosPorDiaCliente(String idCliente);
    public List<PreVehiculo> getVehiculoByCliente(int idCliente);
    public List<PreVehiculo> getInOutVehiculo();
}