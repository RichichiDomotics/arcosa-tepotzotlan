package com.cbj.almacen.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface CatUbicacionesManager extends Serializable {

    public List<Object[]> getCamaras();
    public List<Object[]> getPuertas(String camara);
    public List<Object[]> getPasillos(String puerta,String camara);
    public List<Object[]> getFilas(String pasillo,String puerta,String camara);
    public List<Object[]> getPosiciones(String pasillo,String puerta,String camara,String fila);
    public List<Object[]> getNiveles(String pasillo,String puerta,String camara,String fila);
}
