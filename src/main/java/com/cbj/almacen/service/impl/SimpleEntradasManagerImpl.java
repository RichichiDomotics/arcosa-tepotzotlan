package com.cbj.almacen.service.impl;

import java.util.List;

import com.cbj.almacen.repository.CatalogoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbj.almacen.domain.FormEntrada;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.repository.EntradasDao;
import com.cbj.almacen.repository.ConsultasDao;
import com.cbj.almacen.service.EntradasManager;

@Component
public class SimpleEntradasManagerImpl implements EntradasManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EntradasDao entradasDao;
    @Autowired
    private CatalogoDao catalogoDao;
    @Autowired
    private ConsultasDao consultasDao;

    
    public boolean setEntradas(FormEntrada entrada) {
    	entradasDao.setEntradaVehiculo(entrada);
    	return true;
        }

    public final List<Clientes> getCatClientes() {
    	return catalogoDao.getClientesList();
        }
    
    public final List<Planta> getCatPlantas() {
    	return catalogoDao.getCatalogoPlanta();
        }
    
    public final List<CatalogoGeneral> getCatRecibos() {
    	return catalogoDao.getCatalogoRecibo();
        }
    public final List<CatalogoGeneral> getCatPuertas() {
    	return catalogoDao.getCatalogoPuerta();
        }
    
    public final List<CatalogoGeneral> getCatPuertaPlanta(String Planta) {
    	return catalogoDao.getCatalogoPuertaPlanta(Planta);
        }
    
    public final List<CatalogoGeneral> getCatManiobras() {
    	return catalogoDao.getCatalogoManiobra();
        }
    
    public final List<CatalogoGeneral> getCatFlejes() {
    	return catalogoDao.getCatalogoFleje();
        }
    
    public final List<CatalogoGeneral> getCatVehiculos() {
    	return catalogoDao.getCatalogoVehiculo();
        }
    
    public final List<CatalogoGeneral> getCatTransportes() {
    	return catalogoDao.getCatalogoTransporte();
        }
    
    public List<VistaIngreso> getEntradas() {
    	return entradasDao.getConsultaIngresosList();
        }
    
    public VistaIngresoDetalle  getIngreso(Integer id) {
    	return entradasDao.getConsultaIngresosDetList(id);
        }
}
