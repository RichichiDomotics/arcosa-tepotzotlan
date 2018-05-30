package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Salidas;
import com.cbj.almacen.repository.SalidasDao;
import com.cbj.almacen.service.SalidasManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
@Component
public class SalidasManagerImpl implements SalidasManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private SalidasDao salidasDao;


    @Override
    public int registraSalida(Salidas salida) {
        return salidasDao.insertSalidas(salida);
    }

    @Override
    public boolean updateSalida(Salidas salida) {
        return salidasDao.updateSalidas(salida);
    }

    @Override
    public Salidas consultaSalidaByIdFolioSalida(int id) {
        return salidasDao.findSalidaByIdFolioSalida(id);
    }

    @Override
    public List<Salidas> getSalidaByClienteConsecutivo(String idCliente, int consecutivo) {
        return salidasDao.findSalidaByClienteConsecutivo(idCliente,consecutivo);
    }
    
    @Override
    public List<Salidas> getSalidaByCliente(String idCliente) {
        return salidasDao.findSalidaByCliente(idCliente);
    }

    @Override
    public List<Salidas> getSalidaByConsecutivo(int consecutivo) {
        return salidasDao.findSalidaByConsecutivo(consecutivo);
    }
    
    @Override
    public List<Salidas> getSalidaByFolioSalida(int folioSalida) {
        return salidasDao.findSalidaByFolioSalida(folioSalida);
    }

    @Override
    public List<Salidas> getSalidaByFechaSalidaNull() {
        return salidasDao.getAllFechaSalidaNull();
    }

    @Override
    public List<Salidas> getKilosByFechaSalida(String fechaSalida) {
        return salidasDao.getKilosByFechaSalida(fechaSalida);
    }

}
