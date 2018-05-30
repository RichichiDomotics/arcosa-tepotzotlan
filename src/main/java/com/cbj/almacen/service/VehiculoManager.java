package com.cbj.almacen.service;

import com.cbj.almacen.domain.Vehiculo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface VehiculoManager extends Serializable {

    public boolean setIngresaVehiculo(Vehiculo vehiculo);
    public Vehiculo getIngresaVehiculoById(Integer id);
    public boolean updateIngresaVehiculo(Vehiculo vehiculo);
    public List<Vehiculo> getVehiculosPorSalir();
    public List<Vehiculo> getVehiculoByCliente(int idCliente);
    public List<Vehiculo> getInOutVehiculo(Date fecini, Date fecfin);
    public Vehiculo getidIngresoVehiculo(Integer idIngresoVehiculo);
    public List<Vehiculo> getAll();
    public List<Object> getTiemposAtencion(Date fechaini, Date fechafin);
    public List<Object> getTiemposAtencionCumplidos(Date fechaini, Date fechafin);
    public List<Object> getTiemposAtencionNoCumplidos(Date fechaini, Date fechafin);
}