package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class EntradasController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private EntradasManager entradasManager;
    @Autowired
    private ContadoresManager contadoresManager;
    @Autowired
    private ConsultasManager consultasManager;
    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private RegEntradasManager regEntradasManager;
    @Autowired
    private InventarioManager inventarioManager;
    @Autowired
    private VehiculoManager vehiculoManager;
    @Autowired
    private PdfEntradasManager pdfEntradasManager;
    @Autowired
    private CatalogoManager catalogoManager;
    
    @Autowired
    private ConveniosManager conveniosManager;
    
    @Autowired
    private ServiciosManager serviciosManager;
    
    @Autowired
    private RetemesManager retemesManager;

    @Autowired
    private ClienteManager clientesManager;

    @Autowired
    private DetalleFacturacionManager detalleFacturacionManager;

    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private ImprimirEntradasManager imprimirEntradasManager;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/vehiculoRdSinCapturar", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, headers = {"Accept=text/xml, application/json"}, method = RequestMethod.GET)
    public @ResponseBody List<Object[]> vehiculoRdSinCapturar(HttpServletResponse response){
        return this.consultasManager.getVehiculoRDSinCapturar();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/generarrdarla", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, headers = {"Accept=text/xml, application/json"}, method = RequestMethod.POST)
    public @ResponseBody RegEntradas generarrdarla(Integer idCliente, Integer idVehiculo){
        RegEntradas regEntradas=new RegEntradas();
        regEntradas.setIdCliente(String.valueOf(idCliente));
        regEntradas.setIdIngresoVehiculo(idVehiculo);
        RegEntradas ingreso = this.regEntradasManager.getEntradaByConsecutivo(this.regEntradasManager.registraRegEntrada(regEntradas)).get(0);
        return ingreso;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/arlaJson", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, headers = {"Accept=text/xml, application/json"}, method = RequestMethod.POST)
    public @ResponseBody String arlaJson(@RequestBody JsonArla jsonArla, HttpServletRequest request){
        logger.info("Accion para recuperar un json");
        DetallesRd detallesRd = new DetallesRd();
        Inventario inventario = new Inventario();
        for (int i=0; i<Integer.parseInt(jsonArla.getCantidad());i++){
               
        }
        return "20";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/cuotasJson", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, headers = {"Accept=text/xml, application/json"},  method = RequestMethod.GET)
    public @ResponseBody List<Convenios> cuotasJson(HttpServletResponse response){
        String clientes = "550, 551, 552, 606, 650, 1550, 1551, 1650, 1651, 2550";
        List<Convenios> conveniosList = new ArrayList<Convenios>();
        conveniosList = this.conveniosManager.getArla(clientes);
        return conveniosList;
    }

    @RequestMapping(value = "/formEntradas", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
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

        System.out.println("date:" + fecha);
        System.out.println("time:" + hora);

        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);
        model.addAttribute("clientes", this.entradasManager.getCatClientes());
        model.addAttribute("plantas", this.entradasManager.getCatPlantas());
        model.addAttribute("recibos", this.entradasManager.getCatRecibos());
        model.addAttribute("puertas", this.entradasManager.getCatPuertas());
        model.addAttribute("maniobras", this.entradasManager.getCatManiobras());
        model.addAttribute("flejes", this.entradasManager.getCatFlejes());
        model.addAttribute("vehiculos", this.entradasManager.getCatVehiculos());
        model.addAttribute("transportes", this.entradasManager.getCatTransportes());

        return "formEntradas";
    }

    @RequestMapping(value = "/formatoIngreso", method = RequestMethod.GET)
    public String formatoIngreso(Locale locale, Model model){
        logger.info("formato para los ingresos");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }
        locale= new Locale("es_ES");
        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getUsername();
        Date now = new Date();
        final SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        final SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es","MX"));
        String hora = formatHora.format(now);
        String fecha = formateador.format(now);
        model.addAttribute("usuario",claveUsuario);
        model.addAttribute("time",hora);
        model.addAttribute("fecha",fecha);
        return "formatoingreso";
    }


    @RequestMapping(value = "/capturaIngresos", method = RequestMethod.POST)
    public String ingreso(String idConsulta, String cliente, String chofer, String idCliente, Locale locale, Model model) {

        logger.info("Forma de Captura de Ingresos", idConsulta);
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
        model.addAttribute("cliente", cliente);
        model.addAttribute("chofer", chofer);
        model.addAttribute("idConsulta", idConsulta);
        model.addAttribute("idCliente", idCliente);

        return "capturaIngresos";
    }

    @RequestMapping(value = "/getcbopuertas", method = RequestMethod.POST)
    public String onSubmitgetpuertas(String Planta, Model model) {
        System.out.println("*************" + Planta);
        //String Planta = "P2";
        if (Planta == "P1") {
            Planta = "P1";
        }

        if (Planta == "P2") {
            Planta = "P2";
        }

        if (Planta == "") {
            Planta = "P%";
        }

        model.addAttribute("puertas", this.entradasManager.getCatPuertaPlanta(Planta));


        return "cbopuertas";
    }


    @RequestMapping(value = "/insDetalle", method = RequestMethod.POST)
    public String onSubmitDetalle(@Valid DetallesRd detallesRd,
                                  BindingResult result, Model model) {


        boolean respInventario = false;
        Integer idIngresVehiculo = detallesRd.getIdIngresoVehiculo();
        Integer consecutivo = detallesRd.getConsecutivo();
        String idCliente = detallesRd.getIdCliente();
        if (result.hasErrors()) {
            logger.error("Error al registrar Detalle "+result.getAllErrors().size());
            for(ObjectError error:result.getAllErrors()){
                logger.error("ERROR -> "+error.toString());
            }
            final RegEntradas regEntradasConsulta = regEntradasManager.consultaRegEntradaByIdIngresoVehiculo(detallesRd.getIdIngresoVehiculo());
            model.addAttribute("regEntradas", regEntradasConsulta);
            model.addAttribute("detallesRd", detallesRd);
            model.addAttribute("consecutivoObtenido", regEntradasConsulta.getConsecutivo());
            model.addAttribute("regEntradasAlmacenadas", detalleRdManager.getAllByConsecutivo(regEntradasConsulta.getConsecutivo()));
            model.addAttribute("vistaIngresoDetalle", this.consultasManager.getIngreso(regEntradasConsulta.getIdIngresoVehiculo()));
            return "capturaIngresos";
        }
        
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        detallesRd.setFechaCaptura(date);
        detallesRd.setHoraCaptura(hora);
        if(detallesRd.getCamara().equals("T1") || detallesRd.getCamara().equals("T2") || detallesRd.getCamara().equals("T3")){
            detallesRd.setServicio("80");
        }else{
            detallesRd.setServicio("90");
        }


        boolean respDetalleRd = detalleRdManager.setRegistroEntrada(detallesRd);
        if (respDetalleRd) {
            
            Inventario inventario = new Inventario();
            inventario.setConsecutivo(detallesRd.getConsecutivo());
            inventario.setRenglon(detallesRd.getRenglon());
            inventario.setServicio(detallesRd.getServicio());
            inventario.setLote(detallesRd.getLote());
            inventario.setTunel(detallesRd.getTunel());
            inventario.setCamara(detallesRd.getCamara());
            inventario.setPesou(detallesRd.getPesou());
            inventario.setAltura(detallesRd.getAltura());
            inventario.setIdCliente(detallesRd.getIdCliente());
            inventario.setFechaCaptura(fecha);
            inventario.setHoraCaptura(hora);
            //inventario.setAplicado(detallesRd.getAplicado().toString());
            inventario.setBultos(detallesRd.getBultos());
            inventario.setCantidadInventario(detallesRd.getCantidad());
            inventario.setCaducidad(detallesRd.getCaducidad());
            inventario.setEmbalaje(detallesRd.getEmbalaje());
            inventario.setValorTotal(detallesRd.getValorTotal());
            inventario.setClaveProducto(detallesRd.getClaveProducto());
            inventario.setDescripcion(detallesRd.getDescripcion());
            inventario.setCaducidad(detallesRd.getCaducidad());
            inventario.setMarca(detallesRd.getMarca());
            inventario.setPesoBruto(detallesRd.getPesoBruto().toString());
            inventario.setObservaciones(detallesRd.getObservaciones());
            inventario.setPedimento(detallesRd.getImpedimento());


            respInventario = inventarioManager.setInventario(inventario);
        }

        final RegEntradas regEntradasConsulta = regEntradasManager.consultaRegEntradaByIdIngresoVehiculo(detallesRd.getIdIngresoVehiculo());
        model.addAttribute("regEntradasAlmacenadas", detalleRdManager.getAllByConsecutivo(detallesRd.getConsecutivo()));
        model.addAttribute("vistaIngresoDetalle", this.consultasManager.getIngreso(regEntradasConsulta.getIdIngresoVehiculo()));
        model.addAttribute("consecutivoObtenido", consecutivo);
        final DetallesRd newDetallesRd = new DetallesRd();
        newDetallesRd.setIdIngresoVehiculo(idIngresVehiculo);
        newDetallesRd.setConsecutivo(consecutivo);
        newDetallesRd.setObservaciones(detallesRd.getObservaciones());
        newDetallesRd.setIdCliente(idCliente);
        model.addAttribute("detallesRd", newDetallesRd);
        return "tablaRegDetalle";

    }

    @RequestMapping(value = "/insDetalleFin", method = RequestMethod.POST)
    public String onSubmitDetalleFin(Locale locale,@Valid DetallesRd detallesRd,
                                     BindingResult result, Model model,@RequestParam(value = "bandera")int bandera) {
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        double totalKsEntradas = 0;
        final Vehiculo vehiculo = vehiculoManager.getIngresaVehiculoById(detallesRd.getIdIngresoVehiculo());
        vehiculo.setStatus("2");
        vehiculoManager.updateIngresaVehiculo(vehiculo);
        final RegEntradas regEntradasConsulta = regEntradasManager.consultaRegEntradaByIdIngresoVehiculo(detallesRd.getIdIngresoVehiculo());
        final List<DetallesRd> detallesRdList = detalleRdManager.getAllByConsecutivo(regEntradasConsulta.getConsecutivo());
        for (DetallesRd detallesRdUpdate : detallesRdList) {
            detallesRdUpdate.setPosiciones(detallesRd.getPosiciones());
            detallesRdUpdate.setEstibas(detallesRd.getEstibas());
            detallesRdUpdate.setAltura(detallesRd.getAltura());
            detallesRdUpdate.setAlmaen(detallesRd.getAlmaen());
            detallesRdUpdate.setPlaga(detallesRd.getPlaga());
            detallesRdUpdate.setCalle(detallesRd.getCalle());
            detallesRdUpdate.setFila(detallesRd.getFila());
            detallesRdUpdate.setLibreVidrios(detallesRd.getLibreVidrios());
            detallesRdUpdate.setVehiculoLimpio(detallesRd.getVehiculoLimpio());
            detallesRdUpdate.setVehiculoBuenEstado(detallesRd.getVehiculoBuenEstado());
            detallesRdUpdate.setIncidenciaAnden(detallesRd.getIncidenciaAnden());
            totalKsEntradas += detallesRdUpdate.getPesou()*detallesRdUpdate.getCantidad();
            //detallesRdUpdate.setObservaciones(detallesRd.getObservaciones());
            detalleRdManager.updateRegistroEntrada(detallesRdUpdate);
        }

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);

        System.out.println("date:" + fecha);
        System.out.println("time:" + hora);

        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);

        regEntradasConsulta.setFecha(fecha);
        regEntradasConsulta.setHoras(hora);
        regEntradasConsulta.setHorasAte2(hora);
        logger.info("total de kgs "+totalKsEntradas);
        regEntradasConsulta.setTotalEntrada(totalKsEntradas);
        regEntradasManager.actualizaRegEntrada(regEntradasConsulta);

        pdfEntradasManager.generaPdfEntradas(regEntradasConsulta.getConsecutivo());
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = new Date();
    	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
    			DateFormat.MEDIUM, Utils.REGION_MEXICO);

    	String formattedDate = formatter.format(date);
    	
            /*Contadores FolioRetem = this.contadoresManager.findContador("retemes"); //this.retemesManager.getMaxId();
            Integer FolRetconsecutivo = Integer.parseInt(FolioRetem.getContador())+1;
            FolioRetem.setContador(FolRetconsecutivo.toString());
            this.contadoresManager.updateContadores(FolioRetem);
            logger.info("Folio del Reteme siguiente", FolRetconsecutivo);

            Retemes retemeobj = new Retemes();
            retemeobj.setFolioRemete(FolRetconsecutivo.toString());
            this.retemesManager.setIngresaRetemes(retemeobj);*/
        
        model.addAttribute("now", formattedDate);
    	model.addAttribute("clientes", this.entradasManager.getCatClientes());
        //model.addAttribute("folioRetemes", FolRetconsecutivo.toString());
        model.addAttribute("folioRetemes", Integer.parseInt(this.retemesManager.getMaxId())+1);
        model.addAttribute("user",user.getUsername().toString());
        model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
        model.addAttribute("consecutivo",regEntradasConsulta.getConsecutivo());
        model.addAttribute("cliente",regEntradasConsulta.getIdCliente());
        model.addAttribute("camaras",camaras);
        model.addAttribute("camara",detallesRd.getCamara());
        model.addAttribute("bandera",bandera);
        return "formRetemes";

    }

    /*@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid FormEntrada formEntrada,
                           BindingResult result) {
        System.out.println(formEntrada.toString());
        if (result.hasErrors()) {
            return "error";
        }
        formEntrada.setStatus("1");
        boolean resp = entradasManager.setEntradas(formEntrada);
        if (resp) return "redirect:exito";
        else return "error";

    }*/

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorOperacion(Model model) {
        String mensajeError = "No se inserto correctamente el registro";
        model.addAttribute("message", mensajeError);
        return "error";

    }

    @RequestMapping(value = "/exito", method = RequestMethod.GET)
    public String exitoOperacion(Model model) {
        final String mensajeError = "Se inserto correctamente el registro";
        model.addAttribute("message", mensajeError);
        return "exito";

    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/serviceEntradas", method = RequestMethod.GET)
    public String homews(Locale locale, Model model) {
        logger.info("Ingreso de Vehiculos WS (carga o descarga de producto)", locale);

        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);

        final JSONObject object = new JSONObject();
        try {
            object.put("fecha", fecha);
            object.put("hora", hora);
            object.put("recibos", this.entradasManager.getCatRecibos());
            object.put("clientes", this.entradasManager.getCatClientes());
            object.put("plantas", this.entradasManager.getCatPlantas());
            object.put("puertas", this.entradasManager.getCatPuertas());
            object.put("maniobras", this.entradasManager.getCatManiobras());
            object.put("flejes", this.entradasManager.getCatFlejes());
            object.put("vehiculos", this.entradasManager.getCatVehiculos());
            object.put("transportes", this.entradasManager.getCatTransportes());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        model.addAttribute("objectJson", object);
        return "formEntradasws";
    }

    @RequestMapping(value = "/serviceSaveEntrada", method = RequestMethod.GET)
    public String saveEntradaws(Locale locale, @RequestParam("jsonObj") String jsonObjStr) {
        logger.info("save entrada", locale);
        logger.info("json " + jsonObjStr);
        JSONObject reader = new JSONObject(jsonObjStr);

        FormEntrada formEntrada = new FormEntrada();
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        formEntrada.setFechaEntrada(fecha);
        formEntrada.setHoraEntrada(hora);
        formEntrada.setIdCliente(reader.getInt("idCliente"));
        formEntrada.setIdPlanta(reader.getInt("idPlanta"));
        formEntrada.setIdTipoRecibo(reader.getInt("idTipoRecibo"));
        formEntrada.setIdPuerta(reader.getInt("idPuerta"));
        formEntrada.setIdManiobra(reader.getInt("idManiobra"));
        formEntrada.setIdFleje(reader.getInt("idFleje"));
        formEntrada.setIdVehiculo(reader.getInt("idVehiculo"));
        formEntrada.setIdTransporte(reader.getInt("idTransporte"));
        formEntrada.setNombreOperador(reader.getString("nombreOperador"));
        formEntrada.setPlacasVehiculo(reader.getString("placas"));
        formEntrada.setNombreCiaTransporte(reader.getString("compania"));
        formEntrada.setFleje(reader.getString("fleje"));
        formEntrada.setNumRecibo(reader.getString("recibo"));
        formEntrada.setPermisos(reader.getString("permiso"));

        logger.info(formEntrada.getIdFleje().toString());
        boolean resp = entradasManager.setEntradas(formEntrada);
        if (true) return "exitows";
        else return "errorws";
    }

    @RequestMapping(value = "/menuEntradas", method = RequestMethod.GET)
    public String homeEntradas(Locale locale, Model model) {
        logger.info("Menu Consultas", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "menuEntradas";
    }


    @RequestMapping(value = "/impresionRecibos", method = RequestMethod.GET)
    public String consultaIngresosImpreso(Locale locale, Model model) {
        logger.info("Menu Entradas/impresionRecibos", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ingresos", this.entradasManager.getEntradas());
        return "impresionRecibos";
    }

    @RequestMapping(value = "/impresionRecibos", method = RequestMethod.POST)
    public String onSubmitImpresion(String idConsulta, Model model) {

        Integer id = new Integer(idConsulta);
        model.addAttribute("detalle", this.entradasManager.getIngreso(id));

        return "impresionReciboDetalle";
    }

    @RequestMapping(value = "/consultaEntradaImpresion", method = RequestMethod.POST)
    public String consultaEntradaImpresion(String numeroRd, Model model) {
        logger.info("Forma de Consulta de detalles rd " + numeroRd);

        if (numeroRd.trim().equalsIgnoreCase("")) {
            model.addAttribute("detalle", this.regEntradasManager.getEntradaByActualDateEntrada());
        } else {
            model.addAttribute("detalle", this.regEntradasManager.getEntradaByConsecutivoImpresionEntrada(Integer.parseInt(numeroRd)));
        }


        return "detallesRdEntradaImpresion";
    }

    @RequestMapping(value = "/realizaImpresionEntrada", method = RequestMethod.POST)
    public String realizaImpresionEntrada(String listaids, String numeroRd, Model model) {
        listaids = listaids.substring(1, listaids.length());
        logger.info("INICIA IMPRESION DE ENTRADAS " + listaids.trim());
        String[] sLinea;
        sLinea = listaids.split(",");
        logger.info("arreglo lista de ids " + sLinea.length);
        //Aqui realiza los pdf's y actualiza los datos necesarios 
        for (String registro : sLinea) {
            //pdfEntradasManager.generaPdfEntradas(Integer.parseInt(registro));
            imprimirEntradasManager.imprimirEntrada(Integer.parseInt(registro));
        }


        if (numeroRd.trim().equalsIgnoreCase("")) {
            model.addAttribute("detalle", this.regEntradasManager.getEntradaByActualDateEntrada());
        } else {
            model.addAttribute("detalle", this.regEntradasManager.getEntradaByConsecutivoImpresionEntrada(Integer.parseInt(numeroRd)));
        }

        return "detallesRdEntradaImpresion";
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response) {
        try {
            // get your file as InputStream
            InputStream is = new FileInputStream(fileName);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            logger.info("Error writing file to output stream. Filename was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
    
    @RequestMapping(value = "/formRetemes", method = RequestMethod.GET)
    public String formRetemes(Locale locale, Model model, int bandera) {
        logger.info("Despues de entradas/formRetemes", Utils.REGION_MEXICO);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);
        
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
    	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
    			DateFormat.MEDIUM, Utils.REGION_MEXICO);

    	String formattedDate = formatter.format(date);

        Contadores FolioRetem = this.contadoresManager.findContador("retemes"); //this.retemesManager.getMaxId();
        
        /*if(bandera==0 || bandera==1){
            Integer FolRetconsecutivo = Integer.parseInt(FolioRetem.getContador())+1;
            FolioRetem.setContador(FolRetconsecutivo.toString());
            model.addAttribute("folioRetemes", FolRetconsecutivo.toString());
            logger.info("Folio del Reteme siguiente", FolRetconsecutivo);
        }else{
            Integer FolRetconsecutivo = Integer.parseInt(FolioRetem.getContador());
            FolioRetem.setContador(FolRetconsecutivo.toString());
            model.addAttribute("folioRetemes", FolRetconsecutivo.toString());
            logger.info("Folio del Reteme siguiente", FolRetconsecutivo);
        }
        this.contadoresManager.updateContadores(FolioRetem);*/
        model.addAttribute("folioRetemes", Integer.parseInt(this.retemesManager.getMaxId())+1);
        //logger.info("Folio del Reteme siguiente", FolRetconsecutivo);
        
    	model.addAttribute("camaras",camaras);
    	model.addAttribute("now", formattedDate);
    	model.addAttribute("clientes", this.clientesManager.getClientesAll());

        model.addAttribute("user",user.getUsername().toString());
        model.addAttribute("bandera", bandera);
        return "formRetemes";
    }

    @RequestMapping(value = "/formRetemesTIF", method = RequestMethod.GET)
    public String formRetemesTIF(Locale locale, Model model, int bandera) {
        logger.info("Despues de entradas/formRetemes", Utils.REGION_MEXICO);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        final List<?> camaras = this.catalogoManager.getCatalogo(TipoCatalogo.CAMARAS);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, Utils.REGION_MEXICO);

        String formattedDate = formatter.format(date);

        Contadores FolioRetem = this.contadoresManager.findContador("retemes"); //this.retemesManager.getMaxId();

        /*if(bandera==0 || bandera==1){
            Integer FolRetconsecutivo = Integer.parseInt(FolioRetem.getContador())+1;
            FolioRetem.setContador(FolRetconsecutivo.toString());
            model.addAttribute("folioRetemes", FolRetconsecutivo.toString());
            logger.info("Folio del Reteme siguiente", FolRetconsecutivo);
        }else{
            Integer FolRetconsecutivo = Integer.parseInt(FolioRetem.getContador());
            FolioRetem.setContador(FolRetconsecutivo.toString());
            model.addAttribute("folioRetemes", FolRetconsecutivo.toString());
            logger.info("Folio del Reteme siguiente", FolRetconsecutivo);
        }
        this.contadoresManager.updateContadores(FolioRetem);*/
        model.addAttribute("folioRetemes", Integer.parseInt(this.retemesManager.getMaxIdTIF())+1);
        //logger.info("Folio del Reteme siguiente", FolRetconsecutivo);

        model.addAttribute("camaras",camaras);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", this.clientesManager.getClientesAll());

        model.addAttribute("user",user.getUsername().toString());
        model.addAttribute("bandera", bandera);
        return "formRetemesTIF";
    }
    
    @RequestMapping(value = "/cboServicios", method = RequestMethod.POST)
    public String cboServicios(Locale locale, Model model, String idCliente) {
        logger.info("combo Servicios/formRetemes", locale);
        String idClienteStr = String.valueOf(idCliente);
        if(idClienteStr.equals("") || idClienteStr == null){
        	idClienteStr = "%";
        }
        final List<Servicios> servicios = this.serviciosManager.getByActivoByIdCliente(idClienteStr);
    	model.addAttribute("servicios",servicios);
        model.addAttribute("idCliente",idCliente);
        return "cboservicios";
    }
    
    @RequestMapping(value = "/insertaReteme", method = RequestMethod.POST)
    public String insertaReteme(Locale locale, Model model, String ID_CLIENTE, String FECHA, String CONSECUTIVO, String REFEREN,String CAMARA, String FIRMA_RETEME, String TIPOSERVICIO,  String ALTURA, String OBSERV, Double KILOS, String FOLIORETEME) {
    	Retemes reteme = new Retemes();
        logger.info("inserta Reteme", locale + " " + ID_CLIENTE + FECHA);
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);
        
        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        reteme.setFecha(fecha);
        reteme.setFReal(fecha);
        reteme.setIdCliente(ID_CLIENTE);
        reteme.setConsecutivo(CONSECUTIVO);
        reteme.setKilos(KILOS);
        reteme.setFirmaReteme(FIRMA_RETEME);
        reteme.setTipoServicio(TIPOSERVICIO);
        reteme.setCamara(CAMARA);
        reteme.setAltura(ALTURA);
        reteme.setReferen(REFEREN);
        reteme.setObserv(OBSERV);
        reteme.setFolioRemete(FOLIORETEME);
        reteme.setTIPO("O");

        Clientes cliente = (Clientes) this.clientesManager.getByIdCliente(Integer.parseInt(reteme.getIdCliente()));
        int noDiasFac = Integer.parseInt(cliente.getTipoFacturacion());
        String tipoFacturacion = cliente.getTipoFacturacion();
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.DATE, noDiasFac);
        cal.getTimeInMillis();
        Date fechaInFactura = new Date();
        Date nvaFecha = new Date(cal.getTimeInMillis());
        logger.info("nvaFecha "+formatFecha.format(nvaFecha));
        String fecInFac = formatFecha.format(fechaInFactura);
        String fecFinFac = formatFecha.format(nvaFecha);
        logger.info("Fecha in factura "+fecInFac);
        logger.info("Nueva FEcha "+fecFinFac);
        Servicios serviciosCliente = this.serviciosManager.getByActivoByIdClienteClave(reteme.getIdCliente(),reteme.getTipoServicio());
        logger.info("serviciosCliente " + serviciosCliente.getCuota().toString());
        Double total = reteme.getKilos()*serviciosCliente.getCuota();
        logger.info("total " + total.toString());

        DetalleFacturacion detalleFac = new DetalleFacturacion();
        detalleFac.setIdCliente(Integer.parseInt(reteme.getIdCliente()));
        detalleFac.setRd(reteme.getConsecutivo());
        detalleFac.setFechainiper(fecInFac);
        detalleFac.setFechafinper(fecInFac);
        detalleFac.setCamara(FOLIORETEME);
        detalleFac.setKilos(reteme.getKilos());
        detalleFac.setTiposervicio(reteme.getTipoServicio());
        detalleFac.setCongelacion(serviciosCliente.getCuota());
        detalleFac.setSubtotal(total);
        detalleFac.setTotal(total);
        detalleFac.setId(reteme.getCamara());
        detalleFac.setNofactura(" ");
        detalleFac.setFechafactura(fecInFac);

        detalleFac = this.detalleFacturacionManager.insertDetalleFacturacion(detalleFac);

        /*String FolioRetem = this.retemesManager.getMaxId();
        Integer FolRetconsecutivo = Integer.parseInt(FolioRetem)+1;
        reteme.setFolioRemete(FolRetconsecutivo.toString());*/
        boolean result = this.retemesManager.setIngresaRetemes(reteme);
        List<Retemes> retemes = this.retemesManager.findRetemeByConsecutivo(CONSECUTIVO);
        logger.info("inserta Reteme RETEMES "+retemes.size());
        for (Retemes retemes2 : retemes) {
        	logger.info("for retemes "+ retemes2.getIdFolioReteme());
		}
    	model.addAttribute("retemesReg",retemes);
        return "insertaReteme";
    }

    @RequestMapping(value = "/insertaRetemeTIF", method = RequestMethod.POST)
    public String insertaRetemeTIF(Locale locale, Model model, String ID_CLIENTE, String FECHA, String CONSECUTIVO, String REFEREN,String CAMARA, String FIRMA_RETEME, String TIPOSERVICIO,  String ALTURA, String OBSERV, Double KILOS, String FOLIORETEME) {
        Retemes reteme = new Retemes();
        logger.info("inserta Reteme", locale + " " + ID_CLIENTE + FECHA);
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);

        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        reteme.setFecha(fecha);
        reteme.setFReal(fecha);
        reteme.setIdCliente(ID_CLIENTE);
        reteme.setConsecutivo(CONSECUTIVO);
        reteme.setKilos(KILOS);
        reteme.setFirmaReteme(FIRMA_RETEME);
        reteme.setTipoServicio(TIPOSERVICIO);
        reteme.setCamara(CAMARA);
        reteme.setAltura(ALTURA);
        reteme.setReferen(REFEREN);
        reteme.setObserv(OBSERV);
        reteme.setFolioRemete(FOLIORETEME);
        reteme.setTIPO("T");

        Clientes cliente = (Clientes) this.clientesManager.getByIdCliente(Integer.parseInt(reteme.getIdCliente()));
        int noDiasFac = Integer.parseInt(cliente.getTipoFacturacion());
        String tipoFacturacion = cliente.getTipoFacturacion();
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.DATE, noDiasFac);
        cal.getTimeInMillis();
        Date fechaInFactura = new Date();
        Date nvaFecha = new Date(cal.getTimeInMillis());
        logger.info("nvaFecha "+formatFecha.format(nvaFecha));
        String fecInFac = formatFecha.format(fechaInFactura);
        String fecFinFac = formatFecha.format(nvaFecha);
        logger.info("Fecha in factura "+fecInFac);
        logger.info("Nueva FEcha "+fecFinFac);
        Servicios serviciosCliente = this.serviciosManager.getByActivoByIdClienteClave(reteme.getIdCliente(),reteme.getTipoServicio());
        logger.info("serviciosCliente " + serviciosCliente.getCuota().toString());
        Double total = reteme.getKilos()*serviciosCliente.getCuota();
        logger.info("total " + total.toString());

        DetalleFacturacion detalleFac = new DetalleFacturacion();
        detalleFac.setIdCliente(Integer.parseInt(reteme.getIdCliente()));
        detalleFac.setRd(reteme.getConsecutivo());
        detalleFac.setFechainiper(fecInFac);
        detalleFac.setFechafinper(fecInFac);
        detalleFac.setCamara(FOLIORETEME);
        detalleFac.setKilos(reteme.getKilos());
        detalleFac.setTiposervicio(reteme.getTipoServicio());
        detalleFac.setCongelacion(serviciosCliente.getCuota());
        detalleFac.setSubtotal(total);
        detalleFac.setTotal(total);
        detalleFac.setId(reteme.getCamara());
        detalleFac.setNofactura(" ");
        detalleFac.setFechafactura(fecInFac);

        detalleFac = this.detalleFacturacionManager.insertDetalleFacturacion(detalleFac);

        /*String FolioRetem = this.retemesManager.getMaxId();
        Integer FolRetconsecutivo = Integer.parseInt(FolioRetem)+1;
        reteme.setFolioRemete(FolRetconsecutivo.toString());*/
        boolean result = this.retemesManager.setIngresaRetemes(reteme);
        List<Retemes> retemes = this.retemesManager.findRetemeByConsecutivo(CONSECUTIVO);
        logger.info("inserta Reteme RETEMES "+retemes.size());
        for (Retemes retemes2 : retemes) {
            logger.info("for retemes "+ retemes2.getIdFolioReteme());
        }
        model.addAttribute("retemesReg",retemes);
        return "insertaRetemeTIF";
    }

    @RequestMapping(value = "/terminaCapturaRetemeTIF", method = RequestMethod.GET)
    public String terminaCapturaRetemeTIF(Locale locale,@RequestParam(value = "bandera")int bandera, Model model) {
        logger.info("termina captura /formRetemes", locale);
        model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
        if(bandera==1){
            return "redirect:alm_consultaSalidas";
        }else if(bandera==0){
            return "redirect:alm_consultaIngresos";
        }else{
            return "redirect:formRetemesTIF?bandera=2";
        }
    }

    @RequestMapping(value = "/terminaCapturaReteme", method = RequestMethod.GET)
    public String terminaCapturaReteme(Locale locale,@RequestParam(value = "bandera")int bandera, Model model) {
        logger.info("termina captura /formRetemes", locale);
        model.addAttribute("ingresos", this.consultasManager.getEntradasByStatusIngreso("1"));
        if(bandera==1){
            return "redirect:alm_consultaSalidas";
        }else if(bandera==0){
            return "redirect:alm_consultaIngresos";
        }else{
            return "redirect:formRetemes?bandera=2";
        }
    }
    
    
    @RequestMapping(value = "/ae_mandaRetemes", method = RequestMethod.GET)
    public String ae_mandaRetemes(Locale locale, Model model) {
        logger.info("captura retemes desde menu /formRetemes", locale);
        return "redirect:formRetemes?bandera=2";
    }

    @RequestMapping(value = "/ae_mandaRetemesTIF", method = RequestMethod.GET)
    public String ae_mandaRetemesTIF(Locale locale, Model model) {
        logger.info("captura retemes desde menu /formRetemes", locale);
        return "redirect:formRetemesTIF?bandera=2";
    }
    
    @RequestMapping(value = "/ae_consultaRetemes", method = RequestMethod.GET)
    public String ae_consultaRetemes(Locale locale, Model model) {
        logger.info("consulta retemes desde menu /formGetRetemes", locale);
        return "redirect:formGetRetemes";
    }
    
    @RequestMapping(value = "/formGetRetemes", method = RequestMethod.GET)
    public String formGetRetemes(Locale locale, Model model) {
        logger.info("Despues de entradas/formRetemes", locale);
        
    	model.addAttribute("clientes", this.entradasManager.getCatClientes());
        return "formGetRetemes";
    }
    
    @RequestMapping(value = "/getRetemes", method = RequestMethod.POST)
    public String getRetemes(Locale locale, Model model, String idCliente , String fecini, String fecfin, String folioReteme,String consecutivo) {
        logger.info("Despues de entradas/formRetemes "+fecini+" "+idCliente, locale);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
    	String dateIniString = fecini;
        String dateFinString = fecfin;

        String fechaInicio = "";
        String fechaFinal = "";
    	String fechaNva = "";
    	try {
    		if(!dateIniString.equals("") && !dateFinString.equals("")){
    			Date dateIni = formatter.parse(dateIniString);
                Date dateFin = formatter.parse(dateFinString);
    			
    			final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
                fechaInicio = formatFecha.format(dateIni);
                fechaFinal = formatFecha.format(dateFin);
    			//fechaNva = formatter.format(dateIn);
    		}
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}

		List<Retemes> retemes = this.retemesManager.findRetemeByIdCliente(idCliente,fechaInicio,fechaFinal,folioReteme,consecutivo);
     
       /* for (Retemes retemes2 : retemes) {
        	logger.info("for retemes "+ retemes2.getFecha());
    	}*/
    	model.addAttribute("retemes",retemes);        
    	return "getRetemes";
    }
    
    @RequestMapping(value = "/cboTipoProducto", method = RequestMethod.POST)
    public String cboTipoProducto(Locale locale, Model model, int idCliente) {
        logger.info("combo Tipo Producto/forma Vehiculos", locale);
        final List<Convenios> convenios = this.conveniosManager.getByClientes(idCliente);
    	model.addAttribute("Convenios",convenios);
        return "cboTipoProducto";
    }

    @RequestMapping(value = "/ae_consultaEntradasFormCompara", method = RequestMethod.GET)
    public String entradasFormCompara(Locale locale, Model model) {
        return "entradasFormCompara";
    }
    
    @RequestMapping(value = "/tablaCompara", method = RequestMethod.POST)
    public String tablaCompara(Locale locale, Model model,String fecini, String fecfin) {
        logger.info("consulta comparacion desde menu /tablaCompara", locale);
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateIniString = fecini;
        String dateFinString = fecfin;

        String fechaInicio = "";
        String fechaFinal = "";
        try {
            Date dateIni = formatter.parse(dateIniString);
            Date dateFin = formatter.parse(dateFinString);

            final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
            fechaInicio = formatFecha.format(dateIni);
            fechaFinal = formatFecha.format(dateFin);
            final List<RegEntradas> comparaciones = this.regEntradasManager.getComparaCantidadesTonelaje(fechaInicio,fechaFinal);
            model.addAttribute("comparaciones",comparaciones);
        } catch (ParseException e) {
            model.addAttribute("comparaciones", null);
        }

        return "tablaCompara";
    }

    @RequestMapping(value = "/consultaDetalleRd", method = RequestMethod.POST)
    public String consultaDetalleRd(Locale locale, Model model, String cliente, Integer consecutivo) {
        logger.info("Muestra detalles / detalles_rd", locale);
        final List<DetallesRd> detalleRds = this.detalleRdManager.getAllByConsecutivo(consecutivo);
        model.addAttribute("detalleRds",detalleRds);
        model.addAttribute("cliente",cliente);
        return "consultaDetalleRd";
    }

    @RequestMapping(value = {"/consultaRendimientos"},method = {RequestMethod.GET})
    public String consultaRendimientos(Locale locale, Model model) {
        logger.info("Vista para sacar rendimientos");
        return "consultaRendimientos";
    }

}