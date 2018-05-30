package com.cbj.almacen.service;


import com.cbj.almacen.domain.JefeEjecutivo;

import java.io.Serializable;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface JefeEjecutivoManager extends Serializable {

    public JefeEjecutivo getJefeEjecutivo(Integer idJefeEjecutivo);
    public JefeEjecutivo getJefeEjecutivoUser(String userJefe);

}
