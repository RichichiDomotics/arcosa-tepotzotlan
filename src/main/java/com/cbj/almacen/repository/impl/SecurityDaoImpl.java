package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.User;
import com.cbj.almacen.repository.SecurityDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by jolvera on 11/05/2014.
 */
@Repository(value = "securityDao")
public class SecurityDaoImpl implements SecurityDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getLoginSession(String user, String password) {
        try{
            return (User) em.createQuery("select p from User p where p.username='"+user+"' and p.password='"+password+"'").getSingleResult();
        }catch (NoResultException e){
            User u = new User();
            u.setEnabled(false);
            return  u;
        }catch (Exception ex){
            return null;
        }

    }


    public User getUsuFirmado(String user, String password) {
        try{
            return (User) em.createQuery("select p from User p where p.username='"+user+"' and p.password='"+password+"'").getSingleResult();
        }catch (NoResultException e){
            User u = new User();
            u.setEnabled(false);
            return  u;
        }catch (Exception ex){
            return null;
        }

    }
}
