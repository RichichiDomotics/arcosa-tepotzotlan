package com.cbj.almacen.repository;

import com.cbj.almacen.domain.AvisosHistorial;

import java.util.List;


public interface AvisosHistorialDao {

    public boolean insertAvisosHistorial(AvisosHistorial datosAvisosHistorial);
    public boolean updateAvisosHistorial(AvisosHistorial datosIngresoVehiculo);
    public List<AvisosHistorial> findPorClaveJefe(String claveJefe);
    public List<Object> getTotalAvisos(String claveJefe);
    public List<Object> getAvisosLista(String claveJefe);
    public List<Object> getAvisosListaDetalle(String claveJefe);
    public boolean updateAvisosHistorialVisto(String tipoNotificacion);
}
