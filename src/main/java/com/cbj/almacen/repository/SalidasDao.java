package com.cbj.almacen.repository;


import com.cbj.almacen.domain.Salidas;

import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SalidasDao {

    public int insertSalidas(Salidas salidas);
    public Salidas findSalidaByIdFolioSalida(int id);
    public List<Salidas> findSalidaByClienteConsecutivo(String cliente,int consecutivo);
    public List<Salidas> findSalidaByCliente(String cliente);
    public List<Salidas> findSalidaByConsecutivo(int consecutivo);
    public List<Salidas> findSalidaByFolioSalida(int folioSalida);
    public List<Salidas> getAllFechaSalidaNull();
    public List<Salidas> getKilosByFechaSalida(String fechaSalida);
    public boolean updateSalidas(Salidas salidas);
}
