package com.cbj.almacen.repository;

import java.util.List;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;



public interface VistasDao {
    public List<VistaIngreso> getConsultaIngresosList();
    public VistaIngresoDetalle getConsultaIngresosDetList(Integer id);
}
