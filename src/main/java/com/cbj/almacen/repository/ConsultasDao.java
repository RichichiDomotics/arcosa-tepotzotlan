package com.cbj.almacen.repository;

import java.util.List;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;



public interface ConsultasDao {
    public List<VistaIngreso> getConsultaIngresosList();
    public List<VistaIngreso> getConsultaIngresosListByStatusIngreso(String status);
    public List<VistaIngreso> getConsultaIngresosListByStatusSalida(String status);
    public VistaIngresoDetalle getConsultaIngresosDetList(Integer id);
    public List<Clientes> getClientesList();
    public Clientes getConsultaClienteDetList(Integer id);
    public List<Object[]> getVehiculoRDSinCapturar();
    public List<VistaIngreso> getArlaVehiculos(String clientes);
}
