package com.cbj.almacen.service;

import com.cbj.almacen.domain.User;

import java.io.Serializable;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface UsuarioManager extends Serializable {

    public User getUsuarioByUsername(String username);
    public User getUsuarioByIdEjecutivo(String idEjecutivo);

}
