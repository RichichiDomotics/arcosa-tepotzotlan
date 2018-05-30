package com.cbj.almacen.web;

import com.cbj.almacen.service.AutPuertasManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AutPuertasController {

    private static final Logger logger = LoggerFactory
            .getLogger(AutPuertasController.class);

    @Autowired
    private AutPuertasManager autPuertasManager;

    /**
     * Regresa la lista de puertas y plantas disponibles por cliente y tipo de vehiculo, si no existe regresa lista vacia.
     */
    @RequestMapping(value = "/consultaPlantaClienteVehiculo", method = RequestMethod.POST)
    public String consultaPlantaClienteVehiculo(Locale locale, Model model,int idCliente,int idTipoVehiculo) {
        model.addAttribute("plantas", this.autPuertasManager.getPuertaByClienteVehiculo(idCliente, idTipoVehiculo));
        
        return "cboplantas";
    }
    
    /**
     * Regresa la lista de puertas y plantas disponibles por cliente y tipo de vehiculo, si no existe regresa lista vacia.
     */
    @RequestMapping(value = "/consultaPuertaClienteVehiculo", method = RequestMethod.POST)
    public String consultaPuertaClienteVehiculo(Locale locale, Model model,int idCliente,int idTipoVehiculo) {
        model.addAttribute("puertas", this.autPuertasManager.getPuertaByClienteVehiculo(idCliente, idTipoVehiculo));
        
        return "cbopuertascl";
    }
}
