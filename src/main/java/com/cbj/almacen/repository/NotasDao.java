package com.cbj.almacen.repository;
import com.cbj.almacen.domain.Notas;
import java.util.List;
/**
 * Created by Richard on 07/07/2015.
 */
public interface NotasDao {

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
