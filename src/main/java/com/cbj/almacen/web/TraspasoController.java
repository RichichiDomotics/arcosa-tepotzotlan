package com.cbj.almacen.web;

import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.TraspasosCamara;
import com.cbj.almacen.service.InventarioManager;
import com.cbj.almacen.service.TraspasosCamaraManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jolvera on 09/09/2014.
 */
@Controller
public class TraspasoController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private TraspasosCamaraManager traspasosCamaraManager;
    @Autowired
    private InventarioManager inventarioManager;

    @RequestMapping(value = "/ae_traspasos", method = RequestMethod.GET)
    public String ae_traspasos(Locale locale, Model model) {
        logger.info("ae_traspasoss", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.SHORT, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("now", formattedDate);
        return "formTraspasos";
    }

    @RequestMapping(value = "/ae_inserttraspasos", method = RequestMethod.POST)
    public String ae_traspasos(@Valid TraspasosCamara traspasosCamara, BindingResult result, Locale locale, Model model) {
        logger.info("ae_inserttraspasos");
        if (result.hasErrors()) {
            model.addAttribute("message","Validar Datos de captura");
            return "formTraspasos";
        }
        List<Inventario> inventarios = inventarioManager.getInventarioByConsecutivo(Integer.parseInt(traspasosCamara.getConsecutivo()));
        if(inventarios.size()<1){
            model.addAttribute("message","RD no existente");
            return "formTraspasos";
        }

        traspasosCamaraManager.insertTraspasoCamara(traspasosCamara);

        for(Inventario inventario:inventarios){
            if(inventario.getCamara().equals("T1") || inventario.getCamara().equals("T2") || inventario.getCamara().equals("T3")){
                inventario.setServicio("80");
            }else {
                inventario.setServicio("90");
            }
            inventario.setCamara(traspasosCamara.getCamaraFinal());
            inventarioManager.updateInventario(inventario);
        }
        model.addAttribute("message","Registro Actualizado con éxito");
        return "formTraspasos";
    }
    @RequestMapping(value = "/formConsultaTraspasos",method = RequestMethod.GET)
    public String formConsultaTraspasos(Locale locale, Model model){
        logger.info("Empieza la forma para el historico de traspasos");
        return "formConsultaTraspasos";
    }
    @RequestMapping(value = "/consultaTraspasos", method = RequestMethod.POST)
    public String consultaTraspasos(Locale locale, Model model, String consecutivo, String fechaini, String fechafin) throws ParseException {
        logger.info("Empieza la consulta de los trapasos " +consecutivo);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateIniString = fechaini;
        String dateFinString = fechafin;


        String fechaInicio = "";
        String fechaFinal = "";

            if(!dateIniString.equals("") && !dateFinString.equals("")){
                Date dateIni = formatter.parse(dateIniString);
                Date dateFin = formatter.parse(dateFinString);
                model.addAttribute("traspasos",this.traspasosCamaraManager.getByRDFecha(consecutivo,dateIni,dateFin));
                /*final SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
                fechaInicio = formatFecha.format(dateIni);
                fechaFinal = formatFecha.format(dateFin);*/
                //fechaNva = formatter.format(dateIn);
                //logger.debug(fechaInicio+" - "+fechaFinal);
            }else{
                model.addAttribute("traspasos",this.traspasosCamaraManager.getByRDFecha(consecutivo,new Date(),new Date()));
            }

        return "consultaTraspasos";
    }
}
