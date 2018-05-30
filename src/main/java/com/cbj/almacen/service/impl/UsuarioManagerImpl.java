package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.User;
import com.cbj.almacen.repository.UsuarioDao;
import com.cbj.almacen.service.UsuarioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class UsuarioManagerImpl implements UsuarioManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public User getUsuarioByUsername(String username) {
        return usuarioDao.getUsuarioByUsername(username);
    }

    @Override
    public User getUsuarioByIdEjecutivo(String idEjecutivo) {
        return usuarioDao.getUsuarioByIdEjecutivo(idEjecutivo);
    }

}
