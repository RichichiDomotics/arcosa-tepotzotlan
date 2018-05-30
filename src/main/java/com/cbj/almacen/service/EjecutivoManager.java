package com.cbj.almacen.service;


import com.cbj.almacen.domain.Ejecutivo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface EjecutivoManager extends Serializable {


    public List<Ejecutivo> getAll();
    public List<Ejecutivo> getAllJefe(int idJefe);
    public Ejecutivo getEjecutivo(String claveUsuario);
    public Ejecutivo getEjecutivoId(Integer idEjecutivo);

}
