package com.cbj.almacen.service;


import com.cbj.almacen.domain.Prospecto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface ProspectoManager extends Serializable {

    public boolean insertProspecto(Prospecto prospecto);
    public boolean updateProspecto(Prospecto prospecto);
    public List<Prospecto> getProspectosByIdNom(String id,String nombre,int idEjecutivo);
    public List<Prospecto> getProspectosByIdNomJefe(String id,String nombre);
    public List<Prospecto> getProspectosByActive(String activo,int idEjecutivo);
    public List<Prospecto> getProspectosByActiveJefe(String activo, int idJefe);
    public Prospecto getProspectoByIdNom(int id,String nombre);
    public List<Prospecto> getAll(int idEjecutivo);
    public List<Prospecto> getAllJefe(int idJefe);
    public List<Prospecto> getAllJefeSinAsignar(int idJefe);
    public List<Prospecto> getAllActive(int idEjecutivo);
    public List<Prospecto> getAllActiveJefe(int idJefe);
    public List<Prospecto> getAllFail(int idEtapa);
    public List<Prospecto> getProspectoFiltro(String nombre, String estatus,int idEjecutivo);
    public List<Prospecto> getProspectoFiltroJefe(String nombre, String estatus, int idJefe);
    public List<Prospecto> getProspectoFiltroDesasignadoJefe(String nombre, int idJefe);
    public List<Object> reporteEjecutivoEstatus(int idJefe);
    public List<Object> reporteEjecutivoEtapas(int idJefe);
    public List<Object> reporteEjecutivoEstatusFE(String cveEjecutivo,int idJefe);
    public List<Object> reporteEjecutivoEtapasFE(String cveEjecutivo,int idJefe);
    public List<Object> reportePorEstatus(int idJefe);
    public List<Object> reportePorEtapas(int idJefe);

    public List<Prospecto> getByRazonSocial(String nombre);

    public List<Object> reporteHistorial(String fechaIni, String fechaFin, String idProspecto, String claveJefe);
}
