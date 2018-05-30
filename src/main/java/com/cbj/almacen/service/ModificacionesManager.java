package com.cbj.almacen.service;

/**
 * Created by Richard on 21/07/2015.
 */
import com.cbj.almacen.domain.Modificaciones;
import java.io.Serializable;
import java.util.List;
public interface ModificacionesManager extends Serializable{

    public boolean insertModificacion(Modificaciones modificaciones);
    public boolean updateModificacion(Modificaciones modificaciones);
    public int getFolio();
    public List<Modificaciones> getAll();
    public Modificaciones getModificacionById(Integer idModificacion);
    public List<Modificaciones> getModificacionByFecha(String fechaini, String fechafin);
}
