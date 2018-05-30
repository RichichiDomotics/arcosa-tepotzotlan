package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.TraspasosCamara;
import com.cbj.almacen.repository.TraspasosCamaraDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 09/09/2014.
 */
@Repository(value = "traspasosCamaraDao")
public class TraspasosCamaraDaoImpl implements TraspasosCamaraDao {

    private static final Logger logger = LoggerFactory
            .getLogger(ServiciosDaoImpl.class);

    private EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public TraspasosCamara insertTraspasoCamara(TraspasosCamara traspasosCamara) {
        try{
            em.persist(traspasosCamara);
            em.flush();
            return traspasosCamara;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new TraspasosCamara();
        }
    }
    @Override
    public List<TraspasosCamara> getByRDFecha(String consecutivo, Date fechaini, Date fechafin){
        try{
           /* Query query = em.createNamedQuery("TraspasosCamara.getByRDFecha");
            query.setParameter("consecutivo",consecutivo);
            query.setParameter("fechaini",fechaini);
            query.setParameter("fechafin",fechafin);
            return query.getResultList();*/
            logger.info("numero de rd "+consecutivo);
            if(!consecutivo.equals("")){
                Query query = em.createNamedQuery("TraspasosCamara.getByRDFecha");
                query.setParameter("consecutivo",consecutivo);
                return query.getResultList();
            } else
            if(consecutivo.equals("") && !fechaini.equals("") && !fechafin.equals("")){
                Query query = em.createNamedQuery("TraspasosCamara.getByRDFecha2");
                query.setParameter("fechaini",fechaini);
                query.setParameter("fechafin",fechafin);
                return query.getResultList();

            } else if(!consecutivo.equals("") && !fechaini.equals("") && !fechafin.equals("")){
                Query query = em.createNamedQuery("TraspasosCamara.getByRDFecha3");
                query.setParameter("consecutivo",consecutivo);
                query.setParameter("fechaini",fechaini);
                query.setParameter("fechafin",fechafin);
                return query.getResultList();
            }
        }catch (Exception e){
            logger.error("Error al traer el historico de traspasos "+e.getMessage()+e.getCause()+e);
            return new ArrayList<TraspasosCamara>();
        }
        return new ArrayList<TraspasosCamara>();
    }
}
