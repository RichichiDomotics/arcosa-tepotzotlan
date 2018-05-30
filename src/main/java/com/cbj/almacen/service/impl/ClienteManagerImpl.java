package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.repository.ClienteDao;
import com.cbj.almacen.service.ClienteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Component
public class ClienteManagerImpl implements ClienteManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public Clientes getByIdCliente(int id) {
        return clienteDao.findCliente(id);
    }

    @Override
    public Clientes insertCliente(Clientes cliente) {
        return clienteDao.insertCliente(cliente);
    }

    @Override
    public boolean updateCliente(Clientes cliente) {
        return clienteDao.updateCliente(cliente);
    }

    @Override
    public List<Clientes> getClientesByIdNom(String id, String nombre) {
        return clienteDao.getClientes(id, nombre);
    }

    @Override
    public Clientes getClienteByIdNom(String id, String nombre) {
        return clienteDao.getClienteByIdNom(id, nombre);
    }

    @Override
    public List<Clientes> getClientesAll() {
        return clienteDao.getClientesAll();
    }

    @Override
    public List<Clientes> getFormatosUnicos(){ return clienteDao.getFormatosUnicos(); }

    @Override
    public List<Clientes> getAllIdEjecutivo(String idEjecutivo) {
        return clienteDao.getAllIdEjecutivo(idEjecutivo);
    }

    @Override
    public List<Clientes> getByEstatusSalida(){return clienteDao.getByEstatusSalida();}

}
