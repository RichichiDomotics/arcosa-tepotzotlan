package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
import com.cbj.almacen.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ClientesController {

    private static final Logger logger = LoggerFactory
	    .getLogger(ClientesController.class);

    @Autowired
    private ConsultasManager consultasManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private ServiciosManager serviciosManager;

    @Autowired
    private ProspectoManager prospectoManager;
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private ConveniosManager conveniosManager;
    @Autowired
    private EntradasManager entradasManager;
    @Autowired
    private InventarioManager inventarioManager;
    @Autowired
    private DetalleFacturacionManager detalleFacturacionManager;
    @Autowired
    private AvisosHistorialManager avisosHistorialManager;

    @Autowired
    private MensajesManager mensajesManager;
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/formIngresoCliente", method = RequestMethod.GET)
    public String formIngresoCliente(Locale locale, Model model){
        logger.info("Formulario para ingresar un cliente");
        return "formaIngresoCliente";
    }
    @RequestMapping(value = "/menuClientes", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
	logger.info("Menu Clientes", locale);

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	return "menuClientes";
    }

    @RequestMapping(value = "/consultaClientes", method = RequestMethod.GET)
    public String consultaClientes(Locale locale, Model model) {
	logger.info("Menu Consultas/consultaClientes", locale);

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	model.addAttribute("clientes", this.consultasManager.getClientes());
	return "consultaClientes";
    }
    
    @RequestMapping(value = "/consultaClientes", method = RequestMethod.POST)
    public String consultaDetalleCliente(String idCliente, Model model) {
    	
    	Integer id = new Integer(idCliente);
    	model.addAttribute("detalle", this.consultasManager.getCliente(id));
    	   	
    	return "consultaClientesDetalle";
    }
    
    @RequestMapping(value = "/consultaClientesDetalle", method = RequestMethod.POST)
    public String consultaModificaCliente(String idCliente, Model model) {
    	Integer id = new Integer(idCliente);
        Convenios convenio = this.conveniosManager.getByCliente(id);

        logger.info("convenio" +String.valueOf(convenio));
    	model.addAttribute("detalle", this.consultasManager.getCliente(id));
        model.addAttribute("convenio",convenio );

    	return "consultaClientesDetalle";
    }
    
    @RequestMapping(value = "/ae_formaIngresoCliente", method = RequestMethod.GET)
    public String formaIngresoCliente(Model model) {
            model.addAttribute("clientes",this.clienteManager.getClientesAll());
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        MediUser mediUser = new MediUser(user.getUsername(),"",true,false,false,false,authorities,usurec.getIdEjecutivo());

        //AQUI YA SE TENDRIAN LOS VALORES EN EL OBJENTO NUEVO DE usuario

        logger.info(String.valueOf(mediUser)+"media user*******");
            model.addAttribute("idEjecutivo", mediUser.getIdEjecutivo());

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

//        model.addAttribute("totalAvisos", totalAvisosObj.get(0));
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "tablaClientes";
    }

    public Clientes findCliente(int idCliente) {
        return this.clienteManager.getByIdCliente(idCliente);
    }

    @RequestMapping(value = "/ae_insertarCliente", method = RequestMethod.POST)
    public String ae_insertarCliente(@Valid Clientes cliente, int idProspecto, Convenios convenio,
                                     BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "error";
        }
        logger.info("todo ok "+cliente.getCp());
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final String fecha = formatFecha.format(date);
        cliente.setFingreso(fecha);
        cliente.setSalidaProducto("APROBADA");

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cliente.setCapturadoPor(user.getUsername());
        Clientes clienteResponse = clienteManager.insertCliente(cliente);
        convenio.setIdCliente(clienteResponse.getIdCliente());
        Convenios convenioResponse = conveniosManager.insertConvenio(convenio);
        logger.info("nombre Cliente"+cliente.getNombreCliente());
        Prospecto prospecto = prospectoManager.getProspectoByIdNom(idProspecto,cliente.getNombreCliente());
        prospecto.setActivo("1");
        final boolean respuestaup = prospectoManager.updateProspecto(prospecto);
        if (clienteResponse.getIdCliente()>0) {
            model.addAttribute("nombreCliente",clienteResponse.getNombreCliente());
            model.addAttribute("idCliente",clienteResponse.getIdCliente());
            model.addAttribute("servicios",serviciosManager.getCboServicios());
            model.addAttribute("serviciosRegistrados",serviciosManager.getByActivoByIdCliente(clienteResponse.getIdCliente().toString()));
            return "capturaServicios";
        }else{ return "error";}
    }
    
    @RequestMapping(value = "/ae_insertarServicio", method = RequestMethod.POST)
    public String ae_insertarServicio(@Valid Servicios servicio,
                                     BindingResult result) {
        logger.info("fue al metodo Servicio ");
        if (result.hasErrors()) {
            return "error";
        }
       // logger.info("todo ok "+servicio.getIdClienteServicio());
       // Servicios servicioResponse = serviciosManager.insertServicio(servicio);
       // if (servicioResponse.getIdClienteServicio()>0) 
       // 	return "redirect:exito";
        else return "error";
    }

    @RequestMapping(value = "/exitoCliente", method = RequestMethod.POST)
    public String exitoCliente(Model model) {

        return "exitoCliente";
    }

    @RequestMapping(value = "/consultaDetalleCliente", method = RequestMethod.POST)
    public String consultaDetalleCliente(int idCliente, String nombreCliente, Model model) {
        //Integer id = new Integer(idCliente);
        Clientes cliente = new Clientes();
        //Convenios convenios = new Convenios();
        logger.info("Parametros " + idCliente + " ** " + nombreCliente);
        cliente = this.clienteManager.getByIdCliente(idCliente);
        logger.info("Antes de traer el convenio ");
        //convenios = this.conveniosManager.getByCliente(idCliente);
        List<Convenios>convenios = this.conveniosManager.getByClientes(idCliente);
        logger.info("fue al metodo Servicio " + cliente);
        logger.info("datos del convenio " + convenios);
        model.addAttribute("detalle", cliente);
        model.addAttribute("convenios", convenios);
        model.addAttribute("serviciosRegistrados",serviciosManager.getByActivoByIdCliente(Integer.toString(idCliente)));
        return "consultaDetalleCliente";
    }

    @RequestMapping(value = "/formaConsultaClienteCompleto", method = RequestMethod.GET)
    public String formaConsultaClienteCompleto(Locale locale, Model model) {
        logger.info("formaConsultaClienteCompleto", locale);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);


        model.addAttribute("clientes", this.entradasManager.getCatClientes());

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

