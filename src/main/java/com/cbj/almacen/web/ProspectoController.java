package com.cbj.almacen.web;

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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jolvera on 01/09/2014.
 */
@Controller
public class ProspectoController {

    private static final Logger logger = LoggerFactory
            .getLogger(ClientesController.class);

    @Autowired
    private ProspectoManager prospectoManager;
    @Autowired
    private EtapaProspectoManager etapaProspectoManager;
    @Autowired
    private SeguimientoManager seguimientoManager;
    @Autowired
    private EjecutivoManager ejecutivoManager;
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private MotivoRechazoAutorizadoManager motivoRechazoAutorizadoManager;
    @Autowired
    private AvisosHistorialManager avisosHistorialManager;
    @Autowired
    private JefeEjecutivoManager jefeEjecutivoManager;
    @Autowired
    private MensajesManager mensajesManager;
    @Autowired
    private ObservacionesSeguimientoManager observacionesSeguimientoManager;

    @RequestMapping(value = "/ae_formaIngresoProspectoJefe", method = RequestMethod.GET)
    public String ae_formaIngresoProspectoJefe(@Valid Prospecto prospecto,BindingResult result, Model model) {
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        logger.debug("ejecutivos: "+String.valueOf(ejecutivos));
        model.addAttribute("ejecutivos",ejecutivos);
        return "formaIngresoProspectoJefe";
    }

    @RequestMapping(value = "/ae_formaIngresoProspecto", method = RequestMethod.GET)
    public String ae_formaIngresoProspecto(@Valid Prospecto prospecto,BindingResult result, Model model) {
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        Ejecutivo ejecutivos = this.ejecutivoManager.getEjecutivo(claveUsuario);
        logger.debug("ejecutivos: "+String.valueOf(ejecutivos));
        model.addAttribute("ejecutivos", ejecutivos.getNombreEjecutivo());
        model.addAttribute("idEjecutivo",ejecutivos.getId());
        return "formaIngresoProspecto";
    }


    @RequestMapping(value = "/ae_insertarProspecto", method = RequestMethod.POST)
    public String ae_insertarProspecto(@Valid Prospecto prospecto,BindingResult result,@RequestParam(value = "idEjecutivo")int idEjecutivo ,@RequestParam(value = "ejecutivo")String Ejecutivo ,Model model) {
        if (result.hasErrors()) {
            return "error";
        }

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);

