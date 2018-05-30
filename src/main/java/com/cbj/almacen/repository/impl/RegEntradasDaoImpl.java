package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.repository.RegEntradasDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
@Repository(value = "regEntradasDao")
public class RegEntradasDaoImpl implements RegEntradasDao {

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
    public int insertRegistroRegEntradas(RegEntradas entrada) {
        RegEntradas regEntradas = new RegEntradas();
        try {
            regEntradas = (RegEntradas) em.createQuery("select p from RegEntradas p where p.consecutivo=(SELECT max(p.consecutivo) FROM RegEntradas p)").getSingleResult();
            if(regEntradas!=null) {
                entrada.setConsecutivo(regEntradas.getConsecutivo() + 1);
            }else{
                entrada.setConsecutivo(1);
            }
                em.persist(entrada);

        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }

        return regEntradas.getConsecutivo() + 1;
    }

    @Transactional(readOnly = false)
    public boolean updateRegistroRegEntradas(RegEntradas entrada) {
        boolean respuesta = false;
        logger.trace(entrada.toString());
        try {
            logger.info(entrada.toString());
            em.merge(entrada);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean deleteRegistroRegEntradas(int id) {
        boolean respuesta = false;

        try {
            RegEntradas entradas = findRegistroRegEntradas(id);
            em.remove(em.contains(entradas) ? entradas : em.merge(entradas));
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public RegEntradas findRegistroRegEntradas(int id) {
        RegEntradas entradas = new RegEntradas();
        try {
            entradas = em.find(RegEntradas.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        return entradas;
    }

    @Override
    public RegEntradas findRegistroRegEntradasByIdIngresoVehiculo(int id) {
        RegEntradas entradas = new RegEntradas();
        try {
            Query  query = em.createNamedQuery("RegEntradas.findByIdIngresoVehiculo");
            query.setParameter("idIngresoVehiculo", id);
            entradas = (RegEntradas) query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        return entradas;
    }

    @Override
    public List<RegEntradas> findRegistroRegEntradasByConsecutivo(int consecutivo) {
        try {
            Query  query = em.createNamedQuery("RegEntradas.findByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<RegEntradas>();
        }
    }

    @Override
    public List<RegEntradas> findRegistroRegEntradasByConsecutivoImpresionEntrada(int consecutivo) {
        try {
            Query  query = em.createNamedQuery("RegEntradas.findByConsecutivoImpresionEntrada");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<RegEntradas>();
        }
    }

    @Override
    public List<RegEntradas> getAllByActualDate() {
        try {
            final Date date = new Date();
            final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
            final String fecha = formatFecha.format(date);
            logger.info("fecha" +fecha);
            Query  query = em.createNamedQuery("RegEntradas.getAllByActualDate");
            query.setParameter("fecha", fecha);
            return query.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<RegEntradas>();
        }    }
    
    @Override
    public List<RegEntradas> getAllByActualDateEntrada() {
        try {
            final Date date = new Date();
            final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatFecha.format(date);
            fecha = fecha+" 00:00:00";
            Query  query = em.createNamedQuery("RegEntradas.getAllByActualDateEntrada");
            query.setParameter("fecha", fecha);
            return query.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<RegEntradas>();
        }    }
    
    @Override
    public List<RegEntradas> getComparaCantidadesTonelaje(String fecini, String fecfin) {
        Query query = em.createNamedQuery("RegEntradas.getComparaCantidadesTonelaje");
        query.setParameter("fecini", fecini);
        query.setParameter("fecfin", fecfin);
        return query.getResultList();

    }

    @Override
    public List<RegEntradas> getCancelados(){
        Query query = em.createNamedQuery("RegEntradas.getCancelados");
        return query.getResultList();
    }

}
