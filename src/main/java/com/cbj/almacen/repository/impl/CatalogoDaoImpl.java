package com.cbj.almacen.repository.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.CatalogoGeneral;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.Embalaje;
import com.cbj.almacen.repository.CatalogoDao;
import com.cbj.almacen.domain.Convenios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by jolvera on 25/05/2014.
 */

@Repository(value = "catalogoDao")
public class CatalogoDaoImpl implements CatalogoDao {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasDaoImpl.class);

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
    public List<Clientes> getClientesList() {
        return em.createQuery("select p from Clientes p order by p.ID_CLIENTE")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Clientes> getClientesActivosList() {
        return em.createQuery("select p from Clientes p where p.ESTATUS = '"+Utils.CLIENTE_ESTATUS_ACTIVO+"' order by p.ID_CLIENTE")
                .getResultList();
    }


    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Planta> getCatalogoPlanta() {
        return em.createQuery("select p from Planta p order by p.idPlanta")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoRecibo() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='R' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoPuerta() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo IN ('P1','P2') and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoPuertaPlanta(String Planta) {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo like '"+Planta+"' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoManiobra() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='M' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoFleje() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='F' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoVehiculo() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='V' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoTransporte() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='T' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Embalaje> getEmbalajeList() {
        return em.createQuery("select p from Embalaje p")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoServicios() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='S' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoCamaras() {
            return em.createQuery("select p from CatalogoGeneral p where p.tipo='C' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<CatalogoGeneral> getCatalogoTuneles() {
        return em.createQuery("select p from CatalogoGeneral p where p.tipo='TU' and p.estatus='A' order by p.idCatalogo")
                .getResultList();
    }
    

}