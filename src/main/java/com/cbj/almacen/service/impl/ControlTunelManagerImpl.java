package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.ControlTunel;
import com.cbj.almacen.repository.ControlTunelDao;
import com.cbj.almacen.service.ControlTunelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 22/07/2014.
 */
@Component
public class ControlTunelManagerImpl implements ControlTunelManager {

    @Autowired
    private ControlTunelDao controlTunelDao;

    @Override
    public boolean setControlTunel(ControlTunel controlTunel) {
        return controlTunelDao.insertControlTunel(controlTunel);
    }

    @Override
    public ControlTunel getControlTunelById(Integer id) {
        return controlTunelDao.findControlTunelById(id);
    }

    @Override
    public boolean updateControlTunel(ControlTunel controlTunel) {
        return controlTunelDao.updateControlTunel(controlTunel);
    }

    @Override
    public List<ControlTunel> getControlTunelAll() {
        return controlTunelDao.getAll();
    }

    @Override
    public ControlTunel getIdFolio(String folioAsignado){return controlTunelDao.getIdFolio(folioAsignado);}
}
