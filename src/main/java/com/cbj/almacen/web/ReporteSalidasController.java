package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.domain.BsalidasDetalle;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jolvera on 20/08/2014.
 */
@Controller
public class ReporteSalidasController {

    @Autowired
    private SalidasDetalleManager salidasDetalleManager;
    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private InventarioManager inventarioManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private BsalidasDetalleManager bsalidasDetalleManager;

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/aes_formReporteSalida", method = RequestMethod.GET)
    public String formResporteSalida(Model model) {
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        model.addAttribute("clientes", clientes);
        model.addAttribute("camaras",camaras);
        return "formReporteSalida";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteSalidaPorDiares", method = RequestMethod.POST)
    public String reporteSalidaPorDiares(String fechaSalida, Model model) {
        try {
        	final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaSalida);
            logger.info("fecha que pasa "+ fechaInicioDate);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechas(fechaInicioDate,fechaInicioDate);
            model.addAttribute("conteoPorDia", salida);
            model.addAttribute("fechaInicio", fechaSalida);
        } catch (ParseException e) {
            logger.error("fecha invalidass");
            return "salidaPorDiaResponse";
        }
        return "salidaPorDiaResponse";
    }

    @RequestMapping(value = "/reporteFolioSalidares", method = RequestMethod.POST)
    public String reporteSalidaPorFolio(String folioSalida, Model model) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String now = formatter.format(date);
        List<SalidasDetalle> salidasDetalles = this.salidasDetalleManager.findSalidaByFolioSalida(Integer.parseInt(folioSalida));
        model.addAttribute("folioSalida",folioSalida);
        model.addAttribute("now", now);
        model.addAttribute("salidasDetalle",salidasDetalles);
        model.addAttribute("cliente", this.clienteManager.getByIdCliente(Integer.parseInt(salidasDetalles.get(0).getIdCliente())));
        return "salidaFolioResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteSalidaPorFechasres", method = RequestMethod.POST)
    public String reporteSalidaPorFechasres(Model model, String fechaInicio, String fechaFin) {
    	try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicio);
            final Date fechaFinDate = formatter.parse(fechaFin);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechas(fechaInicioDate, fechaFinDate);
            model.addAttribute("conteoPorFechas", salida);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
        } catch (ParseException e) {
            logger.error(" error e "+e);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            return "salidaPorFechasResponse";
        }
        return "salidaPorFechasResponse";
    }

    @RequestMapping(value = "/reporteSalidaPorFechasConcentradores", method = RequestMethod.POST)
    public String reporteSalidaPorFechasConcentradores(Model model, String fechaInicio, String fechaFin) {
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicio);
            final Date fechaFinDate = formatter.parse(fechaFin);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechasConcentrado(fechaInicioDate, fechaFinDate);
            model.addAttribute("conteoPorFechas", salida);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
        } catch (ParseException e) {
            logger.error(" error e "+e);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            return "salidaPorFechasConcentradoResponse";
        }
        return "salidaPorFechasConcentradoResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteSalidaPorFechasClres", method = RequestMethod.POST)
    public String reporteSalidaPorFechasClres(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
    	try {
    		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechasCliente(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("conteoPorFechasCliente", salida);
            model.addAttribute("clienteDatos", clienteDatos);
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            return "salidaPorFechasClienteResponse";
        }
        return "salidaPorFechasClienteResponse";
    }

    @RequestMapping(value = "/reporteSalidaPorFechasClBachocores", method = RequestMethod.POST)
    public String reporteSalidaPorFechasClBachocores(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<BsalidasDetalle> salida = this.bsalidasDetalleManager.getFechasID(fechaInicioDate, fechaFinDate ,idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("conteoPorFechasCliente", salida);
            model.addAttribute("clienteDatos", clienteDatos);
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
        } catch (ParseException e) {
            logger.error("fecha invalida" + e.getCause() +e.getMessage());
            return "salidaPorFechasClienteBachocoResponse";
        }
        return "salidaPorFechasClienteBachocoResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteSalidaPorFechasClDetres", method = RequestMethod.POST)
    public String reporteSalidaPorFechasClDetres(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
    	try {
    		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechasClienteDetalle(fechaInicioDate, fechaFinDate, idCliente);
            final Object salidaTotal = this.salidasDetalleManager.getKilosByFechasClienteDetalleTotal(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("conteoPorFechasClienteDetalle", salida);
            model.addAttribute("conteoPorFechasClienteDetalleTotal", salidaTotal);
            model.addAttribute("clienteDatos", clienteDatos);
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            return "salidaPorFechasClienteDetalleResponse";
        }
        return "salidaPorFechasClienteDetalleResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteSalidaPorCamarares", method = RequestMethod.POST)
    public String reporteEntradaPorCamarares(Model model, String fechaInicioCliente, String fechaFinCliente, String idCamara) {
    	try {
    		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            if(idCamara.trim().equalsIgnoreCase("")) {
                final List<Object[]> salida = this.salidasDetalleManager.getReporteByCamaraVacia(fechaInicioDate, fechaFinDate);
                model.addAttribute("conteoCamara", salida);
                model.addAttribute("fechaInicio", fechaInicioCliente);
                model.addAttribute("fechaFin", fechaFinCliente);
                model.addAttribute("idCamara", idCamara);
                model.addAttribute("Camara", "TODAS LAS CAMARAS");
            }else {
                final List<Object[]> salida = this.salidasDetalleManager.getReporteByCamara(fechaInicioDate, fechaFinDate, idCamara);
                model.addAttribute("conteoCamara", salida);
                model.addAttribute("fechaInicio", fechaInicioCliente);
                model.addAttribute("fechaFin", fechaFinCliente);
                model.addAttribute("idCamara", idCamara);
            }
        } catch (ParseException e) {
            logger.error("fecha invalida");
            return "salidaPorCamaraResponse";
        }
        return "salidaPorCamaraResponse";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/reporteArrastreSaldosres", method = RequestMethod.POST)
    public String reporteArrastreSaldosres(Locale locale,Model model, String reciboEntrada) {
    	final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("now", formattedDate);
		    /*final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
		    final Date fechaFinDate = formatter.parse(fechaFinCliente);
		    
		    Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
					DateFormat.LONG, locale);

			String formattedDate = dateFormat.format(date);
			model.addAttribute("now", formattedDate);
		    */
		    if(reciboEntrada.trim().equalsIgnoreCase("")) {
		        final List<Object[]> salida = this.salidasDetalleManager.getArrastreSaldosDetalle(Integer.parseInt(reciboEntrada));
		        final List<SalidasDetalle> salidaAgrupado = this.salidasDetalleManager.getArrastreSaldosDetalleAgrupado(Integer.parseInt(reciboEntrada));
		        final List<DetallesRd> detalleRdAgrupado = this.detalleRdManager.getArrastreSaldosDetalleRdAgrupado(Integer.parseInt(reciboEntrada));
		        final List<Inventario> inventarioAgrupado = this.inventarioManager.getArrastreSaldosInventarioAgrupado(Integer.parseInt(reciboEntrada));
                /*List<DetallesRd> detallesRds=this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(reciboEntrada));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");*/
                model.addAttribute("arrastreSalida", salida);
		        model.addAttribute("salidaAgrupado", salidaAgrupado);
		        model.addAttribute("detallesRdAgrupado", detalleRdAgrupado);
		        model.addAttribute("inventarioAgrupado", inventarioAgrupado);
		        model.addAttribute("reciboEntrada", reciboEntrada);
                //model.addAttribute("fechaIngreso",format.format(detallesRds.get(0).getFechaCaptura()));
		    }else {
		        final List<Object[]> salida = this.salidasDetalleManager.getArrastreSaldosDetalle(Integer.parseInt(reciboEntrada));
		        final List<SalidasDetalle> salidaAgrupado = this.salidasDetalleManager.getArrastreSaldosDetalleAgrupado(Integer.parseInt(reciboEntrada));
		        final List<DetallesRd> detalleRdAgrupado = this.detalleRdManager.getArrastreSaldosDetalleRdAgrupado(Integer.parseInt(reciboEntrada));
		        final List<Inventario> inventarioAgrupado = this.inventarioManager.getArrastreSaldosInventarioAgrupado(Integer.parseInt(reciboEntrada));
                List<DetallesRd> detallesRds=this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(reciboEntrada));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                model.addAttribute("arrastreSalida", salida);
		        model.addAttribute("salidaAgrupado", salidaAgrupado);
		        model.addAttribute("detallesRdAgrupado", detalleRdAgrupado);
		        model.addAttribute("inventarioAgrupado", inventarioAgrupado);
		        model.addAttribute("reciboEntrada", reciboEntrada);
                model.addAttribute("fechaIngreso", format.format(detallesRds.get(0).getFechaCaptura()));
		    }
        return "salidaArrastreResponse";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/aes_reporteSalidaPorDia", method = RequestMethod.POST)
    public String aes_buscarReporteSalida(String fechaSalida, Model model) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date fechaInicioDate = formatter.parse(fechaSalida);
        final List<Object[]> salida = this.salidasDetalleManager.getKilosByDay(fechaInicioDate);
        model.addAttribute("conteoPorDia", salida);
        model.addAttribute("clientes", clientes);
        model.addAttribute("fechaInicio", fechaSalida);
        model.addAttribute("camaras",camaras);
        return "formReporteSalida";
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes", clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteSalida";
        }
    }

    @RequestMapping(value = "/aes_reporteSalidaPorFechas", method = RequestMethod.POST)
    public String aes_buscarReportePorFechas(Model model, String fechaInicio, String fechaFin) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicio);
            final Date fechaFinDate = formatter.parse(fechaFin);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechas(fechaInicioDate, fechaFinDate);
            model.addAttribute("conteoPorFechas", salida);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes", clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteSalida";
        }
        model.addAttribute("clientes", clientes);
        model.addAttribute("camaras",camaras);
        return "formReporteSalida";
    }

    @RequestMapping(value = "/aes_reporteSalidaPorFechaCliente", method = RequestMethod.POST)
    public String aes_reporteSalidaPorFechaCliente(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechasCliente(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            model.addAttribute("conteoPorFechasCliente", salida);
            model.addAttribute("clienteDatos", clienteDatos);
            model.addAttribute("fechaInicio", fechaFinCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteSalida";
        }
        return "formReporteSalida";
    }

    @RequestMapping(value = "/aes_reporteSalidaPorFechaClienteDetalle", method = RequestMethod.POST)
    public String aes_reporteSalidaPorFechaClienteDetalle(Model model, String fechaInicioCliente, String fechaFinCliente, String idCliente) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            final List<Object[]> salida = this.salidasDetalleManager.getKilosByFechasClienteDetalle(fechaInicioDate, fechaFinDate, idCliente);
            final Object salidaTotal = this.salidasDetalleManager.getKilosByFechasClienteDetalleTotal(fechaInicioDate, fechaFinDate, idCliente);
            final Clientes clienteDatos = this.clienteManager.getByIdCliente(Integer.parseInt(idCliente));
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            model.addAttribute("conteoPorFechasClienteDetalle", salida);
            model.addAttribute("conteoPorFechasClienteDetalleTotal", salidaTotal);
            model.addAttribute("clienteDatos", clienteDatos);
            model.addAttribute("fechaInicio", fechaInicioCliente);
            model.addAttribute("fechaFin", fechaFinCliente);
            model.addAttribute("idCliente", idCliente);
        } catch (ParseException e) {
            logger.error("fecha invalida");
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);
            return "formReporteSalida";
        }
        return "formReporteSalida";
    }

    @RequestMapping(value = "/aes_reporteSalidaPorCamara", method = RequestMethod.POST)
    public String aes_reporteSalidaPorCamara(Model model, String fechaInicioCliente, String fechaFinCliente, String idCamara) {
        final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            if(idCamara.trim().equalsIgnoreCase("")) {
                final List<Object[]> salida = this.salidasDetalleManager.getReporteByCamaraVacia(fechaInicioDate, fechaFinDate);
                model.addAttribute("conteoCamara", salida);
            }else {
                final List<Object[]> salida = this.salidasDetalleManager.getReporteByCamara(fechaInicioDate, fechaFinDate, idCamara);
                model.addAttribute("conteoCamara", salida);
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
            return "formReporteSalida";
        }
        return "formReporteSalida";
    }
    
    @RequestMapping(value = "/aes_reporteArrastreSaldos", method = RequestMethod.POST)
    public String aes_reporteArrastreSaldos(Locale locale,Model model, String reciboEntrada) {
    	final List<?> clientes = this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE);
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        //try {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
    	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
    			DateFormat.MEDIUM, Utils.REGION_MEXICO);

    	String formattedDate = dateFormat.format(date);
    	model.addAttribute("now", formattedDate);
            /*final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date fechaInicioDate = formatter.parse(fechaInicioCliente);
            final Date fechaFinDate = formatter.parse(fechaFinCliente);
            
            Date date = new Date();
        	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
        			DateFormat.LONG, locale);

        	String formattedDate = dateFormat.format(date);
        	model.addAttribute("now", formattedDate);
            */
            if(reciboEntrada.trim().equalsIgnoreCase("")) {
                final List<Object[]> salida = this.salidasDetalleManager.getArrastreSaldosDetalle(Integer.parseInt(reciboEntrada));
                final List<SalidasDetalle> salidaAgrupado = this.salidasDetalleManager.getArrastreSaldosDetalleAgrupado(Integer.parseInt(reciboEntrada));
                final List<DetallesRd> detalleRdAgrupado = this.detalleRdManager.getArrastreSaldosDetalleRdAgrupado(Integer.parseInt(reciboEntrada));
                final List<Inventario> inventarioAgrupado = this.inventarioManager.getArrastreSaldosInventarioAgrupado(Integer.parseInt(reciboEntrada));
                model.addAttribute("arrastreSalida", salida);
                model.addAttribute("salidaAgrupado", salidaAgrupado);
                model.addAttribute("detallesRdAgrupado", detalleRdAgrupado);
                model.addAttribute("inventarioAgrupado", inventarioAgrupado);
                model.addAttribute("reciboEntrada", reciboEntrada);
            }else {
                final List<Object[]> salida = this.salidasDetalleManager.getArrastreSaldosDetalle(Integer.parseInt(reciboEntrada));
                final List<SalidasDetalle> salidaAgrupado = this.salidasDetalleManager.getArrastreSaldosDetalleAgrupado(Integer.parseInt(reciboEntrada));
                final List<DetallesRd> detalleRdAgrupado = this.detalleRdManager.getArrastreSaldosDetalleRdAgrupado(Integer.parseInt(reciboEntrada));
                final List<Inventario> inventarioAgrupado = this.inventarioManager.getArrastreSaldosInventarioAgrupado(Integer.parseInt(reciboEntrada));
                model.addAttribute("arrastreSalida", salida);
                model.addAttribute("salidaAgrupado", salidaAgrupado);
                model.addAttribute("detallesRdAgrupado", detalleRdAgrupado);
                model.addAttribute("inventarioAgrupado", inventarioAgrupado);
                model.addAttribute("reciboEntrada", reciboEntrada);
                
            }
            model.addAttribute("clientes",clientes);
            model.addAttribute("camaras",camaras);

        /*}catch(ParseException e){
            logger.error("fecha invalida");
            //model.addAttribute("clientes",clientes);
            model.addAttribute("consecutivo",consecutivo);
            return "formReporteSalida";
        }*/
        return "formReporteSalida";
    }

}
