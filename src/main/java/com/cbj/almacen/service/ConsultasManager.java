package com.cbj.almacen.service;

import java.io.Serializable;
import java.util.List;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;


public interface ConsultasManager extends Serializable {

        public List<VistaIngreso> getEntradas();
        public List<VistaIngreso> getEntradasByStatusIngreso(String status);
        public List<VistaIngreso> getEntradasByStatusSalida(String status);
        public VistaIngresoDetalle getIngreso(Integer id);
        public List<Clientes> getClientes();
        public Clientes getCliente(Integer id);
        public List<Object[]> getVehiculoRDSinCapturar();
        public List<VistaIngreso> getArlaVehiculos(String clientes);
}