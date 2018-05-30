package com.cbj.almacen.web;

/**
 * Created by Richard on 21/07/2015.
 */

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.Modificaciones;
import com.cbj.almacen.domain.Notas;
import com.cbj.almacen.domain.Salidas;
import com.cbj.almacen.service.*;
import com.cbj.almacen.service.impl.TipoCatalogo;
import com.thoughtworks.xstream.mapper.Mapper;
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
@Controller
public class ModificacionesController {
    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);
    @Autowired
    private ModificacionesManager modificacionesManager;

    @Autowired
    private CatalogoManager catalogoManager;

    @Autowired
    private ClienteManager clienteManager;

    @Autowired
    private NotasManager notasManager;

    @Autowired
    private UsuarioManager usuarioManager;

    @RequestMapping(value = "/solicitudModificacion", method = RequestMethod.GET)
    public String solicitudModificacion(Locale locale,Model model){
        logger.info("Formulario para capturar solicitudes de modificaciones");
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        final String formattedDate = formatter.format(date);
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        int folio= this.modificacionesManager.getFolio();
        if(folio==0){
            model.addAttribute("folio",1);
        }else{
            model.addAttribute("folio",folio+1);
        }
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("now", formattedDate);
        model.addAttribute("time", hora);
        return "formModificacion";
    }

    @RequestMapping(value = "/bloqueoSalidas", method = RequestMethod.GET)
    public String bloqueoSalida(Locale locale, Model model){
        logger.info("Empieza form pra bloqueo de salidas");
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        return "formBloqueoSalida";
    }

    @RequestMapping(value = "/cambioEstatus",method = RequestMethod.POST)
    public String cambioEstatusBloqueoSalida(Locale locale, Model model,
                                             @RequestParam(value = "idCliente")int idCliente,
                                             @RequestParam(value = "causa")String estatus){
        logger.info("Empieza la actualizacion del estatus del cliente");
        Clientes clientes = this.clienteManager.getByIdCliente(idCliente);
        clientes.setSalidaProducto(estatus);
        boolean respuesta = this.clienteManager.updateCliente(clientes);
        return "formBloqueoSalida";
    }

    @RequestMapping(value = "/insertarModificacion", method = RequestMethod.POST)
    public String insertarModificacion(Locale locale, Model model,@RequestParam(value = "folio")int folio,
                                       @RequestParam(value = "area")String area,@RequestParam(value = "fecha")String fecha,
                                       @RequestParam(value = "hora")String hora,@RequestParam(value = "idCliente")Integer idCliente,
                                       @RequestParam(value = "cambio")String cambio,@RequestParam(value = "causa")String causa,
                                       @RequestParam(value = "consecutivo")String consecutivo,@RequestParam(value = "salida")String salida,
                                       @RequestParam(value = "nombre")String nombre){
        logger.info("Se inserta la modificacion");
        Modificaciones modificaciones = new Modificaciones();
        Date date = null;
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date=formatFecha.parse(fecha);
        } catch (ParseException e) {
            logger.error("error al convertir la fecha"+e);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String newFecha=formatter.format(date);
        modificaciones.setFolio(folio);
        modificaciones.setNombre_solicitante(nombre);
        modificaciones.setArea_solicitante(area);
        modificaciones.setFecha_captura(newFecha);
        modificaciones.setHora_captura(hora);
        modificaciones.setIdCliente(idCliente);
        modificaciones.setCambio_solicitado(cambio);
        modificaciones.setCausa(causa);
        modificaciones.setRd_afectado(consecutivo);
        modificaciones.setSalida_afectada(salida);
        modificaciones.setEstatus(0);
        //modificaciones.setNombre_autoriza(autorizado);
        modificaciones.setTipo("SOLICITUD");
        boolean respuesta = this.modificacionesManager.insertModificacion(modificaciones);
        return "formModificacion";
    }

    @RequestMapping(value = "/autorizarModificaciones", method = RequestMethod.GET)
    public String autorizarModificaciones(Locale locale, Model model){
        logger.info("Empieza el listado de modificaciones");
        List<Modificaciones> modificaciones = this.modificacionesManager.getAll();
        model.addAttribute("modificaciones",modificaciones);
        return "tablaModificaciones";
    }

    @RequestMapping(value = "/autorizarModificacion", method = RequestMethod.POST)
    public String autorizarModificacion(Locale locale, Model model,
                                        @RequestParam(value = "idmodificacion")Integer idModificacion,
                                        @RequestParam(value = "consecutivo")String consecutivo,
                                        @RequestParam(value = "salida")String salida){
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        final String formattedDate = formatter.format(date);
        final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getUsername();
        Modificaciones modificaciones = this.modificacionesManager.getModificacionById(idModificacion);
        model.addAttribute("idCliente",modificaciones.getIdCliente());
        model.addAttribute("now", formattedDate);
        model.addAttribute("time", hora);
        model.addAttribute("idModificacion",idModificacion);
        model.addAttribute("consecutivo",consecutivo);
        model.addAttribute("salida",salida);
        model.addAttribute("usuario", claveUsuario);
        return "formAutorizaModificacion";
    }

    @RequestMapping(value = "registrarAtencion", method = RequestMethod.POST)
    public String registrarAtencion(Locale locale, Model model,
           @RequestParam(value = "nombre")String nombre,
           @RequestParam(value = "fecha")String fecha,
           @RequestParam(value = "hora")String hora,
           @RequestParam(value = "idCliente")Integer idCliente,
           @RequestParam(value = "idModificacion")Integer idModificacion,
           @RequestParam(value = "solucion")String solucion,
           @RequestParam(value = "tipoMod")String tipoMod,
           @RequestParam(value = "consecutivo")String consecutivo,
           @RequestParam(value = "salida")String salida){
        logger.info("Comienza Registro de Atencion");
        Modificaciones modificaciones = this.modificacionesManager.getModificacionById(idModificacion);
        //Modificaciones atencion = new Modificaciones();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nvafecha= formatter.format(date);
        modificaciones.setAtendidopor(nombre);
        modificaciones.setFechaatencion(nvafecha);
        modificaciones.setHoraatencion(hora);
        modificaciones.setSolucion(solucion);
        modificaciones.setTipomodificacion(tipoMod);
        modificaciones.setEstatus(1);
        boolean respuesta = this.modificacionesManager.updateModificacion(modificaciones);
        //atencion.setEstatus(1);
       /* atencion.setNombre_solicitante(nombre);
        atencion.setFecha_captura(fecha);
        atencion.setHora_captura(hora);
        atencion.setIdCliente(idCliente);
        atencion.setCambio_solicitado(solucion);
        atencion.setCausa(tipoMod);
        atencion.setRd_afectado(consecutivo);
        atencion.setSalida_afectada(salida);
        atencion.setTipo("ATENCION");
        boolean respuesta2 = this.modificacionesManager.insertModificacion(atencion);*/
        return "formAutorizaModificacion";
    }

    @RequestMapping(value = "/notasCredito", method = RequestMethod.GET)
    public String notasCredito(Locale locale, Model model){
        logger.info("Empieza form para las notas de credito");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha= simpleDateFormat.format(date);
        model.addAttribute("fecha", fecha);
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("folio", Integer.parseInt(this.notasManager.getid()) + 1);
        model.addAttribute("nota",1);
        return "formNotasCredito";
    }
    @RequestMapping(value = "/insertaNota", method = RequestMethod.POST)
    public String insertaNota(Locale locale, Model model,String folio, int nota, String fecha, Integer idCliente, String nofactura, String facturaa, String fechafactura, String fecharefactura, String motivo, String concepto, double subtotal, String camara, String autoriza, String realiza, String observaciones){
        logger.info("empieza la insercion del las notas de credito");
        Notas notas = new Notas();
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, Utils.REGION_MEXICO);

        final String formattedDate = dateFormat.format(date);


        final SimpleDateFormat formatFecha = new SimpleDateFormat("dd/mm/yyyy");
        //final String fechanota = formatFecha.format(date);
        try{
            Date date1=formatFecha.parse(fechafactura);
            Date date2=formatFecha.parse(fecharefactura);
            Date date3=formatFecha.parse(fecha);
            final SimpleDateFormat formatFecha2 = new SimpleDateFormat("yyyy-mm-dd");
            String fechaa=formatFecha2.format(date1);
            String fechas=formatFecha2.format(date2);
            String fechanota=formatFecha2.format(date3);
            notas.setFECHANOTA(fechanota);
            notas.setFECHAA(fechaa);
            notas.setFECHAS(fechas);
        }catch (Exception e){
            logger.error("error al convertir las fechas "+e.getMessage()+e.getCause());
        }
        notas.setNNOTA(nota);
        notas.setAUTORIZA(autoriza);
        notas.setCAMARA(camara);
        notas.setCONCEPTO(concepto);
        notas.setFACTURAA(facturaa);
        notas.setFACTURAS(nofactura);
        notas.setFOLIO(Integer.parseInt(folio));
        notas.setID_CLIENTE(idCliente);
        notas.setMOTIVO(motivo);
        notas.setOBSERVACIONES(observaciones);
        notas.setREALIZO(realiza);
        notas.setSUBTOTAL(subtotal);
        this.notasManager.updateNota(notas);
        model.addAttribute("notas",this.notasManager.getByFolio(Integer.parseInt(folio)));
        return "insertaNota";
    }

    @RequestMapping(value = "/formConsultaModificacionesFecha" , method = RequestMethod.GET)
    public String consultaModificacionesFecha(Locale locale, Model model){
        logger.info("Comienza la vista para consultar las modificaciones por un rango de fecha");
        return "formConsultaModificacionesFecha";
    }

    @RequestMapping(value = "/getModificaciones", method = RequestMethod.POST)
    public String getModificaciones(Locale locale, Model model, @RequestParam(value = "fecini")String fechaini, @RequestParam(value = "fecfin")String fechafin){
        logger.info("Resultado de la busqueda por fechas de las modificaciones");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateIniString = fechaini;
        String dateFinString = fechafin;

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
        model.addAttribute("modificaciones", this.modificacionesManager.getModificacionByFecha(fechaInicio,fechaFinal));
        model.addAttribute("clientes",this.clienteManager.getClientesAll());
        return "getModificaciones";
    }

}
