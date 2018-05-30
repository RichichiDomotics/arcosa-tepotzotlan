package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.EtapaProspecto;
import com.cbj.almacen.repository.EtapaProspectoDao;
import com.cbj.almacen.service.EtapaProspectoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
@Component
public class EtapaProspectoManagerImpl implements EtapaProspectoManager{

    private static final Logger logger = LoggerFactory
            .getLogger(PdfSalidasManagerImpl.class);

    @Autowired
    private EtapaProspectoDao etapaprospectoDao;

   

    @Override
    public String getEtapaNombre(int id_etapa){
        return etapaprospectoDao.getEtapaNombre(id_etapa);
    }

}