        List<Prospecto> TotProspectos = prospectoManager.getAllActive(idEjecutivo);
        logger.info("Numero de prospectos : "+TotProspectos.size());
        if (TotProspectos.size() >= 20) {
            model.addAttribute("mensaje", "TIENES ASIGNADO 20 PROSPECTOS PARA ESTE EJECUTIVO ES EL LIMITE.");
            return "prospectoRegistrado";
        }
        if (TotProspectos.size() >= 20) return "error";

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        JefeEjecutivo jefeEjecRec = null;
        if(claveUsuario!=null) {
            Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
            int idJefeRec = EjecutivoRec.getIdJefe();
            jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivo(idJefeRec);
        }
        prospecto.setActivo("0");
        prospecto.setEstatus("1");
        prospecto.setIdEjecutivo(idEjecutivo);
        boolean resp =this.prospectoManager.insertProspecto(prospecto);
        AvisosHistorial avisos = new AvisosHistorial();
        avisos.setIdProspecto(prospecto.getIdProspecto());
        avisos.setTipoNotificacion("Nuevo Prospecto");
        String cveJefeEjecutivoreg = "";
        if(jefeEjecRec == null){
            cveJefeEjecutivoreg = user.getUsername();
        }else{
            cveJefeEjecutivoreg = jefeEjecRec.getUsuario();
        }
        avisos.setClaveJefe(cveJefeEjecutivoreg);
        String cveEjecutivo = "";
        if(claveUsuario!=null){
            cveEjecutivo = claveUsuario;
        }else{
            Ejecutivo ejRecJefe = this.ejecutivoManager.getEjecutivoId(idEjecutivo);
            cveEjecutivo = ejRecJefe.getClaveEjecutivo();
        }
        avisos.setClaveEjecutivo(cveEjecutivo);
        avisos.setVisto(0);
        avisos.setFecha(fecha+" "+hora);
        avisos.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE UN NUEVO PROSPECTO :"+prospecto.getRazonSocial()+" REALIZADO POR : "+cveEjecutivo+" Y SU JEFE ES :"+cveJefeEjecutivoreg);
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);
        model.addAttribute("Respuesta", "Prospecto registrado exitosamente");
        if (resp && resp2) {
            model.addAttribute("mensaje", "PROSPECTO REGISTRADO EXITOSAMENTE.");
            return "prospectoRegistrado";
        }
        else {
            model.addAttribute("mensaje", "EXISTIO UN ERROR AL REGISTRAR EL PROSPECTO INTENTELO NUEVAMENTE.");
            return "prospectoRegistrado";
        }
    }

    @RequestMapping(value = "/prospectoRegistrado", method = RequestMethod.GET)
    public String prospectoRegistrado(@Valid Prospecto prospecto,BindingResult result, Model model) {
        return "prospectoRegistrado";
    }

    /****************************************************************************/
    @RequestMapping(value = "/ae_formaProspecto", method = RequestMethod.GET)
    public String formaProspecto(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial", "1");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspecto";
    }

    @RequestMapping(value = "/ae_formaProspectoAutorizado", method = RequestMethod.GET)
     public String formaProspectoAutorizado(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","1");
        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoAutorizado";
    }
    @RequestMapping(value = "/ae_formaProspectoSeguimiento", method = RequestMethod.GET)
    public String formaProspectoSeguimiento(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","1");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoSeguimiento";
    }

    @RequestMapping(value = "/ae_formaProspectoRechazado", method = RequestMethod.GET)
    public String formaProspectoRechazado(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","1");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoRechazado";
    }

    @RequestMapping(value = "/ae_formaProspectoJefe", method = RequestMethod.GET)
    public String formaProspectoJefe(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","1");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);
        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoJefe";
    }
    @RequestMapping(value = "/ae_formaProspectoAutorizadoJefe", method = RequestMethod.GET)
    public String formaProspectoAutorizadoJefe(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","1");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoAutorizadoJefe";
    }
    @RequestMapping(value = "/ae_formaProspectoSeguimientoJefe", method = RequestMethod.GET)
    public String formaProspectoSeguimientoJefe(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","1");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoSeguimientoJefe";
    }

    @RequestMapping(value = "/consultaProspectos", method = RequestMethod.POST)
     public String consultaClientes(String razonSocial , Model model) {
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        logger.info("lista de ejecutivos" + String.valueOf(ejecutivos));
        logger.info("Ajax /consulta prospecto");
        String id = "";
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo= this.ejecutivoManager.getEjecutivo(claveUsuario);
        model.addAttribute("prospectos", this.prospectoManager.getProspectosByIdNom(id, razonSocial, ejecutivo.getId()));
        model.addAttribute("ejecutivos", ejecutivos);
        return "tablaProspecto";
    }
    @RequestMapping(value = "/consultaProspectosFiltro", method = RequestMethod.POST)
    public String consultaProspectosFiltro(String razonSocial , String estatus , Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        List<Prospecto> prospectos = this.prospectoManager.getProspectoFiltro(razonSocial, estatus, ejecutivo.getId());
        model.addAttribute("prospecto", prospectos);
        return "tablaProspectoAutorizado";
    }

    @RequestMapping(value = "/consultaProspectosFiltroJefe", method = RequestMethod.POST)
     public String consultaProspectosFiltroJefe(String razonSocial , String estatus , Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();

        JefeEjecutivo JefeEjecutivo = this.jefeEjecutivoManager.getJefeEjecutivoUser(claveJefe);

        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        List<Prospecto> prospectos = this.prospectoManager.getProspectoFiltroJefe(razonSocial, estatus, JefeEjecutivo.getId());

        model.addAttribute("prospectos", prospectos);
        model.addAttribute("ejecutivos",ejecutivos);
        return "tablaProspectoAutorizadoJefe";
    }

    @RequestMapping(value = "/consultaProspectosFiltroSeguimientoJefe", method = RequestMethod.POST)
    public String consultaProspectosFiltroSeguimientoJefe(String razonSocial , String estatus , Model model) {
        logger.info("Ajax /consulta prospecto");
        //List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();

        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();

        JefeEjecutivo JefeEjecutivo = this.jefeEjecutivoManager.getJefeEjecutivoUser(claveJefe);

        List<Prospecto> prospectos = this.prospectoManager.getProspectoFiltroJefe(razonSocial, estatus,JefeEjecutivo.getId());
        model.addAttribute("prospecto", prospectos);
       // model.addAttribute("ejecutivo",ejecutivos);
        return "listaRevisaSeguimiento";
    }

    @RequestMapping(value = "/consultaProspectosFiltroSeguimiento", method = RequestMethod.POST)
    public String consultaProspectosFiltroSeguimiento(String razonSocial , String estatus , Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        List<Prospecto> prospectos = this.prospectoManager.getProspectoFiltro(razonSocial, estatus, ejecutivo.getId());
        model.addAttribute("prospecto", prospectos);
        return "tablaProspectoSeguimiento";
    }
    @RequestMapping(value = "/consultaProspectosFiltroRechazado", method = RequestMethod.POST)
    public String consultaProspectosFiltroRechazado(String razonSocial , String estatus , Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        List<Prospecto> prospectos = this.prospectoManager.getProspectoFiltro(razonSocial,estatus,ejecutivo.getId());
        model.addAttribute("prospecto", prospectos);
        return "tablaProspectoRechazado";
    }
    @RequestMapping(value = "/consultaProspectosJefe", method = RequestMethod.POST)
    public String consultaClientesJefe(String razonSocial , Model model) {
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        logger.info("lista de ejecutivos" + String.valueOf(ejecutivos));
        logger.info("Ajax /consulta prospecto");
        String id = "";
        model.addAttribute("prospectos", this.prospectoManager.getProspectosByIdNomJefe(id, razonSocial));
        model.addAttribute("ejecutivos", ejecutivos);
        return "tablaProspectoJefe";
    }

    @RequestMapping(value = "/consultaProspectosTodos", method = RequestMethod.POST)
    public String consultaProspectosTodos(Model model) {
        logger.info("Ajax /consulta prospecto");
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        String id = "";
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        model.addAttribute("prospectos", this.prospectoManager.getAll(ejecutivo.getId()));
        model.addAttribute("ejecutivos", ejecutivos);
        return "tablaProspecto";
    }
    @RequestMapping(value = "/consultaProspectosTodosJefe", method = RequestMethod.POST)
    public String consultaProspectosTodosJefe(Model model) {
        logger.info("Ajax /consulta prospecto");
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        String id = "";
        JefeEjecutivo jefeEjecutivoRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(usurec.getUsername());
        model.addAttribute("prospectos", this.prospectoManager.getAllJefe(jefeEjecutivoRec.getId()));
        model.addAttribute("ejecutivos", ejecutivos);
        model.addAttribute("claveUsuario",claveUsuario);

        return "tablaProspectoJefe";
    }
    @RequestMapping(value = "/consultaProspectoId", method = RequestMethod.POST)
    public String consultaProspectoId(int id, String razonSocial , Model model) {
        logger.info("Ajax /consulta prospecto");
        logger.info("Id"+id);
        logger.info("razon social"+razonSocial);
        Prospecto prospecto = this.prospectoManager.getProspectoByIdNom(id, razonSocial);
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        model.addAttribute("prospecto", prospecto);
        model.addAttribute("idProspecto", id);
        model.addAttribute("ejecutivos",ejecutivos);
        logger.info("Cantidad de Registros" + prospecto.getCallenum());
        return "consultaProspectoId";
    }
    @RequestMapping(value = "/autorizaProspecto", method = RequestMethod.POST)
     public String autorizaProspecto(int id, String razonSocial , Model model) {
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaAviso = formatFecha.format(date);
        final String hora = formatHora.format(date);

        logger.info("Ajax /consulta prospecto");
        logger.info("Id"+id);
        logger.info("razon social"+razonSocial);
        Prospecto prospecto = this.prospectoManager.getProspectoByIdNom(id, razonSocial);
        boolean respuesta = false;
        prospecto.setEstatus("3");
        prospecto.setActivo("1");
        respuesta=this.prospectoManager.updateProspecto(prospecto);
        model.addAttribute("inicial", "1");

        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        AvisosHistorial avisos = new AvisosHistorial();
        avisos.setIdProspecto(prospecto.getIdProspecto());
        avisos.setTipoNotificacion("Autoriza Prospecto");
        avisos.setClaveJefe(user.getUsername());
        avisos.setClaveEjecutivo("");
        avisos.setVisto(1);
        avisos.setFecha(fechaAviso+" "+hora);
        avisos.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE REALIZO LA AUTORIZACION DEL PROSPECTO :"+prospecto.getRazonSocial()+" REALIZADO POR : "+user.getUsername());
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);

        return "formaProspecto";
    }
    @RequestMapping(value = "/rechazaProspecto", method = RequestMethod.POST)
    public String rechazaProspecto(int id,String razonSocial , Model model) {
        logger.info("Ajax /consulta prospecto");
        logger.info("Id"+id);
        logger.info("razon social"+razonSocial);

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaAviso = formatFecha.format(date);
        final String hora = formatHora.format(date);

        Prospecto prospecto = this.prospectoManager.getProspectoByIdNom(id, razonSocial);
        boolean respuesta = false;
        prospecto.setEstatus("2");
        prospecto.setActivo("1");
        respuesta=this.prospectoManager.updateProspecto(prospecto);
        model.addAttribute("inicial", "1");

        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        AvisosHistorial avisos = new AvisosHistorial();
        avisos.setIdProspecto(prospecto.getIdProspecto());
        avisos.setTipoNotificacion("Rechazar Prospecto");
        avisos.setClaveJefe(user.getUsername());
        avisos.setClaveEjecutivo("");
        avisos.setVisto(1);
        avisos.setFecha(fechaAviso+" "+hora);
        avisos.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE REALIZO EL RECHAZO DEL PROSPECTO :"+prospecto.getRazonSocial()+" REALIZADO POR : "+user.getUsername());
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);

        return "formaProspectoJefe";
    }
    @RequestMapping(value = "/consultaProspectoAutorizado", method = RequestMethod.POST)
    public String consultaProspectoAutorizado(Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        List<Prospecto> prospecto = this.prospectoManager.getProspectosByActive("3",ejecutivo.getId());
        model.addAttribute("prospecto", prospecto);
        logger.info("Cantidad de Registros" + prospecto);
        return "tablaProspectoAutorizado";
    }

    @RequestMapping(value = "/consultaProspectoAutorizadoJefe", method = RequestMethod.POST)
    public String consultaProspectoAutorizadoJefe(Model model) {
        logger.info("Ajax /consulta prospecto");
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();

        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();


        JefeEjecutivo jefeEjecutivoRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(user.getUsername());

        List<Prospecto> prospecto = this.prospectoManager.getProspectosByActiveJefe("1",jefeEjecutivoRec.getId());
        model.addAttribute("prospectos", prospecto);
        model.addAttribute("ejecutivos",ejecutivos);
        logger.info("Cantidad de Registros"+prospecto);
        return "tablaProspectoAutorizadoJefe";
    }

    @RequestMapping(value = "/consultaProspectoSeguimiento", method = RequestMethod.POST)
    public String consultaProspectoSeguimiento(Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        List<Prospecto> prospecto = this.prospectoManager.getProspectosByActive("3", ejecutivo.getId());

        for (int i = 0; i < prospecto.size(); i++) {
            Prospecto eleProspecto = prospecto.get(i);
            Integer idProspectoRec = eleProspecto.getIdProspecto();
            List<Seguimiento> conteoSEg = this.seguimientoManager.getSeguimiento(idProspectoRec);
            if(conteoSEg.size()>0){
                prospecto.get(i).setNumConteo(conteoSEg.size());
            }else{
                prospecto.get(i).setNumConteo(0);
            }
        }

        model.addAttribute("prospecto", prospecto);
        logger.info("Cantidad de Registros"+prospecto);
        return "tablaProspectoSeguimiento";
    }

    @RequestMapping(value = "/consultaProspectoRechazado", method = RequestMethod.POST)
    public String consultaProspectoRechazado(Model model) {
        logger.info("Ajax /consulta prospecto");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        Ejecutivo ejecutivo=this.ejecutivoManager.getEjecutivo(claveUsuario);
        List<Prospecto> prospecto = this.prospectoManager.getProspectosByActive("2",ejecutivo.getId());
        //List<MotivoRechazoAutorizado> motivo = this.motivoRechazoAutorizadoManager.getAll();
        for (int i = 0; i < prospecto.size(); i++) {
            Prospecto eleProspecto = prospecto.get(i);
            Integer idProspectoRec = eleProspecto.getIdProspecto();
            int numMotivos = this.motivoRechazoAutorizadoManager.getAll(idProspectoRec);
            //List<Object> conteos = observacionesSeguimientoManager.getNumObservaciones(idProspectoRec,idSeguimiento);
            //if(conteos.size()>0){
                //conteos.get(0);
                //logger.info("Resultado de observaciones Seguimientos"+conteos.get(0));
                prospecto.get(i).setNumConteo(numMotivos);
            //}
        }
        model.addAttribute("prospecto", prospecto);
        logger.info("Cantidad de Registros"+prospecto);
        return "tablaProspectoRechazado";
    }
    @RequestMapping(value = "/numeroMotivos", method = RequestMethod.POST)
    public String numeroMotivos(int idProspecto, Model model){
        int motivo = this.motivoRechazoAutorizadoManager.getAll(idProspecto);
        logger.info("motivo " + motivo);
        model.addAttribute("motivo", motivo);
        return "numeroMotivos";
    }

    @RequestMapping(value = "/CapturaSeguimiento", method = RequestMethod.POST)
    public String CapturaSeguimiento(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        Prospecto prospecto= this.prospectoManager.getProspectoByIdNom(idProspecto, "");
        String etapa = "";
        logger.info("idEtapa " + prospecto.getIdEtapa());
        Integer idEtapa = 0;
        if(prospecto.getIdEtapa()>0){
            idEtapa = prospecto.getIdEtapa();
            etapa = this.etapaProspectoManager.getEtapaNombre(prospecto.getIdEtapa());
        }

        String formattedDate = formatter.format(date);
        model.addAttribute("now", formattedDate);
        model.addAttribute("idProspecto", idProspecto);
        model.addAttribute("etapa",etapa);
        model.addAttribute("idEtapa",idEtapa);
        return "CapturaSeguimiento";
    }

    @RequestMapping(value = "/CapturaMotivo", method = RequestMethod.POST)
    public String CapturaMotivo(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        String formattedDate = formatter.format(date);
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("now", formattedDate);
        model.addAttribute("idProspecto", idProspecto);
        model.addAttribute("claveUsuario",claveUsuario);
        return "CapturaMotivo";
    }

	@RequestMapping(value = "/CapturaObservacionJefe", method = RequestMethod.POST)
    public String CapturaObservacionJefe(Locale locale,@RequestParam(value = "idSeguimiento")int idSeguimiento,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        String formattedDate = formatter.format(date);
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for (GrantedAuthority autority : user.getAuthorities()) {
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        List<ObservacionesSeguimiento> observaciones = this.observacionesSeguimientoManager.getObservaciones(idProspecto,idSeguimiento);

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("now", formattedDate);
        model.addAttribute("idSeguimiento", idSeguimiento);
        model.addAttribute("idProspecto", idProspecto);
        model.addAttribute("claveUsuario", claveUsuario);
        model.addAttribute("observaciones", observaciones);
        return "CapturaObservacionJefe";
    }

    @RequestMapping(value = "/CapturaMotivoJefe", method = RequestMethod.POST)
    public String CapturaMotivoJefe(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        String formattedDate = formatter.format(date);
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("now", formattedDate);
        model.addAttribute("idProspecto", idProspecto);
        model.addAttribute("claveUsuario",claveUsuario);
        return "CapturaMotivoJefe";
    }
    @RequestMapping(value = "/ae_RegistraMotivo", method = RequestMethod.POST)
    public String RegistraMotivo(Model model, @RequestParam(value = "idProspecto")int idProspecto,
                                      @RequestParam(value = "fecha")String fecha,@RequestParam(value = "clave")String clave,
                                      @RequestParam(value = "motivo")String motivo,@RequestParam(value = "evento")String evento){

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaAviso = formatFecha.format(date);
        final String hora = formatHora.format(date);

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
        int idJefeRec = EjecutivoRec.getIdJefe();
        JefeEjecutivo jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivo(idJefeRec);
        String cveJefeEjecutivoreg = jefeEjecRec.getUsuario();
        String cveEjecutivo = claveUsuario;

        MotivosRechazoAutorizado motivoRechazoAutorizado= new MotivosRechazoAutorizado();
        boolean respuesta=false;
        motivoRechazoAutorizado.setIdProspecto(idProspecto);
        motivoRechazoAutorizado.setFecha(fecha);
        motivoRechazoAutorizado.setClaveUsuario(clave);
        motivoRechazoAutorizado.setMotivo(motivo);
        motivoRechazoAutorizado.setTipoEvento(evento);
        motivoRechazoAutorizado.setSolicitudMotivo(1);
        respuesta=this.motivoRechazoAutorizadoManager.insertMotivo(motivoRechazoAutorizado);

        AvisosHistorial avisos = new AvisosHistorial();
        avisos.setIdProspecto(idProspecto);
        avisos.setTipoNotificacion("Solicitud de Reactivacion");
        avisos.setClaveJefe(cveJefeEjecutivoreg);
        avisos.setClaveEjecutivo(cveEjecutivo);
        avisos.setVisto(0);
        avisos.setFecha(fechaAviso+" "+hora);
        avisos.setObservacion("*IDPROSPECTO:"+idProspecto+"* SE SOLICITO REACTIVAR AL PROSPECTO CON EL ID :"+idProspecto+" REALIZADO POR : "+cveEjecutivo+" Y SU JEFE ES :"+cveJefeEjecutivoreg);
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);

        return "CapturaMotivo";

    }
    @RequestMapping(value = "/ae_RegistraMotivoJefe", method = RequestMethod.POST)
    public String RegistraMotivoJefe(Model model, @RequestParam(value = "idProspecto")int idProspecto,
                                 @RequestParam(value = "fecha")String fecha,@RequestParam(value = "clave")String clave,
                                 @RequestParam(value = "motivo")String motivo,@RequestParam(value = "evento")String evento){
        MotivosRechazoAutorizado motivoRechazoAutorizado= new MotivosRechazoAutorizado();
        boolean respuesta=false;
        motivoRechazoAutorizado.setIdProspecto(idProspecto);
        motivoRechazoAutorizado.setFecha(fecha);
        motivoRechazoAutorizado.setClaveUsuario(clave);
        motivoRechazoAutorizado.setMotivo(motivo);
        motivoRechazoAutorizado.setTipoEvento(evento);
        motivoRechazoAutorizado.setSolicitudMotivo(2);
        respuesta=this.motivoRechazoAutorizadoManager.insertMotivo(motivoRechazoAutorizado);

        return "CapturaMotivoJefe";

    }

	@RequestMapping(value = "/RegistraObservacionJefe", method = RequestMethod.POST)
    public String RegistraObservacionJefe(Model model, @RequestParam(value = "idSeguimiento")int idSeguimiento,
                                     @RequestParam(value = "fecha")String fecha,@RequestParam(value = "clave")String clave,
                                     @RequestParam(value = "observacion")String observacion,@RequestParam(value = "idProspecto")int idProspecto,
                                          @RequestParam(value = "idEjecutivo")int idEjecutivo){
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha2 = formatFecha.format(date);
        final String hora = formatHora.format(date);

        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        JefeEjecutivo jefeEjecRec = null;
        if(claveUsuario!=null) {
            Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
            int idJefeRec = EjecutivoRec.getIdJefe();
            jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivo(idJefeRec);
        }

        ObservacionesSeguimiento observacionesSeguimiento = new ObservacionesSeguimiento();
        observacionesSeguimiento.setClaveUsuario(user.getUsername());
        observacionesSeguimiento.setFecha(fecha);
        observacionesSeguimiento.setIdSeguimiento(idSeguimiento);
        observacionesSeguimiento.setObservacion(observacion);
        observacionesSeguimiento.setIdProspecto(idProspecto);
        boolean resp =this.observacionesSeguimientoManager.insertObservacion(observacionesSeguimiento);
        AvisosHistorial avisos = new AvisosHistorial();
        avisos.setIdProspecto(idProspecto);
        avisos.setTipoNotificacion("Observacion Seguimiento");
        String cveJefeEjecutivoreg = "";
        if(jefeEjecRec == null){
            cveJefeEjecutivoreg = user.getUsername();
        }else{
            cveJefeEjecutivoreg = jefeEjecRec.getUsuario();
        }
        avisos.setClaveJefe(cveJefeEjecutivoreg);
        String cveEjecutivo = "";
        if(claveUsuario!=null){
            cveEjecutivo = claveUsuario;
        }else{
            Ejecutivo ejRecJefe = this.ejecutivoManager.getEjecutivoId(idEjecutivo);
            cveEjecutivo = ejRecJefe.getClaveEjecutivo();
        }
        avisos.setClaveEjecutivo("");
        avisos.setVisto(1);
        avisos.setFecha(fecha2+" "+hora);
        avisos.setObservacion("*IDPROSPECTO:"+idProspecto+"* SE REGISTRO UNA OBSERVACION A UN SEGUIMIENTO DEL PROSPECTO CON ID :"+idProspecto+" HECHO POR :"+cveJefeEjecutivoreg);
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);
        model.addAttribute("Respuesta", "Prospecto registrado exitosamente");
        if (resp && resp2) return "CapturaObservacionJefe";
        else return "error";

    }

    @RequestMapping(value = "/ae_RegistraSeguimiento", method = RequestMethod.POST)
    public String RegistraSeguimiento(Model model, @RequestParam(value = "idProspecto")int idProspecto,
        @RequestParam(value = "fecha")String fecha,@RequestParam(value = "etapa")String etapa,
        @RequestParam(value = "seguimiento")String seguimiento,@RequestParam(value = "tipoSeguimiento")String tipoSeguimiento,
        @RequestParam(value = "idetapa")int idetapa){
        Seguimiento seguimiento1=new Seguimiento();
        Prospecto prospecto = this.prospectoManager.getProspectoByIdNom(idProspecto,"");
        //cambiar estatus del estatus
        Integer idEtapaInicial = prospecto.getIdEtapa();
        boolean respuesta=false;
        boolean resp=false;

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaAviso = formatFecha.format(date);
        final String hora = formatHora.format(date);

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
        int idJefeRec = EjecutivoRec.getIdJefe();
        JefeEjecutivo jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivo(idJefeRec);
        String cveJefeEjecutivoreg = jefeEjecRec.getUsuario();
        String cveEjecutivo = claveUsuario;

        prospecto.setIdEtapa(idetapa);
        seguimiento1.setFecha(fecha);
        seguimiento1.setEtapaSeguimiento(etapa);
        seguimiento1.setIdProspecto(idProspecto);
        seguimiento1.setSeguimiento(seguimiento);
        seguimiento1.setTipoSeguimiento(tipoSeguimiento);
        respuesta=this.seguimientoManager.insertSeguimiento(seguimiento1);
        resp=this.prospectoManager.updateProspecto(prospecto);

        if(idetapa!=idEtapaInicial){
            AvisosHistorial avisos = new AvisosHistorial();
            avisos.setIdProspecto(prospecto.getIdProspecto());
            avisos.setTipoNotificacion("Cambio de Etapa");
            avisos.setClaveJefe(cveJefeEjecutivoreg);
            avisos.setClaveEjecutivo(cveEjecutivo);
            avisos.setVisto(0);
            avisos.setFecha(fechaAviso+" "+hora);
            avisos.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE REGISTRO UN CAMBIO DE ETAPA PROSPECTO :"+prospecto.getRazonSocial()+" LA ETAPA NUEVA ES : "+etapa+" HECHO POR EL  :"+cveEjecutivo+" Y SU JEFE ES :"+cveJefeEjecutivoreg);
            boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);
        }

        AvisosHistorial avisos2 = new AvisosHistorial();
        avisos2.setIdProspecto(prospecto.getIdProspecto());
        avisos2.setTipoNotificacion("Nuevo Seguimiento");
        avisos2.setClaveJefe(cveJefeEjecutivoreg);
        avisos2.setClaveEjecutivo(cveEjecutivo);
        avisos2.setVisto(0);
        avisos2.setFecha(fechaAviso+" "+hora);
        avisos2.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE REGISTRO NUEVO SEGUIMIENTO PARA EL PROSPECTO :"+prospecto.getRazonSocial()+" HECHO POR EL  :"+cveEjecutivo+" Y SU JEFE ES :"+cveJefeEjecutivoreg);
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos2);

        return "CapturaSeguimiento";

    }
    @RequestMapping(value = "/ListaSeguimiento", method = RequestMethod.POST)
     public String ListaSeguimiento(@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        Prospecto prospecto=  this.prospectoManager.getProspectoByIdNom(idProspecto,"");
        List<Seguimiento> seguimiento= this.seguimientoManager.getSeguimiento(prospecto.getIdProspecto());
        if(seguimiento.size()>0) {
            for (int i = 0; i < seguimiento.size(); i++) {
                Seguimiento eleSeguimiento = seguimiento.get(i);
                Integer idSeguimiento = eleSeguimiento.getId();
                Integer idProspectoRec = eleSeguimiento.getIdProspecto();
                List<Object> conteos = observacionesSeguimientoManager.getNumObservaciones(idProspectoRec,idSeguimiento);
                if(conteos.size()>0){
                    //conteos.get(0);
                    //logger.info("Resultado de observaciones Seguimientos"+conteos.get(0));
                    seguimiento.get(i).setNumeroObservaciones(Integer.parseInt(conteos.get(0).toString()));
                }
            }
        }

        model.addAttribute("prospecto",prospecto);
        model.addAttribute("seguimiento",seguimiento);
        return "tablaSeguimiento";
    }

    @RequestMapping(value = "/listaRevisaSeguimiento", method = RequestMethod.POST)
    public String ListaRevisaSeguimiento(Model model) {
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();


        JefeEjecutivo jefeEjecutivoRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(user.getUsername());

        List<Prospecto>prospecto=  this.prospectoManager.getProspectosByActiveJefe("3",jefeEjecutivoRec.getId());
        List<Seguimiento>seguimientos=this.seguimientoManager.getAll();

        for (int i = 0; i < prospecto.size(); i++) {
            Prospecto eleProspecto = prospecto.get(i);
            Integer idProspectoRec = eleProspecto.getIdProspecto();
            List<Seguimiento> conteoSEg = this.seguimientoManager.getSeguimiento(idProspectoRec);
            if(conteoSEg.size()>0){
                prospecto.get(i).setNumConteo(conteoSEg.size());
            }else{
                prospecto.get(i).setNumConteo(0);
            }
        }

        model.addAttribute("prospecto",prospecto);
        model.addAttribute("seguimiento",seguimientos);
        return "listaRevisaSeguimiento";
    }
    @RequestMapping(value = "/formaSeguimiento", method = RequestMethod.POST)
    public String formaSeguimiento(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        List<Seguimiento> seguimiento = this.seguimientoManager.getSeguimiento(idProspecto);
        /*model.addAttribute("now", seguimiento.getFecha());
        model.addAttribute("idSeguimiento", seguimiento.getId());
        model.addAttribute("etapa",seguimiento.getEtapaSeguimiento());
        model.addAttribute("segui",seguimiento.getSeguimiento());
        model.addAttribute("tipoSeg",seguimiento.getTipoSeguimiento());*/
        return "formaSeguimiento";
    }
    @RequestMapping(value = "/ae_consultaProspectoAceptado", method = RequestMethod.GET)
    public String consultaProspectoAceptado(Model model) {
        logger.info("Ajax /consulta prospecto");
        List<Prospecto> prospecto = this.prospectoManager.getAllFail(5);
        List<Ejecutivo> ejecutivos =this.ejecutivoManager.getAll();
        model.addAttribute("prospectos", prospecto);
        model.addAttribute("ejecutivos",ejecutivos);
        logger.info("Cantidad de Registros"+prospecto);

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "tablaProspectoAceptado";
    }

    @RequestMapping(value = "/reportesprospectos", method = RequestMethod.GET)
    public String reportesprospectos(Model model) {
        logger.info("Reportes prospectos Etapa por EJecutivo");

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
        int idJefeRec = EjecutivoRec.getIdJefe();

        List<Object>repEjecutivoEstausEjecutivo= this.prospectoManager.reporteEjecutivoEstatusFE(claveUsuario,idJefeRec);
        List<Object>repEjecutivoEtapasEjecutivo= this.prospectoManager.reporteEjecutivoEtapasFE(claveUsuario,idJefeRec);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(user.getUsername());
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(user.getUsername());
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        model.addAttribute("repEjecutivoEstatusEjecutivo", repEjecutivoEstausEjecutivo);
        model.addAttribute("repEjecutivoEtapasEjecutivo", repEjecutivoEtapasEjecutivo);

        return "reportesprospectos";
    }

    @RequestMapping(value = "/reportesprospectosJefe", method = RequestMethod.GET)
    public String reportesprospectosJefe(Model model) {
        logger.info("Reportes prospectos");


        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        JefeEjecutivo jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(user.getUsername());
        int idJefeEjecutivo = jefeEjecRec.getId();

        List<Object>repEjecutivoEstatus= this.prospectoManager.reporteEjecutivoEstatus(idJefeEjecutivo);
        List<Object>repEjecutivoEtapas= this.prospectoManager.reporteEjecutivoEtapas(idJefeEjecutivo);
        List<Object>repEstatus= this.prospectoManager.reportePorEstatus(idJefeEjecutivo);
        List<Object>repEtapas= this.prospectoManager.reportePorEtapas(idJefeEjecutivo);

        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAllJefe(idJefeEjecutivo);
        logger.debug("ejecutivos: " + String.valueOf(ejecutivos));
        model.addAttribute("ejecutivos",ejecutivos);

        model.addAttribute("repEjecutivoEstatus", repEjecutivoEstatus);
        model.addAttribute("repEjecutivoEtapas", repEjecutivoEtapas);
        model.addAttribute("repEstatus", repEstatus);
        model.addAttribute("repEtapas", repEtapas);

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        //User user = (User) SecurityContextHolder.getContext().
        //        getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "reportesprospectosJefe";
    }


    @RequestMapping(value = "/cambiaVisto", method = RequestMethod.POST)
    public String cambiaVisto(@Valid Prospecto prospecto,BindingResult result,@RequestParam(value = "tipoAviso")String tipoAviso  ,Model model) {

        boolean updateAviso = false;
        updateAviso = this.avisosHistorialManager.updateAvisosHistorialVisto(tipoAviso);

        if (updateAviso == true) return "homevisto";
        else return "error";
    }

    @RequestMapping(value = "/ae_formaProspectoReAutorizaJefe", method = RequestMethod.GET)
    public String ae_formaProspectoReAutorizaJefe(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","2");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoReAutorizadoJefe";
    }

    @RequestMapping(value = "/consultaProspectoReAutorizadoJefe", method = RequestMethod.POST)
     public String consultaProspectoReAutorizadoJefe(Model model) {
        logger.info("Ajax /consulta prospecto");
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();


        JefeEjecutivo jefeEjecutivoRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(user.getUsername());
        List<Prospecto> prospecto = this.prospectoManager.getProspectosByActiveJefe("2",jefeEjecutivoRec.getId());
        model.addAttribute("prospectos", prospecto);
        model.addAttribute("ejecutivos",ejecutivos);
        logger.info("Cantidad de Registros"+prospecto);
        return "tablaProspectoReAutorizadoJefe";
    }


    @RequestMapping(value = "/consultaExistentesByNombre", method = RequestMethod.POST)
    public String consultaExistentesByNombre(@RequestParam(value = "nombre")String nombre,Model model) {
        List<Prospecto> prospectos=  this.prospectoManager.getByRazonSocial(nombre);
        logger.info("Prospectos " + nombre);
        Integer conteoProspectos = 0;
        if(prospectos.size()>0) {
            conteoProspectos = prospectos.size();
        }

        model.addAttribute("prospectos",prospectos);
        model.addAttribute("conteoProspectos",conteoProspectos);
        return "consultaExistentesByNombre";
    }


    @RequestMapping(value = "/desasignaProspecto", method = RequestMethod.POST)
    public String desasignaProspecto(@RequestParam(value = "idProspecto")Integer idProspecto,Model model) {
        Prospecto prospecto=  this.prospectoManager.getProspectoByIdNom(idProspecto, "");
        logger.info("Prospecto " + idProspecto);
        boolean exitoso = true;
        if(!prospecto.equals(null)) {
            prospecto.setActivo("2");
            exitoso = this.prospectoManager.updateProspecto(prospecto);
            if(exitoso){
                final Date date = new Date();
                final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
                final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
                final String fecha = formatFecha.format(date);
                final String hora = formatHora.format(date);

	            /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
                User user = (User) SecurityContextHolder.getContext().
                        getAuthentication().getPrincipal();
                List authorities = new ArrayList();

                for(GrantedAuthority autority : user.getAuthorities()){
                    authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
                }

                com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
                String claveUsuario = usurec.getIdEjecutivo();

                JefeEjecutivo jefeEjecRec = null;
                if(claveUsuario!=null) {
                    Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
                    int idJefeRec = EjecutivoRec.getIdJefe();
                    jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivo(idJefeRec);
                }

                AvisosHistorial avisos = new AvisosHistorial();
                avisos.setIdProspecto(prospecto.getIdProspecto());
                avisos.setTipoNotificacion("Prospecto Desasignado");
                String cveJefeEjecutivoreg = "";
                if(jefeEjecRec == null){
                    cveJefeEjecutivoreg = user.getUsername();
                }else{
                    cveJefeEjecutivoreg = jefeEjecRec.getUsuario();
                }
                avisos.setClaveJefe(cveJefeEjecutivoreg);
                String cveEjecutivo = "";
                if(claveUsuario!=null){
                    cveEjecutivo = claveUsuario;
                }else{
                    Ejecutivo ejRecJefe = this.ejecutivoManager.getEjecutivoId(prospecto.getIdEjecutivo());
                    cveEjecutivo = ejRecJefe.getClaveEjecutivo();
                }
                avisos.setClaveEjecutivo(cveEjecutivo);
                avisos.setVisto(0);
                avisos.setFecha(fecha+" "+hora);
                String cveEjecutivoReg = "";
                if(cveEjecutivo==""){
                    cveEjecutivoReg = cveJefeEjecutivoreg;
                }else{
                    cveJefeEjecutivoreg = cveEjecutivo;
                }
                avisos.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE DESASIGNO EL PROSPECTO :"+prospecto.getRazonSocial()+" Y LO DESASIGNO EL USUARIO :"+cveEjecutivoReg+" Y SU JEFE ES :"+cveJefeEjecutivoreg);
                boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);
                return "exito";
            }else{
                return "error";
            }
        }else{
            return "error";
        }
    }

    @RequestMapping(value = "/ae_formaProspectoDesasignado", method = RequestMethod.GET)
    public String ae_formaProspectoDesasignado(Model model) {
        logger.info("forma inicio prospecto ");
        model.addAttribute("inicial","2");

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);

        return "formaProspectoDesasignado";
    }


    @RequestMapping(value = "/consultaProspectosDesasignado", method = RequestMethod.POST)
    public String consultaProspectosDesasignado(String nombre, Model model) {
        logger.info("Ajax /Tabla Desasignado");
        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        logger.info("prospectos Razon social " + nombre.trim());
        JefeEjecutivo jefeEjecutivoRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(user.getUsername());
        List<Prospecto> prospecto = this.prospectoManager.getProspectoFiltroDesasignadoJefe(nombre.trim(), jefeEjecutivoRec.getId());

        model.addAttribute("prospectos", prospecto);
        return  "tablaProspectoDesasignado";
    }


    @RequestMapping(value = "/CapturaReAsignacion", method = RequestMethod.POST)
    public String CapturaReAsignacion(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        String formattedDate = formatter.format(date);

        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        model.addAttribute("now", formattedDate);
        logger.debug("ejecutivos: " + String.valueOf(ejecutivos));
        model.addAttribute("ejecutivos",ejecutivos);
        model.addAttribute("idProspecto",idProspecto);

        return "CapturaReAsignacion";
    }


    @RequestMapping(value = "/registraReAsignacion", method = RequestMethod.POST)
    public String registraReAsignacion(Model model, @RequestParam(value = "idProspecto")int idProspecto,
                                 @RequestParam(value = "fecha")String fecha,@RequestParam(value = "idEjecutivo")int idEjecutivo){

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaAviso = formatFecha.format(date);
        final String hora = formatHora.format(date);

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
        int idJefeRec = EjecutivoRec.getIdJefe();
        JefeEjecutivo jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivo(idJefeRec);
        String cveJefeEjecutivoreg = jefeEjecRec.getUsuario();
        String cveEjecutivo = claveUsuario;

        Prospecto prospecto= new Prospecto();
        prospecto = this.prospectoManager.getProspectoByIdNom(idProspecto,"");

        boolean respuesta=false;
        prospecto.setActivo("1");
        prospecto.setIdEjecutivo(idEjecutivo);


        respuesta=this.prospectoManager.updateProspecto(prospecto);

        AvisosHistorial avisos = new AvisosHistorial();
        avisos.setIdProspecto(idProspecto);
        avisos.setTipoNotificacion("Re-Asignacion De Prospecto");
        avisos.setClaveJefe(user.getUsername());
        avisos.setClaveEjecutivo(cveEjecutivo);
        avisos.setVisto(1);
        avisos.setFecha(fechaAviso+" "+hora);
        avisos.setObservacion("*IDPROSPECTO:"+prospecto.getIdProspecto()+"* SE REASIGNO EL PROSPECTO :"+prospecto.getRazonSocial()+" AL EJECUTIVO :"+idEjecutivo+" Y LO ASIGNO ES :"+user.getUsername());
        boolean resp2 =this.avisosHistorialManager.setIngresaAvisosHistorial(avisos);

        return "CapturaReAsignacion";

    }



    @RequestMapping(value = "/RecDetEtapaEjecutivo", method = RequestMethod.POST)
    public String RecDetEtapaEjecutivo(@RequestParam(value = "cveEjecutivo")String cveEjecutivo,Model model) {
        logger.info("Reportes prospectos Etapa por EJecutivo");

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }


        Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(cveEjecutivo);
        int idJefeRec = EjecutivoRec.getIdJefe();

        List<Object>repEjecutivoEtapasEjecutivo= this.prospectoManager.reporteEjecutivoEtapasFE(cveEjecutivo,idJefeRec);

        model.addAttribute("repEjecutivoEtapasEjecutivo", repEjecutivoEtapasEjecutivo);

        return "RecDetEtapaEjecutivo";
    }

    @RequestMapping(value = "/RecDetEstatusEjecutivo", method = RequestMethod.POST)
    public String RecDetEstatusEjecutivo(@RequestParam(value = "cveEjecutivo")String cveEjecutivo,Model model) {
        logger.info("Reportes prospectos Etapa por EJecutivo");

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }


        Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(cveEjecutivo);
        int idJefeRec = EjecutivoRec.getIdJefe();

        List<Object>repEjecutivoEstausEjecutivo= this.prospectoManager.reporteEjecutivoEstatusFE(cveEjecutivo,idJefeRec);

        model.addAttribute("repEjecutivoEstatusEjecutivo", repEjecutivoEstausEjecutivo);

        return "RecDetEstatusEjecutivo";
    }

    @RequestMapping(value = "/reporteDetalleProspectos", method = RequestMethod.GET)
    public String reporteDetalleProspectos(Model model) {
        logger.info("Reportes Detalle Prospecto");

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        List<Prospecto> prospectosr= null;
        String tipoUsuario = "";
        if (claveUsuario != null && !claveUsuario.equals("")) {
            Ejecutivo EjecutivoRec = this.ejecutivoManager.getEjecutivo(claveUsuario);
            int idEjecutivoRec = EjecutivoRec.getId();
            prospectosr = this.prospectoManager.getAll(idEjecutivoRec);
            tipoUsuario = "ejecutivo";
        }else{
            JefeEjecutivo jefeEjecRec = this.jefeEjecutivoManager.getJefeEjecutivoUser(user.getUsername());
            int idJefeRec = jefeEjecRec.getId();
            prospectosr = this.prospectoManager.getAllJefe(idJefeRec);
            tipoUsuario = "jefe";
        }

        model.addAttribute("prospectosgen", prospectosr);

        //RECUPERA LOS DATOS  PARA LOS AVISOS
        String claveJefe = user.getUsername();
        List<Object> totalAvisosObj = this.avisosHistorialManager.getTotalAvisos(claveJefe);
        List<Object> listaAvisos = this.avisosHistorialManager.getAvisosLista(claveJefe);
        List<Object> listaAvisosDetalle = this.avisosHistorialManager.getAvisosListaDetalle(claveJefe);

        Object TotalAvisosT = null;
        if(totalAvisosObj.size()!=0) {
            TotalAvisosT = totalAvisosObj.get(0);
        }
        model.addAttribute("totalAvisos", TotalAvisosT);
        model.addAttribute("listaAvisos", listaAvisos);
        model.addAttribute("listaAvisosDetalle", listaAvisosDetalle);

        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesAgrupados = this.mensajesManager.getMiMensajesXUsuario(claveJefe);
        List<Mensajes> MensajesTodos = this.mensajesManager.getMisMensajes(claveJefe);
        //logger.info("MensajesAgrupados .", MensajesAgrupados.size());
        //logger.info("MensajesTodos.", MensajesTodos.size());
        model.addAttribute("MensajesAgrupados", MensajesAgrupados);
        model.addAttribute("MensajesTodos", MensajesTodos);
        model.addAttribute("tipoUsuario", tipoUsuario);

        return "reporteDetalleProspectos";
    }

    @RequestMapping(value = "/buscaDetalleHistoria", method = RequestMethod.POST)
    public String buscaDetalleHistoria(@RequestParam(value = "fechaIni")String fechaIni,@RequestParam(value = "fechaFin")String fechaFin,
                                       @RequestParam(value = "idProspecto")String idProspecto,Model model) throws ParseException {
        logger.info("Reportes Historial prospectos");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = fechaIni;
        String dateInString2 = fechaFin;
        String fechaInicioR ="";
        String fechaFinR = "";

        try {

            Date date = formatter.parse(dateInString);
            Date date2 = formatter.parse(dateInString2);

            final SimpleDateFormat formatterstr = new SimpleDateFormat("yyyy-MM-dd");
            fechaInicioR = formatterstr.format(date);
            fechaFinR = formatterstr.format(date2);

            logger.info("***** fechaInicioR " + fechaInicioR);
            logger.info("***** fechaFinR " + fechaFinR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();

        List<Object> historial= null;

        if (claveUsuario != null && !claveUsuario.equals("")) {
            historial = this.prospectoManager.reporteHistorial(fechaInicioR+" 00:00:00", fechaFinR+" 23:59:59", idProspecto, "");
        }else{
            if(idProspecto!=""){
                historial = this.prospectoManager.reporteHistorial(fechaInicioR+" 00:00:00", fechaFinR+" 23:59:59", idProspecto, "");
            }else{
                historial = this.prospectoManager.reporteHistorial(fechaInicioR+" 00:00:00", fechaFinR+" 23:59:59", idProspecto, user.getUsername());
            }
        }

        model.addAttribute("historial", historial);

        return "buscaDetalleHistoria";
    }

}
