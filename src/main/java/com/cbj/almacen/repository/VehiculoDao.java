package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Vehiculo;

import java.util.Date;
import java.util.List;


public interface VehiculoDao {

    public boolean insertVehiculo(Vehiculo datosIngresoVehiculo);
    public Vehiculo findVehiculoById(Integer idVehiculo);
    public boolean updateVehiculo(Vehiculo datosIngresoVehiculo);
    public List<Vehiculo> findVehiculosPorSalir();
    public List<Vehiculo> getVehiculoByCliente(int idCliente);
    public List<Vehiculo> getInOutVehiculo(Date fecini, Date fecfin);
    public Vehiculo getidIngresoVehiculo(Integer idIngresoVehiculo);
    public List<Vehiculo> getAll();
    public List<Object> getTiemposAtencion(Date fechaini, Date fechafin);
    public List<Object> getTiemposAtencionCumplidos(Date fechaini, Date fechafin);
    public List<Object> getTiemposAtencionNoCumplidos(Date fechaini, Date fechafin);
}
