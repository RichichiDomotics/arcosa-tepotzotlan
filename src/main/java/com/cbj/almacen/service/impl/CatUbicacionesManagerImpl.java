package com.cbj.almacen.service.impl;

import com.cbj.almacen.repository.CatUbicacionesDao;
import com.cbj.almacen.service.CatUbicacionesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class CatUbicacionesManagerImpl implements CatUbicacionesManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CatUbicacionesDao catUbicacionesDao;

    @Override
    public List<Object[]> getCamaras() {
        return catUbicacionesDao.getCamaras();
    }

    public List<Object[]> getPuertas(String camara){
        return catUbicacionesDao.getPuertas(camara);
    }
    public List<Object[]> getPasillos(String puerta, String camara){
        return catUbicacionesDao.getPasillos(puerta,camara);
    }
    public List<Object[]> getFilas(String pasillo,String puerta, String camara){
        return catUbicacionesDao.getFilas(pasillo,puerta,camara);
    }
    public List<Object[]> getPosiciones(String pasillo,String puerta, String camara, String filas){
        return catUbicacionesDao.getPosiciones(pasillo,puerta,camara,filas);
    }
    public List<Object[]> getNiveles(String pasillo,String puerta, String camara, String filas){
        return catUbicacionesDao.getNiveles(pasillo,puerta,camara,filas);
    }

}
