package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.AutPuertas;
import com.cbj.almacen.repository.AutPuertasDao;
import com.cbj.almacen.service.AutPuertasManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 12/11/2014.
 */
@Component
public class AutPuertasManagerImpl implements AutPuertasManager{

    private static final long serialVersionUID = 1L;

    @Autowired
    private AutPuertasDao autPuertasDao;

    @Override
    public List<AutPuertas> getPuertaByClienteVehiculo(int idCliente, int idTipoVehiculo) {
        return autPuertasDao.getPuertaByClienteVehiculo(idCliente, idTipoVehiculo);
    }
}
