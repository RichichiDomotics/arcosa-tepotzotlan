package com.cbj.almacen.service.impl;

//import com.cbj.almacen.domain.MotivoRechazoAutorizado;
import com.cbj.almacen.domain.MotivosRechazoAutorizado;
import com.cbj.almacen.repository.MotivoRechazoAutorizadoDao;
import com.cbj.almacen.service.MotivoRechazoAutorizadoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class MotivoRechazoAutorizadoManagerImpl implements MotivoRechazoAutorizadoManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MotivoRechazoAutorizadoDao MotivoRechazoAutorizadoDao;

    @Override
    public boolean insertMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado) {
        return MotivoRechazoAutorizadoDao.insertMotivo(motivoRechazoAutorizado);
    }

    @Override
    public boolean updateMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado) {
        return MotivoRechazoAutorizadoDao.updateMotivo(motivoRechazoAutorizado);
    }

    @Override
    public int getAll(int idProspecto){ return MotivoRechazoAutorizadoDao.getAll(idProspecto); }

}
