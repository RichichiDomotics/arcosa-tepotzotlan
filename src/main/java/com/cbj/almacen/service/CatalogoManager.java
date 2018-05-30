package com.cbj.almacen.service;

import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.Convenios;
import com.cbj.almacen.service.impl.TipoCatalogo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 25/05/2014.
 */
public interface CatalogoManager extends Serializable {
    public List<?> getCatalogo(final TipoCatalogo tipoCatalogo);
}
