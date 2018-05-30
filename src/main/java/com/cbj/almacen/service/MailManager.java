package com.cbj.almacen.service;

import com.cbj.almacen.domain.Vehiculo;

import java.io.Serializable;

/**
 * Created by jolvera on 18/11/2014.
 */
public interface MailManager extends Serializable {

    public void sendSalidaMail(Vehiculo vehiculo);
}
