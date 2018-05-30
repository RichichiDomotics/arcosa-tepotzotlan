package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Handles requests for the application home page.
 */
@Controller
public class InventariosController {

    private static final Logger logger = LoggerFactory
	    .getLogger(InventariosController.class);

    @Autowired
    private InventarioManager inventarioManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private CatUbicacionesManager catUbicacionesManager;
    @Autowired
    private UbicacionesManager ubicacionesManager;
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private ConveniosManager conveniosManager;
    private List<Object> comparator;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/ae_formInventarios", method = RequestMethod.GET)
    public String consultaClientes(Locale locale, Model model) {
	logger.info("Inventarios/formInventarios", locale);

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
	return "formInventarios";
    }

    @RequestMapping(value ="/formEntradasSeguro" , method = RequestMethod.GET)
    public String formEntradasSeguro(Locale locale, Model model){
        logger.info("Busqueda de seguros para las entradas");
        return "formEntradasSeguro";
    }

    @RequestMapping(value = "/entradasSeguro", method = RequestMethod.POST)
    public String entradasSeguro(Locale locale, Model model,
                                 @RequestParam(value = "consecutivo") String consecutivo,
                                 @RequestParam(value = "camara") String camara,
                                 @RequestParam(value = "idCliente") String idCliente,
                                 @RequestParam(value = "claveProducto") String claveProducto,
                                 @RequestParam(value = "tunel") String tunel){
       List<DetallesRd> detallesRds= this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(consecutivo));
        model.addAttribute("detallesRD", detallesRds);
        model.addAttribute("cliente",this.clienteManager.getByIdCliente(Integer.parseInt(detallesRds.get(0).getIdCliente())));
        model.addAttribute("cuota",this.conveniosManager.getByClientes(Integer.parseInt(detallesRds.get(0).getIdCliente())));
        return "entradasSeguro";
    }

    @RequestMapping(value = "/ae_formInventariosComercial", method = RequestMethod.GET)
    public String consultaClientesComercial(Locale locale, Model model) {
        logger.info("Inventarios/formInventarios", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        if(claveUsuario.equals("GSANTAMARIA") || claveUsuario.equals("VALENCIA") || claveUsuario.equals("RMONTANO")){
            model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        }else{
            model.addAttribute("clientes", this.clienteManager.getAllIdEjecutivo(claveUsuario));
        }

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formInventarios";
    }

    @RequestMapping(value = "/ae_formInventarioRD", method = RequestMethod.GET)
    public String formInventarioRD(Locale locale, Model model) {
        logger.info("Inventarios/formInventarios", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        return "formInventarioRD";
    }

    @RequestMapping(value = "/ae_formFormatosUnicos", method = RequestMethod.GET)
    public String consultaFormtatosUnicos(Locale locale, Model model) {
        logger.info("Inventarios/formInventarios", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", this.clienteManager.getFormatosUnicos());
        return "formFormatosUnicos";
    }

    @RequestMapping(value = "/ae_formCitroFrut", method = RequestMethod.GET)
    public String consultaCitroFrut(Locale locale, Model model) {
        //logger.info("Inventarios/formInventarios", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", this.clienteManager.getFormatosUnicos());
        return "formCitroFrut";
    }
    
    @RequestMapping(value = "/inventariosReporte", method = RequestMethod.POST)
    public String inventariosReporte(Locale locale, Model model,
    @RequestParam(value = "consecutivo") String consecutivo,
    @RequestParam(value = "camara") String camara,
    @RequestParam(value = "idCliente") String idCliente,
    @RequestParam(value = "claveProducto") String claveProducto,
    @RequestParam(value = "tunel") String tunel )
    {
        //List<Inventarioview> inventarios = this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel);
        List<Inventario> inventarios1 = this.inventarioManager.findInventarioReporte2(consecutivo, camara, idCliente, claveProducto, tunel);
        //logger.info(inventarios1.toString());
        if(inventarios1.toString().equals("[]")){
            model.addAttribute("nomCliente", "");
        }else {
            Clientes clientes = this.clienteManager.getByIdCliente(Integer.parseInt(inventarios1.get(0).getIdCliente()));
            model.addAttribute("nomCliente", clientes.getNombreCliente());
        }
        model.addAttribute("reporte", this.inventarioManager.findInventarioReporte2(consecutivo, camara, idCliente, claveProducto, tunel));
        model.addAttribute("consecutivo", consecutivo);
        model.addAttribute("camara", camara);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("clientes",this.clienteManager.getClientesAll());
        model.addAttribute("claveProducto", claveProducto);
        model.addAttribute("tunel", tunel);
	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	return "inventariosReporte";

    }

    @RequestMapping(value = "/inventariosReporteRD", method = RequestMethod.POST)
    public String inventariosReporteRD(Locale locale, Model model,
                                     @RequestParam(value = "consecutivo") String consecutivo,
                                     @RequestParam(value = "camara") String camara,
                                     @RequestParam(value = "idCliente") String idCliente,
                                     @RequestParam(value = "claveProducto") String claveProducto,
                                     @RequestParam(value = "tunel") String tunel )
    {
        List<Inventarioview> inventarios = this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel);
        //model.addAttribute("reporte", this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel));
        List<DetallesRd> detallesRds=this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(consecutivo));
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy");
        String fechanueva = fecha.format(detallesRds.get(0).getFechaCaptura());
        model.addAttribute("reporte", this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(consecutivo)));
        model.addAttribute("consecutivo", consecutivo);
        model.addAttribute("camara", camara);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("claveProducto", claveProducto);
        model.addAttribute("tunel", tunel);
        model.addAttribute("Cliente", this.clienteManager.getByIdCliente(Integer.parseInt(detallesRds.get(0).getIdCliente())));
        model.addAttribute("fechaIngreso",fechanueva);
        model.addAttribute("convenios",this.conveniosManager.getTipoPeriodo());
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "inventariosReporteRD";

    }

    @RequestMapping(value = "/inventariosReporteFormtatosUnicos", method = RequestMethod.POST)
    public String inventariosReporteFormatosUnicos(Locale locale, Model model,
                                     @RequestParam(value = "consecutivo") String consecutivo,
                                     @RequestParam(value = "camara") String camara,
                                     @RequestParam(value = "idCliente") String idCliente,
                                     @RequestParam(value = "claveProducto") String claveProducto,
                                     @RequestParam(value = "tunel") String tunel )
    {
        logger.debug("id del cliente" + idCliente);
        List<Inventarioview> inventarios = this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel);

        model.addAttribute("reporte", this.inventarioManager.getInventarioReporte(consecutivo, camara, idCliente, claveProducto, tunel));
        model.addAttribute("consecutivo", consecutivo);
        model.addAttribute("camara", camara);
        model.addAttribute("idCliente", idCliente);
        model.addAttribute("claveProducto", claveProducto);
        model.addAttribute("tunel", tunel);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        if (idCliente.equals("526")) {
            return "reporteAlseaMerma";
        }else if (idCliente.equals("497")) {
            return "reporteAlsea";
        }else if (idCliente.equals("179") || idCliente.equals("207")) {
            return "reporteNegrita";
        }else if(idCliente.equals("187")){
            return "reporteDialsa";
        }else if(idCliente.equals("341")){
            return "reporteSigma";
        }else if(idCliente.equals("258") ||  idCliente.equals("333")){
            return "reporteGifan";
        }else if(idCliente.equals("186") || idCliente.equals("326") || idCliente.equals("704")){
            model.addAttribute("detalles",this.detalleRdManager.getAllByCliente(idCliente));
            return "reporteCitrofrut";
        }else if(idCliente.equals("550") || idCliente.equals("551") || idCliente.equals("552") || idCliente.equals("606") || idCliente.equals("650") || idCliente.equals("1550") || idCliente.equals("1551") || idCliente.equals("1651")){
            return "reporteArla";
        }else if(idCliente.equals("531")){
            return "reporteAgrosuper";
        }else if(idCliente.equals("556")){
            return "reportePilgrims";
        }else if(idCliente.equals("595")){
            return "reporteSigma";
        }
        else {return "inventariosReporte";}
    }

    @RequestMapping(value = "/inventarioCitrofrut", method = RequestMethod.POST)
    public String inventarioCitrofrut(Locale locale, Model model,
                                      @RequestParam(value = "consecutivo") String consecutivo1)
    {
        logger.debug("Lista de conseutivos: "+consecutivo1);
        //String[] consecutivo = consecutivo1.split(",");
        List<Inventario> inventarios = this.inventarioManager.getCitroFrut(consecutivo1.split(","));
        model.addAttribute("inventarios",inventarios);
        return "ReporteCitro";
    }

    @RequestMapping(value = "/ae_formInventariosCamara", method = RequestMethod.GET)
    public String ae_formInventariosCamara(Locale locale, Model model)
    {
        final List<?> camaras = this.inventarioManager.getSaldoXCamara();
        final List<?> tunel = this.inventarioManager.getSaldoXTunel();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("camaras", camaras);
        model.addAttribute("tunel", tunel);
        return "saldosXCamara";

    }

    @RequestMapping(value = "/ae_formInventariosCliente", method = RequestMethod.GET)
    public String ae_formInventariosCliente(Locale locale, Model model)
    {
        final List<?> clientes = this.inventarioManager.getSaldoXCliente();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", clientes);
        return "saldosXCliente";

    }

    @RequestMapping(value = "/ae_formInventariosTodaCamaras", method = RequestMethod.GET)
    public String ae_formInventariosTodaCamaras(Locale locale, Model model)
    {
        final List<?> clientes = this.inventarioManager.getTodasCamaras();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("clientes", clientes);
        return "todasCamaras";

    }

    @RequestMapping(value = "/alm_regUbicaciones", method = RequestMethod.GET)
    public String registraUbicacion(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_regUbicaciones", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("camaras", this.catUbicacionesManager.getCamaras());
        return "registraUbicacion";
    }

    @RequestMapping(value = "/cargaPuerta", method = RequestMethod.POST)
    public String cargaPuerta(Locale locale, Model model,String camara) {
        logger.info("cargaPuerta", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("puertas", this.catUbicacionesManager.getPuertas(camara));
        return "cargaPuerta";
    }

    @RequestMapping(value = "/cargaPasillo", method = RequestMethod.POST)
    public String cargaPasillo(Locale locale, Model model,String Puerta, String Camara) {
        logger.info("cargaPasillo "+Puerta+ " "+Camara, locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("pasillos", this.catUbicacionesManager.getPasillos(Puerta, Camara));
        return "cargaPasillo";
    }

    @RequestMapping(value = "/cargaFila", method = RequestMethod.POST)
    public String cargaFila(Locale locale, Model model,String pasillo,String puerta,String camara) {
        logger.info("cargaFila", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("filas", this.catUbicacionesManager.getFilas(pasillo, puerta, camara));
        return "cargaFila";
    }

    @RequestMapping(value = "/cargaPosicion", method = RequestMethod.POST)
     public String cargaPosicion(Locale locale, Model model,String pasillo,String puerta,String camara, String fila) {
        logger.info("cargaPosicion", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("posiciones", this.catUbicacionesManager.getPosiciones(pasillo, puerta, camara, fila));
        return "cargaPosicion";
    }

    @RequestMapping(value = "/cargaNivel", method = RequestMethod.POST)
    public String cargaNivel(Locale locale, Model model,String pasillo,String puerta,String camara, String fila) {
        logger.info("cargaNivel", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("niveles", this.catUbicacionesManager.getNiveles(pasillo, puerta, camara, fila));
        return "cargaNivel";
    }

    @RequestMapping(value = "/insPosicion", method = RequestMethod.POST)
    public String insPosicion(@Valid Ubicaciones ubicaciones,Locale locale, Model model) {
        logger.info("Inserta posicion", locale);

        Ubicaciones ubicacionrec = new Ubicaciones();
        boolean upbandera = false;
        ubicacionrec = this.ubicacionesManager.getRevisionUbicacion(ubicaciones.getCamara(),ubicaciones.getPuerta(),ubicaciones.getPasillo(),ubicaciones.getFilaCalle(),ubicaciones.getPosicion(),ubicaciones.getNivel(),ubicaciones.getTipoTarima(),ubicaciones.getConsecutivo());
        if(ubicacionrec.getIdUbica()==null){
            //INSERTA NUEVO REGISTRO
            ubicacionrec = this.ubicacionesManager.insertaRegistroUbicacion(ubicaciones);
        }else{
            //ACTUALIZA REGISTRO
            upbandera = this.ubicacionesManager.updateUbicacion(ubicaciones);
        }

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("registro", ubicacionrec);
        return "tablaRegUbicacion";
    }


    @RequestMapping(value = "/updatePosicion", method = RequestMethod.POST)
    public String updatePosicion(@Valid Ubicaciones ubicaciones,String tipoTarima, String consecutivos,Locale locale, Model model) {
        logger.info("Inserta posicion", locale);

        Ubicaciones ubicacionrec = new Ubicaciones();
        boolean upbandera = false;
        ubicacionrec = this.ubicacionesManager.getUbicacionIdUbica(ubicaciones.getIdUbica());
        ubicacionrec.setTipoTarima(tipoTarima);
        ubicacionrec.setConsecutivo(consecutivos);
        //ACTUALIZA REGISTRO
        upbandera = this.ubicacionesManager.updateUbicacion(ubicacionrec);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("registro", ubicacionrec);
        return "updatePosicion";
    }

    @RequestMapping(value = "/alm_getUbicaciones", method = RequestMethod.GET)
    public String formUbicaciones(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_getUbicaciones", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formUbicaciones";
    }

    @RequestMapping(value = "/tablaRecUbicacion", method = RequestMethod.POST)
     public String tablaRecUbicacion(Locale locale, Model model, String camara, String consecutivo) {
        logger.info("Recupera posicion "+consecutivo, locale);
        List<Object[]> ubicacionrec = null;
        if(camara.equals("")){
            camara = "%";
        }

        if(consecutivo.equals("")){
            consecutivo = "%";
        }

        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacion(camara,consecutivo);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecUbicacion";
    }

    @RequestMapping(value = "/tablaRecUbicacion2", method = RequestMethod.POST)
    public String tablaRecUbicacion2(Locale locale, Model model, String camara, String consecutivo) {
        logger.info("Recupera posicion "+consecutivo, locale);
        List<Object[]> ubicacionrec = null;
        camara = "%";
        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacion(camara,consecutivo);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecUbicacion2";
    }

    @RequestMapping(value = "/tablaRecUbicacionVacias", method = RequestMethod.POST)
    public String tablaRecUbicacionVacias(Locale locale, Model model, String camara) {
        logger.info("Recupera posiciones vacias", locale);
        List<Object[]> ubicacionrec = null;
        if(camara.equals("")){
            camara = "%";
        }

        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacionVacias(camara);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecUbicacionVacias";
    }

    @RequestMapping(value = "/borraUbicacion", method = RequestMethod.POST)
    public String borraUbicacion(Locale locale, Model model, Integer idUbica) {
        logger.info("Borra Ubicacion", locale);

        boolean ubicacionrec = (boolean) this.ubicacionesManager.borraUbicacion(idUbica);
        return "borraUbicacion";
    }

    @RequestMapping(value = "/formaAcutalizaUbicacion", method = RequestMethod.POST)
    public String formaAcutalizaUbicacion(Locale locale, Model model, Integer idUbica) {
        logger.info("Forma Acutaliza Ubicacion", locale);

        Ubicaciones ubicacion = this.ubicacionesManager.getUbicacionIdUbica(idUbica);
        model.addAttribute("ubicacion", ubicacion);
        return "formaActualizaUbicacion";
    }

    @RequestMapping(value = "/tablaRecConsolidados", method = RequestMethod.POST)
    public String tablaRecConsolidados(Locale locale, Model model, String camara) {
        logger.info("Recupera posiciones vacias", locale);
        List<Object[]> ubicacionrec = null;
        List<Object[]> ubicacionrecVacio = null;
        if(camara.equals("")){
            camara = "%";
        }
        logger.info("camara", camara);
        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getConsolidados(camara);

        ubicacionrecVacio = (List<Object[]>) this.ubicacionesManager.getConsolidadosVacio(camara);

        //ubicacionrec.addAll(ubicacionrecVacio);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        model.addAttribute("ubicacionesvacias", ubicacionrecVacio);
        return "tablaRecConsolidados";
    }

    @RequestMapping(value = "/formInventariosTif", method = RequestMethod.GET)
    public String formaInventarioTif(Locale locale, Model model, Integer idUbica) {
        logger.info("Forma Acutaliza Ubicacion", locale);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        return "formInventariosTif";
    }

    @RequestMapping(value = "/inventariosTif", method = RequestMethod.POST)
    public String inventariosTif(Locale locale, Model model, int idCliente) {
        logger.info("Recupera posiciones vacias", locale);
        model.addAttribute("reporte", this.inventarioManager.getInventarioTif(idCliente));
        return "inventariosTif";
    }
//*********************************************************************************
/*   @RequestMapping(value = "/alm_getUbicaciones", method = RequestMethod.GET)
    public String formUbicaciones(Locale locale, Model model) {
        logger.info("Menu Consultas/alm_getUbicaciones", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formUbicaciones";
    }

    @RequestMapping(value = "/tablaRecCatUbicacion", method = RequestMethod.POST)
    public String tablaRecCatUbicacion(Locale locale, Model model, String camara, String consecutivo) {
        logger.info("Recupera posicion", locale);
        List<Object[]> ubicacionrec = null;
        if(camara.equals("")){
            camara = "%";
        }

        if(consecutivo.equals("")){
            consecutivo = "%";
        }

        ubicacionrec = (List<Object[]>) this.ubicacionesManager.getUbicacion(camara,consecutivo);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("ubicaciones", ubicacionrec);
        return "tablaRecCatUbicacion";
    }*/
}