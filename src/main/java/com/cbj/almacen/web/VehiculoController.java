package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.service.CatalogoManager;
import com.cbj.almacen.service.ClienteManager;
import com.cbj.almacen.service.MailManager;
import com.cbj.almacen.service.VehiculoManager;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class VehiculoController {

    private static final Logger logger = LoggerFactory
            .getLogger(VehiculoController.class);

    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private VehiculoManager vehiculoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private MailManager mailManager;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/adm_ingresoVehiculo", method = RequestMethod.GET)
    public String homeIngresoVehiculo(Locale locale, Model model) {
        logger.info("Ingreso de Vehiculos (carga o descarga de producto)", locale);

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
        model.addAttribute("plantas", this.catalogoManager.getCatalogo(TipoCatalogo.PLANTAS));
        model.addAttribute("recibos", this.catalogoManager.getCatalogo(TipoCatalogo.RECIBOS));
        model.addAttribute("puertas", this.catalogoManager.getCatalogo(TipoCatalogo.PUERTAS));
        model.addAttribute("maniobras", this.catalogoManager.getCatalogo(TipoCatalogo.MANIOBRAS));
        model.addAttribute("flejes", this.catalogoManager.getCatalogo(TipoCatalogo.FLEJES));
        model.addAttribute("vehiculos", this.catalogoManager.getCatalogo(TipoCatalogo.VEHICULOS));
        model.addAttribute("transportes", this.catalogoManager.getCatalogo(TipoCatalogo.TRANSPORTES));
        return "ingresoVehiculo";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String onSubmitIngresoVehiculo(@Valid Vehiculo vehiculo,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        vehiculo.setStatus("1");
        boolean resp = vehiculoManager.setIngresaVehiculo(vehiculo);
        if (resp) return "redirect:exito";
        else return "error";

    }

    @RequestMapping(value = "/autorizaSalida", method = RequestMethod.POST)
    public String autorizaSalida(String idCliente, Model model) {

        Integer id = new Integer(idCliente);
        model.addAttribute("autorizaSalida", this.clienteManager.getByIdCliente(id).getSalidaProducto());

        return "autorizaVehiculoSalida";
    }

    /**
     * Muestra los vehiculos posbles para salida.
     */
    @RequestMapping(value = "/adm_salidaVehiculo", method = RequestMethod.GET)
    public String ae_salidaVehiculo(Locale locale, Model model) {
        model.addAttribute("vehiculos",this.vehiculoManager.getVehiculosPorSalir());
        return "salidaVehiculo";
    }

    /**
     * Muestra los vehiculos posbles para salida.
     */
    @RequestMapping(value = "/ae_SalidaAutoriza", method = RequestMethod.GET)
    public String ae_SalidaAutoriza(Locale locale, Model model,int idVehiculo,String fleje) {
        logger.info("idVehiculo "+idVehiculo);
        final Vehiculo vehiculo = this.vehiculoManager.getIngresaVehiculoById(idVehiculo);

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
        vehiculo.setStatus("3");
        if(fleje!=""){
            vehiculo.setFleje(fleje);
        }
        vehiculoManager.updateIngresaVehiculo(vehiculo);
        this.mailManager.sendSalidaMail(vehiculo);
        model.addAttribute("vehiculos",this.vehiculoManager.getVehiculosPorSalir());
        return "salidaVehiculosal";
    }

    @RequestMapping(value = "/ae_InOutFormVehiculo", method = RequestMethod.GET)
    public String inOutFormVehiculo(Locale locale, Model model) {

        return "inOutFormVehiculo";
    }

    @RequestMapping(value = "/inOutVehiculo", method = RequestMethod.POST)
    public String inOutVehiculo(Locale locale, Model model,String fecini, String fecfin) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            final Date fechaInicioDate = formatter.parse(fecini);
            final Date fechaFinDate = formatter.parse(fecfin);
            model.addAttribute("vehiculos",this.vehiculoManager.getInOutVehiculo(fechaInicioDate,fechaFinDate));
        } catch (ParseException e) {
            model.addAttribute("vehiculos", null);
        }

        return "inOutVehiculo";
    }

    @RequestMapping (value = "tiemposAtencion", method = RequestMethod.GET)
    public String tiemposAtencion(Locale locale, Model model){
        return "tiemposAtencion";
    }

    @RequestMapping(value = "/getTiemposAtencion", method = RequestMethod.POST)
    public String getTiemposAtencion(Locale locale, Model model,String fecini, String fecfin) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            final Date fechaInicioDate = formatter.parse(fecini);
            final Date fechaFinDate = formatter.parse(fecfin);
            model.addAttribute("vehiculos",this.vehiculoManager.getTiemposAtencion(fechaInicioDate,fechaFinDate));
            model.addAttribute("cumplidos",this.vehiculoManager.getTiemposAtencionCumplidos(fechaInicioDate,fechaFinDate).size());
            model.addAttribute("nocumplidos",this.vehiculoManager.getTiemposAtencionNoCumplidos(fechaInicioDate,fechaFinDate).size());
            model.addAttribute("totales",this.vehiculoManager.getTiemposAtencion(fechaInicioDate,fechaFinDate).size());
            
        } catch (ParseException e) {
            model.addAttribute("vehiculos", null);
        }

        return "getTiemposAtencion";
    }
}
