package com.cbj.almacen.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.repository.VistasDao;

@Repository(value = "vistasDao")
public class VistasDaoImpl implements VistasDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<VistaIngreso> getConsultaIngresosList() {
    return em.createQuery("select p from VistaIngreso p")
		.getResultList();
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public VistaIngresoDetalle getConsultaIngresosDetList(Integer id) {
    VistaIngresoDetalle detalle = (VistaIngresoDetalle)em.createQuery("select p from VistaIngresoDetalle p where idFormEntrada="+id).getSingleResult(); 
    return detalle;
    }
    
    
}
