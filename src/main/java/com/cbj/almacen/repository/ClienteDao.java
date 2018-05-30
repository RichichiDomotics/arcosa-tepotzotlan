package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Clientes;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface ClienteDao {

    public Clientes findCliente(int id);

    public Clientes insertCliente(Clientes cliente);
    public boolean updateCliente(Clientes cliente);
    public List<Clientes> getClientes(String idCliente,String nombre);
    public Clientes getClienteByIdNom(String idCliente,String nombre);
    public List<Clientes> getClientesAll();
    public List<Clientes> getFormatosUnicos();
    public List<Clientes> getAllIdEjecutivo(String idEjecutivo);
    public List<Clientes> getByEstatusSalida();
}
