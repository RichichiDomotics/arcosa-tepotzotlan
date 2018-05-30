package com.cbj.almacen.service;


//import com.cbj.almacen.domain.MotivoRechazoAutorizado;
import com.cbj.almacen.domain.MotivosRechazoAutorizado;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jolvera on 01/09/2014.
 */
public interface MotivoRechazoAutorizadoManager extends Serializable {

    public boolean insertMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado);
    
    public boolean updateMotivo(MotivosRechazoAutorizado motivoRechazoAutorizado);

    public int getAll(int idProspecto);

   
}