//        model.addAttribute("totalAvisos", totalAvisosObj.get(0));
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaConsultaClienteCompleto";
    }

    @RequestMapping(value = "/formaConsultaClienteFichaTecnica", method = RequestMethod.GET)
    public String formaConsultaClienteFichaTecnica(Locale locale, Model model) {
        logger.info("formaConsultaClienteCompleto", locale);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);


        model.addAttribute("clientes", this.entradasManager.getCatClientes());

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

//        model.addAttribute("totalAvisos", totalAvisosObj.get(0));
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaConsultaClienteFichaTecnica";
    }


    @RequestMapping(value = "/consultaDetalleClienteCompleto", method = RequestMethod.POST)
    public String consultaDetalleClienteCompleto(int idCliente, Model model) {
        //Integer id = new Integer(idCliente);
        Clientes cliente = new Clientes();
        Convenios convenios = new Convenios();
        logger.info("Parametros " + idCliente + " ** ");
        cliente = this.clienteManager.getByIdCliente(idCliente);
        logger.info("Antes de traer el convenio ");
        convenios = this.conveniosManager.getByCliente(idCliente);
        logger.info("fue al metodo Servicio " + cliente);
        logger.info("datos del convenio " + convenios);
        model.addAttribute("detalle", cliente);
        model.addAttribute("convenios", convenios);
        model.addAttribute("reporte", this.inventarioManager.getInventarioReporte("", "", Integer.toString(idCliente), "", ""));
        model.addAttribute("facturaciones", this.detalleFacturacionManager.getIdClienteDetalle(idCliente));
        model.addAttribute("serviciosRegistrados",serviciosManager.getByActivoByIdCliente(Integer.toString(idCliente)));
        return "consultaDetalleClienteCompleto";
    }

    @RequestMapping(value = "/consultaDetalleClienteFichaTecnica", method = RequestMethod.POST)
    public String consultaDetalleClienteFichaTecnica(int idCliente, Model model) {
        //Integer id = new Integer(idCliente);
        Clientes cliente = new Clientes();
        Convenios convenios = new Convenios();
        logger.info("Parametros " + idCliente + " ** ");
        cliente = this.clienteManager.getByIdCliente(idCliente);
        logger.info("Antes de traer el convenio ");
        convenios = this.conveniosManager.getByCliente(idCliente);
        logger.info("fue al metodo Servicio " + cliente);
        logger.info("datos del convenio " + convenios);
        model.addAttribute("detalle", cliente);
        model.addAttribute("convenios", convenios);
        model.addAttribute("reporte", this.inventarioManager.getInventarioReporte("", "", Integer.toString(idCliente), "", ""));
        model.addAttribute("facturaciones", this.detalleFacturacionManager.getIdClienteDetalle(idCliente));
        model.addAttribute("serviciosRegistrados",serviciosManager.getByActivoByIdCliente(Integer.toString(idCliente)));
        return "consultaDetalleClienteFichaTecnica";
    }


    @RequestMapping(value = "/consultaDetalleFactura", method = RequestMethod.POST)
    public String consultaDetalleFacturaCompleto(String nofactura, Model model) {
        //Integer id = new Integer(idCliente);
        logger.info("Detalle facturacion " + nofactura + " ** ");
        List<Object> facturas = this.detalleFacturacionManager.getIdDetalleFactura(nofactura);
        logger.info("Detalle facturacion " + facturas.size() + " ** ");
        model.addAttribute("detalleFacturas", facturas);
        return "consultaDetalleFactura";
    }



    @RequestMapping(value = "/insertaServicio", method = RequestMethod.POST)
    public String insertaServicio(@Valid Servicios servicio,
                                      BindingResult result,Model model) {
        logger.info("fue al metodo Ingresa Servicio ");
        //if (result.hasErrors()) {
        //    return "error";
        //}
        // logger.info("todo ok "+servicio.getIdClienteServicio());
         boolean servicioResponse = serviciosManager.insertServicio(servicio);
        model.addAttribute("serviciosRegistrados",serviciosManager.getByActivoByIdCliente(servicio.getIdCliente().toString()));
         if (servicioResponse)
         	return "tablaServicios";
        else return "error";
    }
}