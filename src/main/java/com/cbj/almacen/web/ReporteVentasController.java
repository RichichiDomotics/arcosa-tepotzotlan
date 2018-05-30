package com.cbj.almacen.web;

import com.cbj.almacen.domain.*;
import com.cbj.almacen.repository.DetalleFacturacionDao;
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
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Richard on 07/07/2015.
 */

@Controller
public class ReporteVentasController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private DetalleFacturacionManager detalleFacturacionManager;

    @Autowired
    private DetalleFacturacionCopiaManager detalleFacturacionCopiaManager;

    @Autowired
    private NotasManager notasManager;

    @Autowired
    private UsuarioManager usuarioManager;

    @Autowired
    private InventarioManager inventarioManager;

    @Autowired
    private RegEntradasManager regEntradasManager;

    @Autowired
    private ClienteManager clienteManager;

    @Autowired
    private VehiculoManager vehiculoManager;

    @Autowired
    private DetalleRdManager detalleRdManager;

    @Autowired
    private ContinuidadesManager continuidadesManager;

    @Autowired
    private ConveniosManager conveniosManager;

    @RequestMapping(value = "/ae_reporteVentasEjecutivo", method = RequestMethod.GET)
    public String aes_formReporteEntrada(Model model) {
        logger.info("consultar ventas por ejecutivo");
        return "formGetVentas";
    }
    @RequestMapping(value = "/ae_reporteVentasEjecutivoMes", method = RequestMethod.GET)
    public String aes_formReporteEntradaMes(Model model) {
        logger.info("consultar ventas por ejecutivo");
        return "formGetVentasxMes";
    }
    @RequestMapping(value = "/ae_reporteVentasEjecutivoId", method = RequestMethod.GET)
    public String aes_formReporteEntradaId(Model model) {
        logger.info("consultar ventas por ejecutivo");
        User user = (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        List authorities = new ArrayList();

        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        String claveUsuario = usurec.getIdEjecutivo();
        model.addAttribute("idEjecutivo", claveUsuario);
        return "formGetVentasIdEjecutivo";
    }

    @RequestMapping(value = "/getVentas", method = RequestMethod.POST)
    public String getVentas(Locale locale, Model model, String fecini, String fecfin) {
        logger.info("Resultado Ventas"+fecini+" "+ locale);
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
        List<Object> ventas = this.detalleFacturacionManager.getVentasEjecutivo(fechaInicio,fechaFinal);
        List<Object> ventasNetas = this.detalleFacturacionManager.getVentasNetasEjecutivo(fechaInicio, fechaFinal);
        List<Object> notasCredito= this.notasManager.getNotasCredito(fechaInicio, fechaFinal);
        List<Object> notasDetalle= this.notasManager.getDetalleNotasCredito(fechaInicio, fechaFinal);
        double total= this.detalleFacturacionManager.getVentasTotal(fechaInicio,fechaFinal);
        double totalnotas= this.notasManager.getNotasCreditoTotal(fechaInicio,fechaFinal);
        double totalnetas = this.detalleFacturacionManager.getVentasNetasEjecutivoTotal(fechaInicio,fechaFinal);
        double totalventas = totalnetas - totalnotas;
        model.addAttribute("ventas",ventas);
        model.addAttribute("ventasNetas",ventasNetas);
        model.addAttribute("notas",notasCredito);
        model.addAttribute("notasDetalle",notasDetalle);
        model.addAttribute("total",total);
        model.addAttribute("totalnotas",totalnotas);
        model.addAttribute("totalnetas",totalnetas);
        model.addAttribute("totalventas",totalventas);
        return "getVentas";
    }

    @RequestMapping(value = "/getVentasxMes", method = RequestMethod.POST)
    public String getVentasxMes(Locale locale, Model model, String fecini, String fecfin, String mes) {
        logger.info("Resultado Ventas"+fecini+" "+ locale);
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
        List<Object> ventas = this.detalleFacturacionCopiaManager.getVentasEjecutivo(fechaInicio,fechaFinal);
        List<Object> ventasNetas = this.detalleFacturacionCopiaManager.getVentasNetasEjecutivo(fechaInicio, fechaFinal);
        List<Object> notasCredito= this.notasManager.getNotasCredito(fechaInicio, fechaFinal);
        List<Object> notasDetalle= this.notasManager.getDetalleNotasCredito(fechaInicio, fechaFinal);
        double total= this.detalleFacturacionCopiaManager.getVentasTotal(fechaInicio, fechaFinal);
        double totalnotas= this.notasManager.getNotasCreditoTotal(fechaInicio, fechaFinal);
        double totalnetas = this.detalleFacturacionCopiaManager.getVentasNetasEjecutivoTotal(fechaInicio, fechaFinal);
        double totalventas = totalnetas - totalnotas;
        model.addAttribute("ventas",ventas);
        model.addAttribute("ventasNetas",ventasNetas);
        model.addAttribute("notas",notasCredito);
        model.addAttribute("notasDetalle",notasDetalle);
        model.addAttribute("total",total);
        model.addAttribute("totalnotas",totalnotas);
        model.addAttribute("totalnetas",totalnetas);
        model.addAttribute("totalventas",totalventas);
        model.addAttribute("mes",mes);
        return "getVentasxMes";
    }

    @RequestMapping(value = "/getVentasIdEjecutivo", method = RequestMethod.POST)
    public String getVentasIdEjcutivo(Locale locale, Model model, String fecini, String fecfin, String idEjecutivo) {
        logger.info("Resultado Ventas"+fecini+" "+ locale);
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
        List<Object> ventas = this.detalleFacturacionManager.getVentasEjecutivoId(fechaInicio, fechaFinal, idEjecutivo);
        List<Object> ventasNetas = this.detalleFacturacionManager.getVentasNetasEjecutivoId(fechaInicio, fechaFinal, idEjecutivo);
        double totalnetas = this.detalleFacturacionManager.getVentasNetasEjecutivoTotalID(fechaInicio, fechaFinal, idEjecutivo);
        List<Object> notasCredito= this.notasManager.getNotasCreditoId(fechaInicio, fechaFinal, idEjecutivo);
        List<Object> notasDetalle= this.notasManager.getDetalleNotasCreditoId(fechaInicio, fechaFinal, idEjecutivo);
        double totalnotas= this.notasManager.getNotasCreditoTotalId(fechaInicio, fechaFinal, idEjecutivo);
        double totalventas = totalnetas - totalnotas;
        model.addAttribute("ventasNetas",ventasNetas);
        model.addAttribute("totalnetas",totalnetas);
        model.addAttribute("ventas",ventas);
        model.addAttribute("notas",notasCredito);
        model.addAttribute("notasDetalle",notasDetalle);
        model.addAttribute("totalnotas",totalnotas);
        model.addAttribute("totalventas",totalventas);
        return "getVentasIdEjecutivo";
    }

    @RequestMapping(value = "/formgetFacturasRD", method = RequestMethod.GET)
    public String formgetFacturasRD(Locale locale, Model model, String consecutivo){
        logger.info("forma para consultar las facturas por RD");
        return "formgetFacturasRD";
    }

    @RequestMapping(value = "/getFacturasRD", method = RequestMethod.POST)
    public String getFacturasRD(Locale locale, Model model, String consecutivo){
        logger.info("Comienzo la consulta de las facturas por RD");
        model.addAttribute("facturas",this.detalleFacturacionManager.getFacturasByRD(consecutivo));
        return "getFacturasRD";
    }

    @RequestMapping(value = "/formgetFacturasFecha", method = RequestMethod.GET)
    public String formgetFacturasFecha(Locale locale, Model model){
        logger.info("forma para consultar las facturas por Fecha");
        return "formgetFacturasFecha";
    }

    @RequestMapping(value = "/getFacturasFecha", method = RequestMethod.POST)
    public String getFacturasFecha(Locale locale, Model model,@RequestParam(value = "fechaini") String fecha){
        logger.info("Comienzo la consulta de las facturas por Fecha "+fecha);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateIniString = fecha;
        String fechaInicio = "";
        try {
            if(!dateIniString.equals("")){
                Date dateIni = formatter.parse(dateIniString);

                final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
                fechaInicio = formatFecha.format(dateIni);
                //fechaNva = formatter.format(dateIn);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("facturas",this.detalleFacturacionManager.getFacturasByFecha(fechaInicio));
        return "getFacturasFecha";
    }

    @RequestMapping(value = "/formFacturasImpresas", method = RequestMethod.GET)
    public String formFacturasImpresas(Locale locale, Model model){
        logger.info("forma para consultar las facturas por Fecha");
        return "formFacturasImpresas";
    }

    @RequestMapping(value = "/getFacturasImpresas", method = RequestMethod.POST)
    public String getFacturasImpresas(Locale locale, Model model,@RequestParam(value = "fechaini")String fecini, @RequestParam(value = "fechafin")String fecfin){
        logger.info("Comienzo la consulta de las facturas por Fecha "+fecini);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateIniString = fecini;
        String dateFinString = fecfin;

        String fechaInicio = "";
        String fechaFinal = "";
        String fechaNva = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/mm/yyyy");
        try {

                Date dateIni = formatter.parse(dateIniString);
                Date dateFin = formatter.parse(dateFinString);
                model.addAttribute("before", dateFormat2.format(dateIni));
                model.addAttribute("after", dateFormat2.format(dateFin));

                final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
                fechaInicio = formatFecha.format(dateIni);
                fechaFinal = formatFecha.format(dateFin);
                //fechaNva = formatter.format(dateIn);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();


        String formattedDate = dateFormat.format(date);
        model.addAttribute("now",formattedDate);
        model.addAttribute("saldos",this.inventarioManager.getSaldosTotales());
        model.addAttribute("facturasNetas",this.detalleFacturacionManager.getFacturasNetas(fechaInicio,fechaFinal));
        model.addAttribute("facturasImpresas",this.detalleFacturacionManager.getFacturasImpresas(fechaInicio,fechaFinal));
        model.addAttribute("facturasTotal",this.detalleFacturacionManager.getFacturasTotal(fechaInicio,fechaFinal));
        model.addAttribute("pendientes",this.detalleFacturacionManager.getPendientes(fechaInicio,fechaFinal));
        model.addAttribute("notas",this.notasManager.getNotasCreditoTotal(fechaInicio,fechaFinal));

        return "getFacturasImpresas";
    }

    @RequestMapping(value = "/formFacturasDiarias")
    public String formFacturasDiarias(Locale locale, Model model){
        logger.info("Empieza form para las facturas diarias");
        return "formFacturasDiarias";
    }

    @RequestMapping(value = "/getFacturasConcepto", method = RequestMethod.POST)
    public String getFacturasConcepto(Locale locale, Model model,@RequestParam(value = "fechaini")String fecini, @RequestParam(value = "fechafin")String fecfin){
        logger.info("Comienzo la consulta de las facturas por Fecha "+fecini);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateIniString = fecini;
        String dateFinString = fecfin;

        String fechaInicio = "";
        String fechaFinal = "";
        String fechaNva = "";
        try {

            Date dateIni = formatter.parse(dateIniString);
            Date dateFin = formatter.parse(dateFinString);

            final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
            fechaInicio = formatFecha.format(dateIni);
            fechaFinal = formatFecha.format(dateFin);
            //fechaNva = formatter.format(dateIn);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now",formattedDate);
        model.addAttribute("concepto", this.detalleFacturacionManager.getFacturasConcepto(fechaInicio,fechaFinal));
        model.addAttribute("rubros", this.detalleFacturacionManager.getFacturasClienteRubro(fechaInicio,fechaFinal));
        model.addAttribute("detalle", this.detalleFacturacionManager.getFacturasClienteRubroDetalle(fechaInicio,fechaFinal));
        model.addAttribute("total", this.detalleFacturacionManager.getVentasTotal(fechaInicio,fechaFinal));

        return "getFacturasConcepto";
    }

    @RequestMapping(value = "/RDCancelados", method = RequestMethod.GET)
    public String getCancelados(Locale locale, Model model){
        logger.info("Vista para consultar RD's cancelados");
        model.addAttribute("cancelados",this.regEntradasManager.getCancelados());
        model.addAttribute("clientes", this.clienteManager.getClientesAll());
        model.addAttribute("vehiculos", this.vehiculoManager.getAll());
        return "cancelados";
    }

    @RequestMapping(value = "/formCrearFacturas", method = RequestMethod.GET)
    public String formCrearFacturas(Locale locale, Model model){
        logger.info("Formulario para empezar a crear facturas");
        model.addAttribute("clientes",this.clienteManager.getClientesAll());
        return "formCrearFacturas";
    }

    @RequestMapping(value = "/crearFacturas", method = RequestMethod.POST)
    public String crearFacturas(Locale locale, Model model, @RequestParam(value = "listaids")String listaids,
                                @RequestParam(value = "fechaini")String fechaini,
                                @RequestParam(value = "fechafin")String fechafin){
        logger.info("Empieza creacion de facturas: "+fechaini+" - "+fechafin+" "+listaids);
        logger.info(listaids.substring(1,(listaids.length())));
        String [] ids;
        ids = listaids.split(",");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateIniString = fechaini;
        String dateFinString = fechafin;


        String fechaInicio = "";
        String fechaFinal = "";
        String fechaNva = "";


        try {

            Date dateIni = formatter.parse(dateIniString);
            Date dateFin = formatter.parse(dateFinString);

            final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaInicio = formatFecha.format(dateIni);
            logger.info("fecha sumada: "+ sumarDiasFecha(dateFin,1));
            fechaFinal = formatFecha.format(sumarDiasFecha(dateFin,1));
            logger.info("otra fecha sumada: "+fechaFinal);
            List<Object> facturas = this.detalleRdManager.getRDAlmacenajesNuevos(listaids.substring(0,(listaids.length()-1)),dateIni,dateFin);
            List<Object> tuneles = this.detalleRdManager.getRDTunelesNuevos(listaids.substring(0, (listaids.length() - 1)), dateIni, dateFin);
            List<Object> meys = this.detalleRdManager.getRDMEYS(listaids.substring(0, (listaids.length() - 1)), dateIni, dateFin);
            List<Object> continuidades = this.inventarioManager.getRDContinuidades(listaids.substring(0, (listaids.length() - 1)), dateIni, sumarDiasFecha(dateFin,-1));
            List<Object> detalleFacturacions=this.detalleFacturacionManager.getAll(fechaInicio,fechaFinal);
            List<Object> detalleFacturacions2=this.continuidadesManager.getAll(fechaInicio,fechaFinal);
            for(int u=0;u<ids.length;u++){
                if(ids[u].equals("341") || ids[u].equals("428") || ids[u].equals("536") || ids[u].equals("109") || ids[u].equals("116") || ids[u].equals("194") || ids[u].equals("196") || ids[u].equals("394") ||
                        ids[u].equals("395") || ids[u].equals("187") || ids[u].equals("314") || ids[u].equals("258") || ids[u].equals("341") || ids[u].equals("387")){
                    ids[u]="";
                }
            }

            String algo="";
            String algo3="";
            logger.info("tamaño de los almacenajes: "+facturas.size());
            logger.info("Tamaño de las meys: "+meys.size());
            logger.info("tamaño de los tuneles: "+tuneles.size());
            logger.info("tamaño de las continuidades: "+continuidades.size());
            logger.info("tamaño de detalles facturacion: "+detalleFacturacions.size());
            if(facturas.size()>0) {
                for (int i = 0; i < facturas.size(); i++) {
                    algo = Arrays.toString((Object[]) facturas.toArray()[i]);
                    String[] algo2 = algo.split(",");
                    for (int j = 0; j < detalleFacturacions.size(); j++) {
                        algo3 = Arrays.toString((Object[]) detalleFacturacions.toArray()[j]);
                        String[] algo4 = algo3.split(",");
                        SimpleDateFormat convertir1 = new SimpleDateFormat("yyyy-MM-dd");
                        String nvafecha = "";
                        Date fechaconv = convertir1.parse(algo4[1]);
                        SimpleDateFormat formatonvo = new SimpleDateFormat("yyyy-MM-dd");
                        nvafecha = formatonvo.format(fechaconv);
                        logger.info("factura completa: "+algo);
                        logger.info("fecha a comparar: "+nvafecha+"="+algo2[2].substring(1,algo2[2].length()));
                        logger.info("fecha fin de periodo: "+algo2[3]);
                        logger.info("RD a comparar: "+algo4[0].substring(1,algo4[0].length())+"="+algo2[1].substring(1,algo2[1].length()));
                        logger.info("servicio a comparar: "+algo4[2].substring(1,algo4[2].length()-1)+"="+algo2[6].substring(1,algo2[6].length()));
                        logger.info("resultado de la comparacion"+ algo4[0].substring(1,algo4[0].length()).equals(algo2[1].substring(1,algo2[1].length())) + nvafecha.equals(algo2[2].substring(1,algo2[2].length())) + algo4[2].substring(0,algo4[2].length()-1).equals(algo2[6].substring(1,algo2[6].length())));
                        logger.info("-----------------------------------------------------");
                        if (algo4[0].substring(1, algo4[0].length()).equals(algo2[1].substring(1, algo2[1].length())) && nvafecha.equals(algo2[2].substring(1, algo2[2].length())) && algo4[2].substring(1, algo4[2].length() - 1).equals(algo2[6].substring(1, algo2[6].length()))) {
                            //logger.info("dentro del if para remover el campo si ya existe");
                            facturas.set(i,"[]");
                            //facturas.remove(i);

                        }
                        //algo3 = "";
                    }
                    //algo = "";
                }
            }
            //algo="";
            //algo3="";


            if(tuneles.size()>0) {
                for (int i = 0; i < tuneles.size(); i++) {
                    algo = Arrays.toString((Object[]) tuneles.toArray()[i]);
                    String[] algo2 = algo.split(",");
                    for (int j = 0; j < detalleFacturacions.size(); j++) {
                        algo3 = Arrays.toString((Object[]) detalleFacturacions.toArray()[j]);
                        String[] algo4 = algo3.split(",");
                        SimpleDateFormat convertir1 = new SimpleDateFormat("yyyy-MM-dd");
                        String nvafecha = "";
                        Date fechaconv = convertir1.parse(algo4[1]);
                        SimpleDateFormat formatonvo = new SimpleDateFormat("yyyy-MM-dd");
                        nvafecha = formatonvo.format(fechaconv);
                        /*logger.info("fecha a comparar: "+nvafecha+"="+algo2[2].substring(1,algo2[2].length()));
                        logger.info("RD a comparar: "+algo4[0].substring(1,algo4[0].length())+"="+algo2[1].substring(1,algo2[1].length()));
                        logger.info("servicio a comparar: "+algo4[2].substring(1,algo4[2].length()-1)+"="+algo2[6].substring(1,algo2[6].length()));
                        logger.info("resultado de la comparacion"+ algo4[0].substring(1,algo4[0].length()).equals(algo2[1].substring(1,algo2[1].length())) + nvafecha.equals(algo2[2].substring(1,algo2[2].length())) + algo4[2].substring(0,algo4[2].length()-1).equals(algo2[6].substring(1,algo2[6].length())));
                        logger.info("-----------------------------------------------------");*/
                        if (algo4[0].substring(1, algo4[0].length()).equals(algo2[1].substring(1, algo2[1].length())) && nvafecha.equals(algo2[2].substring(1, algo2[2].length())) && algo4[2].substring(1, algo4[2].length() - 1).equals(algo2[6].substring(1, algo2[6].length()))) {
                            //logger.info("dentro del if para remover el campo si ya existe");
                            //tuneles.remove(i);
                            tuneles.set(i,"[]");
                        }
                       // algo3 = "";
                    }
                   // algo = "";
                }
            }

            //algo="";
            //algo3="";

            if (meys.size()>0) {
                for (int i = 0; i < meys.size(); i++) {
                    algo = Arrays.toString((Object[]) meys.toArray()[i]);
                    String[] algo2 = algo.split(",");
                    for (int j = 0; j < detalleFacturacions.size(); j++) {
                        algo3 = Arrays.toString((Object[]) detalleFacturacions.toArray()[j]);
                        String[] algo4 = algo3.split(",");
                        SimpleDateFormat convertir1 = new SimpleDateFormat("yyyy-MM-dd");
                        String nvafecha = "";
                        Date fechaconv = convertir1.parse(algo4[1]);
                        SimpleDateFormat formatonvo = new SimpleDateFormat("yyyy-MM-dd");
                        nvafecha = formatonvo.format(fechaconv);
                        /*logger.info("fecha a comparar: "+nvafecha+"="+algo2[2].substring(1,algo2[2].length()));
                        logger.info("RD a comparar: "+algo4[0].substring(1,algo4[0].length())+"="+algo2[1].substring(1,algo2[1].length()));
                        logger.info("servicio a comparar: "+algo4[2].substring(1,algo4[2].length()-1)+"="+algo2[6].substring(1,algo2[6].length()));
                        logger.info("resultado de la comparacion"+ algo4[0].substring(1,algo4[0].length()).equals(algo2[1].substring(1,algo2[1].length())) + nvafecha.equals(algo2[2].substring(1,algo2[2].length())) + algo4[2].substring(1,algo4[2].length()-1).equals(algo2[6].substring(1,algo2[6].length())));
                        logger.info("-----------------------------------------------------");*/
                        if (algo4[0].substring(1, algo4[0].length()).equals(algo2[1].substring(1, algo2[1].length())) && nvafecha.equals(algo2[2].substring(1, algo2[2].length())) && algo4[2].substring(1, algo4[2].length() - 1).equals(algo2[6].substring(1, algo2[6].length()))) {
                            //logger.info("dentro del if para remover el campo si ya existe: "+ Arrays.toString((Object[]) meys.toArray()[i]));
                            //meys.remove(i);
                            meys.set(i,"[]");
                        }
                        //algo3 = "";
                    }
                    //algo = "";
                }
            }

            //algo="";
            //algo3="";

            if(continuidades.size()>0) {
                for (int i = 0; i < continuidades.size(); i++) {
                    algo = Arrays.toString((Object[]) continuidades.toArray()[i]);
                    String[] algo2 = algo.split(",");
                    for (int j = 0; j < detalleFacturacions2.size(); j++) {
                        algo3 = Arrays.toString((Object[]) detalleFacturacions2.toArray()[j]);
                        String[] algo4 = algo3.split(",");
                        SimpleDateFormat convertir1 = new SimpleDateFormat("yyyy-MM-dd");
                        String nvafecha = "";
                        Date fechaconv = convertir1.parse(algo4[1]);
                        SimpleDateFormat formatonvo = new SimpleDateFormat("yyyy-MM-dd");
                        nvafecha = formatonvo.format(fechaconv);
                        /*logger.info("fecha a comparar: "+nvafecha+"="+algo2[2].substring(1,algo2[2].length()));
                        logger.info("RD a comparar: "+algo4[0].substring(1,algo4[0].length())+"="+algo2[1].substring(1,algo2[1].length()));
                        logger.info("servicio a comparar: "+algo4[2].substring(1,algo4[2].length()-1)+"="+algo2[6].substring(1,algo2[6].length()));
                        logger.info("resultado de la comparacion"+ algo4[0].substring(1,algo4[0].length()).equals(algo2[1].substring(1,algo2[1].length())) + nvafecha.equals(algo2[2].substring(1,algo2[2].length())) + algo4[2].substring(0,algo4[2].length()-1).equals(algo2[6].substring(1,algo2[6].length())));
                        logger.info("-----------------------------------------------------");*/
                        if (algo4[0].substring(1, algo4[0].length()).equals(algo2[1].substring(1, algo2[1].length())) && nvafecha.equals(algo2[2].substring(1, algo2[2].length())) /*&& algo4[2].substring(1, algo4[2].length() - 1).equals(algo2[6].substring(1, algo2[6].length()))*/) {
                            //logger.info("dentro del if para remover el campo si ya existe");
                            //continuidades.remove(i);
                            continuidades.set(i,"[]");
                        }
                        //algo3 = "";
                    }
                   // algo = "";
                }
            }


            model.addAttribute("facturas", facturas);
                model.addAttribute("tuneles", tuneles);
                model.addAttribute("meys", meys);
                model.addAttribute("continuidades", continuidades);
                //model.addAttribute("detfactu",detalleFacturacions);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        //logger.info("arreglo de ids"+ids);

        return "crearFacturas";
    }


    @RequestMapping(value = "/insFactura", method = RequestMethod.POST)
    public String onSubmitDetalle(Locale locale, Model model, @RequestParam(value = "listidcliente")String listidcliente,
                                  @RequestParam(value = "listrd")String listrd,@RequestParam(value = "listiniper")String listiniper,
                                  @RequestParam(value = "listfinper")String listfinper, @RequestParam(value = "listcamara")String listcamara,
                                  @RequestParam(value = "listkilos")String listkilos,@RequestParam(value = "listservicio")String listservicio,
                                  @RequestParam(value = "listcuota")String listcuota,@RequestParam(value = "listtotal")String listtotal,
                                  @RequestParam(value = "listfechafac")String listfechafac,@RequestParam(value = "registros")int registros){
        logger.info("insertando facturas: "+listidcliente);
        String[] clientes=listidcliente.split(",");
        String[] rds=listrd.split(",");
        String[] iniper=listiniper.split(",");
        String[] finper=listfinper.split(",");
        String[] camara=listcamara.split(",");
        String[] kilos=listkilos.split(",");
        String[] servicio=listservicio.split(",");
        String[] cuota=listcuota.split(",");
        String[] total=listtotal.split(",");
        String[] fechafac=listfechafac.split(",");
        DetalleFacturacion detalleFacturacion = new DetalleFacturacion();
        Continuidades continuidades = new Continuidades();
        for(int i=0; i<registros; i++){
            detalleFacturacion.setIdCliente(Integer.parseInt(clientes[i]));
            detalleFacturacion.setRd(rds[i]);
            detalleFacturacion.setFechainiper(iniper[i]);
            detalleFacturacion.setFechafinper(finper[i]);
            detalleFacturacion.setCamara(camara[i]);
            detalleFacturacion.setKilos(Double.parseDouble(kilos[i]));
            detalleFacturacion.setTiposervicio(servicio[i]);
            detalleFacturacion.setCongelacion(Double.parseDouble(cuota[i]));
            detalleFacturacion.setSubtotal(Double.parseDouble(total[i]));
            detalleFacturacion.setTotal(Double.parseDouble(total[i]));
            detalleFacturacion.setFechafactura(fechafac[i]);
            detalleFacturacion.setId("F");
            detalleFacturacion.setNofactura(" ");
            this.detalleFacturacionManager.insertDetalleFacturacion(detalleFacturacion);
            continuidades= this.continuidadesManager.getByConsecutivo(Integer.parseInt(rds[i]));
            logger.info("contenido de continuidades ");
            if(continuidades.getRD()==0 && servicio[i].equals("ALM")){
                logger.info("rd a insertar en la tabla de continuidades "+rds[i]);
                continuidades.setCLIENTE(clientes[i]);
                continuidades.setRD(Integer.parseInt(rds[i]));
                continuidades.setINICIO(iniper[i]);
                continuidades.setFIN(finper[i]);
                continuidades.setCUOTA(Double.parseDouble(cuota[i]));
                //continuidades.setDIAREVISION(this.clienteManager.getByIdCliente(Integer.parseInt(clientes[i])).getDiasRevision());
                continuidades.setPERIODO(Integer.parseInt(this.conveniosManager.getByClaveProducto(this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(rds[i])).get(0).getClaveProducto()).getPeriodo()));
                this.continuidadesManager.insertContinuidad(continuidades);
            }else if(continuidades.getRD()>0 && servicio[i].equals("ALMC")){
                logger.info("rd a actualizar en la tabla de continuidades "+rds[i]);
                continuidades.setINICIO(iniper[i]);
                continuidades.setFIN(finper[i]);
                this.continuidadesManager.updateContinuidad(continuidades);
            }

        }

        return "crearFacturas";
    }

    @RequestMapping(value = "/consultaPreFacturas", method = RequestMethod.GET)
    public String consultaPreFacturas(Locale locale, Model model){
        logger.info("form para consultar las prefacturas");
        model.addAttribute("clientes", this.clienteManager.getClientesAll());
        return "consultaPrefacturas";
    }

    @RequestMapping(value = "/preFacturas", method = RequestMethod.POST)
    public String preFacturas(Locale locale, Model model,@RequestParam(value = "fechaini")String fechaini,@RequestParam(value = "fechafin")String fechafin, @RequestParam(value = "idcliente")String idcliente){
        logger.info("Empieza la consulta de las pre-facturas");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateIniString = fechaini;
        String dateFinString = fechafin;
        String fechaInicio = "";
        String fechaFinal = "";

        try{
            Date dateIni = formatter.parse(dateIniString);
            Date dateFin = formatter.parse(dateFinString);

            final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaInicio = formatFecha.format(dateIni);
            fechaFinal = formatFecha.format(dateFin);
            logger.info("prefacturas: "+Arrays.toString(this.detalleFacturacionManager.getPreFacturas(fechaInicio,fechaFinal,idcliente).toArray()));
            model.addAttribute("clientes", this.clienteManager.getClientesAll());
            model.addAttribute("prefacturas", this.detalleFacturacionManager.getPreFacturas(fechaInicio,fechaFinal,idcliente));
            model.addAttribute("idCliente",Integer.parseInt(idcliente));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "preFacturas";
    }



    public Date sumarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe

        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }
}
