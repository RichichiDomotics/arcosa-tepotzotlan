package com.cbj.almacen.service.impl;


import com.cbj.almacen.domain.ObservacionesSeguimiento;
import com.cbj.almacen.repository.ObservacionesSeguimientoDao;
import com.cbj.almacen.service.ObservacionesSeguimientoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class ObservacionesSeguimientoManagerImpl implements ObservacionesSeguimientoManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ObservacionesSeguimientoDao observacionesSeguimientoDao;

    @Override
    public boolean insertObservacion(ObservacionesSeguimiento observacionesSeguimiento) {
        return observacionesSeguimientoDao.insertObservacion(observacionesSeguimiento);
    }

    @Override
    public boolean updateObservacion(ObservacionesSeguimiento observacionesSeguimiento) {
        return observacionesSeguimientoDao.updateObservacion(observacionesSeguimiento);
    }

    @Override
    public List<Object> getNumObservaciones(int idProspecto, int idSeguimiento){return observacionesSeguimientoDao.getNumObservaciones(idProspecto, idSeguimiento);}

    @Override
    public List<ObservacionesSeguimiento> getObservaciones(int idProspecto, int idSeguimiento){return observacionesSeguimientoDao.getObservaciones(idProspecto, idSeguimiento);}


}
