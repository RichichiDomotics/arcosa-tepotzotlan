package com.cbj.almacen.repository;

import com.cbj.almacen.domain.TraspasosCamara;

import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 09/09/2014.
 */
public interface TraspasosCamaraDao {
        public TraspasosCamara insertTraspasoCamara(TraspasosCamara traspasosCamara);
        public List<TraspasosCamara> getByRDFecha(String consecutivo, Date fechaini, Date fechafin);
}
