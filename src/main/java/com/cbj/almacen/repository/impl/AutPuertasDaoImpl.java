package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.AutPuertas;
import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.repository.AutPuertasDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 12/11/2014.
 */
@Repository(value = "autPuertasDao")
public class AutPuertasDaoImpl implements AutPuertasDao {

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


    @Override
    public List<AutPuertas> getPuertaByClienteVehiculo(int idCliente, int idTipoVehiculo) {
        try {
            Query query = em.createNamedQuery("AutPuertas.getByClienteTipoVehiculo");
            query.setParameter("idCliente", idCliente);
            query.setParameter("idTipoVehiculo", idTipoVehiculo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<AutPuertas>();
        }
    }
}
