package com.cbj.almacen.service.impl;

/**
 * Created by Richard on 21/07/2015.
 */
import com.cbj.almacen.domain.Modificaciones;
import com.cbj.almacen.repository.ModificacionesDao;
import com.cbj.almacen.service.ModificacionesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModificacionesManagerImpl implements ModificacionesManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ModificacionesDao modificacionesDao;

    public boolean insertModificacion(Modificaciones modificaciones){
        return modificacionesDao.insertModificacion(modificaciones);
    }

    public boolean updateModificacion(Modificaciones modificaciones){
        return modificacionesDao.updateModificacion(modificaciones);
    }
    public int getFolio(){
        return modificacionesDao.getFolio();
    }
    public List<Modificaciones> getAll(){
        return modificacionesDao.getAll();
    }
    public Modificaciones getModificacionById(Integer idModificacion){
        return modificacionesDao.getModificacionById(idModificacion);
    }
    public List<Modificaciones> getModificacionByFecha(String fechaini, String fechafin){
        return modificacionesDao.getModificacionByFecha(fechaini, fechafin);
    }
}
