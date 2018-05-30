package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Retemes;

import java.util.List;


public interface RetemesDao {

    public boolean insertReteme(Retemes datosIngresoReteme);
    public List<Retemes> findRetemeByConsecutivo(String consecutivo);
    public List<Retemes> findRetemeByIdCliente(String idCliente, String fecini,String fecfin, String folioReteme,String consecutivo);
    public String getMaxId();
    public String getMaxIdTIF();
}
