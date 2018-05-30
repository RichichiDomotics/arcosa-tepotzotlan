package com.cbj.almacen.repository.impl;

/**
 * Created by Ricardo on 29/03/2016.
 */
import com.cbj.almacen.domain.Continuidades;
import com.cbj.almacen.repository.ContinuidadesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContinuidadesDaoImpl implements ContinuidadesDao {

    private static final Logger logger = LoggerFactory
            .getLogger(DetalleFacturacionDaoImpl.class);

    private EntityManager em = null;
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertContinuidad(Continuidades continuidades) {
        boolean respuesta=false;
        try{
            em.persist(continuidades);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateContinuidad(Continuidades continuidades) {
        boolean respuesta = false;
        try {
            em.merge(continuidades);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public List<Object> getAll(String fechainicio, String fechafin){
        try{
            Query query = em.createNamedQuery("Continuidades.getAll");
            query.setParameter("fechaini",fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al traer toda la tabla de facturacion");
            e.printStackTrace();
            return new ArrayList<Object>();
        }
    }

    @Override
    public Continuidades getByConsecutivo(int consecutivo){
        try{
            Query query = em.createNamedQuery("Continuidades.getByConsecutivo");
            query.setParameter("consecutivo",consecutivo);
            return (Continuidades) query.getResultList().get(0);
        }catch (Exception e){
            logger.error("Error al traer las continuidades del RD "+consecutivo);
            e.printStackTrace();
            return new Continuidades();
        }
    }
}
