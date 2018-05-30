package com.cbj.almacen.repository;

import java.util.List;


public interface CatUbicacionesDao {
    public List<Object[]> getCamaras();
    public List<Object[]> getPuertas(String camara);
    public List<Object[]> getPasillos(String puerta, String camara);
    public List<Object[]> getFilas(String pasillo,String puerta, String camara);
    public List<Object[]> getPosiciones(String pasillo,String puerta, String camara,String fila);
    public List<Object[]> getNiveles(String pasillo,String puerta, String camara,String fila);
}
