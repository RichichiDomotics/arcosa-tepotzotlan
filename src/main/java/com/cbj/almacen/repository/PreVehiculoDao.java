package com.cbj.almacen.repository;

import java.util.List;

import com.cbj.almacen.domain.PreVehiculo;


public interface PreVehiculoDao {

    public boolean insertVehiculo(PreVehiculo datosIngresoVehiculo);
    public PreVehiculo findVehiculoById(Integer idVehiculo);
    public boolean updateVehiculo(PreVehiculo datosIngresoVehiculo);
    public List<PreVehiculo> findVehiculosPorDia();
    public List<PreVehiculo> findVehiculosPorDiaCliente(String idCliente);
    public List<PreVehiculo> getVehiculoByCliente(int idCliente);
    public List<PreVehiculo> getInOutVehiculo();
}
