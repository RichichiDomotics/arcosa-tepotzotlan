package com.cbj.almacen.service;

import com.cbj.almacen.domain.Salidas;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SalidasManager  extends Serializable {

    public int registraSalida(Salidas salida);
    public boolean updateSalida(Salidas salida);
    public Salidas consultaSalidaByIdFolioSalida(int id);
    public List<Salidas> getSalidaByClienteConsecutivo(String idCliente, int consecutivo);
    List<Salidas> getSalidaByCliente(String idCliente);
    public List<Salidas> getSalidaByConsecutivo(int consecutivo);
    public List<Salidas> getSalidaByFolioSalida(int folioSalida);
    public List<Salidas> getSalidaByFechaSalidaNull();
    public List<Salidas> getKilosByFechaSalida(String fechaSalida);

}
