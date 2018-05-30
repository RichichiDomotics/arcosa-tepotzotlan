package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Seguimiento;
import com.cbj.almacen.repository.SeguimientoDao;
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
 * Created by jolvera on 02/06/2014.
 */
@Repository(value = "seguimientoDao")
public class SeguimientoDaoImpl implements SeguimientoDao {

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
    public boolean insertSeguimiento(Seguimiento seguimiento) {
        boolean respuesta=false;
        try{
            em.persist(seguimiento);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateSeguimiento(Seguimiento seguimiento) {
        boolean respuesta = false;
        try {
            em.merge(seguimiento);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;

    }
    @Override
    public List<Seguimiento> getAll() {
        try{
            Query query = em.createNamedQuery("Seguimiento.getAll");
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Seguimiento>();
        }
    }
    public List<Seguimiento> getSeguimiento(int idProspecto){
        Seguimiento seguimiento = new Seguimiento();
        try{
            Query query = em.createNamedQuery("Seguimiento.getSeguimiento");
            query.setParameter("idProspecto", idProspecto);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Seguimiento>();
        }
    }

}
