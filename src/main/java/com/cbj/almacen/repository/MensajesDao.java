package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Mensajes;

import java.util.List;


public interface MensajesDao {

    public boolean insertMensajes(Mensajes datosMensaje);
    public boolean updateMensajes(Mensajes datosMensaje);
    public Mensajes getMisMensajesXId(int idMensaje);
    public List<Mensajes> getMiMensajesXUsuario(String usuarioRecibe);
    public List getTodosMisMensajes(String usuarioRecibe);
    public List<Mensajes> getMisMensajes(String usuarioRecibe);
    public boolean updateMensajesVisto(int idMensaje);
}
