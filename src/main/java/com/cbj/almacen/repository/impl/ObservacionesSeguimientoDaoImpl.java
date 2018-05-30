package com.cbj.almacen.repository.impl;

//import com.cbj.almacen.domain.MotivoRechazoAutorizado;
import com.cbj.almacen.domain.ObservacionesSeguimiento;
import com.cbj.almacen.repository.ObservacionesSeguimientoDao;
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
@Repository(value = "observacionesSeguimientoDao")
public class ObservacionesSeguimientoDaoImpl implements ObservacionesSeguimientoDao{
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
    public boolean insertObservacion(ObservacionesSeguimiento observacionesSeguimiento) {
        boolean respuesta=false;
        try{
            em.persist(observacionesSeguimiento);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateObservacion(ObservacionesSeguimiento observacionesSeguimiento) {
        boolean respuesta = false;
        try {
            em.merge(observacionesSeguimiento);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    public List<Object> getNumObservaciones(int idProspecto, int idSeguimiento){
        try {
            Query query = em.createNamedQuery("ObservacionesSeguimiento.getNumObservaciones");
            query.setParameter("idProspecto", idProspecto);
            query.setParameter("idSeguimiento", idSeguimiento);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }

    }

    public List<ObservacionesSeguimiento> getObservaciones(int idProspecto, int idSeguimiento){
        try {
            Query query = em.createNamedQuery("ObservacionesSeguimiento.getObservaciones");
            query.setParameter("idProspecto", idProspecto);
            query.setParameter("idSeguimiento", idSeguimiento);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<ObservacionesSeguimiento>();
        }

    }
}
