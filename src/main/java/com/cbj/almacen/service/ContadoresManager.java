package com.cbj.almacen.service;

import com.cbj.almacen.domain.Contadores;

import java.io.Serializable;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface ContadoresManager extends Serializable {

    public Contadores findContador(String tipoContador);
    public boolean updateContadores(Contadores contadores);

}
