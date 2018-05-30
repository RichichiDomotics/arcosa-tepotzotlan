package com.cbj.almacen.service.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.RegEntradas;
import com.cbj.almacen.domain.Salidas;
import com.cbj.almacen.domain.Vehiculo;
import com.cbj.almacen.repository.EntradasDao;
import com.cbj.almacen.repository.RegEntradasDao;
import com.cbj.almacen.repository.SalidasDao;
import com.cbj.almacen.service.MailManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by jolvera on 18/11/2014.
 */
@Component
public class MailManagerImpl implements MailManager {

    private static final Logger logger = LoggerFactory
            .getLogger(MailManagerImpl.class);

    @Autowired
    private RegEntradasDao regEntradasDao;
    @Autowired
    private SalidasDao salidasDao;

    @Override
    public void sendSalidaMail(Vehiculo vehiculo) {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "almacenNotificacion@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            String cid = ContentIdGenerator.getContentId();
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("almacenNotificacion@almacen.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("jolvera70@gmail.com"));
            message.addRecipient(
            Message.RecipientType.BCC,
                    new InternetAddress("jolvera70@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.BCC,
                    new InternetAddress("orcas40@gmail.com"));
            message.setSubject("Salida de vehiculo DESARROLLO");

            String mensaje = readFile(Utils.PATH_MAIL_SALIDA);
            mensaje = mensaje.replace("CID_ID",cid);
            mensaje = mensaje.replace("CARGA_DESACARGA",getCargaDescarga(vehiculo));
            mensaje = mensaje.replace("PLACAS",vehiculo.getPlacasVehiculo());
            mensaje = mensaje.replace("OPERADOR",vehiculo.getNombreOperador());
            mensaje = mensaje.replace("FLEJE",vehiculo.getFleje());
            mensaje = mensaje.replace("CARGADO_DESCARGADO",getCargadoDescargado(vehiculo));
            mensaje = mensaje.replace("PESO",getPeso(vehiculo));
            //message.setText(mensaje,"ISO-8859-1","html");
            MimeBodyPart texto = new MimeBodyPart();
            texto.setText(mensaje,"ISO-8859-1","html");
            MimeMultipart content = new MimeMultipart();
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(Utils.PATH_IMG+"logo-arcosa.png");
            imagePart.setContentID("<" + cid + ">");
            imagePart.setDisposition(MimeBodyPart.INLINE);

            content.addBodyPart(texto);
            content.addBodyPart(imagePart);
            message.setContent(content);
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("almacenNotificacion@gmail.com", "4lm4c3n#");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            logger.error("Error al enviar mal "+e);
        }
    }


    private String getCargaDescarga(Vehiculo vehiculo) {
        String texto = "";
        if(vehiculo.getIdTipoRecibo().toString().equalsIgnoreCase(Utils.TIPO_MOVIMIENTO_ENTRADA)){
            texto = "carga";
        }else if(vehiculo.getIdTipoRecibo().toString().equalsIgnoreCase(Utils.TIPO_MOVIMIENTO_SALIDA)){
            texto= "descarga";
        }
        return texto;
    }

    private String getCargadoDescargado(Vehiculo vehiculo) {
        String texto = "";
        if(vehiculo.getIdTipoRecibo().toString().equalsIgnoreCase(Utils.TIPO_MOVIMIENTO_ENTRADA)){
            texto = "cargado";
        }else if(vehiculo.getIdTipoRecibo().toString().equalsIgnoreCase(Utils.TIPO_MOVIMIENTO_SALIDA)){
            texto= "descargado";
        }
        return texto;
    }

    private String getPeso(Vehiculo vehiculo) {
        String peso = "";
        if(vehiculo.getIdTipoRecibo().toString().equalsIgnoreCase(Utils.TIPO_MOVIMIENTO_ENTRADA)){
            RegEntradas regEntradas = regEntradasDao.findRegistroRegEntradasByIdIngresoVehiculo(vehiculo.getIdIngresoVehiculo());
            if(regEntradas.getTotalEntrada()!=null){
                peso = regEntradas.getTotalEntrada().toString();
            }
        }else if(vehiculo.getIdTipoRecibo().toString().equalsIgnoreCase(Utils.TIPO_MOVIMIENTO_SALIDA)){
            Salidas salidas = salidasDao.findSalidaByIdFolioSalida(vehiculo.getIdIngresoVehiculo());
            if(salidas!=null && salidas.getCantidadKilosalida()!=null) {
                peso = salidas.getCantidadKilosalida();
            }
        }

        return peso;
    }

    /** Method used to read contents of html/text/XML file */
    public String readFile(String fileName){


        StringBuffer sb = new StringBuffer();
        BufferedReader input = null;

        try{

            input = new BufferedReader(new FileReader(fileName));

            String str = null;

            while ((str = input.readLine()) != null){
                sb.append(str);
            }


        }catch(Exception exp){

            exp.printStackTrace();

        }finally{

            try {
                if (input!= null)
                    input.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        return sb.toString();

    }
}
