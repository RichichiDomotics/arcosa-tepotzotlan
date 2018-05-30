package com.cbj.almacen.repository;

import com.cbj.almacen.domain.JefeEjecutivo;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface JefeEjecutivoDao {

    public JefeEjecutivo getJefeEjecutivo(Integer idJefeEjecutivo);
    public JefeEjecutivo getJefeEjecutivoUser(String userJefe);

}
