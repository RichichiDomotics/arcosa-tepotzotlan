package com.cbj.almacen.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import com.cbj.almacen.Utils;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbj.almacen.domain.PreVehiculo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PreVehiculoController {

    private static final Logger logger = LoggerFactory
            .getLogger(PreVehiculoController.class);

    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private PreVehiculoManager preVehiculoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private PdfEntradasManager pdfEntradasManager;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/v_ingresoPreVehiculo", method = RequestMethod.GET)
    public String homePreIngresoVehiculo(Locale locale, Model model) {
        logger.info("Pre Ingreso Vehiculos (vigilante)", locale);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);

        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("vehiculos", this.catalogoManager.getCatalogo(TipoCatalogo.VEHICULOS));
        return "ingresoPreVehiculo";
    }


    @RequestMapping(value = "/v_insertaVigilancia", method = RequestMethod.POST)
    public String insertaVigilancia(@Valid PreVehiculo vehiculo,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        boolean resp = preVehiculoManager.setIngresaVehiculo(vehiculo);
        if (resp) return "redirect:exito";
        else return "error";

    }

    @RequestMapping(value = "/autorizaPreSalida", method = RequestMethod.POST)
    public String autorizaPreSalida(String idCliente, Model model) {

        Integer id = new Integer(idCliente);
        model.addAttribute("autorizaSalida", this.clienteManager.getByIdCliente(id).getSalidaProducto());

        return "autorizaPreVehiculoSalida";
    }

    /**
     * Muestra los vehiculos posbles para salida.
     */
    @RequestMapping(value = "/ae_salidaPreVehiculo", method = RequestMethod.GET)
    public String ae_salidaPreVehiculo(Locale locale, Model model) {
        model.addAttribute("vehiculos",this.preVehiculoManager.getVehiculosPorDia());
        return "salidaPreVehiculo";
    }

    /**
     * Muestra los vehiculos posbles para salida.
     */
    @RequestMapping(value = "/SalidaPreAutoriza", method = RequestMethod.POST)
    public String ae_SalidaPreAutoriza(Locale locale, Model model,@RequestParam(value = "idVehiculo")int idVehiculo) {
        logger.info("idVehiculo "+idVehiculo);
        final PreVehiculo vehiculo = this.preVehiculoManager.getIngresaVehiculoById(idVehiculo);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        vehiculo.setFechaSalida(fecha);
        vehiculo.setHoraSalida(hora);
        vehiculo.setEstatus(2);
        preVehiculoManager.updateIngresaVehiculo(vehiculo);
        model.addAttribute("vehiculos",this.preVehiculoManager.getVehiculosPorDia());
        return "salidaPreVehiculo";
    }

    @RequestMapping(value = "/ae_InOutPreVehiculo", method = RequestMethod.GET)
    public String ae_InOutPteVehiculo(Locale locale, Model model) {
        model.addAttribute("vehiculos",this.preVehiculoManager.getInOutVehiculo());
        return "inOutPreVehiculo";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/v_listaPreVehiculo", method = RequestMethod.GET)
    public String listaPreVehiculo(Locale locale, Model model) {
        logger.info("Pre Ingreso Vehiculos (vigilante)", locale);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);

        //String idClienteStr = String.valueOf(idCliente);
        //if(idClienteStr.equals("") || idClienteStr == null
        final String idClienteStr = "%";
        //}
        
        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("registros", this.preVehiculoManager.getVehiculosPorDiaCliente(idClienteStr));
        return "listaPreVehiculo";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/ae_listaVigilancia", method = RequestMethod.POST)
    public String ae_listaVigilancia(Locale locale, Model model,String idCliente) {
        logger.info("Pre Ingreso Vehiculos (vigilante)", locale);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        
        String idClienteStr = String.valueOf(idCliente);
        if(idClienteStr.equals("") || idClienteStr == null){
        	idClienteStr = "%";
        }
        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("registros", this.preVehiculoManager.getVehiculosPorDiaCliente(idClienteStr));
        return "listaPreVehiculo";
    }

}
