package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Servicios;

import java.util.List;

/**
 * Created by Colvera on 02/06/2014.
 */
public interface ServiciosDao {
    public boolean insertServicio(Servicios datosServicio);
    public List<Servicios> getByActivo();
    public List<Servicios> getByActivoByIdCliente(String idCliente);
    public List<Object[]> getCboServicios();
    public Servicios getByActivoByIdClienteClave(String idCliente,String clave);
}
