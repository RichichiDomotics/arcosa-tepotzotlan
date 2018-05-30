package com.cbj.almacen.web;

import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 20/08/2014.
 */
@Controller
public class ReporteEntradasController {

    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private RegEntradasManager regEntradasManager;
    @Autowired
    private VehiculoManager vehiculoManager;

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/aes_formReporteEntrada", method = RequestMethod.GET)
    public String aes_formReporteEntrada(Model model) {
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        model.addAttribute("clientes", clientes);
        model.addAttribute("camaras",camaras);
        return "formReporteEntrada";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/aes_reporteEntradaPorDia", method = RequestMethod.POST)
    public String aes_reporteEntradaPorDia(String fechaInicio, Model model) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date fechaInicioDate = formatter.parse(fechaInicio);
        final List<Object[]> entrada = this.detalleRdManager.getKilosByDay(fechaInicioDate);

        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("conteoPorDia", entrada);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes", clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteEntrada";
        }
        model.addAttribute("clientes", clientes);
        model.addAttribute("camaras",camaras);
        return "formReporteEntrada";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @Transactional(readOnly = true)
    @RequestMapping(value = "/reporteEntradaPorDiares", method = RequestMethod.POST)
    public String reporteEntradaPorDiares(String fechaInicio, Model model) {
        try {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date fechaInicioDate = formatter.parse(fechaInicio);
        logger.info("Entrada por dia "+fechaInicioDate);
        final List<Object[]> entrada = this.detalleRdManager.getKilosByDay(fechaInicioDate);
        List<RegEntradas> regEntradases = this.regEntradasManager.getEntradaByConsecutivo(Integer.parseInt(entrada.get(0)[3].toString()));
        Vehiculo vehiculo = this.vehiculoManager.getidIngresoVehiculo(regEntradases.get(0).getIdIngresoVehiculo());
        logger.info("Entrada "+entrada);
        model.addAttribute("tipo Producto");
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("conteoPorDia", entrada);
        } catch (ParseException e) {
            logger.error("fecha invalidass");
            return "entradaPorDiaResponse";
        }
        return "entradaPorDiaResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteEntradaPorFechasres", method = RequestMethod.POST)
    public String reporteEntradaPorFechasres(Model model, String fechaInicio, String fechaFin) {
    	try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicio);
            final Date fechaFinDate = formatter.parse(fechaFin);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechas(fechaInicioDate, fechaFinDate);
            logger.info("Entrada "+entrada);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            model.addAttribute("conteoPorDia", entrada);
        } catch (ParseException e) {
            logger.error(" error e "+e);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            return "entradaPorFechasResponse";
        }
        return "entradaPorFechasResponse";
    }

    @RequestMapping(value = "/reporteEntradaPorFechasConcentradores", method = RequestMethod.POST)
    public String reporteEntradaPorFechasConcentradores(Model model, String fechaInicio, String fechaFin) {
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicio);
            final Date fechaFinDate = formatter.parse(fechaFin);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechasConcentrado(fechaInicioDate, fechaFinDate);
            logger.info("Entrada "+entrada);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            model.addAttribute("conteoPorDia", entrada);
        } catch (ParseException e) {
            logger.error(" error e "+e.getMessage() +" "+ e.getCause());
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            return "entradaPorFechasConcentradoResponse";
        }
        return "entradaPorFechasConcentradoResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteEntradaPorFechasClres", method = RequestMethod.POST)
    public String reporteEntradaPorFechasClres(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
    	try {
    		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechasCliente(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
            model.addAttribute("conteoPorFechasCliente", entrada);
            model.addAttribute("clienteDatos", clienteDatos);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            return "entradaPorFechasClienteResponse";
        }
        return "entradaPorFechasClienteResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteEntradaPorFechasClDetres", method = RequestMethod.POST)
    public String reporteEntradaPorFechasClDetres(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
    	try {
    		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechasClienteDetalle(fechaInicioDate, fechaFinDate, idCliente);
            logger.debug(String.valueOf(entrada));
            final Object entradaTotal = this.detalleRdManager.getKilosByFechasClienteDetalleTotal(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
            model.addAttribute("conteoPorFechasClienteDetalle", entrada);
            model.addAttribute("conteoPorFechasClienteDetalleTotal", entradaTotal);
            model.addAttribute("clienteDatos", clienteDatos);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            return "entradaPorFechasClienteDetalleResponse";
        }
        return "entradaPorFechasClienteDetalleResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteEntradaPorCamarares", method = RequestMethod.POST)
    public String reporteEntradaPorCamarares(Model model, String fechaInicioCliente, String fechaFinCliente, String idCamara) {
    	try {
    		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            if(idCamara.trim().equalsIgnoreCase("")){
                final List<Object[]> entrada = this.detalleRdManager.getReporteByCamaraVacia(fechaInicioDate, fechaFinDate);
                model.addAttribute("conteoCamara", entrada);
                model.addAttribute("fechaInicio", fechaInicioCliente);
                model.addAttribute("fechaFin", fechaFinCliente);
                model.addAttribute("idCamara", idCamara);
                model.addAttribute("Camara", "TODAS LAS CAMARAS");
            }else{
                final List<Object[]> entrada = this.detalleRdManager.getReporteByCamara(fechaInicioDate, fechaFinDate, idCamara);
                model.addAttribute("conteoCamara", entrada);
                model.addAttribute("fechaInicio", fechaInicioCliente);
                model.addAttribute("fechaFin", fechaFinCliente);
                model.addAttribute("idCamara", idCamara);
            }
        } catch (ParseException e) {
            logger.error("fecha invalida");
            return "entradaPorCamaraResponse";
        }
        return "entradaPorCamaraResponse";
    }
    

    @RequestMapping(value = "/aes_reporteEntradaPorFechas", method = RequestMethod.POST)
    public String aes_reporteEntradaPorFechas(Model model, String fechaInicio, String fechaFin) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicio);
            final Date fechaFinDate = formatter.parse(fechaFin);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechas(fechaInicioDate, fechaFinDate);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            model.addAttribute("conteoPorFechas", entrada);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes", clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteEntrada";
        }
        model.addAttribute("clientes", clientes);
        model.addAttribute("camaras",camaras);
        return "formReporteEntrada";
    }

    @RequestMapping(value = "/aes_reporteEntradaPorFechaCliente", method = RequestMethod.POST)
    public String aes_reporteEntradaPorFechaCliente(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechasCliente(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
            model.addAttribute("conteoPorFechasCliente", entrada);
            model.addAttribute("clienteDatos", clienteDatos);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteEntrada";
        }
        return "formReporteEntrada";
    }

    @RequestMapping(value = "/aes_reporteEntradaPorFechaClienteDetalle", method = RequestMethod.POST)
    public String aes_reporteEntradaPorFechaClienteDetalle(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> entrada = this.detalleRdManager.getKilosByFechasClienteDetalle(fechaInicioDate, fechaFinDate, idCliente);
            final Object entradaTotal = this.detalleRdManager.getKilosByFechasClienteDetalleTotal(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("clientes",clientes);
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
            model.addAttribute("camaras",camaras);
            model.addAttribute("conteoPorFechasClienteDetalle", entrada);
            model.addAttribute("conteoPorFechasClienteDetalleTotal", entradaTotal);
            model.addAttribute("clienteDatos", clienteDatos);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteEntrada";
        }
        return "formReporteEntrada";
    }

    @RequestMapping(value = "/aes_reporteEntradaPorCamara", method = RequestMethod.POST)
    public String aes_reporteEntradaPorCamara(Model model, String fechaInicioCliente, String fechaFinCliente, String idCamara) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        logger.error("idCamara "+idCamara);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            if(idCamara.trim().equalsIgnoreCase("")){
                final List<Object[]> entrada = this.detalleRdManager.getReporteByCamaraVacia(fechaInicioDate, fechaFinDate);
                model.addAttribute("conteoCamara", entrada);
            }else{
                final List<Object[]> entrada = this.detalleRdManager.getReporteByCamara(fechaInicioDate, fechaFinDate, idCamara);
                model.addAttribute("conteoCamara", entrada);
            }
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCamara", idCamara);
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);

        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteEntrada";
        }
        return "formReporteEntrada";
    }

}
