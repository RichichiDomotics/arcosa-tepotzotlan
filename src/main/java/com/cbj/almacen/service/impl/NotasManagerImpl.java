package com.cbj.almacen.service.impl;

/**
 * Created by Richard on 07/07/2015.
 */

import com.cbj.almacen.domain.Notas;
import com.cbj.almacen.repository.NotasDao;
import com.cbj.almacen.service.NotasManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotasManagerImpl implements NotasManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private NotasDao notasDao;

    public boolean insertNota(Notas notas){
        return notasDao.insertNota(notas);
    }
    public boolean updateNota(Notas notas){
        return notasDao.updateNota(notas);
    }
    public List<Object> getNotasCredito(String fechainicio, String fechafin){
        return notasDao.getNotasCredito(fechainicio, fechafin);
    }
    public List<Object> getDetalleNotasCredito(String fechainicio, String fechafin){
        return notasDao.getDetalleNotasCredito(fechainicio, fechafin);
    }
    public double getNotasCreditoTotal(String fechainicio, String fechafin){
        return  notasDao.getNotasCreditoTotal(fechainicio, fechafin);
    }
    public List<Object> getNotasCreditoId(String fechainicio, String fechafin, String idEjecutivo){
        return notasDao.getNotasCreditoId(fechainicio, fechafin, idEjecutivo);
    }
    public List<Object> getDetalleNotasCreditoId(String fechainicio, String fechafin, String idEjecutivo){
        return notasDao.getDetalleNotasCreditoId(fechainicio, fechafin, idEjecutivo);
    }
    public double getNotasCreditoTotalId(String fechainicio, String fechafin, String idEjecutivo){
        return notasDao.getNotasCreditoTotalId(fechainicio, fechafin, idEjecutivo);
    }
    public String getid(){
        return notasDao.getid();
    }
    public List<Notas> getByFolio(int folio){
        return notasDao.getByFolio(folio);
    }
}
