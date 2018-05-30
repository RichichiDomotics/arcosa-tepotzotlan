package com.cbj.almacen.service;

import com.cbj.almacen.domain.Convenios;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface ConveniosManager  extends Serializable {
    public List<Convenios> getByClientes(int idCliente);
    public Convenios getByCliente(int idCliente);
    public Convenios insertConvenio(Convenios convenio);
    public  List<Convenios> getAll();
    public List<Object> getTipoPeriodo();
    public Convenios getByClaveProducto(String claveProducto);
    public List<Convenios> getArla(String clientes);
}
