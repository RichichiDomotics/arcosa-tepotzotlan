package com.cbj.almacen.repository.impl;

/**
 * Created by Richard on 21/07/2015.
 */

import com.cbj.almacen.domain.Modificaciones;
import com.cbj.almacen.repository.ModificacionesDao;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "ModificacionesDao")
public class ModificacionesDaoImpl implements ModificacionesDao {
    private static final Logger logger = LoggerFactory
            .getLogger(RegEntradasDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    @Transactional(readOnly = false)
    public boolean insertModificacion(Modificaciones modificaciones) {
        boolean respuesta=false;
        try{
            em.persist(modificaciones);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateModificacion(Modificaciones modificaciones) {
        boolean respuesta = false;
        try {
            em.merge(modificaciones);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public int getFolio(){
        int folio=0;
        try{
            Query query = em.createNamedQuery("Modificacion.getFolio");
            folio = Integer.parseInt(query.getResultList().get(0).toString());
        }catch (Exception e){
            logger.error("No se puede traer el ultimo folio"+e);
        }
        return folio;
    }

    @Override
    public List<Modificaciones> getAll(){
        List<Modificaciones> modificaciones = new ArrayList<Modificaciones>();
        try{
            Query query = em.createNamedQuery("Modificacion.getAll");
            modificaciones =  query.getResultList();
            return modificaciones;
        }catch (Exception e){
            logger.error("error al traer las modificaciones"+e);
            return new ArrayList<Modificaciones>();
        }
    }
    @Override
    public Modificaciones getModificacionById(Integer idModificacion){
        Modificaciones modificaciones = new Modificaciones();
        try{
            Query query = em.createNamedQuery("Modificacion.getModificacionById");
            query.setParameter("idModificacion",idModificacion);
            modificaciones = (Modificaciones) query.getResultList().get(0);
        }catch (Exception e){
            logger.error("Error al traer la modificacion"+e);
        }
        return modificaciones;
    }

    @Override
    public List<Modificaciones> getModificacionByFecha(String fechaini, String fechafin){
        try{
            Query query = em.createNamedQuery("Modificacion.getModificacionByFecha");
            query.setParameter("fechaini", fechaini);
            query.setParameter("fechafin",fechafin);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al traer las modificaciones por fecha"+e.getStackTrace());
            return new ArrayList<Modificaciones>();
        }
    }
}
