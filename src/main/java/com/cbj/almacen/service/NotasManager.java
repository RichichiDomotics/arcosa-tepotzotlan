package com.cbj.almacen.service;

/**
 * Created by Richard on 07/07/2015.
 */

import com.cbj.almacen.domain.Notas;

import java.io.Serializable;
import java.util.List;

public interface NotasManager extends Serializable {

    public boolean insertNota(Notas notas);
    public boolean updateNota(Notas notas);
    public List<Object> getNotasCredito(String fechainicio, String fechafin);
    public List<Object> getDetalleNotasCredito(String fechainicio, String fechafin);
    public double getNotasCreditoTotal(String fechainicio, String fechafin);
    public List<Object> getNotasCreditoId(String fechainicio, String fechafin, String idEjecutivo);
    public List<Object> getDetalleNotasCreditoId(String fechainicio, String fechafin, String idEjecutivo);
    public double getNotasCreditoTotalId(String fechainicio, String fechafin, String idEjecutivo);
    public String getid();
    public List<Notas> getByFolio(int folio);
}
