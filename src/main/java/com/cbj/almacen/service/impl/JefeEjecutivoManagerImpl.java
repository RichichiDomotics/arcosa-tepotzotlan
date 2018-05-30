package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.JefeEjecutivo;
import com.cbj.almacen.repository.JefeEjecutivoDao;
import com.cbj.almacen.service.JefeEjecutivoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jolvera on 01/09/2014.
 */
@Component
public class JefeEjecutivoManagerImpl implements JefeEjecutivoManager{

    //private static final Logger logger = LoggerFactory.getLogger(PdfSalidasManagerImpl.class);

    @Autowired
    private JefeEjecutivoDao jefeEjecutivoDao;


    @Override
    public JefeEjecutivo getJefeEjecutivo(Integer idJefeEjecutivo){ return jefeEjecutivoDao.getJefeEjecutivo(idJefeEjecutivo); }

    @Override
    public JefeEjecutivo getJefeEjecutivoUser(String userJefe){ return jefeEjecutivoDao.getJefeEjecutivoUser(userJefe); }

}
