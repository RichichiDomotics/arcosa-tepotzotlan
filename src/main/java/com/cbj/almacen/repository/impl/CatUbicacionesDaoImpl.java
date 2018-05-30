package com.cbj.almacen.repository.impl;

import com.cbj.almacen.repository.CatUbicacionesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Repository(value = "catUbicacionesDao")
public class CatUbicacionesDaoImpl implements CatUbicacionesDao {

    private static final Logger logger = LoggerFactory
            .getLogger(CatUbicacionesDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Object[]> getCamaras() {
        try {
            final Query query = em.createNamedQuery("CatUbicaciones.getCamaras");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getPuertas(String camara) {
        try {
            final Query query = em.createNamedQuery("CatUbicaciones.getPuertas");
            query.setParameter("camara", camara);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getPasillos(String puerta, String camara) {
        try {
            final Query query = em.createNamedQuery("CatUbicaciones.getPasillos");
            query.setParameter("puerta", puerta);
            query.setParameter("camara", camara);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getFilas(String pasillo, String puerta, String camara) {
        try {
            final Query query = em.createNamedQuery("CatUbicaciones.getFilas");
            query.setParameter("pasillo", pasillo);
            query.setParameter("puerta", puerta);
            query.setParameter("camara", camara);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getPosiciones(String pasillo,String puerta, String camara,String fila) {
        try {
            final Query query = em.createNamedQuery("CatUbicaciones.getPosiciones");
            query.setParameter("pasillo", pasillo);
            query.setParameter("puerta", puerta);
            query.setParameter("camara", camara);
            query.setParameter("fila", fila);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getNiveles(String pasillo,String puerta, String camara,String fila) {
        try {
            final Query query = em.createNamedQuery("CatUbicaciones.getNiveles");
            query.setParameter("pasillo", pasillo);
            query.setParameter("puerta", puerta);
            query.setParameter("camara", camara);
            query.setParameter("fila", fila);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

}
