package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.ControlTunel;
import com.cbj.almacen.repository.ControlTunelDao;
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
 * Created by jolvera on 22/07/2014.
 */
@Repository(value = "controlTunelDao")
public class ControlTunelDaoImpl implements ControlTunelDao {
    private static final Logger logger = LoggerFactory
            .getLogger(InventarioDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertControlTunel(ControlTunel controlTunel) {
        boolean respuesta=false;
        try{
            em.persist(controlTunel);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public ControlTunel findControlTunelById(Integer folioAsignado) {
        ControlTunel controlTunel = new ControlTunel();
        try{
            controlTunel= em.find(ControlTunel.class,folioAsignado);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return controlTunel;

    }

    @Transactional(readOnly = false)
    public boolean updateControlTunel(ControlTunel controlTunel) {
        boolean respuesta=false;
        try{
            em.merge(controlTunel);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Override
    public List<ControlTunel> getAll() {
        try{
            Query query = em.createNamedQuery("ControlTunel.getAll");
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<ControlTunel>();
        }
    }

    @Override
    public ControlTunel getIdFolio(String folioAsignado){
        ControlTunel controlTunel= new ControlTunel();
        try{
            Query query = em.createNamedQuery("ControlTunel.getIdFolio");
            query.setParameter("folioAsignado",folioAsignado);
            controlTunel = (ControlTunel) query.getResultList().get(0);
        }catch (Exception e){
            logger.error("Error al traer el folio"+e.getMessage()+e.getCause());
        }
        return  controlTunel;
    }
}
