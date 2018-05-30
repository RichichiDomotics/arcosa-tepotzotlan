package com.cbj.almacen.service;

import java.io.Serializable;
import java.util.List;

import com.cbj.almacen.domain.FormEntrada;
import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;


public interface EntradasManager extends Serializable {

    public boolean setEntradas(FormEntrada entrada);

    public List<Clientes> getCatClientes();

    public List<Planta> getCatPlantas();

    public List<CatalogoGeneral> getCatRecibos();

    public List<CatalogoGeneral> getCatPuertas();

    public List<CatalogoGeneral> getCatPuertaPlanta(String Planta);

    public List<CatalogoGeneral> getCatManiobras();

    public List<CatalogoGeneral> getCatFlejes();

    public List<CatalogoGeneral> getCatVehiculos();

    public List<CatalogoGeneral> getCatTransportes();

    public List<VistaIngreso> getEntradas();

    public VistaIngresoDetalle getIngreso(Integer id);


}