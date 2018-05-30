package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.Prospecto;
import com.cbj.almacen.repository.ProspectoDao;
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
 * Created by jolvera on 01/09/2014.
 */
@Repository(value = "prospectoDao")
public class ProspectoDaoImpl implements ProspectoDao {

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
    public boolean saveProspecto(Prospecto prospecto) {
        boolean respuesta = false;
        try {
            em.persist(prospecto);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean updateProspecto(Prospecto prospecto) {
        boolean respuesta = false;
        try {
            em.merge(prospecto);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public List<Prospecto> getProspectos(String idProspecto, String nombre, int idEjecutivo) {
        if(!idProspecto.equalsIgnoreCase("") && !nombre.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Prospecto.findByIdProspectoNombre");
            query.setParameter("idProspecto", idProspecto);
            query.setParameter("nombre", nombre);
            query.setParameter("idEjecutivo", idEjecutivo);
            return query.getResultList();
        }else if(!idProspecto.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Prospecto.findByIdProspecto");
            query.setParameter("idProspecto", idProspecto);
            query.setParameter("idEjecutivo", idEjecutivo);
            return query.getResultList();
        }else if(!nombre.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Prospecto.findByNombre");
            query.setParameter("nombre", nombre+"%");
            query.setParameter("idEjecutivo", idEjecutivo);
            return query.getResultList();
        }
        return new ArrayList<Prospecto>();
    }
    public List<Prospecto> getProspectosJefe(String idProspecto, String nombre) {
        if(!idProspecto.equalsIgnoreCase("") && !nombre.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Prospecto.findByIdProspectoNombreJefe");
            query.setParameter("idProspecto", idProspecto);
            query.setParameter("nombre", nombre);
            return query.getResultList();
        }else if(!idProspecto.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Prospecto.findByIdProspectoJefe");
            query.setParameter("idProspecto", idProspecto);
            return query.getResultList();
        }else if(!nombre.equalsIgnoreCase("")){
            Query query = em.createNamedQuery("Prospecto.findByNombreJefe");
            query.setParameter("nombre", nombre+"%");
            return query.getResultList();
        }
        return new ArrayList<Prospecto>();
    }

    @Override
    public List<Prospecto> getProspectosByActive(String activo,int idEjecutivo) {
        Query query = em.createNamedQuery("Prospecto.findByActivo");
        query.setParameter("estatus", activo);
        query.setParameter("idEjecutivo", idEjecutivo);
        return query.getResultList();
    }

    @Override
    public List<Prospecto> getProspectosByActiveJefe(String activo, int idJefe) {
        Query query = em.createNamedQuery("Prospecto.findByActivoJefe");
        query.setParameter("estatus", activo);
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public Prospecto getProspecto(int idProspecto, String nombre) {
        Prospecto prospecto = new Prospecto();
        try {
                Query query = em.createNamedQuery("Prospecto.findByIdProspecto2");
                query.setParameter("idProspecto", idProspecto);
                prospecto = (Prospecto) query.getResultList().get(0);

        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return prospecto;
    }


    @Override
    public List<Prospecto> getAll(int idEjecutivo) {
        try{
            Query query = em.createNamedQuery("Prospecto.getAll");
            query.setParameter("idEjecutivo", idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Prospecto>();
        }
    }

    @Override
    public List<Prospecto> getAllJefe(int idJefe) {
        try{
            Query query = em.createNamedQuery("Prospecto.getAllJefe");
            query.setParameter("idJefe", idJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Prospecto>();
        }
    }

    @Override
    public List<Prospecto> getAllJefeSinAsignar(int idJefe) {
        try{
            Query query = em.createNamedQuery("Prospecto.getAllJefeSinAsignar");
            query.setParameter("idJefe", idJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Prospecto>();
        }
    }

    @Override
    public List<Prospecto> getAllActive(int idEjecutivo) {
        try{
            Query query = em.createNamedQuery("Prospecto.getAllActive");
            query.setParameter("idEjecutivo", idEjecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Prospecto>();
        }
    }

    @Override
    public List<Prospecto> getAllActiveJefe(int idJefe) {
        try{
            Query query = em.createNamedQuery("Prospecto.getAllActiveJefe");
            query.setParameter("idJefe", idJefe);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Prospecto>();
        }
    }

    @Override
    public List<Prospecto> getAllFail(int idEtapa) {
        try{
            Query query = em.createNamedQuery("Prospecto.getAllFail");
            query.setParameter("idEtapa",idEtapa);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return new ArrayList<Prospecto>();
        }
    }


    @Override
    public List<Prospecto> getProspectoFiltro(String nombre, String estatus,int idEjecutivo){
        Query query = em.createNamedQuery("Prospecto.findByFiltro");
        query.setParameter("nombre", nombre+"%");
        query.setParameter("estatus", estatus);
        query.setParameter("idEjecutivo", idEjecutivo);
        return query.getResultList();
    }

    @Override
    public List<Prospecto> getProspectoFiltroJefe(String nombre, String estatus,int idJefe){
        Query query = em.createNamedQuery("Prospecto.findByFiltroJefe");
        query.setParameter("nombre", nombre+"%");
        query.setParameter("estatus", estatus);
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }


    @Override
    public List<Prospecto> getProspectoFiltroDesasignadoJefe(String nombre, int idJefe){
        Query query = em.createNamedQuery("Prospecto.findByFiltroJefeDesasignado");

        if(!nombre.equals("")){
            nombre = nombre+"%";
        }else{
            nombre = "%";
        }

        query.setParameter("nombre", nombre);
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Object> reporteEjecutivoEstatus(int idJefe){
        Query query = em.createNamedQuery("Prospecto.reporteEjecutivoEstatus");
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Object> reporteEjecutivoEtapas(int idJefe){
        Query query = em.createNamedQuery("Prospecto.reporteEjecutivoEtapas");
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Object> reporteEjecutivoEstatusFE(String cveEjecutivo,int idJefe){
        Query query = em.createNamedQuery("Prospecto.reporteEjecutivoEstatusFE");
        query.setParameter("cveEjecutivo", cveEjecutivo);
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Object> reporteEjecutivoEtapasFE(String cveEjecutivo,int idJefe){
        Query query = em.createNamedQuery("Prospecto.reporteEjecutivoEtapasFE");
        query.setParameter("cveEjecutivo", cveEjecutivo);
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Object> reportePorEstatus(int idJefe){
        Query query = em.createNamedQuery("Prospecto.reportePorEstatus");
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Object> reportePorEtapas(int idJefe){
        Query query = em.createNamedQuery("Prospecto.reportePorEtapas");
        query.setParameter("idJefe", idJefe);
        return query.getResultList();
    }

    @Override
    public List<Prospecto> getByRazonSocial(String nombre){
        Query query = em.createNamedQuery("Prospecto.findByRazonSocial");
        query.setParameter("nombre", "%"+nombre+"%");
        return query.getResultList();
    }

    @Override
    public List<Object> reporteHistorial(String fechaIni, String fechaFin, String idProspecto, String claveJefe){
        if(claveJefe==""){
            Query query = em.createNamedQuery("Prospecto.reporteHistorialIdProspecto");
            query.setParameter("idProspecto", Integer.parseInt(idProspecto));
            query.setParameter("fechaIni", fechaIni);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        }else{
            if(idProspecto!=""){
                Query query = em.createNamedQuery("Prospecto.reporteHistorialIdProspecto");
                query.setParameter("idProspecto", Integer.parseInt(idProspecto));
                query.setParameter("fechaIni", fechaIni);
                query.setParameter("fechaFin", fechaFin);
                return query.getResultList();
            }else{
                Query query = em.createNamedQuery("Prospecto.reporteHistorial");
                query.setParameter("fechaIni", fechaIni);
                query.setParameter("fechaFin", fechaFin);
                query.setParameter("claveJefe", claveJefe);
                return query.getResultList();
            }
        }

    }


}
