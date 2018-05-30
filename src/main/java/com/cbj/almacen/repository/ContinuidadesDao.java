package com.cbj.almacen.repository;

/**
 * Created by Ricardo on 29/03/2016.
 */
import java.util.List;

import com.cbj.almacen.domain.Continuidades;
public interface ContinuidadesDao {
    public boolean insertContinuidad(Continuidades continuidades);
    public boolean updateContinuidad(Continuidades continuidades);
    public List<Object> getAll(String fechainicio, String fechafin);
    public Continuidades getByConsecutivo(int consecutivo);
}
