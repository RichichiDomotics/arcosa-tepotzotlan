package com.cbj.almacen.service;

import com.cbj.almacen.domain.Retemes;

import java.io.Serializable;
import java.util.List;


public interface RetemesManager extends Serializable {

    public boolean setIngresaRetemes(Retemes reteme);
    public List<Retemes> findRetemeByConsecutivo(String consecutivo);
    public List<Retemes> findRetemeByIdCliente(String idCliente, String fecini, String fecfin, String folioReteme,String consecutivo);
    public String getMaxId();
    public String getMaxIdTIF();
}