package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.TraspasosCamara;
import com.cbj.almacen.repository.TraspasosCamaraDao;
import com.cbj.almacen.service.TraspasosCamaraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 09/09/2014.
 */
@Component
public class TraspasosCamaraManagerImpl implements TraspasosCamaraManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TraspasosCamaraDao traspasosCamaraDao;

    @Override
    public TraspasosCamara insertTraspasoCamara(TraspasosCamara traspasosCamara) {
        return traspasosCamaraDao.insertTraspasoCamara(traspasosCamara);
    }
    public List<TraspasosCamara> getByRDFecha(String consecutivo, Date fechaini, Date fechafin){
        return traspasosCamaraDao.getByRDFecha(consecutivo, fechaini, fechafin);
    }
}
