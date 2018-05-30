package com.cbj.almacen.repository.impl;

import org.springframework.stereotype.Repository;
import com.cbj.almacen.domain.BsalidasDetalle;
import com.cbj.almacen.repository.BsalidasDetalleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Ricardo on 18/01/2016.
 */

@Repository(value = "BsalidasDetalleDao")
public class BsalidasDetalleDaoImpl implements BsalidasDetalleDao{
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
    public List<BsalidasDetalle> getFechasID(Date fechaini, Date fechafin, String idCliente){
        try {
            Query query = em.createNamedQuery("BsalidasDetalle.getFechasID");
            query.setParameter("fechaini",fechaini, TemporalType.DATE);
            query.setParameter("fechafin",fechafin, TemporalType.DATE);
            query.setParameter("idCliente",idCliente);
            return  query.getResultList();
        }catch (Exception e){
            logger.error("Error al traer las salidas de bachoco"+e.getMessage()+e.getCause());
            return new ArrayList<BsalidasDetalle>();
        }
    }
     @Override
    public List<BsalidasDetalle> getFolioSalida(String folioSalida){
         try{
             Query query = em.createNamedQuery("BsalidasDetalle.getFolioSalida");
             query.setParameter("foliosalida",folioSalida);
             return query.getResultList();
         }catch (Exception e){
             logger.error("EEror al traer las salidas de bachoco con el folio baby"+e.getMessage()+e.getCause());
             return new ArrayList<BsalidasDetalle>();
         }
     }
}
