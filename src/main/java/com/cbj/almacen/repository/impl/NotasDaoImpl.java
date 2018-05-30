package com.cbj.almacen.repository.impl;

/**
 * Created by Richard on 07/07/2015.
 */

import com.cbj.almacen.domain.Notas;
import com.cbj.almacen.repository.NotasDao;
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
public class NotasDaoImpl implements NotasDao {
    private static final Logger logger = LoggerFactory
            .getLogger(DetalleFacturacionDaoImpl.class);

    private EntityManager em = null;
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertNota(Notas notas) {
        boolean respuesta=false;
        try{
            em.persist(notas);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateNota(Notas notas) {
        boolean respuesta = false;
        try {
            em.merge(notas);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public List<Object> getNotasCredito(String fechainicio, String fechafin){
        try {
            Query query = em.createNamedQuery("Notas.getNotasCredito");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getDetalleNotasCredito(String fechainicio, String fechafin){
        try {
            Query query = em.createNamedQuery("Notas.getDetalleNotasCredito");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }

    @Override
    public double getNotasCreditoTotal(String fechainicio, String fechafin){
        double totalNotas=0;
        try {
            Query query = em.createNamedQuery("Notas.getNotasCreditoTotal");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            totalNotas = Double.parseDouble(query.getResultList().get(0).toString());
        }catch (Exception e){
            logger.error("Error al consultar el total de notas credito",e);
        }
        return totalNotas;
    }

    @Override
    public List<Object> getNotasCreditoId(String fechainicio, String fechafin, String idEjecutivo){
        try {
            Query query = em.createNamedQuery("Notas.getNotasCreditoId");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            query.setParameter("idEjecutivo",idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getDetalleNotasCreditoId(String fechainicio, String fechafin, String idEjecutivo){
        try {
            Query query = em.createNamedQuery("Notas.getDetalleNotasCreditoId");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            query.setParameter("idEjecutivo",idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al generar el reporte de venas por ejecutivo",e);
            return  new ArrayList<Object>();
        }
    }

    @Override
    public double getNotasCreditoTotalId(String fechainicio, String fechafin, String idEjecutivo){
        double totalNotas=0;
        try {
            Query query = em.createNamedQuery("Notas.getNotasCreditoTotalId");
            query.setParameter("fechainicio", fechainicio);
            query.setParameter("fechafin",fechafin+"%");
            query.setParameter("idEjecutivo",idEjecutivo);
            totalNotas = Double.parseDouble(query.getResultList().get(0).toString());
        }catch (Exception e){
            logger.error("Error al consultar el total de notas credito",e);
        }
        return totalNotas;
    }
    @Override
    public String getid(){
        try{
            Query query = em.createNamedQuery("Notas.getMaxId");
            return query.getResultList().get(0).toString();
        }catch (Exception e){
            logger.error("Error al traer el ultimo id de la tabla de notas"+e.getMessage()+e.getCause());
            return "";
        }
    }

    @Override
    public List<Notas> getByFolio(int folio){
        try{
            Query query = em.createNamedQuery("Notas.getByFolio");
            query.setParameter("folio", folio);
            return query.getResultList();
        }catch(Exception e){
            logger.error("Error al traer las ntas por el folio"+e.getMessage()+e.getCause());
            return  new ArrayList<Notas>();
        }
    }
}
