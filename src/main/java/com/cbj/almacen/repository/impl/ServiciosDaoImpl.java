package com.cbj.almacen.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cbj.almacen.domain.Convenios;
import com.cbj.almacen.domain.Servicios;
import com.cbj.almacen.repository.ServiciosDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by colvera on 10/06/2014.
 */
@Repository(value = "serviciosDao")
public class ServiciosDaoImpl implements ServiciosDao {

	 private static final Logger logger = LoggerFactory
	            .getLogger(ConveniosDaoImpl.class);

	    private EntityManager em = null;

	    /*
	     * Sets the entity manager.
	     */
	    @PersistenceContext
	    public void setEntityManager(EntityManager em) {
	        this.em = em;
	    }

	    @Override
	    public List<Servicios> getByActivo() {
	        try {
	            Query query = em.createNamedQuery("Servicios.getByActivo");
	            return query.getResultList();
	        }catch (NoResultException e){
	            return new ArrayList<Servicios>();
	        }
	    }
	    
	    @Override
	    public List<Servicios> getByActivoByIdCliente(String idCliente) {
	        try {
	            Query query = em.createNamedQuery("Servicios.getByActivoByIdCliente");
	            query.setParameter("idCliente", idCliente);
	            return query.getResultList();
	        }catch (NoResultException e){
				logger.error("Error al obtener datos de cliente ",e);
				return new ArrayList<Servicios>();
			}catch (Exception e){
				logger.error("Error al obtener datos de cliente ",e);
				return new ArrayList<Servicios>();
			}
	    }

	@Override
	public List<Object[]> getCboServicios() {
		try {
			final Query query = em.createNamedQuery("Servicios.getCboServicios");
			List<Object[]> res = query.getResultList();
			return res;
		}catch (NoResultException e){
			return new ArrayList<Object[]>();
		}
	}

	@Transactional(readOnly = false)
	public boolean insertServicio(Servicios datoServicio){
		boolean respuesta=false;
		try{
			em.persist(datoServicio);
			respuesta=true;
		}catch(Exception e){
			respuesta=false;
		}
		return respuesta;
	}

	@Override
	public Servicios getByActivoByIdClienteClave(String idCliente, String clave) {
		try {
			Servicios servicios = new Servicios();
			Query query = em.createNamedQuery("Servicios.getByActivoByIdClienteClave");
			query.setParameter("idCliente", idCliente);
			query.setParameter("clave", clave);
			servicios = (Servicios) query.getResultList().get(0);
			return servicios;
		}catch (NoResultException e){
			return new Servicios();
		}
	}
	    
}
