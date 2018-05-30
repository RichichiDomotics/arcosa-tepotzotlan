package com.cbj.almacen.service;


import com.cbj.almacen.domain.Seguimiento;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface SeguimientoManager extends Serializable {

    public boolean insertSeguimiento(Seguimiento seguimiento);
    public boolean updateSeguimiento(Seguimiento seguimiento);
    public List<Seguimiento> getAll();
    public List<Seguimiento> getSeguimiento(int idProspecto);
}
