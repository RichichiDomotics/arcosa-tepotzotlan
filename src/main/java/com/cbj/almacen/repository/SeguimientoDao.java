package com.cbj.almacen.repository;


import com.cbj.almacen.domain.Seguimiento;

import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SeguimientoDao {

    public boolean insertSeguimiento(Seguimiento seguimiento);
    
    public boolean updateSeguimiento(Seguimiento seguimiento);

    public List<Seguimiento> getAll();

    public List<Seguimiento> getSeguimiento(int idProspecto);
}
