package com.cbj.almacen.service;

import com.cbj.almacen.domain.AvisosHistorial;

import java.io.Serializable;
import java.util.List;


public interface AvisosHistorialManager extends Serializable {

    public boolean setIngresaAvisosHistorial(AvisosHistorial avisosHistorial);
    public boolean updateIngresaAvisosHistorial(AvisosHistorial avisosHistorial);
    public List<AvisosHistorial> getAvisosPorClaveJefe(String claveJefe);
    public List<Object> getTotalAvisos(String claveJefe);
    public List<Object> getAvisosLista(String claveJefe);
    public List<Object> getAvisosListaDetalle(String claveJefe);
    public boolean updateAvisosHistorialVisto(String tipoNotificacion);
}