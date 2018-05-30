package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.AvisosHistorial;
import com.cbj.almacen.repository.AvisosHistorialDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "avisosHistorialDao")
public class AvisosHistorialDaoImpl implements AvisosHistorialDao {

    private static final Logger logger = LoggerFactory
            .getLogger(AvisosHistorialDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

   
    @Transactional(readOnly = false)
    public boolean insertAvisosHistorial(AvisosHistorial datosAvisosHistorial){
	boolean respuesta=false;
    try{
    	em.persist(datosAvisosHistorial);
    	respuesta=true;
	}catch(Exception e){
		respuesta=false;
    }
	return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateAvisosHistorial(AvisosHistorial datosAvisosHistorial) {
        boolean respuesta=false;
        logger.trace(datosAvisosHistorial.toString());
        try{
            logger.info(datosAvisosHistorial.toString());
            em.merge(datosAvisosHistorial);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public List<AvisosHistorial> findPorClaveJefe(String claveJefe) {
        try{
            Query query = em.createNamedQuery("AvisosHistorial.getXJefe");
            query.setParameter("claveJefe", claveJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<AvisosHistorial>();
        }
    }

    @Override
    public List<Object> getTotalAvisos(String claveJefe) {
        try{
            Query query = em.createNamedQuery("AvisosHistorial.getTotalAvisos");
            query.setParameter("claveJefe", claveJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getAvisosLista(String claveJefe) {
        try{
            Query query = em.createNamedQuery("AvisosHistorial.getAvisosLista");
            query.setParameter("claveJefe", claveJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getAvisosListaDetalle(String claveJefe) {
        try {
            Query query = em.createNamedQuery("AvisosHistorial.getAvisosListaDetalle");
            query.setParameter("claveJefe", claveJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Object>();
        }
    }



    @Transactional(readOnly = false)
    public boolean updateAvisosHistorialVisto(String tipoNotificacion) {
        boolean respuesta=false;
        try {
            Query query = em.createNamedQuery("AvisosHistorial.updateAvisosHistorialXNotificacion");
            query.setParameter("tipoNotificacion", tipoNotificacion);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return respuesta;
        }
    }

}
