package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SalidasController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private VehiculoManager vehiculoManager;
    @Autowired
    private SalidasManager salidasManager;
    @Autowired
    private SalidasDetalleManager salidasDetalleManager;
    @Autowired
    private SalidasAlmacenManager salidasAlmacenManager;
    @Autowired
    private InventarioManager inventarioManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;
    @Autowired
    private PdfSalidasManager pdfSalidasManager;
    @Autowired
    private BsalidasDetalleManager bsalidasDetalleManager;
    @Autowired
    private ImprimirSalidasManager imprimirSalidasManager;
    //private DetallesRdManager detallesRdManager;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/formSalidas", method = RequestMethod.GET)
    public String formSalidas(Locale locale, Model model) {
        logger.info("Registro de salidas", locale);

        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));

        return "formSalidas";
    }

    @RequestMapping(value = "/formSalidasSubmit", method = RequestMethod.POST)
    public String formSalidasSubmit(String idCliente,String nombreCliente, String entregadoA,Model model) {
        
        model.addAttribute("clientes", this.catalogoManager.getCatalogo(TipoCatalogo.CLIENTE));
        model.addAttribute("idClienteconsultado", idCliente);
        model.addAttribute("nombreCliente", nombreCliente);
        model.addAttribute("entregado",entregadoA);
        return "formSalidas";
    }


    @RequestMapping(value = "/consultaSalida", method = RequestMethod.POST)
    public String consultaRd(String numeroRd, String idCliente, String producto, String caducidad,String entregado, Model model) {
        logger.info("Forma de Consulta de detalles rd " + numeroRd + " " + idCliente +
                " " + producto + " " + caducidad);

        String numeroRdb = numeroRd;
        String idClienteb = idCliente;
        String productob = producto;
        String caducidadb = caducidad;

        model.addAttribute("detalle", this.inventarioManager.getInventarioByConsulta(numeroRdb, idClienteb, productob, caducidadb));//.consultasManager.getDetallesdeRd(numeroRd, idCliente, producto, caducidad));
        model.addAttribute("idCliente",idClienteb);
        model.addAttribute("entregado",entregado);
        return "detallesRd";
    }

    @RequestMapping(value = "/consultaSalidaImpresion", method = RequestMethod.POST)
    public String consultaSalidaImpresion(String folioSalida, Model model) {
        logger.info("Forma de Consulta de folio Salida " + folioSalida);

        if(folioSalida.trim().equalsIgnoreCase("")){
            model.addAttribute("detalle", this.salidasManager.getSalidaByFechaSalidaNull());
        }else{
            model.addAttribute("detalle", this.salidasManager.getSalidaByFolioSalida(Integer.parseInt(folioSalida)));
        }

        return "detallesRdImpresion";
    }

    @RequestMapping(value = "/realizaSalida", method = RequestMethod.POST)
    public String insertaSalidaDetalles(Locale locale,String listaids, String listacantidad, String numeroRd, String idCliente, String producto, String caducidad,String entregado, Model model) {
        listaids = listaids.substring(1, listaids.length());
        logger.info("ENTREGADO A: "+entregado);
        logger.info("INICIA REGISTRO DE SALIDA DETALLES RD " + listaids.trim());
        listacantidad = listacantidad.substring(1, listacantidad.length());
        logger.info("INICIA REGISTRO DE CANTIDADES DE RD " + listacantidad.trim());
        String[] sLinea;
        sLinea = listaids.split(",");
        String[] sLineaCantidad;
        double kilosSalida = 0;
        sLineaCantidad = listacantidad.split(",");
        logger.info("arreglo lista de ids " + sLinea.length);
        logger.info("arreglo lista de cantidades " + sLineaCantidad.length);

        /*recupera id cliente y el consecutivo  e inserta en la tabla de salidas solo si no esta registrado
         * si ya esta registrado no hace el registro y obtiene el folioSalida
    	 * para insertarlo en salida almacen y salida_detalle*/
        for (int i = 0; i < sLinea.length; i++) {
            if (sLinea[i] != "") {
                final Inventario inventario = inventarioManager.getByIdInventario(Integer.parseInt(sLinea[i]));
                logger.info("solo pinta las lineas cuando entra" + Integer.parseInt(sLinea[i]));
                logger.info("solo pinta las lineas cuando entra" + sLineaCantidad[i]);
    			/*llena la tabla de salidas
    			 * obtengo el numero folio Salida
    			 */
                java.util.List<Salidas> salidas = salidasManager.getSalidaByClienteConsecutivo(inventario.getIdCliente(), inventario.getConsecutivo());
                if (salidas.size()<1) {
                    Salidas salida = new Salidas();
                    salida.setIdCliente(inventario.getIdCliente());
                    salida.setConsecutivo(inventario.getConsecutivo());
                    final Clientes clientes = clienteManager.getByIdCliente(Integer.parseInt(inventario.getIdCliente()));
                    salida.setNomCliente(clientes.getNombreCliente());
                    salida.setPesou(String.valueOf(inventario.getPesou()));
                    salida.setEmbalaje(inventario.getEmbalaje());
                    salida.setRenglon(String.valueOf(inventario.getRenglon()));
                    salida.setProducto(inventario.getDescripcion());
                    int consecutivo = salidasManager.registraSalida(salida);
                    logger.info("NO ESTA REGISTRADO Y ENTRA AQUI "+String.valueOf(consecutivo));
                    salidas = salidasManager.getSalidaByClienteConsecutivo(inventario.getIdCliente(), inventario.getConsecutivo());
                }
                
                logger.info("insertar en salidas detalle");
                //final SalidasDetalle salidasDetalle = salidasDetalleManager.getSalidaByClienteConsecutivo(detallesRd.getIdCliente(), String.valueOf(detallesRd.getConsecutivo()));
                //if (salidas.size()>=1) {
                final SalidasDetalle salidasDetalle = new SalidasDetalle();
                salidasDetalle.setFolioSalida(salidas.get(0).getFolioSalida());
                salidasDetalle.setIdCliente(salidas.get(0).getIdCliente());
                salidasDetalle.setCantidadSalida(Double.parseDouble(sLineaCantidad[i]));
                kilosSalida = kilosSalida+Double.parseDouble(sLineaCantidad[i]);
                salidasDetalle.setPesou(inventario.getPesou());
                salidasDetalle.setEmbalaje(inventario.getEmbalaje());
                salidasDetalle.setConsecutivo(inventario.getConsecutivo());
                salidasDetalle.setRenglon(String.valueOf(inventario.getRenglon()));
                salidasDetalle.setLote(inventario.getLote());
                salidasDetalle.setCamara(inventario.getCamara());
                salidasDetalle.setCaducidad(inventario.getCaducidad());
                salidasDetalle.setMarca(inventario.getMarca());
                salidasDetalle.setProducto(inventario.getDescripcion());
                salidasDetalle.setDescripcion(inventario.getDescripcion());
                salidasDetalle.setEntregara(entregado);
                salidasDetalle.setPesoNeto(Double.parseDouble(inventario.getPesoBruto()));
                final Date date = new Date();
                final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                        DateFormat.LONG, locale);

                final String formattedDate = dateFormat.format(date);
                model.addAttribute("now", formattedDate);

                final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
                final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
                final String fecha = formatFecha.format(date);
                final String hora = formatHora.format(date);
                
                salidasDetalle.setFechaSalida(date);
                salidasDetalleManager.registraSalidaDetalle(salidasDetalle);
                //}
                logger.info("insertar en salidas almacen");
                //final SalidasAlmacen salidasAlmacen = salidasAlmacenManager.getSalidaByClienteConsecutivo(inventario.getIdCliente(), inventario.getConsecutivo());
                final SalidasAlmacen salidasAlmacen = new SalidasAlmacen();
                //if (salidas.get(0).getFolioSalida() == null) {
                    salidasAlmacen.setFolioSalida(salidas.get(0).getFolioSalida());
                    salidasAlmacen.setConsecutivo(salidas.get(0).getConsecutivo());
                    salidasAlmacen.setIdCliente(salidas.get(0).getIdCliente());
                    salidasAlmacen.setEntregara(entregado);
                    salidasAlmacenManager.registraSalidaAlmacen(salidasAlmacen);
                //}
                inventario.setCantidadInventario(inventario.getCantidadInventario() - Double.valueOf(sLineaCantidad[i]));
                inventarioManager.updateInventario(inventario);
                final Salidas salida =  salidas.get(0);
                salida.setCantidadKilosalida(new Double(kilosSalida).toString());
                salidasManager.updateSalida(salida);
            }
        }
        model.addAttribute("idCliente",idCliente);
        model.addAttribute("detalle", this.inventarioManager.getInventarioByConsulta(numeroRd, idCliente, producto, caducidad));
        return "detallesRd";
    }
    
    @RequestMapping(value = "/realizaImpresionSalida", method = RequestMethod.POST)
    public String realizaImpresionSalida(String listaids, String folioSalida, Model model) {
        listaids = listaids.substring(1, listaids.length());
        logger.info("INICIA IMPRESION DE SALIDAS " + listaids.trim());
        String[] sLinea;
        sLinea = listaids.split(",");
        logger.info("arreglo lista de ids " + sLinea.length);
        //Aqui realiza los pdf's y actualiza los datos necesarios para indicar que ya se hizo la salida
        //como la fecha de salida
        for (String registro : sLinea) {
            pdfSalidasManager.generaPdfSalidas(Integer.parseInt(registro));
        }

        if(folioSalida.trim().equalsIgnoreCase("")){
            model.addAttribute("detalle", this.salidasManager.getSalidaByFechaSalidaNull());
        }else{
            model.addAttribute("detalle", this.salidasManager.getSalidaByFolioSalida(Integer.parseInt(folioSalida)));
        }
        
        return "detallesRdImpresion";
    }

    @RequestMapping(value = "/imprimirSalida",method = RequestMethod.POST)
    public String imprimirSalida(String listaids, String folioSalida, Model model){
        logger.info("Imprimir salida");
        boolean respuesta = false;
        listaids = listaids.substring(1, listaids.length());
        logger.info("INICIA IMPRESION DE SALIDAS " + listaids.trim());
        String[] sLinea;
        sLinea = listaids.split(",");
        logger.info("arreglo lista de ids " + sLinea.length);
        for (String registro : sLinea) {
            respuesta = imprimirSalidasManager.imprimirSalida(Integer.parseInt(registro));
        }

        if(folioSalida.trim().equalsIgnoreCase("")){
            model.addAttribute("detalle", this.salidasManager.getSalidaByFechaSalidaNull());
        }else{
            model.addAttribute("detalle", this.salidasManager.getSalidaByFolioSalida(Integer.parseInt(folioSalida)));
        }

        return "detallesRdImpresion";
    }

    @RequestMapping(value = "impSal",method = RequestMethod.GET)
    public String impSal(Model model){
        logger.info("Empieza la vista de la impresion de salida");
        return "impSal";
    }

    @RequestMapping(value = "/terminaSalida", method = RequestMethod.POST)
    public String terminaSalida(Locale locale, Model model, String idCliente) {
        String mensaje ="";
        logger.info("-->idInventario :"+idCliente);
        List<Vehiculo> detalles =  this.vehiculoManager.getVehiculoByCliente(Integer.parseInt(idCliente));
        logger.info("-->detalles :"+detalles);
        if(detalles.size()>0){
        	logger.info("-->idVehiculo ingresado :"+detalles.get(0).getIdIngresoVehiculo());
            final Vehiculo vehiculo= this.vehiculoManager.getIngresaVehiculoById(detalles.get(0).getIdIngresoVehiculo());
            logger.info("vehiculo "+vehiculo.toString());
            final Date date1 = new Date();
            final DateFormat dateFormat1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, locale);
            final String formattedDate1 = dateFormat1.format(date1);

            final SimpleDateFormat formatFecha1 = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
            final String fecha1 = formatFecha1.format(date1);
            final String hora = formatHora.format(date1);
            vehiculo.setStatus("2");
            vehiculo.setFechaSalida(fecha1);
            if(this.vehiculoManager.updateIngresaVehiculo(vehiculo)){
                logger.info("entrando en if para verificar que se acutalizo el vehiculo");
                List<Salidas> salidas = this.salidasManager.getSalidaByCliente(idCliente);
                if (salidas.size() > 0) {
                    logger.info("entrado al if para verificar si trajo alguna salida");
                	Salidas salida = null;
                	int conteo = 0;
                	for(Salidas salidabarre:salidas){
                		logger.info("---->fecha salida "+salidabarre.getFechaSalida());
                		if(salidabarre.getFechaSalida()==null || salidabarre.getFechaSalida().equals("")){
                			logger.info("---->Conteo "+conteo);
                			salida = salidabarre;
                		}
                		conteo ++;
                	}
                    salida = salida;
                    logger.info("Salidas arreglo "+salidas);
                    final Date date = new Date();
                    final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                            DateFormat.LONG, locale);
                    final String formattedDate = dateFormat.format(date);
                    model.addAttribute("now", formattedDate);

                    final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
                    final String fecha = formatFecha.format(date);
                    salida.setFechaSalida(fecha);
                    salida.setEstatusSalida("1");
                    if (this.salidasManager.updateSalida(salida)) {
                        model.addAttribute("message", "Salida terminada con Ã©xito");
                    } else {
                        model.addAttribute("message", "No se pudo terminar la salida no existe registro de Vehiculo");
                    }
                }
            } else {
                model.addAttribute("message", "No se pudo terminar la salida no existe registro de Vehiculo");
            }
        }

                return "alm_consultaSalidas";
    }

    @RequestMapping(value = "/consultaSalidaDetalleDet", method = RequestMethod.POST)
    public String consultaSalidaDetalleDet(Locale locale, Model model, String cliente, int consecutivo , int folioSalida) {
        logger.info("Muestra detalles / detalles_rd ", locale);
       final List<SalidasDetalle> salidasDetalle = this.salidasDetalleManager.getSalidaByConsecutivoFolioSalida(consecutivo,folioSalida);
        model.addAttribute("salidasDetalle",salidasDetalle);
        model.addAttribute("cliente",cliente);
        return "consultaSalidaDetalleDet";
    }

    @RequestMapping(value = "/consultaSalidaDetalleDetBachoco", method = RequestMethod.POST)
    public String consultaSalidaDetalleDetBachoco(Locale locale, Model model, String cliente, int consecutivo , int folioSalida) {
        logger.info("Muestra detalles / detalles_rd ", locale);
        final List<BsalidasDetalle> bsalidasDetalle = this.bsalidasDetalleManager.getFolioSalida(String.valueOf(folioSalida));
        model.addAttribute("salidasDetalle",bsalidasDetalle);
        model.addAttribute("cliente",cliente);
        return "consultaSalidaDetalleDetBachoco";
    }

}
