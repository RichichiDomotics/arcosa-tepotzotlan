package com.cbj.almacen.service;

import com.cbj.almacen.domain.Ubicaciones;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface UbicacionesManager extends Serializable {

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

   /*
     public Clientes insertCliente(Clientes cliente);
    public boolean updateCliente(Clientes cliente);

    public List<Object[]> getPuertas(String camara);
    public List<Object[]> getPasillos(String puerta, String camara);
    public List<Object[]> getFilas(String pasillo, String puerta, String camara);
    public List<Object[]> getPosiciones(String pasillo, String puerta, String camara, String fila);
    public List<Object[]> getNiveles(String pasillo, String puerta, String camara, String fila);*/
}
