package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.FormEntrada;
import com.cbj.almacen.repository.EntradasDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jolvera on 04/05/2014.
 */
public class SimpleEntradasManagerAdapter extends SimpleEntradasManagerImpl{

    @Autowired
    private EntradasDao entradasDao;

    public boolean setEntradas(JSONObject entradaJson) {
        FormEntrada entrada = new FormEntrada();
        entrada.setIdFleje(Integer.valueOf(entradaJson.getString("id")));
        entradasDao.setEntradaVehiculo(entrada);
        return true;
    }
}
