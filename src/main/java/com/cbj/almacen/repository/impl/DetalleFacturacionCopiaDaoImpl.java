package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.DetalleFacturacionCopia;
import com.cbj.almacen.repository.DetalleFacturacionCopiaDao;
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
@Repository(value = "detalleFacturacionCopiaDao")
public class DetalleFacturacionCopiaDaoImpl implements DetalleFacturacionCopiaDao {

    private static final Logger logger = LoggerFactory
            .getLogger(DetalleFacturacionCopiaDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public DetalleFacturacionCopia insertDetalleFacturacion(DetalleFacturacionCopia detalleFacturacion) {
        try{
            em.persist(detalleFacturacion);
            em.flush();
            return detalleFacturacion;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new DetalleFacturacionCopia();
        }
    }

    @Override
    public List<Object> getIdClienteDetalle(Integer idCliente) {
        try{
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getDetFacturaByIdCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getIdDetalleFactura(String nofactura) {
        try{
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getIdDetalleFactura");
            query.setParameter("nofactura", nofactura);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al obtener detalle de Factura.",e);
            return new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getVentasEjecutivo(String fechainicio, String fechafin){
        try {
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasEjecutivo");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }
    @Override
    public List<Object> getVentasNetasEjecutivo(String fechainicio, String fechafin){
        try {
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasNetasEjecutivo");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }
    @Override
    public double getVentasTotal(String fechainicio, String fechafin){
        double totalventas=0;
        try {
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasTotal");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            totalventas = Double.parseDouble(query.getResultList().get(0).toString());
        }catch (Exception e){
            logger.error("Error al generar el total de ventas",e);

        }
        return totalventas;
    }
    @Override
    public double getVentasNetasEjecutivoTotal(String fechainicio, String fechafin){
        double totalnetas = 0;
        try{
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasNetasEjecutivoTotal");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            totalnetas = Double.parseDouble(query.getResultList().get(0).toString());
        }catch (Exception e){
            logger.error("Error al traer el total de ventas netas",e);
        }
        return totalnetas;
    }
    @Override
    public List<Object> getVentasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo){
        try {
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasEjecutivoId");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            query.setParameter("idEjecutivo",idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }
    @Override
    public List<Object> getVentasNetasEjecutivoId(String fechainicio, String fechafin, String idEjecutivo){
        try {
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasNetasEjecutivoId");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            query.setParameter("idEjecutivo",idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }
    public double getVentasNetasEjecutivoTotalID(String fechainicio, String fechafin, String idEjecutivo){
        double totalnetas = 0;
        try{
            Query query = em.createNamedQuery("DetalleFacturacionCopia.getVentasNetasEjecutivoTotalId");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin);
            query.setParameter("idEjecutivo",idEjecutivo);
            totalnetas = Double.parseDouble(query.getResultList().get(0).toString());
        }catch (Exception e){
            logger.error("Error al traer el total de ventas netas",e);
        }
        return totalnetas;
    }

}
