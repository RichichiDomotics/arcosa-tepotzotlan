package com.cbj.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.service.VistasManager;
import com.cbj.almacen.repository.VistasDao;

@Component
public class VistasManagerImpl implements VistasManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private VistasDao vistasDao;

    
    public List<VistaIngreso> getEntradas() {
	return vistasDao.getConsultaIngresosList();
    }

    public VistaIngresoDetalle getIngreso(Integer id) {
    	return vistasDao.getConsultaIngresosDetList(id);
        }
    
    }
