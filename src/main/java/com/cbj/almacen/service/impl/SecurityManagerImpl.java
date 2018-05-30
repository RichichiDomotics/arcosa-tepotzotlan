package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.User;
import com.cbj.almacen.repository.SecurityDao;
import com.cbj.almacen.service.SecurityUserManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jolvera on 11/05/2014.
 */
@Component
public class SecurityManagerImpl implements SecurityUserManager{

    @Autowired
    private SecurityDao securityDao;

    @Override
    public User getLoginSession(JSONObject entradaJson) {
        return securityDao.getLoginSession(entradaJson.getString("user"), entradaJson.getString("password"));
    }

    @Override
    public User getUsuFirmado(String usuario, String password) {
        return securityDao.getUsuFirmado(usuario, password);
    }
}
