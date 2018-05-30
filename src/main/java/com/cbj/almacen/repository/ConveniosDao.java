package com.cbj.almacen.repository;


import com.cbj.almacen.domain.Convenios;

import java.util.List;

/**
 * Created by Colvera on 02/06/2014.
 */
public interface ConveniosDao {
    public List<Convenios> getByClientes(int idCliente);
    public Convenios insertConvenio(Convenios convenio);
    public Convenios getByCliente(int idCliente);
    public  List<Convenios> getAll();
    public List<Object> getTipoPeriodo();
    public Convenios getByClaveProducto(String claveProducto);
    public List<Convenios> getArla(String clientes);
}
