package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Salidas;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.repository.SalidasDetalleDao;
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
 * Created by jolvera on 02/06/2014.
 */
@Repository(value = "salidasDetalleDao")
public class SalidasDetalleDaoImpl implements SalidasDetalleDao{

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
    public int insertSalidasDetalle(final SalidasDetalle salidasDetalle) {
        //Salidas regSalidas = new Salidas();
        try {
            //regSalidas = (Salidas) em.createQuery("select p from Salidas p where p.consecutivo=(SELECT max(p.consecutivo) FROM Salidas p)").getSingleResult();
            //salidas.setConsecutivo(regSalidas.getConsecutivo() + 1);
            em.persist(salidasDetalle);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }

        return salidasDetalle.getConsecutivo() + 1;

    }

    @Override
     public List<SalidasDetalle> findSalidaByClienteConsecutivo(final String cliente, final int folioSalida) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getByClienteConsecutivo");
            query.setParameter("idCliente", cliente);
            // query.setParameter("consecutivo", consecutivo);
            query.setParameter("folioSalida", folioSalida);
            //SalidasDetalle resultadoSalidasDetalle = new SalidasDetalle();
            logger.debug("VARIABLE idCliente "+ cliente);
            //logger.debug("VARIABLE consecutivo "+ consecutivo);
            logger.debug("VARIABLE folioSalida "+ folioSalida);
            ArrayList<SalidasDetalle> resultadoSalidasDetalle = (ArrayList<SalidasDetalle>) query.getResultList();

            return resultadoSalidasDetalle;
        }catch (NoResultException e){
            ArrayList<SalidasDetalle> resultadoSalidasDetalle =  new ArrayList<SalidasDetalle>();
            return resultadoSalidasDetalle;
        }
    }

    @Override
    public List<SalidasDetalle> findSalidaFolioSalida(final int folioSalida) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getFolioSalida");
            // query.setParameter("consecutivo", consecutivo);
            query.setParameter("folioSalida", folioSalida);
            //SalidasDetalle resultadoSalidasDetalle = new SalidasDetalle();
            //logger.debug("VARIABLE consecutivo "+ consecutivo);
            logger.debug("VARIABLE folioSalida "+ folioSalida);
            ArrayList<SalidasDetalle> resultadoSalidasDetalle = (ArrayList<SalidasDetalle>) query.getResultList();

            return resultadoSalidasDetalle;
        }catch (NoResultException e){
            ArrayList<SalidasDetalle> resultadoSalidasDetalle =  new ArrayList<SalidasDetalle>();
            return resultadoSalidasDetalle;
        }
    }

    @Override
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final String cliente, final int folioSalida) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getByClienteConsecutivo");
            query.setParameter("idCliente", cliente);
            //query.setParameter("consecutivo", consecutivo);
            query.setParameter("folioSalida", folioSalida);
            //SalidasDetalle resultadoSalidasDetalle = new SalidasDetalle();
            logger.debug("VARIABLE idCliente "+ cliente);
            //logger.debug("VARIABLE consecutivo "+ consecutivo);
            logger.debug("VARIABLE folioSalida "+ folioSalida);
            ArrayList<SalidasDetalle> resultadoSalidasDetalle = (ArrayList<SalidasDetalle>) query.getResultList();

            return resultadoSalidasDetalle;
        }catch (NoResultException e){
            ArrayList<SalidasDetalle> resultadoSalidasDetalle =  new ArrayList<SalidasDetalle>();
            return resultadoSalidasDetalle;
        }
    }

    @Override
    public List<SalidasDetalle> findSalidaByConsecutivo(final int consecutivo) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getByConsecutivo");
            query.setParameter("consecutivo", consecutivo);

            //SalidasDetalle resultadoSalidasDetalle = new SalidasDetalle();
            logger.debug("VARIABLE consecutivo "+ consecutivo);
            ArrayList<SalidasDetalle> resultadoSalidasDetalle = (ArrayList<SalidasDetalle>) query.getResultList();

            return resultadoSalidasDetalle;
        }catch (NoResultException e){
            ArrayList<SalidasDetalle> resultadoSalidasDetalle =  new ArrayList<SalidasDetalle>();
            return resultadoSalidasDetalle;
        }
    }

    @Override
    public List<SalidasDetalle> findSalidaByFolioSalida(final int consecutivo) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getByFolioSalida");
            query.setParameter("folioSalida", consecutivo);

            //SalidasDetalle resultadoSalidasDetalle = new SalidasDetalle();
            logger.debug("VARIABLE FolioSalida "+ consecutivo);
            ArrayList<SalidasDetalle> resultadoSalidasDetalle = (ArrayList<SalidasDetalle>) query.getResultList();

            return resultadoSalidasDetalle;
        }catch (NoResultException e){
            ArrayList<SalidasDetalle> resultadoSalidasDetalle =  new ArrayList<SalidasDetalle>();
            return resultadoSalidasDetalle;
        }
    }

    @Override
    public List<SalidasDetalle> findSalidaByConsecutivoFolioSalida(final int consecutivo,final int folioSalida) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getByConsecutivoFolioSalida");
            query.setParameter("consecutivo", consecutivo);
            query.setParameter("folioSalida", folioSalida);

            //SalidasDetalle resultadoSalidasDetalle = new SalidasDetalle();
            logger.debug("VARIABLE consecutivo "+ consecutivo);
            logger.debug("VARIABLE folioSalida "+ folioSalida);
            ArrayList<SalidasDetalle> resultadoSalidasDetalle = (ArrayList<SalidasDetalle>) query.getResultList();

            return resultadoSalidasDetalle;
        }catch (NoResultException e){
            ArrayList<SalidasDetalle> resultadoSalidasDetalle =  new ArrayList<SalidasDetalle>();
            return resultadoSalidasDetalle;
        }
    }

    @Override
    public List<Object[]> getKilosByDay(Date fechaSalida) {
        logger.debug("fecha de la salida"+fechaSalida.toString());
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getKilosByDay");
            query.setParameter("fechaSalida", fechaSalida);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechas(Date fechaInicio, Date fechaFin) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getKilosByFechas");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getKilosByFechasConcentrado");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechasCliente(Date fechaInicio, Date fechaFin, String idCliente) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getKilosByFechasCliente");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechasClienteDetalle(Date fechaInicio, Date fechaFin, String idCliente) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getKilosByFechasClienteDetalle2");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public Object getKilosByFechasClienteDetalleTotal(Date fechaInicio, Date fechaFin, String idCliente) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getKilosByFechasClienteDetalleTotal");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return (Object) query.getSingleResult();
        }catch (NoResultException e){
            return new Object();
        }
    }

    @Override
    public List<Object[]> getReporteByCamara(Date fechaInicio, Date fechaFin, String idCamara) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getReporteByCamara");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCamara", idCamara);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getReporteByCamaraVacia(Date fechaInicio, Date fechaFin) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getReporteByCamaraVacia");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }
    
    @Override
    public List<Object[]> getArrastreSaldosDetalle(int consecutivo) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getArrastreSaldosDetalle");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }
    
    @Override
    public List<SalidasDetalle> getArrastreSaldosDetalleAgrupado(int consecutivo) {
        try {
            final Query query = em.createNamedQuery("SalidasDetalle.getArrastreSaldosDetalleAgrupado");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<SalidasDetalle>();
        }
    }
}
