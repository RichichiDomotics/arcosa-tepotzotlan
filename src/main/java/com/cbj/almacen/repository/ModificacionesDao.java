package com.cbj.almacen.repository;

/**
 * Created by Richard on 21/07/2015.
 */
import com.cbj.almacen.domain.Modificaciones;
import java.util.List;
public interface ModificacionesDao {

    public boolean insertModificacion(Modificaciones modificaciones);
    public boolean updateModificacion(Modificaciones modificaciones);
    public int getFolio();
    public List<Modificaciones> getAll();
    public Modificaciones getModificacionById(Integer idModificacion);
    public List<Modificaciones> getModificacionByFecha(String fechaini, String fechafin);
}
