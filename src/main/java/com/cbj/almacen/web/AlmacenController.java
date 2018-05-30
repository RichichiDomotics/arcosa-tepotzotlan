package com.cbj.almacen.web;

import com.cbj.almacen.domain.Ejecutivo;
import com.cbj.almacen.domain.Mensajes;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
public class AlmacenController {

    private static final Logger logger = LoggerFactory
	    .getLogger(AlmacenController.class);

    @Autowired
    private ProductManager productManager;

    @Autowired
    private AvisosHistorialManager avisosHistorialManager;

    @Autowired
    private UsuarioManager usuarioManager;

    @Autowired
    private EjecutivoManager ejecutivoManager;

    @Autowired
    private MensajesManager mensajesManager;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
	logger.info("Welcome home :D :D :D! The client locale is {}.", locale);
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
       // User mediUser = (User) new UserDetailServiceImpl().loadUserByUsername(user.getUsername());

        //AQUI YA SE TENDRIAN LOS VALORES EN EL OBJENTO NUEVO DE usuario

        //logger.info(String.valueOf(mediUser.getUsername()));
                /*
        String roles = "";
        logger.info(user.getIdExtra()+":");
        for(GrantedAuthority autority : user.getAuthorities()){
            roles += autority.getAuthority()+",";
        }*/
    String claveJefe = user.getUsername();
	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);
	model.addAttribute("now", formattedDate);
	model.addAttribute("products", this.productManager.getProducts());
   // model.addAttribute("user",user.getUsername() + " : "+roles);

   //RECUPERA LOS DATOS  PARA LOS AVISOS
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
   logger.info("MensajesAgrupados .", MensajesAgrupados.size());
   logger.info("MensajesTodos.", MensajesTodos.size());
   model.addAttribute("MensajesAgrupados", MensajesAgrupados);
   model.addAttribute("MensajesTodos", MensajesTodos);

	return "home";
    }


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
  
        return "login";
  
    } 
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String inicio(Model model) {
  
        return "login";
  
    } 
    
    @RequestMapping(value="/403", method = RequestMethod.GET)
    public String accesodenegado(Model model) {
    	final String mensajeError = "no tienes permiso para ingresar en esta pagina!";
		model.addAttribute("message",mensajeError);
        return "403";
  
    }     
    
    @RequestMapping(value="/loginError", method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("error", "true");
        return "login";
  
    }

    @RequestMapping(value = "/CapturaMensaje", method = RequestMethod.POST)
    public String CapturaMensaje(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,Model model) {
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

        List<Ejecutivo> ejecutivos = this.ejecutivoManager.getAll();
        model.addAttribute("ejecutivos",ejecutivos);

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("now", formattedDate);
        model.addAttribute("idProspecto", idProspecto);
        model.addAttribute("claveUsuario",user.getUsername());
        return "CapturaMensaje";
    }

    @RequestMapping(value = "/registraMensaje", method = RequestMethod.POST)
    public String registraMensaje(Model model, @RequestParam(value = "idProspecto")int idProspecto,
                                 @RequestParam(value = "fecHoraEnviado")String fecHoraEnviado,@RequestParam(value = "usuarioEnvia")String usuarioEnvia,
                                 @RequestParam(value = "usuarioRecibe")String usuarioRecibe,@RequestParam(value = "mensaje")String mensaje){

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaHoraEnviado = formatFecha.format(date);
        final String hora = formatHora.format(date);

        String fecHoraEnviadoReg = fechaHoraEnviado+" "+hora;

        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByIdEjecutivo(usuarioRecibe);
        String claveUsuario = usurec.getUsername();
        logger.info("claveUsuario valor " + claveUsuario);
        if(claveUsuario==null){
            claveUsuario = usuarioRecibe;
        }
        Mensajes MensajesReg= new Mensajes();
        boolean respuesta=false;
        MensajesReg.setUsuarioEnvia(usuarioEnvia);
        MensajesReg.setUsuarioRecibe(claveUsuario);
        MensajesReg.setMensaje(mensaje);
        MensajesReg.setFecHoraEnviado(fecHoraEnviadoReg);
        respuesta=this.mensajesManager.setIngresaMensajes(MensajesReg);

        return "CapturaMensaje";

    }


    @RequestMapping(value = "/RecuperaTodosMensajes", method = RequestMethod.POST)
    public String RecuperaTodosMensajes(Locale locale,Model model) {
        /*TRAE DATOS DEL USUARIO FIRMADO PARA RECUPERAR SU IDEJECUTIVO*/
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        String userNameRec = user.getUsername();
        //RECUPERA LOS MENSAJES
        List<Mensajes> MensajesTodosRec = this.mensajesManager.getTodosMisMensajes(userNameRec);
        logger.info("MensajesTodosRec size. "+ MensajesTodosRec.size());
        model.addAttribute("MensajesTodosRec", MensajesTodosRec);

        return "RecuperaTodosMensajes";
    }

    @RequestMapping(value = "/RecuperaMensaje", method = RequestMethod.POST)
    public String RecuperaMensaje(Locale locale,@RequestParam(value = "idMensaje")int idMensaje,Model model) {

        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fechaHoraEnviado = formatFecha.format(date);
        final String hora = formatHora.format(date);

        String fecHoraEnviadoReg = fechaHoraEnviado+" "+hora;

        Mensajes mensajeRec = this.mensajesManager.getMisMensajesXId(idMensaje);
        /*MARCA COMO LEIDO EL MENSAJE*/
        mensajeRec.setFechaHoraVisto(fecHoraEnviadoReg);
        boolean resUpdate = this.mensajesManager.updateIngresaMensajes(mensajeRec);
        model.addAttribute("mensajeRec",mensajeRec);
        logger.info("mensajeRec.", mensajeRec.getMensaje());
        return "RecuperaMensaje";
    }

    @RequestMapping(value = "/ContestaMensaje", method = RequestMethod.POST)
    public String ContestaMensaje(Locale locale,@RequestParam(value = "idProspecto")int idProspecto,@RequestParam(value = "usuarioRecibe")String usuarioRecibe,
                                  @RequestParam(value = "usuarioEnvia")String usuarioEnvia,Model model) {
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

        //com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        //String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("now", formattedDate);
        model.addAttribute("idProspecto", idProspecto);
        model.addAttribute("usuarioEnvia",usuarioEnvia);
        model.addAttribute("usuarioRecibe",usuarioRecibe);
        return "ContestaMensaje";
    }


}
