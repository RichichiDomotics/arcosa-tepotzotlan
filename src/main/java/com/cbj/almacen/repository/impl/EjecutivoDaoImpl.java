package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Ejecutivo;
import com.cbj.almacen.repository.EjecutivoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 5/11/2015.
 */
@Repository(value = "ejecutivoDao")
public class EjecutivoDaoImpl implements EjecutivoDao{
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

    @Override
    public List<Ejecutivo> getAll() {
        try{
            Query query = em.createNamedQuery("Ejecutivo.getAll");
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Ejecutivo>();
        }
    }

    @Override
    public List<Ejecutivo> getAllJefe(int idJefe) {
        try{
            Query query = em.createNamedQuery("Ejecutivo.getAllJefe");
            query.setParameter("idJefe", idJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Ejecutivo>();
        }
    }

    public Ejecutivo getEjecutivo(String claveUsuario) {
        Ejecutivo ejecutivo = new Ejecutivo();
        try{
            Query query = em.createNamedQuery("Ejecutivo.getEjecutivo");
            query.setParameter("claveEjecutivo", claveUsuario);
            ejecutivo= (Ejecutivo)query.getResultList().get(0);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return  ejecutivo;
    }

    public Ejecutivo getEjecutivoId(Integer idEjecutivo) {
        Ejecutivo ejecutivo = new Ejecutivo();
        try{
            Query query = em.createNamedQuery("Ejecutivo.getEjecutivoId");
            query.setParameter("idEjecutivo", idEjecutivo);
            ejecutivo= (Ejecutivo)query.getResultList().get(0);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return  ejecutivo;
    }
}
