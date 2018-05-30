package com.cbj.almacen.repository;

import com.cbj.almacen.domain.User;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface UsuarioDao {
    public User getUsuarioByUsername(String username);
    public User getUsuarioByIdEjecutivo(String idEjecutivo);
}
