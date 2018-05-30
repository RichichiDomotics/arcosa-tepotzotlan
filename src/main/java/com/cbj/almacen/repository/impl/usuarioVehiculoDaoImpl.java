package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.UsuarioVehiculo;
import com.cbj.almacen.repository.UsuarioVehiculoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by jolvera on 18/12/2014.
 */
@Repository(value = "usuarioVehiculoDao")
public class usuarioVehiculoDaoImpl implements UsuarioVehiculoDao {

    private static final Logger logger = LoggerFactory
            .getLogger(usuarioVehiculoDaoImpl.class);

    private EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public UsuarioVehiculo getByUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo) {
        UsuarioVehiculo usuarioVehiculoResponse = new UsuarioVehiculo();
        try {
            Query query = em.createNamedQuery("UsuarioVehiculo.findByUsuarioVehiculo");
            query.setParameter("username", usuarioVehiculo.getUsername());
            query.setParameter("idVehiculo", usuarioVehiculo.getIdVehiculo());
            usuarioVehiculoResponse = (UsuarioVehiculo) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e.getMessage() + e);
            return new UsuarioVehiculo();
        }
        return usuarioVehiculo;
    }

    @Override
    public UsuarioVehiculo getByVehiculo(UsuarioVehiculo usuarioVehiculo) {
        UsuarioVehiculo usuarioVehiculoResponse = new UsuarioVehiculo();
        try {
            Query query = em.createNamedQuery("UsuarioVehiculo.findByVehiculo");
            query.setParameter("idVehiculo", usuarioVehiculo.getIdVehiculo());
            usuarioVehiculoResponse = (UsuarioVehiculo) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e.getMessage() + e);
            return new UsuarioVehiculo();
        }
        return usuarioVehiculoResponse;
    }

    @Transactional(readOnly = false)
    public UsuarioVehiculo registraSeguimiento(UsuarioVehiculo usuarioVehiculo) {
        try{
            em.persist(usuarioVehiculo);
            return usuarioVehiculo;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new UsuarioVehiculo();
        }
    }
}
