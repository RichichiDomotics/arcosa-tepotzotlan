package com.cbj.almacen.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.PreVehiculo;
import com.cbj.almacen.repository.PreVehiculoDao;

@Repository(value = "preVehiculoDao")
public class PreVehiculoDaoImpl implements PreVehiculoDao {

    private static final Logger logger = LoggerFactory
            .getLogger(PreVehiculoDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

   
    @Transactional(readOnly = false)
    public boolean insertVehiculo(PreVehiculo datosIngresoVehiculo){
	boolean respuesta=false;
    try{
    	em.persist(datosIngresoVehiculo);
    	respuesta=true;
	}catch(Exception e){
        logger.info("Error al insertar pre registro "+e);
        respuesta=false;
    }
	return respuesta;
    }
    
    @Override
    public PreVehiculo findVehiculoById(Integer id) {
    	PreVehiculo vehiculo = new PreVehiculo();
        try{
        	vehiculo= em.find(PreVehiculo.class,id);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return vehiculo;

    }
    
    @Transactional(readOnly = false)
    public boolean updateVehiculo(PreVehiculo datosIngresoVehiculo) {
        boolean respuesta=false;
        logger.trace(datosIngresoVehiculo.toString());
        try{
            logger.info(datosIngresoVehiculo.toString());
            em.merge(datosIngresoVehiculo);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public List<PreVehiculo> findVehiculosPorDia() {
        try{
            Query query = em.createNamedQuery("PreVehiculo.getPorDia");
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<PreVehiculo>();
        }
    }
    
    @Override
    public List<PreVehiculo> getVehiculoByCliente(int idCliente) {
            Query query = em.createNamedQuery("PreVehiculo.getVehiculoByCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
    }
    
    @Override
    public List<PreVehiculo> getInOutVehiculo() {
            Query query = em.createNamedQuery("PreVehiculo.getInOutVehiculo");
            return query.getResultList();
    }

    @Override
    public List<PreVehiculo> findVehiculosPorDiaCliente(String idCliente) {
        try{
            Query query = em.createNamedQuery("preVehiculo.getPorDiaCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<PreVehiculo>();
        }
    }

}
