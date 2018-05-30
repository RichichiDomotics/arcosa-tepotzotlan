package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.JefeEjecutivo;
import com.cbj.almacen.repository.JefeEjecutivoDao;
import org.hibernate.id.IncrementGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 5/11/2015.
 */
@Repository(value = "jefeEjecutivoDao")
public class JefeEjecutivoDaoImpl implements JefeEjecutivoDao{
    private static final Logger logger = LoggerFactory
            .getLogger(JefeEjecutivoDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public JefeEjecutivo getJefeEjecutivo(Integer idJefeEjecutivo) {
        JefeEjecutivo jefeEjecutivo = new JefeEjecutivo();
        try{
            Query query = em.createNamedQuery("JefeEjecutivo.getJefeEjecutivo");
            query.setParameter("idJefeEjecutivo", idJefeEjecutivo);
            jefeEjecutivo= (JefeEjecutivo)query.getResultList().get(0);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return  jefeEjecutivo;
    }

    @Override
    public JefeEjecutivo getJefeEjecutivoUser(String userJefe) {
        JefeEjecutivo jefeEjecutivo = new JefeEjecutivo();
        try{
            Query query = em.createNamedQuery("JefeEjecutivo.getJefeEjecutivoUser");
            query.setParameter("userJefe", userJefe);
            jefeEjecutivo= (JefeEjecutivo)query.getResultList().get(0);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return  jefeEjecutivo;
    }
}
