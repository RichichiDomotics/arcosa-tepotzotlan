package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.Embalaje;
import com.cbj.almacen.repository.CatalogoDao;
import com.cbj.almacen.repository.EntradasDao;
import com.cbj.almacen.service.CatalogoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cbj.almacen.domain.Convenios;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 25/05/2014.
 */
@Component
public class CatalogoManagerImpl implements CatalogoManager {

    @Autowired
    private CatalogoDao catalogoDao;

    @Override
    public List<?> getCatalogo(final TipoCatalogo tipo) {
        List<?> catalogo = new ArrayList<Object>();
        switch (tipo) {
            case CLIENTE:
                catalogo = getCatClientes();
                break;
            case PLANTAS:
                catalogo = getCatPlantas();
                break;
            case RECIBOS:
                catalogo = getCatRecibos();
                break;
            case PUERTAS:
                catalogo = getCatPuertas();
                break;
            case MANIOBRAS:
                catalogo = getCatManiobras();
                break;
            case FLEJES:
                catalogo = getCatFlejes();
                break;
            case VEHICULOS:
                catalogo = getCatVehiculos();
                break;
            case TRANSPORTES:
                catalogo = getCatTransportes();
                break;
            case EMBALAJES:
                catalogo = getCatEmbalaje();
                break;
            case SERVICIOS:
                catalogo = getCatServicios();
                break;
            case CAMARAS:
                catalogo = getCatCamaras();
                break;
            case TUNELES:
                catalogo = getCatTuneles();
                break;    
            default:
                // throw some exception
                break;
        }
        return catalogo;
        }

    private List<Clientes> getCatClientes() {
        return catalogoDao.getClientesActivosList();
    }

    private List<Planta> getCatPlantas() {
        return catalogoDao.getCatalogoPlanta();
    }

    private List<CatalogoGeneral> getCatRecibos() {
        return catalogoDao.getCatalogoRecibo();
    }

    private List<CatalogoGeneral> getCatPuertas() {
        return catalogoDao.getCatalogoPuerta();
    }

    private List<CatalogoGeneral> getCatPuertaPlanta(String Planta) {
        return catalogoDao.getCatalogoPuertaPlanta(Planta);
    }

    private List<CatalogoGeneral> getCatManiobras() {
        return catalogoDao.getCatalogoManiobra();
    }

    private List<CatalogoGeneral> getCatFlejes() {
        return catalogoDao.getCatalogoFleje();
    }

    private List<CatalogoGeneral> getCatVehiculos() {
        return catalogoDao.getCatalogoVehiculo();
    }

    private List<CatalogoGeneral> getCatTransportes() {
        return catalogoDao.getCatalogoTransporte();
    }
    
    private List<Embalaje> getCatEmbalaje() {
        return catalogoDao.getEmbalajeList();
    }

    private List<CatalogoGeneral> getCatServicios() {
        return catalogoDao.getCatalogoServicios();
    }

    private List<CatalogoGeneral> getCatCamaras() {
        return catalogoDao.getCatalogoCamaras();
    }

    private List<CatalogoGeneral> getCatTuneles() {
        return catalogoDao.getCatalogoTuneles();
    }
    
}
