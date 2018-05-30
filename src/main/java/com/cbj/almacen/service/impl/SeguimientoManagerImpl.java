package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Seguimiento;
import com.cbj.almacen.repository.SeguimientoDao;
import com.cbj.almacen.service.SeguimientoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class SeguimientoManagerImpl implements SeguimientoManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SeguimientoDao seguimientoDao;

    @Override
    public boolean insertSeguimiento(Seguimiento seguimiento) {
        return seguimientoDao.insertSeguimiento(seguimiento);
    }

    @Override
    public boolean updateSeguimiento(Seguimiento seguimiento) {
        return seguimientoDao.updateSeguimiento(seguimiento);
    }

    @Override
    public List<Seguimiento> getAll(){return seguimientoDao.getAll();}

    @Override
    public List<Seguimiento> getSeguimiento(int idProspecto){return seguimientoDao.getSeguimiento(idProspecto);}

}
