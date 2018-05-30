package com.cbj.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbj.almacen.domain.Retemes;
import com.cbj.almacen.repository.RetemesDao;
import com.cbj.almacen.service.RetemesManager;

@Component
public class RetemesManagerImpl implements RetemesManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RetemesDao retemesDao;
    
    
    public boolean setIngresaRetemes(Retemes reteme) {
    	return retemesDao.insertReteme(reteme);
        }

    @Override
    public List<Retemes> findRetemeByConsecutivo(String consecutivo) {
        return retemesDao.findRetemeByConsecutivo(consecutivo);
    }
    
    @Override
    public List<Retemes> findRetemeByIdCliente(String idCliente, String fecini, String fecfin, String folioReteme,String consecutivo) {
        return retemesDao.findRetemeByIdCliente(idCliente,fecini,fecfin,folioReteme,consecutivo);
    }
    
    @Override
    public String getMaxId() {
        return retemesDao.getMaxId();
    }

    @Override
    public String getMaxIdTIF(){return retemesDao.getMaxIdTIF();}

}
