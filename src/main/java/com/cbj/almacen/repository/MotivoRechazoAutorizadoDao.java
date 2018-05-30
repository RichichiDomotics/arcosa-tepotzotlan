package com.cbj.almacen.repository;


//import com.cbj.almacen.domain.MotivoRechazoAutorizado;
import com.cbj.almacen.domain.MotivosRechazoAutorizado;

import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface MotivoRechazoAutorizadoDao {

    public boolean insertMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado);
    
    public boolean updateMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado);

    public int getAll(int idProspecto);
}
