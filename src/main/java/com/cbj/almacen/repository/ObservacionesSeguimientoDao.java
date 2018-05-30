package com.cbj.almacen.repository;


import com.cbj.almacen.domain.ObservacionesSeguimiento;

import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface ObservacionesSeguimientoDao {

    public boolean insertObservacion(ObservacionesSeguimiento observacionesSeguimiento);
    
    public boolean updateObservacion(ObservacionesSeguimiento observacionesSeguimiento);

    public List<Object> getNumObservaciones(int idProspecto, int idSeguimiento);

    public List<ObservacionesSeguimiento> getObservaciones(int idProspecto, int idSeguimiento);

}
