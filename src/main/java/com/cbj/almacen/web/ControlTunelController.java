package com.cbj.almacen.web;

import com.cbj.almacen.domain.ControlTunel;
import com.cbj.almacen.domain.Mensajes;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jolvera on 22/07/2014.
 */
@Controller
public class ControlTunelController {

    private static final Logger logger = LoggerFactory
            .getLogger(VehiculoController.class);

    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ControlTunelManager controlTunelManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private AvisosHistorialManager avisosHistorialManager;
    @Autowired
    private MensajesManager mensajesManager;

    private ArrayList<String> periodo = new ArrayList<String>();

    @RequestMapping(value = "/controlTunel", method = RequestMethod.POST)
    public String controlTunel(Model model, @RequestParam(value = "fecha")Date fecha) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String nvafecha = simpleDateFormat.format(fecha);
        model.addAttribute("fecha",nvafecha);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("periodo", periodo);
        model.addAttribute("listaTunel", this.controlTunelManager.getControlTunelAll());
        //RECUPERA LOS DATOS  PARA LOS AVISOS
        /*User user = (User) SecurityContextHolder.getContext().
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
        model.addAttribute("MensajesTodos", MensajesTodos);*/

        return "controlTunel";
        }

    @RequestMapping(value = "/calendarioTunel", method = RequestMethod.GET)
    public String calendarioTunel(Locale locale, Model model){
        logger.info("Comienza calendario para el Tunel");
        List<ControlTunel> controlTunels=this.controlTunelManager.getControlTunelAll();
        //logger.info("fecha: "+controlTunels.get(0).getFechaCapturada());
        model.addAttribute("listaTunel", this.controlTunelManager.getControlTunelAll());
        return "calendarioTunel";
    }

    @RequestMapping(value = "/listaTunel",method = RequestMethod.POST)
    public String listaTunel(Locale locale,Model model, @RequestParam(value = "folioAsignado")String folioAsignado){
        logger.info("Comianza tabla para el apartado del tunel" + folioAsignado);
        model.addAttribute("lista",this.controlTunelManager.getControlTunelById(Integer.parseInt(folioAsignado)));
        return "listaTunel";
    }

    @RequestMapping(value = "/insControlTunel", method = RequestMethod.POST)
    public String insControlTunel(@Valid ControlTunel controlTunel,
                                  BindingResult result) {
        logger.info("ENTRO A insControlTunel (y)");
        if (result.hasErrors()) {
            logger.error("Error al validar bean ControlTunel "+result.getAllErrors().get(0));
            return "error";
        }
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        controlTunel.setFecha(fecha+" "+hora);
        controlTunel.setNombreCliente(clienteManager.getByIdCliente(Integer.parseInt(controlTunel.getIdCliente())).getNombreCliente());
        boolean resp = controlTunelManager.setControlTunel(controlTunel);
        if (resp) {
            logger.info("VA A ALMACENAR");
            return "redirect:exito";
        }else {
            logger.error("ERROR AL  ALMACENAR");
            return "error";}
    }


    private void getPeriodoValues() {
        /*periodo.add("01");
        periodo.add("02");
        periodo.add("03");
        periodo.add("04");
        periodo.add("05");
        periodo.add("06");
        periodo.add("07");
        periodo.add("08");
        periodo.add("09");
        periodo.add("10");
        periodo.add("11");
        periodo.add("12");
        periodo.add("13");
        periodo.add("14");
        periodo.add("15");
        periodo.add("16");
        periodo.add("17");
        periodo.add("18");
        periodo.add("19");
        periodo.add("20");
        periodo.add("21");
        periodo.add("22");
        periodo.add("23");
        periodo.add("24");*/
        periodo.add("12");
        periodo.add("24");
        periodo.add("48");
    }



}
