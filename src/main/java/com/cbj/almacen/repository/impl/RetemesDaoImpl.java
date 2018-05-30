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

import com.cbj.almacen.domain.Retemes;
import com.cbj.almacen.repository.RetemesDao;

@Repository(value = "retemesDao")
public class RetemesDaoImpl implements RetemesDao {

    private static final Logger logger = LoggerFactory
            .getLogger(RetemesDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

   
    @Transactional(readOnly = false)
    public boolean insertReteme(Retemes datosIngresoReteme){
	boolean respuesta=false;
    try{
    	em.persist(datosIngresoReteme);
    	respuesta=true;
	}catch(Exception e){
		respuesta=false;
    }
	return respuesta;
    }
    
    @Override
    public List<Retemes> findRetemeByConsecutivo(String consecutivo) {
        try{
            Query query = em.createNamedQuery("Retemes.getByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Retemes>();
        }
    }
    
    @Override
    public List<Retemes> findRetemeByIdCliente(String idCliente, String fecini, String fecfin , String folioReteme,String consecutivo) {
        try{
            String query ="SELECT c.FOLIORETEME, c.FECHA, c.CONSECUTIVO, c.KILOS, c.ID_CLIENTE, c.TIPOSERVICIO, c.OBSERV FROM Retemes c WHERE 1=1";
            if(!consecutivo.trim().equalsIgnoreCase("")) query+="AND c.CONSECUTIVO = '" + consecutivo + "' ";
            if(!idCliente.trim().equalsIgnoreCase("")) query+="AND c.ID_CLIENTE = '" + idCliente + "' ";
            if(!folioReteme.trim().equalsIgnoreCase("")) query+="AND c.FOLIORETEME = '" + folioReteme + "' ";
            if(!fecini.trim().equalsIgnoreCase("") && !fecfin.trim().equalsIgnoreCase("")) query+="AND (c.FECHA >= '"+fecini+"' AND c.FECHA <='"+fecfin+"' )";
            return em.createQuery(query).getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Retemes>();
        }
    }
    
    
    @Override
    public String getMaxId() {
        try{
            Query query = em.createNamedQuery("Retemes.getMaxId");
            return query.getSingleResult().toString();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return "";
        }
    }
    @Override
    public String getMaxIdTIF() {
        try{
            Query query = em.createNamedQuery("Retemes.getMaxIdTIF");
            return query.getSingleResult().toString();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return "";
        }
    }
    
  
}
