package com.cbj.almacen.repository;

import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.Embalaje;
import com.cbj.almacen.domain.Convenios;

import java.util.List;

/**
 * Created by jolvera on 25/05/2014.
 */
public interface CatalogoDao {

    public List<Clientes> getClientesList();
    public List<Clientes> getClientesActivosList();
    public List<Planta> getCatalogoPlanta();
    public List<CatalogoGeneral> getCatalogoRecibo();
    public List<CatalogoGeneral> getCatalogoPuerta();
    public List<CatalogoGeneral> getCatalogoPuertaPlanta(String Planta);
    public List<CatalogoGeneral> getCatalogoManiobra();
    public List<CatalogoGeneral> getCatalogoFleje();
    public List<CatalogoGeneral> getCatalogoVehiculo();
    public List<CatalogoGeneral> getCatalogoTransporte();
    public List<Embalaje> getEmbalajeList();
    public List<CatalogoGeneral> getCatalogoServicios();
    public List<CatalogoGeneral> getCatalogoCamaras();
    public List<CatalogoGeneral> getCatalogoTuneles();

}
