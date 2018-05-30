package com.cbj.almacen.service;



import com.cbj.almacen.domain.ObservacionesSeguimiento;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface ObservacionesSeguimientoManager extends Serializable {

    public boolean insertObservacion(ObservacionesSeguimiento observacionesSeguimiento);
    
    public boolean updateObservacion(ObservacionesSeguimiento observacionesSeguimiento);

    public List<Object> getNumObservaciones(int idProspecto, int idSeguimiento);

    public List<ObservacionesSeguimiento> getObservaciones(int idProspecto, int idSeguimiento);

}
