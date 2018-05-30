package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.User;
import com.cbj.almacen.repository.UsuarioDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by jolvera on 10/06/2014.
 */
@Repository(value = "usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

    private static final Logger logger = LoggerFactory
            .getLogger(InventarioDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getUsuarioByUsername(String username) {
        User usuario = new User();

        try{
            Query query = em.createNamedQuery("user.findByUserName");
            query.setParameter("username", username);
            usuario = (User) query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return usuario;
        }
        return usuario;
    }

    @Override
    public User getUsuarioByIdEjecutivo(String idEjecutivo) {
        User usuario = new User();

        try{
            Query query = em.createNamedQuery("user.findByIdEjecutivo");
            query.setParameter("idEjecutivo", idEjecutivo);
            usuario = (User) query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return usuario;
        }
        return usuario;
    }

}
