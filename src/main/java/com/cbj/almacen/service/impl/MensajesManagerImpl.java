package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Mensajes;
import com.cbj.almacen.repository.MensajesDao;
import com.cbj.almacen.service.MensajesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MensajesManagerImpl implements MensajesManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MensajesDao mensajesDao;
    
    
    public boolean setIngresaMensajes(Mensajes mensajes) {
        return mensajesDao.insertMensajes(mensajes);
    }

    public boolean updateIngresaMensajes(Mensajes mensajes) {
    	return mensajesDao.updateMensajes(mensajes);
    }

    public Mensajes getMisMensajesXId(int idMensaje) {
        return mensajesDao.getMisMensajesXId(idMensaje);
    }

    public List<Mensajes> getMiMensajesXUsuario(String usuarioRecibe) {
        return mensajesDao.getMiMensajesXUsuario(usuarioRecibe);
    }

    public List<Mensajes> getTodosMisMensajes(String usuarioRecibe) {
        return mensajesDao.getTodosMisMensajes(usuarioRecibe);
    }
    public List<Mensajes> getMisMensajes(String usuarioRecibe) {
        return mensajesDao.getMisMensajes(usuarioRecibe);
    }

    public boolean updateMensajesVisto(int idMensaje) {
        return mensajesDao.updateMensajesVisto(idMensaje);
    }

}
