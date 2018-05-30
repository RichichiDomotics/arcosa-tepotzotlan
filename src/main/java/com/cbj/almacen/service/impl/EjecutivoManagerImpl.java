package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Ejecutivo;
import com.cbj.almacen.repository.EjecutivoDao;
import com.cbj.almacen.service.EjecutivoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
@Component
public class EjecutivoManagerImpl implements EjecutivoManager{

    private static final Logger logger = LoggerFactory
            .getLogger(PdfSalidasManagerImpl.class);

    @Autowired
    private EjecutivoDao ejecutivoDao;


    @Override
    public List<Ejecutivo> getAll() {
        return ejecutivoDao.getAll();
    }
    public List<Ejecutivo> getAllJefe(int idJefe) {
        return ejecutivoDao.getAllJefe(idJefe);
    }
    public Ejecutivo getEjecutivo(String claveUsuario){ return ejecutivoDao.getEjecutivo(claveUsuario); }
    public Ejecutivo getEjecutivoId(Integer idEjecutivo){ return ejecutivoDao.getEjecutivoId(idEjecutivo); }

}
