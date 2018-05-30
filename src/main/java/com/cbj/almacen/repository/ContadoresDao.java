package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Contadores;

/**
 * Created by jolvera on 10/06/2014.
 */
public interface ContadoresDao {

    public Contadores findContador(String tipoContador);
    public boolean updateContadores(Contadores contadores);

}
