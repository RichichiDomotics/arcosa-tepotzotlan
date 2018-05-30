package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Contadores;
import com.cbj.almacen.repository.ContadoresDao;
import com.cbj.almacen.service.ContadoresManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class ContadoresManagerImpl implements ContadoresManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ContadoresDao contadoresDao;

    @Override
    public Contadores findContador(String tipoContador) {
        return contadoresDao.findContador(tipoContador);
    }

    @Override
    public boolean updateContadores(Contadores contadores) {
        return contadoresDao.updateContadores(contadores);
    }

}
