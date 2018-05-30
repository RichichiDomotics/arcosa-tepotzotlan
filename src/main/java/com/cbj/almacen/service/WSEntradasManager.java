package com.cbj.almacen.service;

import java.io.Serializable;
import java.util.List;
import com.cbj.almacen.domain.FormEntrada;
import com.cbj.almacen.domain.Planta;
import com.cbj.almacen.domain.CatalogoGeneral;


public interface WSEntradasManager extends ConsultasManager {

        public boolean setEntradas(String entradaWS);
}