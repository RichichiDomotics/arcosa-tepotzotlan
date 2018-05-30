package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Ubicaciones;
import com.cbj.almacen.repository.UbicacionesDao;
import com.cbj.almacen.service.UbicacionesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class UbicacionesManagerImpl implements UbicacionesManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UbicacionesDao ubicacionesDao;

    @Override
    public Ubicaciones getRevisionUbicacion(String camara, String puerta, String pasillo, String filaCalle,
                                               Integer posicion, Integer nivel, String tipoTarima, String consecutivo) {
        return ubicacionesDao.getRevisionUbicacion(camara, puerta, pasillo, filaCalle, posicion, nivel, tipoTarima, consecutivo);
    }

    @Override
    public Ubicaciones insertaRegistroUbicacion(Ubicaciones ubicaciones) {
        return ubicacionesDao.insertaRegistroUbicacion(ubicaciones);
    }

    @Override
    public boolean updateUbicacion(Ubicaciones ubicaciones) {
        return ubicacionesDao.updateUbicacion(ubicaciones);
    }

    @Override
    public List<Object[]> getUbicacion(String camara,String consecutivo){
        return ubicacionesDao.getUbicacion(camara, consecutivo);
    }

    @Override
    public List<Object[]> getUbicacionVacias(String camara){
        return ubicacionesDao.getUbicacionVacias(camara);
    }

    @Override
    public boolean borraUbicacion(Integer idUbica){
        return ubicacionesDao.borraUbicacion(idUbica);
    }

    @Override
    public Ubicaciones getUbicacionIdUbica(Integer idUbica){
        return ubicacionesDao.getUbicacionIdUbica(idUbica);
    }

    @Override
    public List<Object[]> getConsolidados(String camara){
        return ubicacionesDao.getConsolidados(camara);
    }

    @Override
    public List<Object[]> getConsolidadosVacio(String camara){
        return ubicacionesDao.getConsolidadosVacio(camara);
    }

    /*public List<Object[]> getPuertas(String camara){
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
    }*/

}
