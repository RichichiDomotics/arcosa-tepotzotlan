package com.cbj.almacen.repository;

import com.cbj.almacen.domain.RegEntradas;

import java.util.List;

/**
 * Created by bvelasco on 17/05/2014.
 */
public interface RegEntradasDao {

    public int insertRegistroRegEntradas(RegEntradas entrada);
    public boolean updateRegistroRegEntradas(RegEntradas entrada);
    public boolean deleteRegistroRegEntradas(int id);
    public RegEntradas findRegistroRegEntradas(int id);
    public RegEntradas findRegistroRegEntradasByIdIngresoVehiculo(int id);
    public List<RegEntradas> findRegistroRegEntradasByConsecutivo(int id);
    public List<RegEntradas> findRegistroRegEntradasByConsecutivoImpresionEntrada(int id);
    public List<RegEntradas> getAllByActualDate();
    public List<RegEntradas> getAllByActualDateEntrada();
    public List<RegEntradas> getComparaCantidadesTonelaje(String fecini, String fecfin);
    public List<RegEntradas> getCancelados();
    
}
