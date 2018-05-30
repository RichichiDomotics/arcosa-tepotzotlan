package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.SalidasAlmacen;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.repository.SalidasAlmacenDao;
import com.cbj.almacen.repository.SalidasDetalleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by jolvera on 02/06/2014.
 */
@Repository(value = "salidasAlmacenDao")
public class SalidasAlmacenDaoImpl implements SalidasAlmacenDao{

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
    public int insertSalidasAlmacen(SalidasAlmacen salidasAlmacen) {
        //Salidas regSalidas = new Salidas();
        try {
            //regSalidas = (Salidas) em.createQuery("select p from Salidas p where p.consecutivo=(SELECT max(p.consecutivo) FROM Salidas p)").getSingleResult();
            //salidas.setConsecutivo(regSalidas.getConsecutivo() + 1);
            em.persist(salidasAlmacen);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }

        return salidasAlmacen.getConsecutivo() + 1;

    }

    @Override
    public SalidasAlmacen findSalidaByClienteConsecutivo(String cliente, int consecutivo) {
        try {
            Query query = em.createNamedQuery("SalidasAlmacen.getByClienteConsecutivo");
            query.setParameter("idCliente", cliente);
            query.setParameter("consecutivo", consecutivo);
            return (SalidasAlmacen) query.getSingleResult();
        }catch (NoResultException e){
            return new SalidasAlmacen();
        }
    }
    public SalidasAlmacen findSalidabyFolioSalida (String folioSalida){
        try{
            Query query = em.createNamedQuery("SalidasAlmacen.getByFolioSalida");
            query.setParameter("folioSalida",Integer.parseInt(folioSalida));
            return (SalidasAlmacen) query.getResultList().get(0);
        }catch (Exception e){
            logger.error("No se pudieron consultar las salidas por folio"+e.getMessage());
            return new SalidasAlmacen();
        }
    }
}
