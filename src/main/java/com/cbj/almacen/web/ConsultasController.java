package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ConsultasController {

    private static final Logger logger = LoggerFactory
            .getLogger(ConsultasController.class);

    @Autowired
    private ConsultasManager consultasManager;
    @Autowired
    private RegEntradasManager regEntradasManager;
    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ConveniosManager conveniosManager;
    @Autowired
    private UsuarioVehiculoManager usuarioVehiculoManager;
    @Autowired
    private ClienteManager clienteManager;
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/menuConsultas", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Menu Consultas", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "menuConsultas";
    }

    @RequestMapping(value = "/alm_consultaIngresos", method = RequestMethod.GET)
    public String consultaIngresos(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_consultaIngresos", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
        model.addAttribute("rdvehiculo", this.consultasManager.getVehiculoRDSinCapturar());
        return "alm_consultaIngresos";
    }

    @RequestMapping(value = "/alm_consultaIngresos", method = RequestMethod.POST)
    public String onSubmit(String idConsulta, Model model) {

        if (idConsulta != null) {
            Integer id = new Integer(idConsulta);
            model.addAttribute("vistaIngresodetalle", this.consultasManager.getIngreso(id));
            UserDetails userDetails =
                    (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UsuarioVehiculo usuarioVehiculo = new UsuarioVehiculo();
            usuarioVehiculo.setIdVehiculo(id);
            usuarioVehiculo.setUsername(userDetails.getUsername());
            UsuarioVehiculo usuarioVehiculoConsulta = this.usuarioVehiculoManager.getByVehiculo(usuarioVehiculo);
            if (usuarioVehiculoConsulta.getUsername() == null || usuarioVehiculoConsulta.getUsername().equalsIgnoreCase("")) {
                Calendar cal = Calendar.getInstance();
                usuarioVehiculo.setHabilitado(true);
                usuarioVehiculo.setFechaRegistro(cal.getTime());
                this.usuarioVehiculoManager.registraSeguimiento(usuarioVehiculo);
                return "consultaIngresosDetalle";
            } else if (usuarioVehiculoConsulta.getUsername() != null && usuarioVehiculoConsulta.getUsername().equalsIgnoreCase(usuarioVehiculo.getUsername())) {
                return "consultaIngresosDetalle";
            } else {
                model.addAttribute("registrado", "registrado");
                model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
                return "alm_consultaIngresos";
            }
        } else {
            model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusSalida("1"));
            return "alm_consultaIngresos";
        }
    }
        @RequestMapping(value = "/alm_consultaSalidas", method = RequestMethod.GET)
        public String consultaSalidas (Locale locale, Model model){
            logger.info("Menu Consultas/consultaSalidas", locale);

            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, locale);

            String formattedDate = dateFormat.format(date);
            model.addAttribute("now", formattedDate);
            model.addAttribute("salidas", this.consultasManager.getEntradasByStatusSalida("1"));
            return "alm_consultaSalidas";
        }

        @RequestMapping(value = "/alm_consultaSalidas", method = RequestMethod.POST)
        public String onSubmitSalidas (String idConsulta, Model model){

            if (idConsulta != null) {
                Integer id = new Integer(idConsulta);
                model.addAttribute("vistaSalidadetalle", this.consultasManager.getIngreso(id));
                UserDetails userDetails =
                        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UsuarioVehiculo usuarioVehiculo = new UsuarioVehiculo();
                usuarioVehiculo.setIdVehiculo(id);
                usuarioVehiculo.setUsername(userDetails.getUsername());
                UsuarioVehiculo usuarioVehiculoConsulta = this.usuarioVehiculoManager.getByVehiculo(usuarioVehiculo);
                if (usuarioVehiculoConsulta.getUsername() == null || usuarioVehiculoConsulta.getUsername().equalsIgnoreCase("")) {
                    Calendar cal = Calendar.getInstance();
                    usuarioVehiculo.setHabilitado(true);
                    usuarioVehiculo.setFechaRegistro(cal.getTime());
                    this.usuarioVehiculoManager.registraSeguimiento(usuarioVehiculo);
                    return "consultaSalidaDetalle";
                } else if (usuarioVehiculoConsulta.getUsername() != null && usuarioVehiculoConsulta.getUsername().equalsIgnoreCase(usuarioVehiculo.getUsername())) {
                    return "consultaSalidaDetalle";
                } else {
                    model.addAttribute("registrado", "registrado");
                    model.addAttribute("salidas", this.consultasManager.getEntradasByStatusSalida("1"));
                    return "alm_consultaSalidas";
                }
            } else {
                model.addAttribute("salidas", this.consultasManager.getEntradasByStatusSalida("1"));
                return "alm_consultaSalidas";
            }
        }

        @RequestMapping(value = "/insRegDetalle", method = RequestMethod.POST)
        public String onSubmit (@Valid VistaIngresoDetalle vistaIngresoDetalle,
                BindingResult result, Model model){

            if (result.hasErrors()) {
                logger.error("Error de validaciones de vistaIngresoDetalle " + result.getAllErrors().size());
                for (ObjectError error : result.getAllErrors()) {
                    logger.error("ERROR -> " + error.toString());
                }

                return "error";
            }
            int consecutivo = 0;
            RegEntradas regEntradas = regEntradasManager.consultaRegEntradaByIdIngresoVehiculo(vistaIngresoDetalle.getIdIngresoVehiculo());

            if (regEntradas == null || regEntradas.getConsecutivo() == null) {
                regEntradas = new RegEntradas();
                regEntradas.setIdIngresoVehiculo(vistaIngresoDetalle.getIdIngresoVehiculo());
                regEntradas.setIdCliente(vistaIngresoDetalle.getIdCliente());
                regEntradas.setVehiculo(vistaIngresoDetalle.getVehiculo());
                regEntradas.setTransporte(vistaIngresoDetalle.getTransporte());
                regEntradas.setAnden(vistaIngresoDetalle.getNombrePuerta());

                consecutivo = regEntradasManager.registraRegEntrada(regEntradas);
                logger.info("Consecutivo " + consecutivo);
            } else {
                consecutivo = regEntradas.getConsecutivo();
            }

            int idCliente = Integer.parseInt(vistaIngresoDetalle.getIdCliente());
            logger.info("idCliente " + idCliente);
            model.addAttribute("Convenios", conveniosManager.getByClientes(idCliente));
            model.addAttribute("consecutivoObtenido", consecutivo);
            model.addAttribute("regEntradas", regEntradas);
            model.addAttribute("detallesRd", new DetallesRd());
            model.addAttribute("vistaIngresoDetalle", vistaIngresoDetalle);
            model.addAttribute("embalajes", this.catalogoManager.getCatalogo(TipoCatalogo.EMBALAJES));
            model.addAttribute("regEntradasAlmacenadas", detalleRdManager.getAllByConsecutivo(consecutivo));

            return "capturaIngresos";
        }

        @RequestMapping(value = "/alm_consultaEntradasImp", method = RequestMethod.GET)
        public String consultaEntradasImp (Locale locale, Model model){
            logger.info("Menu Impresiones/alm_consultaEntradasImp", locale);

            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, locale);

            String formattedDate = dateFormat.format(date);
            model.addAttribute("now", formattedDate);
            model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
            return "consultaEntradasImp";
        }

        @RequestMapping(value = "/alm_consultaSalidasImp", method = RequestMethod.GET)
        public String consultaSalidasImp (Locale locale, Model model){
            logger.info("Menu Impresiones/alm_consultaSalidasImp", locale);

            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, locale);

            String formattedDate = dateFormat.format(date);
            model.addAttribute("now", formattedDate);
            model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
            return "consultaSalidasImp";
        }
        @RequestMapping(value = "/consultarBloqueados",method = RequestMethod.POST)
        public String consultarBloequeados(Locale locale, Model model){
            List<Clientes> clientes = this.clienteManager.getByEstatusSalida();
            model.addAttribute("clientes",clientes);
            return "clientesBloqueados";
        }

    }