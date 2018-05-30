package com.cbj.almacen.service.impl;

import com.cbj.almacen.domain.Prospecto;
import com.cbj.almacen.repository.ProspectoDao;
import com.cbj.almacen.service.ProspectoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
@Component
public class ProspectoManagerImpl implements ProspectoManager{

    private static final Logger logger = LoggerFactory
            .getLogger(PdfSalidasManagerImpl.class);

    @Autowired
    private ProspectoDao prospectoDao;

    @Override
    public boolean insertProspecto(Prospecto prospecto) {
        return prospectoDao.saveProspecto(prospecto);
    }

    @Override
    public boolean updateProspecto(Prospecto prospecto) {
        return prospectoDao.updateProspecto(prospecto);
    }

    @Override
    public List<Prospecto> getProspectosByIdNom(String id, String nombre,int idEjecutivo) {
        return prospectoDao.getProspectos(id, nombre,idEjecutivo);
    }

    @Override
    public List<Prospecto> getProspectosByIdNomJefe(String id, String nombre) {
        return prospectoDao.getProspectosJefe(id, nombre);
    }

    @Override
    public List<Prospecto> getProspectosByActive(String activo,int idEjecutivo) {
        return prospectoDao.getProspectosByActive(activo,idEjecutivo);
    }
    @Override
    public List<Prospecto> getProspectosByActiveJefe(String activo, int idJefe) {
        return prospectoDao.getProspectosByActiveJefe(activo, idJefe);
    }

    @Override
    public Prospecto getProspectoByIdNom(int id, String nombre) {
        return prospectoDao.getProspecto(id,nombre);
    }

    @Override
    public List<Prospecto> getAll(int idEjecutivo) {
        return prospectoDao.getAll(idEjecutivo);
    }

    @Override
    public List<Prospecto> getAllJefe(int idJefe) {return prospectoDao.getAllJefe(idJefe);}

    @Override
    public List<Prospecto> getAllJefeSinAsignar(int idJefe) {
        return prospectoDao.getAllJefeSinAsignar(idJefe);
    }

    @Override
    public List<Prospecto> getAllActive(int idEjecutivo) {
        return prospectoDao.getAllActive(idEjecutivo);
    }

    @Override
    public List<Prospecto> getAllActiveJefe(int idJefe) {
        return prospectoDao.getAllActiveJefe(idJefe);
    }

    @Override
    public List<Prospecto> getAllFail(int idEtapa){ return prospectoDao.getAllFail(idEtapa); }

    @Override
    public List<Prospecto> getProspectoFiltro(String nombre, String estatus,int idEjecutivo){return prospectoDao.getProspectoFiltro(nombre,estatus,idEjecutivo);}

    @Override
    public List<Prospecto> getProspectoFiltroJefe(String nombre, String estatus, int idJefe){return prospectoDao.getProspectoFiltroJefe(nombre,estatus,idJefe);}

    @Override
    public List<Prospecto> getProspectoFiltroDesasignadoJefe(String nombre, int idJefe){return prospectoDao.getProspectoFiltroDesasignadoJefe(nombre,idJefe);}

    @Override
    public List<Object> reporteEjecutivoEstatus(int idJefe){return prospectoDao.reporteEjecutivoEstatus(idJefe);}

    @Override
    public List<Object> reporteEjecutivoEtapas(int idJefe){return prospectoDao.reporteEjecutivoEtapas(idJefe);}

    @Override
    public List<Object> reporteEjecutivoEstatusFE(String cveEjecutivo,int idJefe){return prospectoDao.reporteEjecutivoEstatusFE(cveEjecutivo,idJefe);}

    @Override
    public List<Object> reporteEjecutivoEtapasFE(String cveEjecutivo,int idJefe){return prospectoDao.reporteEjecutivoEtapasFE(cveEjecutivo,idJefe);}

    @Override
    public List<Object> reportePorEstatus(int idJefe){return prospectoDao.reportePorEstatus(idJefe);}

    @Override
    public List<Object> reportePorEtapas(int idJefe){return prospectoDao.reportePorEtapas(idJefe);}

    @Override
    public List<Prospecto> getByRazonSocial(String nombre){return prospectoDao.getByRazonSocial(nombre);}

    @Override
    public List<Object> reporteHistorial(String fechaIni, String fechaFin, String idProspecto, String claveJefe){return prospectoDao.reporteHistorial(fechaIni, fechaFin, idProspecto, claveJefe);}


}
