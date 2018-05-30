package com.cbj.almacen.service;


import com.cbj.almacen.domain.RegEntradas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
public interface RegEntradasManager extends Serializable {

    public int registraRegEntrada(RegEntradas entradas);
    public boolean actualizaRegEntrada(RegEntradas entradas);
    public RegEntradas consultaRegEntradaByIdIngresoVehiculo(int id);
    public List<RegEntradas> getEntradaByConsecutivo(int consecutivo);
    public List<RegEntradas> getEntradaByConsecutivoImpresionEntrada(int consecutivo);
    public List<RegEntradas> getEntradaByActualDate();
    public List<RegEntradas> getEntradaByActualDateEntrada();
    public List<RegEntradas> getComparaCantidadesTonelaje(String fecini, String fecfin);
    public List<RegEntradas> getCancelados();
}
