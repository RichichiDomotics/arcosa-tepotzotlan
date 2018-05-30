package com.cbj.almacen.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cbj.almacen.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbj.almacen.domain.FormEntrada;
import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.repository.EntradasDao;

@Repository(value = "entradasDao")
public class EntradasDaoImpl implements EntradasDao {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

   

    @Transactional(readOnly = false)
    public boolean setEntradaVehiculo(FormEntrada entrada) {
	boolean respuesta=false;
    try{
    	em.persist(entrada);
    	respuesta=true;
	}catch(Exception e){
		respuesta=false;
    }
	return respuesta;
    }
    
    @Transactional(readOnly = false)
    public boolean setRegistroEntradas(RegEntradas inentrada) {
	boolean respuesta=false;
	System.out.println("****"+inentrada.toString());
    try{
    	em.persist(inentrada);
    	respuesta=true;
	}catch(Exception e){
		respuesta=false;
    }
	return respuesta;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<VistaIngreso> getConsultaIngresosList() {
    return em.createQuery("select p from VistaIngreso p where p.tipoMovimiento = '" + Utils.TIPO_MOVIMIENTO_ENTRADA+"'")
		.getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<VistaIngreso> getConsultaSalidasList() {
        return em.createQuery("select p from VistaIngreso p where p.tipoMovimiento = '" + Utils.TIPO_MOVIMIENTO_SALIDA+"'")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public VistaIngresoDetalle getConsultaIngresosDetList(Integer id) {
    VistaIngresoDetalle detalle = (VistaIngresoDetalle)em.createQuery("select p from VistaIngresoDetalle p where p.idFormEntrada="+id).getSingleResult();
    return detalle;
    }

    @Transactional(readOnly = false)
    public boolean updateEntradas(FormEntrada formEntrada) {
        boolean respuesta=false;
        try{
            logger.info(formEntrada.toString());
            em.merge(formEntrada);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;    }
}
