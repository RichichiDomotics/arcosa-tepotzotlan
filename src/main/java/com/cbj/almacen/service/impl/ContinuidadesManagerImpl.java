package com.cbj.almacen.service.impl;

/**
 * Created by Ricardo on 29/03/2016.
 */

import com.cbj.almacen.domain.Continuidades;
import com.cbj.almacen.repository.ContinuidadesDao;
import com.cbj.almacen.service.ContinuidadesManager;
import com.cbj.almacen.service.NotasManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ContinuidadesManagerImpl implements ContinuidadesManager{
    private static final long serialVersionUID = 1L;

    @Autowired
    private ContinuidadesDao continuidadesDao;
    public boolean insertContinuidad(Continuidades continuidades){ return continuidadesDao.insertContinuidad(continuidades);}
    public boolean updateContinuidad(Continuidades continuidades){ return continuidadesDao.updateContinuidad(continuidades);}
    public List<Object> getAll(String fechainicio, String fechafin){return continuidadesDao.getAll(fechainicio,fechafin);}
    public Continuidades getByConsecutivo(int consecutivo){return continuidadesDao.getByConsecutivo(consecutivo);}
}
