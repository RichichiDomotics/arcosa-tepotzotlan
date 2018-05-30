package com.cbj.almacen.service;

import com.cbj.almacen.domain.Clientes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface ClienteManager extends Serializable {

    public Clientes getByIdCliente(int id);

    public Clientes insertCliente(Clientes cliente);
    public boolean updateCliente(Clientes cliente);
    public List<Clientes> getClientesByIdNom(String id,String nombre);
    public Clientes getClienteByIdNom(String id,String nombre);
    public List<Clientes> getClientesAll();
    public List<Clientes> getFormatosUnicos();
    public List<Clientes> getAllIdEjecutivo(String idEjecutivo);
    public List<Clientes> getByEstatusSalida();
}
