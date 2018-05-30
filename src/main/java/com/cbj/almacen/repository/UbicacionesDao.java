package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Ubicaciones;

import java.util.List;


public interface UbicacionesDao {
    public Ubicaciones getRevisionUbicacion(String camara, String puerta, String pasillo, String filaCalle,
                                               Integer posicion, Integer nivel, String tipoTarima, String consecutivo);
    public Ubicaciones insertaRegistroUbicacion(Ubicaciones ubicaciones);
    public boolean updateUbicacion(Ubicaciones ubicaciones);
    public List<Object[]> getUbicacion(String camara, String consecutivo);
    public List<Object[]> getUbicacionVacias(String camara);
    public boolean borraUbicacion(Integer idUbica);
    public Ubicaciones getUbicacionIdUbica(Integer idUbica);
    public List<Object[]> getConsolidados(String camara);
    public List<Object[]> getConsolidadosVacio(String camara);


    /*public List<Object[]> getPuertas(String camara);
    public List<Object[]> getPasillos(String puerta, String camara);
    public List<Object[]> getFilas(String pasillo, String puerta, String camara);
    public List<Object[]> getPosiciones(String pasillo, String puerta, String camara, String fila);
    public List<Object[]> getNiveles(String pasillo, String puerta, String camara, String fila);*/
}
