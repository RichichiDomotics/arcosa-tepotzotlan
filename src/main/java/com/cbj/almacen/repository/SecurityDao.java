package com.cbj.almacen.repository;

import com.cbj.almacen.domain.User;

/**
 * Created by jolvera on 11/05/2014.
 */
public interface SecurityDao {

    public User getLoginSession(String user, String password);
    public User getUsuFirmado(String user, String password);
}
