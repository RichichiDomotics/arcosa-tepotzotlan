package com.cbj.almacen.service;

import java.io.Serializable;

/**
 * Created by jolvera on 22/06/2014.
 */
public interface PdfSalidasManager extends Serializable {

    public void generaPdfSalidas(int idSalida);
}
