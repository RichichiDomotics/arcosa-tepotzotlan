package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.repository.ClienteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 10/06/2014.
 */
@Repository(value = "clienteDao")
public class ClienteDaoImpl implements ClienteDao {

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
    public Clientes findCliente(int id) {
        Clientes cliente = new Clientes();
        try{
             cliente = em.find(Clientes.class,id);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return cliente;
    }

    @Transactional(readOnly = false)
    public Clientes insertCliente(Clientes cliente) {
        try{
            em.persist(cliente);
            em.flush();
            return cliente;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new Clientes();
        }
    }

    @Transactional(readOnly = false)
    public boolean updateCliente(Clientes cliente) {
        boolean respuesta=false;
        try{
            em.merge(cliente);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Override
    public List<Clientes> getClientes(String idCliente, String nombre) {
        try{
        if(!idCliente.equalsIgnoreCase("") && !nombre.equalsIgnoreCase("")){
        Query query = em.createNamedQuery("Clientes.findByIdClienteNombre");
        query.setParameter("idCliente", idCliente);
        query.setParameter("nombre", nombre);
        return query.getResultList();
        }else if(!idCliente.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Clientes.findByIdCliente");
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        }else if(!nombre.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Clientes.findByNombre");
            query.setParameter("nombre", nombre);
            return query.getResultList();
        }
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Clientes>();
        }
        return new ArrayList<Clientes>();
    }

    @Override
    public Clientes getClienteByIdNom(String idCliente, String nombre) {
        Clientes clientes = new Clientes();
        try{
            Query query = em.createNamedQuery("Clientes.findByIdClienteNombre");
            query.setParameter("idCliente", idCliente);
            query.setParameter("nombre", nombre);
            clientes = (Clientes) query.getResultList().get(0);
            }catch (Exception e){
                logger.error(e.getMessage() + e.getCause());
                return clientes;
            }
        return clientes;
    }

    @Override
    public List<Clientes> getClientesAll() {
        try{
                Query query = em.createNamedQuery("Clientes.getAll");
                return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Clientes>();
        }
       // return new ArrayList<Clientes>();
    }

    @Override
    public List<Clientes> getFormatosUnicos() {
        try{
            Query query = em.createNamedQuery("Clientes.getFormatosUnicos");
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Clientes>();
        }
        // return new ArrayList<Clientes>();
    }

    @Override
    public List<Clientes> getAllIdEjecutivo(String idEjecutivo) {
        try{
            Query query = em.createNamedQuery("Clientes.getAllIdEjecutivo");
            query.setParameter("idEjecutivo", idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Clientes>();
        }
        // return new ArrayList<Clientes>();
    }

    @Override
    public List<Clientes> getByEstatusSalida(){
        try{
            Query query = em.createNamedQuery("Clientes.getByEstatusSalida");
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al traer los clientes bloqueados"+e.getMessage()+e.getCause());
            return new ArrayList<Clientes>();
        }
    }
}
