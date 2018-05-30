package com.cbj.almacen.service.impl;

import java.io.Serializable;
import java.util.List;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;


public interface ConsultasManager extends Serializable {

        public List<VistaIngreso> getEntradas();
        public VistaIngresoDetalle getIngreso(Integer id);
        public List<Clientes> getClientes();
        public Clientes getCliente(Integer id);
}