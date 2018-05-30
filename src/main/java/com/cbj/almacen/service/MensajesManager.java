package com.cbj.almacen.service;

import com.cbj.almacen.domain.Mensajes;

import java.io.Serializable;
import java.util.List;


public interface MensajesManager extends Serializable {

    public boolean setIngresaMensajes(Mensajes mensajes);
    public boolean updateIngresaMensajes(Mensajes mensajes);
    public Mensajes getMisMensajesXId(int idMensaje);
    public List<Mensajes> getMiMensajesXUsuario(String usuarioRecibe);
    public List<Mensajes> getTodosMisMensajes(String usuarioRecibe);
    public List<Mensajes> getMisMensajes(String usuarioRecibe);
    public boolean updateMensajesVisto(int idMensaje);
}