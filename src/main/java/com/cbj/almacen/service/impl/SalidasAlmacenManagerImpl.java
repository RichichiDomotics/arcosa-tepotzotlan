package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.SalidasAlmacen;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.repository.SalidasAlmacenDao;
import com.cbj.almacen.repository.SalidasDetalleDao;
import com.cbj.almacen.service.SalidasAlmacenManager;
import com.cbj.almacen.service.SalidasDetalleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jolvera on 02/06/2014.
 */
@Component
public class SalidasAlmacenManagerImpl implements SalidasAlmacenManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private SalidasAlmacenDao salidasAlmacenDao;


    @Override
    public int registraSalidaAlmacen(SalidasAlmacen salidasAlmacen) {
        return salidasAlmacenDao.insertSalidasAlmacen(salidasAlmacen);
    }

    @Override
    public SalidasAlmacen getSalidaByClienteConsecutivo(String idCliente,int consecutivo) {
        return salidasAlmacenDao.findSalidaByClienteConsecutivo(idCliente, consecutivo);
    }

    @Override
    public SalidasAlmacen findSalidabyFolioSalida (String folioSalida){
        return salidasAlmacenDao.findSalidabyFolioSalida(folioSalida);
    }
}
