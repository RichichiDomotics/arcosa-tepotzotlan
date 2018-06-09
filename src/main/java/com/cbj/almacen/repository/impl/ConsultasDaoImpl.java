package com.cbj.almacen.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbj.almacen.domain.VistaIngreso;
import com.cbj.almacen.domain.VistaIngresoDetalle;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.repository.ConsultasDao;

@Repository(value = "consultasDao")
public class ConsultasDaoImpl implements ConsultasDao {

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
    public List<VistaIngreso> getConsultaIngresosListByStatusIngreso(String status) {
        Query query = em.createNamedQuery("VistaIngreso.findByStatusIngreso");
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<VistaIngreso> getConsultaIngresosListByStatusSalida(String status) {
        Query query = em.createNamedQuery("VistaIngreso.findByStatusSalida");
        query.setParameter("status", status);
        return query.getResultList();
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public VistaIngresoDetalle getConsultaIngresosDetList(Integer id) {
    VistaIngresoDetalle detalle = (VistaIngresoDetalle)em.createQuery("select p from VistaIngresoDetalle p where p.idIngresoVehiculo ="+id).getSingleResult();
    return detalle;
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Clientes> getClientesList() {
    return em.createQuery("select p from Cliente p")
		.getResultList();
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public Clientes getConsultaClienteDetList(Integer id) {
    Clientes detalle = (Clientes)em.createQuery("select p from Cliente p where idCliente="+id).getSingleResult(); 
    return detalle;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Object[]> getVehiculoRDSinCapturar() {
        return em.createQuery("select p.consecutivo, v.idIngresoVehiculo from RegEntradas p, Vehiculo v where p.idIngresoVehiculo=v.idIngresoVehiculo and v.status='1'")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<VistaIngreso> getArlaVehiculos(String clientes){
        try{
            Query query = em.createQuery("SELECT c FROM VistaIngreso c where c.status='1' and c.tipoMovimiento='1111' and c.idCliente in ("+clientes+")");
            return query.getResultList();
        }catch (QueryException e){
            e.printStackTrace();
            return new ArrayList<VistaIngreso>();
        }
    }
}
