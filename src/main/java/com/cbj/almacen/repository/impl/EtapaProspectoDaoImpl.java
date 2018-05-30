package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.EtapaProspecto;
import com.cbj.almacen.repository.EtapaProspectoDao;
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
 * Created by jolvera on 01/09/2014.
 */
@Repository(value = "etapaprospectoDao")
public class EtapaProspectoDaoImpl implements EtapaProspectoDao {

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
    public String getEtapaNombre(int id_etapa){
        Query query = em.createNamedQuery("EtapaProspecto.getEtapa");
        query.setParameter("idEtapa", id_etapa);
        return  String.valueOf(query.getResultList().get(0));
    }

}
