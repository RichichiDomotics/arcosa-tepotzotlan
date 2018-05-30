package com.cbj.almacen.repository.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.repository.DetalleRdDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by jolvera on 17/05/2014.
 */
@Repository(value = "detalleRdDao")
public class EntradaRdDaoImpl implements DetalleRdDao {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradaRdDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertRegistroDetallesRd(DetallesRd detalles) {
        boolean respuesta = false;
        logger.trace(detalles.toString());
        try {
            logger.info(detalles.toString());
            em.persist(detalles);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateRegistroDetallesRd(DetallesRd detalles) {
        boolean respuesta = false;
        logger.trace(detalles.toString());
        try {
            logger.info(detalles.toString());
            em.merge(detalles);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean deleteRegistroDetallesRd(int id) {
        boolean respuesta = false;

        try {
            DetallesRd detallesRd = findRegistroDetallesRd(id);
            em.remove(em.contains(detallesRd) ? detallesRd : em.merge(detallesRd));
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public DetallesRd findRegistroDetallesRd(int id) {
        DetallesRd detallesRd = new DetallesRd();
        try {
            detallesRd = em.find(DetallesRd.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        return detallesRd;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<DetallesRd> findDetallesSalidas(String consecutivo, String idCliente, String descripcion, String caducidad) {
        List<DetallesRd> detalle = null;
        try {
            detalle = em.createQuery("select p from DetallesRd p WHERE p.consecutivo like '" + consecutivo + "%' AND p.idCliente like '" + idCliente + "%' AND p.claveProducto like '" + descripcion + "%' AND p.caducidad like '" + caducidad + "%'").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return detalle;
    }

    @Override
    public List<DetallesRd> getAll() {
        Query query = em.createNamedQuery("DetallesRd.getAll");
        return query.getResultList();

    }

    @Override
    public List<DetallesRd> getAllByConsecutivo(int consecutivo) {
            Query query = em.createNamedQuery("DetallesRd.getAllByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
    }
    
    @Override
    public List<DetallesRd> getAllByCliente(String idCliente) {
            Query query = em.createNamedQuery("DetallesRd.getAllByCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
    }
    
    @Override
    public List<DetallesRd> getAllByFolioSalida(int folioSalida, int consecutivo, String idCliente) {
            Query query = em.createNamedQuery("DetallesRd.getAllByFolioSalida");
            query.setParameter("folioSalida", folioSalida);
            query.setParameter("consecutivo", consecutivo);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
    }

    @Override
    public List<Object[]> getKilosByDay(Date fechaSalida) {
        logger.debug("Entradas por dia " + fechaSalida.toString());
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByDay");
            query.setParameter("fechaSalida", fechaSalida, TemporalType.DATE);
            List<Object[]> res = query.getResultList();
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }


    @Override
    public List<Object[]> getKilosByFechas(Date fechaInicio, Date fechaFin) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechas");
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
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasConcentrado");
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
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasCliente");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasClienteDetalle");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio, final Date fechaFin, final String idCliente) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getKilosByFechasClienteDetalleTotal");
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
            final Query query = em.createNamedQuery("DetallesRd.getReporteByCamara");
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
            final Query query = em.createNamedQuery("DetallesRd.getReporteByCamaraVacia");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }
    
    @Override
    public List<DetallesRd> getArrastreSaldosDetalleRdAgrupado(int consecutivo) {
        try {
            final Query query = em.createNamedQuery("DetallesRd.getArrastreSaldosDetalleRdAgrupado");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<DetallesRd>();
        }
    }

    @Override
    public List<Object> getRDAlmacenajesNuevos(String idcliente, Date fechaini, Date fechafin){
        try{
            /*logger.info("fechas: "+fechaini+"-"+fechafin);
            Query query = em.createNamedQuery("DetallesRd.getRDAlmacenajesNuevos");
            query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini ,TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);
            return query.getResultList();*/
            //String query="SELECT d.idCliente, d.consecutivo, date_format(d.fechaCaptura, '%Y-%m-%d'),date_format(d.fechaCaptura, '%Y-%m-%d')  +  INTERVAL (c.periodo-1) DAY, d.camara, round(sum(d.cantidad * d.pesou),2),'ALM',c.cuotaAlmacenaje, round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje,round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje, '',curdate() FROM DetallesRd d, Convenios c WHERE c.claveProducto=d.claveProducto and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin and d.idCliente= :idcliente GROUP BY d.consecutivo";
            Query query = em.createNativeQuery("SELECT d.idCliente as cliente, d.consecutivo as rd , date_format(d.fechaCaptura, '%Y-%m-%d') as iniper," +
                    "CASE WHEN c.periodo=30 THEN DATE_SUB(date_format(d.fechaCaptura, '%Y-%m-%d') + INTERVAL 1 MONTH, INTERVAL 1 DAY)  " +
                    "WHEN DATE_FORMAT(LAST_DAY(curdate()),'%d') = '31' and c.periodo = 15 and DATE_FORMAT(d.fechaCaptura,'%d') >16 THEN DATE_ADD(date_format(d.fechaCaptura, '%Y-%m-%d'),INTERVAL c.periodo DAY) " +
                    "WHEN DATE_FORMAT(LAST_DAY(curdate()),'%d') = '31' and c.periodo = 20 and DATE_FORMAT(d.fechaCaptura,'%d') >11 THEN DATE_ADD(date_format(d.fechaCaptura, '%Y-%m-%d'),INTERVAL c.periodo DAY)" +
                    "ELSE DATE_ADD(date_format(d.fechaCaptura, '%Y-%m-%d'), INTERVAL (c.periodo-1) DAY) END as finper, d.camara as camara, (select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)as kilos" +
                    ",'ALM' as servicio,c.cuotaAlmacenaje as cuota, round((select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)*c.cuotaAlmacenaje,2) as subtotal," +
                    "round((select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)*c.cuotaAlmacenaje,2) as total, '',curdate() FROM detalles_rd d, convenios c " +
                    "WHERE c.claveProducto=d.claveProducto and d.servicio=90 and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin " +
                    "and d.idCliente in ( "+idcliente+" ) and d.idCliente not in (341,428,536,109,116,194,196,394,395,187,314,333,258,387,396,393) GROUP BY d.consecutivo");
            //query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini ,TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);
            return query.getResultList();
            //return em.createNativeQuery(query).getResultList();
        }catch (Exception e){
            logger.error("Error al traer los almacenajes nuevos"+e.getStackTrace()+e.getMessage());
            return new ArrayList<Object>();
        }
    }
    @Override
    public List<Object> getRDTunelesNuevos(String idcliente, Date fechaini, Date fechafin){
        try{
            /*logger.info("fechas: "+fechaini+"-"+fechafin);
            Query query = em.createNamedQuery("DetallesRd.getRDAlmacenajesNuevos");
            query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini ,TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);
            return query.getResultList();*/
            //String query="SELECT d.idCliente, d.consecutivo, date_format(d.fechaCaptura, '%Y-%m-%d'),date_format(d.fechaCaptura, '%Y-%m-%d')  +  INTERVAL (c.periodo-1) DAY, d.camara, round(sum(d.cantidad * d.pesou),2),'ALM',c.cuotaAlmacenaje, round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje,round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje, '',curdate() FROM DetallesRd d, Convenios c WHERE c.claveProducto=d.claveProducto and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin and d.idCliente= :idcliente GROUP BY d.consecutivo";
            Query query = em.createNativeQuery("SELECT d.idCliente as cliente, d.consecutivo as rd , date_format(d.fechaCaptura, '%Y-%m-%d') as iniper," +
                    "date_format(d.fechaCaptura, '%Y-%m-%d') as finper, d.camara as camara, (select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo) as kilos" +
                    ",'TUNEL' as servicio,c.congelacion as cuota, round((select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)*c.congelacion,2) as subtotal," +
                    "round((select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)*c.congelacion,2) as total, '',curdate() FROM detalles_rd d, convenios c " +
                    "WHERE c.claveProducto=d.claveProducto and d.servicio=80 and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin " +
                    "and d.idCliente in ( "+idcliente+" ) and d.idCliente not in (341,428,536,109,116,194,196,394,395,187,314,333,258,387,396,393) GROUP BY d.consecutivo");
            //query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini ,TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);
            return query.getResultList();
            //return em.createNativeQuery(query).getResultList();
        }catch (Exception e){
            logger.error("Error al traer los tuneles nuevos"+e.getStackTrace()+e.getMessage());
            return new ArrayList<Object>();
        }
    }
    @Override
    public List<Object> getRDMEYS(String idcliente, Date fechaini, Date fechafin){
        try{
            /*logger.info("fechas: "+fechaini+"-"+fechafin);
            Query query = em.createNamedQuery("DetallesRd.getRDAlmacenajesNuevos");
            query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini ,TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);
            return query.getResultList();*/
            //String query="SELECT d.idCliente, d.consecutivo, date_format(d.fechaCaptura, '%Y-%m-%d'),date_format(d.fechaCaptura, '%Y-%m-%d')  +  INTERVAL (c.periodo-1) DAY, d.camara, round(sum(d.cantidad * d.pesou),2),'ALM',c.cuotaAlmacenaje, round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje,round(sum(d.cantidad * d.pesou),2)*c.cuotaAlmacenaje, '',curdate() FROM DetallesRd d, Convenios c WHERE c.claveProducto=d.claveProducto and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin and d.idCliente= :idcliente GROUP BY d.consecutivo";
            Query query = em.createNativeQuery("SELECT d.idCliente as cliente, d.consecutivo as rd , date_format(d.fechaCaptura, '%Y-%m-%d') as iniper," +
                    "CASE WHEN c.periodo=30 THEN DATE_SUB(date_format(d.fechaCaptura, '%Y-%m-%d') + INTERVAL 1 MONTH, INTERVAL 1 DAY)" +
                    "WHEN DATE_FORMAT(LAST_DAY(curdate()),'%d') = '31' and c.periodo = 15 and DATE_FORMAT(d.fechaCaptura,'%d') >16 THEN DATE_ADD(date_format(d.fechaCaptura, '%Y-%m-%d'),INTERVAL c.periodo DAY) " +
                    "WHEN DATE_FORMAT(LAST_DAY(curdate()),'%d') = '31' and c.periodo = 20 and DATE_FORMAT(d.fechaCaptura,'%d') >11 THEN DATE_ADD(date_format(d.fechaCaptura, '%Y-%m-%d'),INTERVAL c.periodo DAY)" +
                    "ELSE DATE_ADD(date_format(d.fechaCaptura, '%Y-%m-%d'), INTERVAL (c.periodo-1) DAY) END as finper, d.camara as camara, (select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)as kilos" +
                    ",'MEYS' as servicio,c.mentysal as cuota, round((select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)*c.mentysal,2) as subtotal," +
                    "round((select ROUND(SUM(cantidad*pesou),2) from detalles_rd where claveProducto=c.claveProducto AND consecutivo = d.consecutivo)*c.mentysal,2) as total, '',curdate() FROM detalles_rd d, convenios c " +
                    "WHERE c.claveProducto=d.claveProducto  and date(d.fechaCaptura) >= :fechaini and date(d.fechaCaptura) <= :fechafin " +
                    "and d.idCliente in ( "+idcliente+" ) and d.idCliente not in (109,116,194,196,394,395,396,393)  GROUP BY d.consecutivo");
            //query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini ,TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);
            return query.getResultList();
            //return em.createNativeQuery(query).getResultList();
        }catch (Exception e){
            logger.error("Error al traer las MEYS nuevas"+e.getStackTrace()+e.getMessage());
            return new ArrayList<Object>();
        }
    }

    public List<Object[]> getRendimientos(Date fechaini, Date fechafin) {
        try {
            Query e = this.em.createNativeQuery("SELECT d.consecutivo, d.camara, DATE(d.fechaCaptura), ROUND(SUM(d.cantidad * d.pesou), 2) AS kilos, SUM(d.cantidad) AS piezas, c.ID_CLIENTE, c.NOM_CLIENTE, d.posiciones, e.cuotaAlmacenaje, d.estibas, CASE WHEN d.idCliente IN (154,1543,1542,498,501,1541,167,171,341,531) AND d.camara = 8 THEN ROUND(1.41 * (d.posiciones / d.estibas), 2) ELSE ROUND(s.superficie * (d.posiciones / d.estibas), 2) END AS superficie, ROUND(d.posiciones / d.estibas, 2) AS base, d.altura, d.almaen, CASE WHEN e.cuotaAlmacenaje < 1 THEN \'KILOGRAMO\' ELSE \'TARIMA\' END AS forma_cobro, e.periodo, CASE WHEN e.cuotaAlmacenaje < 1 THEN ROUND( ROUND((ROUND(SUM(cantidad * pesou), 2) ),2) * e.cuotaAlmacenaje,2) ELSE ROUND(d.posiciones * e.cuotaAlmacenaje,2) END AS produccion_pesos, CASE WHEN e.cuotaAlmacenaje < 1 THEN CASE WHEN d.idCliente IN (154,1543,1542,498,501,1541,167,171,341,531) AND d.camara = 8 THEN ROUND((ROUND(ROUND(SUM(cantidad * pesou)) * e.cuotaAlmacenaje,2)) / (1.41 * (ROUND(d.posiciones / d.estibas, 2))),2) ELSE ROUND(ROUND(ROUND(SUM(cantidad * pesou)) * e.cuotaAlmacenaje,2) / (s.superficie * (ROUND(d.posiciones / d.estibas, 2))),2) END ELSE ROUND((ROUND(d.posiciones * e.cuotaAlmacenaje,2)) / (s.superficie * (ROUND(d.posiciones / d.estibas, 2))),2) END AS rendimiento_docto FROM detalles_rd d,clientes c,convenios e,superficie_camara s WHERE d.camara = s.camara AND d.idCliente = c.ID_CLIENTE AND d.claveProducto = e.claveProducto AND DATE(fechaCaptura) >= :fechaini AND DATE(fechaCaptura) <= :fechafin GROUP BY d.consecutivo ORDER BY d.consecutivo");
            e.setParameter("fechaini", fechaini, TemporalType.DATE);
            e.setParameter("fechafin", fechafin, TemporalType.DATE);
            return e.getResultList();
        } catch (Exception var4) {
            logger.error("Error al traer los rendimientos");
            var4.printStackTrace();
            return new ArrayList();
        }
    }

}
