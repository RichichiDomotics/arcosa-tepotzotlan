package com.cbj.almacen.service;

import com.cbj.almacen.domain.Servicios;
//import com.cbj.almacen.domain.Inventario;


import java.io.Serializable;
import java.util.List;

/**
 * Created by colvera on 20/08/2014.
 */
public interface ServiciosManager extends Serializable {
	public boolean insertServicio(Servicios servicio);
	public List<Servicios> getByActivo();
	public List<Servicios> getByActivoByIdCliente(String idCliente);
	public List<Object[]> getCboServicios();
	public Servicios getByActivoByIdClienteClave(String idCliente, String clave);
}
