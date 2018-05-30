package com.cbj.almacen.repository;

import java.util.List;

import com.cbj.almacen.domain.FormEntrada;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;


public interface EntradasDao {

    public boolean setEntradaVehiculo(FormEntrada entrada);
    public List<VistaIngreso> getConsultaIngresosList();
    public List<VistaIngreso> getConsultaSalidasList();
    public VistaIngresoDetalle getConsultaIngresosDetList(Integer id);
    
}
