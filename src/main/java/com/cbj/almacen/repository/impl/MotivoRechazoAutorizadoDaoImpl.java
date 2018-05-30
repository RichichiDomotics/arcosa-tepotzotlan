package com.cbj.almacen.repository.impl;

//import com.cbj.almacen.domain.MotivoRechazoAutorizado;
import com.cbj.almacen.domain.MotivosRechazoAutorizado;
import com.cbj.almacen.repository.MotivoRechazoAutorizadoDao;
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
/**
 * Created by Richard on 6/2/2015.
 */
@Repository(value = "motivoRechazoAutorizadoDao")
public class MotivoRechazoAutorizadoDaoImpl implements MotivoRechazoAutorizadoDao{
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
    public boolean insertMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado) {
        boolean respuesta=false;
        try{
            em.persist(motivoRechazoAutorizado);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado) {
        boolean respuesta = false;
        try {
            em.merge(motivoRechazoAutorizado);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }
    public int getAll(int idProspecto){
        try{
            Query query = em.createNamedQuery("Motivo.getAll");
            query.setParameter("idProspecto",idProspecto);
            return Integer.parseInt(query.getSingleResult().toString());
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return 0;
        }
    }
}
