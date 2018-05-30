package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.repository.RegEntradasDao;
import com.cbj.almacen.service.RegEntradasManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bvelasco on 17/05/2014.
 */
@Component
public class RegEntradasManagerImpl implements RegEntradasManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private RegEntradasDao regEntradasDao;

    @Override
    public int registraRegEntrada(RegEntradas entradas) {
    	return regEntradasDao.insertRegistroRegEntradas(entradas);
    }

    @Override
    public boolean actualizaRegEntrada(RegEntradas entradas) {
        return regEntradasDao.updateRegistroRegEntradas(entradas);
    }

    @Override
    public RegEntradas consultaRegEntradaByIdIngresoVehiculo(int id) {
        return regEntradasDao.findRegistroRegEntradasByIdIngresoVehiculo(id);
    }

    @Override
    public List<RegEntradas> getEntradaByConsecutivo(int consecutivo) {
        return regEntradasDao.findRegistroRegEntradasByConsecutivo(consecutivo);
    }

    @Override
    public List<RegEntradas> getEntradaByConsecutivoImpresionEntrada(int consecutivo) {
        return regEntradasDao.findRegistroRegEntradasByConsecutivoImpresionEntrada(consecutivo);
    }

    @Override
    public List<RegEntradas> getEntradaByActualDate() {
        return regEntradasDao.getAllByActualDate();
    }
    
    @Override
    public List<RegEntradas> getEntradaByActualDateEntrada() {
        return regEntradasDao.getAllByActualDateEntrada();
    }
    
    @Override  
    public List<RegEntradas> getComparaCantidadesTonelaje(String fecini,String fecfin) {
        return regEntradasDao.getComparaCantidadesTonelaje(fecini,fecfin);
    }

    @Override
    public List<RegEntradas> getCancelados(){
        return  regEntradasDao.getCancelados();
    }
}
