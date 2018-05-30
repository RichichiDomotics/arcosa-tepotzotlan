package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Ejecutivo;

import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface EjecutivoDao {

    
    public List<Ejecutivo> getAll();
    public List<Ejecutivo> getAllJefe(int idJefe);
    public Ejecutivo getEjecutivo(String claveUsuario);
    public Ejecutivo getEjecutivoId(Integer idEjecutivo);
   

}
