package com.cbj.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.repository.ConsultasDao;
import com.cbj.almacen.service.ConsultasManager;

@Component
public class SimpleConsultasManagerImpl implements ConsultasManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ConsultasDao consultasDao;


    public List<VistaIngreso> getEntradas() {
        return consultasDao.getConsultaIngresosList();
    }

    public List<VistaIngreso> getEntradasByStatusIngreso(String status) {
        return consultasDao.getConsultaIngresosListByStatusIngreso(status);
    }

    public List<VistaIngreso> getEntradasByStatusSalida(String status) {
        return consultasDao.getConsultaIngresosListByStatusSalida(status);
    }

    public VistaIngresoDetalle getIngreso(Integer id) {
        return consultasDao.getConsultaIngresosDetList(id);
    }

    public List<Clientes> getClientes() {
        return consultasDao.getClientesList();
    }

    public Clientes getCliente(Integer id) {
        return consultasDao.getConsultaClienteDetList(id);
    }

    public List<Object[]> getVehiculoRDSinCapturar() {
        return consultasDao.getVehiculoRDSinCapturar();
    }

    public List<VistaIngreso> getArlaVehiculos(String clientes) {
        return consultasDao.getArlaVehiculos(clientes);
    }

    public List<Object> getArlaVehiculosDetalle (String clientes){ return  consultasDao.getArlaVehiculosDetalle(clientes); }
}
