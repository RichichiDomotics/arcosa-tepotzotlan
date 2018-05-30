package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.AvisosHistorial;
import com.cbj.almacen.repository.AvisosHistorialDao;
import com.cbj.almacen.service.AvisosHistorialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvisosHistorialManagerImpl implements AvisosHistorialManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AvisosHistorialDao avisosHistorialDao;
    
    
    public boolean setIngresaAvisosHistorial(AvisosHistorial avisosHistorial) {
        return avisosHistorialDao.insertAvisosHistorial(avisosHistorial);
    }

    public boolean updateIngresaAvisosHistorial(AvisosHistorial avisosHistorial) {
    	return avisosHistorialDao.updateAvisosHistorial(avisosHistorial);
    }

    public List<AvisosHistorial> getAvisosPorClaveJefe(String claveJefe) {
        return avisosHistorialDao.findPorClaveJefe(claveJefe);
    }

    public List<Object> getTotalAvisos(String claveJefe) {
        return avisosHistorialDao.getTotalAvisos(claveJefe);
    }

    public List<Object> getAvisosLista(String claveJefe) {
        return avisosHistorialDao.getAvisosLista(claveJefe);
    }

    public List<Object> getAvisosListaDetalle(String claveJefe) {
        return avisosHistorialDao.getAvisosListaDetalle(claveJefe);
    }

    public boolean updateAvisosHistorialVisto(String tipoNotificacion) {
        return avisosHistorialDao.updateAvisosHistorialVisto(tipoNotificacion);
    }

}
