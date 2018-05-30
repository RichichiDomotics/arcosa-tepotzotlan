package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Convenios;
import com.cbj.almacen.repository.ConveniosDao;
import com.cbj.almacen.service.ConveniosManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
@Component
public class ConveniosManagerImpl implements ConveniosManager {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ConveniosDao conveniosDao;

    @Override
    public List<Convenios> getByClientes(int idCliente) {
        return conveniosDao.getByClientes(idCliente);
    }

    @Override
    public Convenios getByCliente(int idCliente) {
        return conveniosDao.getByCliente(idCliente);
    }

    @Override
    public Convenios insertConvenio(Convenios convenio) { return conveniosDao.insertConvenio(convenio); };

    @Override
    public  List<Convenios> getAll(){return conveniosDao.getAll();}

    @Override
    public List<Object> getTipoPeriodo(){return  conveniosDao.getTipoPeriodo();}

    @Override
    public Convenios getByClaveProducto(String claveProducto){
        return conveniosDao.getByClaveProducto(claveProducto);
    }

    @Override
    public List<Convenios> getArla(String clientes){ return conveniosDao.getArla(clientes); }
}
