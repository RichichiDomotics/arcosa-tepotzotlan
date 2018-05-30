package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Ubicaciones;
import com.cbj.almacen.repository.UbicacionesDao;
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
 * Created by jolvera on 10/06/2014.
 */
@Repository(value = "ubicacionesDao")
public class UbicacionesDaoImpl implements UbicacionesDao {

    private static final Logger logger = LoggerFactory
            .getLogger(UbicacionesDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Ubicaciones getRevisionUbicacion(String camara, String puerta, String pasillo, String filaCalle,
                                               Integer posicion, Integer nivel, String tipoTarima, String consecutivo) {
        List<Ubicaciones>  res = null;
        Ubicaciones  resubi = new Ubicaciones();
        try {
            final Query query = em.createNamedQuery("Ubicaciones.getRevisionUbicacion");
            query.setParameter("camara", camara);
            query.setParameter("puerta", puerta);
            query.setParameter("pasillo", pasillo);
            query.setParameter("filaCalle", filaCalle);
            query.setParameter("posicion", posicion);
            query.setParameter("nivel", nivel);
            query.setParameter("tipoTarima", tipoTarima);
            query.setParameter("consecutivo", consecutivo);
            //res = (Ubicaciones) query.getResultList();
            res = query.getResultList();
            if(res.size()>0){
                resubi = res.get(0);
            }
            return resubi;
        }catch (NoResultException e){
            return resubi;
        }
    }


    @Transactional(readOnly = false)
    public boolean updateUbicacion(Ubicaciones ubicaciones) {
        boolean respuesta=false;
        try{
            em.merge(ubicaciones);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Transactional(readOnly = false)
    public Ubicaciones insertaRegistroUbicacion(Ubicaciones ubicaciones) {
        try{
            em.persist(ubicaciones);
            em.flush();
            return ubicaciones;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new Ubicaciones();
        }
    }

    @Override
    public List<Object[]> getUbicacion(String camara, String consecutivo) {
        try {
            final Query query = em.createNamedQuery("Ubicaciones.recUbicacion");
            query.setParameter("camara", camara);
            query.setParameter("consecutivo", consecutivo);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getUbicacionVacias(String camara) {
        try {
            final Query query = em.createNamedQuery("Ubicaciones.recUbicacionVacia");
            query.setParameter("camara", camara);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Transactional(readOnly = false)
    public boolean borraUbicacion(Integer idUbica) {
        boolean respuesta=false;
        try{

            Ubicaciones objUbicacion = em.find(Ubicaciones.class, idUbica);
            em.remove(objUbicacion);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public Ubicaciones getUbicacionIdUbica(Integer idUbica) {
        Ubicaciones ubicacion = new Ubicaciones();
        try{
            ubicacion = em.find(Ubicaciones.class, idUbica);
            return ubicacion;
        }catch(Exception e){
            return new Ubicaciones();
        }
    }

    @Override
    public List<Object[]> getConsolidados(String camara) {
        try {
            final Query query = em.createNamedQuery("Ubicaciones.recConsolidados");
            query.setParameter("camara", camara);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getConsolidadosVacio(String camara) {
        try {
            final Query query = em.createNamedQuery("Ubicaciones.recConsolidadosVacio");
            query.setParameter("camara", camara);
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

   /* @Override
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
    }*/

}
