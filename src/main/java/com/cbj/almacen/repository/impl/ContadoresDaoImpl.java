package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Contadores;
import com.cbj.almacen.repository.ContadoresDao;
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
 * Created by jolvera on 10/06/2014.
 */
@Repository(value = "contadoresDao")
public class ContadoresDaoImpl implements ContadoresDao {

    private static final Logger logger = LoggerFactory
            .getLogger(ContadoresDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Contadores findContador(String tipoContador) {
        Contadores contador = new Contadores();
        try{
            Query query = em.createNamedQuery("Contadores.getXTipo");
            query.setParameter("tipoContador", tipoContador);
            contador = (Contadores) query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new Contadores();
        }
        return contador;
    }

    @Transactional(readOnly = false)
    public boolean updateContadores(Contadores contadores) {
        boolean respuesta = false;
        try {
            em.merge(contadores);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;

    }
}
