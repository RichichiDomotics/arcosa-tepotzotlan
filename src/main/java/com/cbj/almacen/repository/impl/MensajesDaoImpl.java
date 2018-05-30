package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Mensajes;
import com.cbj.almacen.repository.MensajesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "mensajesDao")
public class MensajesDaoImpl implements MensajesDao {

    private static final Logger logger = LoggerFactory
            .getLogger(MensajesDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

   
    @Transactional(readOnly = false)
    public boolean insertMensajes(Mensajes datosMensaje){
	boolean respuesta=false;
    try{
    	em.persist(datosMensaje);
    	respuesta=true;
	}catch(Exception e){
		respuesta=false;
    }
	return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateMensajesVisto(int idMensaje) {
        boolean respuesta=false;
        try {
            Query query = em.createNamedQuery("Mensajes.updateMensajesVisto");
            query.setParameter("idMensaje", idMensaje);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return respuesta;
        }
    }

    @Transactional(readOnly = false)
    public boolean updateMensajes(Mensajes datosMensaje) {
        boolean respuesta=false;
        logger.trace(datosMensaje.toString());
        try{
            logger.info(datosMensaje.toString());
            em.merge(datosMensaje);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public Mensajes getMisMensajesXId(int idMensaje) {
        Mensajes mensaje = new Mensajes();
        try{
            Query query = em.createNamedQuery("Mensajes.getMisMensajesXId");
            query.setParameter("idMensaje", idMensaje);

            //if(query.getResultList().size() == 1){
                mensaje = (Mensajes) query.getResultList().get(0);
            //}

        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return mensaje;
        }
        return mensaje;
    }

    @Override
    public List<Mensajes> getMiMensajesXUsuario(String usuarioRecibe) {
        try{
            Query query = em.createNamedQuery("Mensajes.getMiMensajesXUsuario");
            query.setParameter("usuarioRecibe", usuarioRecibe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Mensajes>();
        }
    }

    @Override
    public List<Mensajes> getTodosMisMensajes(String usuarioRecibe) {
        try{
            Query query = em.createNamedQuery("Mensajes.getTodosMisMensajesT");
            query.setParameter("usuarioRecibe", usuarioRecibe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            List<Mensajes> mensajeserror = null;
            return mensajeserror;
        }
    }

    @Override
    public List<Mensajes> getMisMensajes(String usuarioRecibe) {
        try{
            Query query = em.createNamedQuery("Mensajes.getMisMensajes");
            query.setParameter("usuarioRecibe", usuarioRecibe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Mensajes>();
        }
    }

}
