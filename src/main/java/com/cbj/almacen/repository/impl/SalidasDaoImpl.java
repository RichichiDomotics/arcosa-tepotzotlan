package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Salidas;
import com.cbj.almacen.repository.SalidasDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
@Repository(value = "salidasDao")
public class SalidasDaoImpl implements SalidasDao {

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
    public int insertSalidas(Salidas salidas) {
        Salidas regSalidas = new Salidas();
        try {
            if(salidas.getConsecutivoSalida()==null || salidas.getConsecutivoSalida()<=0){
                regSalidas = (Salidas) em.createQuery("select p from Salidas p where p.consecutivoSalida=(SELECT max(p.consecutivoSalida) FROM Salidas p)").getSingleResult();
            }
            if (regSalidas != null && regSalidas.getConsecutivoSalida() != null && regSalidas.getConsecutivoSalida() > 0) {
                salidas.setConsecutivoSalida(regSalidas.getConsecutivoSalida() + 1);
            }

            em.persist(salidas);
        }catch (NoResultException nre){
            salidas.setConsecutivoSalida(1);
            insertSalidas(salidas);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }

        return salidas.getConsecutivo() + 1;

    }

    @Override
    public Salidas findSalidaByIdFolioSalida(int id) {

        Salidas salidas = new Salidas();
        try {
            salidas = em.find(Salidas.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        return salidas;
    }

    @Override
    public List<Salidas> findSalidaByClienteConsecutivo(String cliente, int consecutivo) {
        List<Salidas> salidas = new ArrayList<Salidas>();
        try {
            Query query = em.createNamedQuery("Salidas.getByClienteConsecutivo");
            query.setParameter("idCliente", cliente);
            //query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return salidas;
        }
    }
    
    @Override
    public List<Salidas> findSalidaByCliente(String cliente) {
        List<Salidas> salidas = new ArrayList<Salidas>();
        try {
            Query query = em.createNamedQuery("Salidas.getByCliente");
            query.setParameter("idCliente", cliente);
            return query.getResultList();
        }catch (NoResultException e){
            return salidas;
        }
    }

    @Override
    public List<Salidas> findSalidaByFolioSalida(int folioSalida) {
        try {
            Query query = em.createNamedQuery("Salidas.getByFolioSalida");
            query.setParameter("folioSalida", folioSalida);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Salidas>();
        }
    }
    
    @Override
    public List<Salidas> findSalidaByConsecutivo(int consecutivo) {
        try {
            Query query = em.createNamedQuery("Salidas.getByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Salidas>();
        }
    }

    @Override
    public List<Salidas> getAllFechaSalidaNull() {
        try {
            Query query = em.createNamedQuery("Salidas.getAllFechaSalidaNull");
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Salidas>();
        }
    }

    @Override
    public List<Salidas> getKilosByFechaSalida(String fechaSalida) {
        try {
            Query query = em.createNamedQuery("Salidas.getKilosByFechaSalida");
            query.setParameter("fechaSalida",fechaSalida);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Salidas>();
        }
    }

    @Transactional(readOnly = false)
    public boolean updateSalidas(Salidas salidas) {
        boolean respuesta = false;
        try {
            em.merge(salidas);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;

    }

}
