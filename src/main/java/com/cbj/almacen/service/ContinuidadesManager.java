package com.cbj.almacen.service;

import com.cbj.almacen.domain.Continuidades;
import java.io.Serializable;
import java.util.List;

import java.util.List;

/**
 * Created by Ricardo on 29/03/2016.
 */
public interface ContinuidadesManager extends Serializable{
    public boolean insertContinuidad(Continuidades continuidades);
    public boolean updateContinuidad(Continuidades continuidades);
    public List<Object> getAll(String fechainicio, String fechafin);
    public Continuidades getByConsecutivo(int consecutivo);

}
