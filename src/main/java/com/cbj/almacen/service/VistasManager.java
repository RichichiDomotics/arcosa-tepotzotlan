package com.cbj.almacen.service;

import java.io.Serializable;
import java.util.List;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;


public interface VistasManager extends Serializable {

        public List<VistaIngreso> getEntradas();
        public VistaIngresoDetalle getIngreso(Integer id);
}