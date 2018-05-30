package com.cbj.almacen.service.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
import com.cbj.almacen.repository.*;
import com.cbj.almacen.service.PdfEntradasManager;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 22/06/2014.
 */
@Component
public class PdfEntradasManagerImpl implements PdfEntradasManager {

    private static final Logger logger = LoggerFactory
            .getLogger(PdfEntradasManagerImpl.class);

    @Autowired
    private InventarioDao inventarioDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private RegEntradasDao regEntradasDao;
    @Autowired
    private DetalleRdDao detalleRdDao;

    @Autowired
    private VehiculoDao vehiculoDao;

    @Autowired
    private ServletContext servletContext;


    @Override
    public void generaPdfEntradas(int consecutivo) {
        logger.info(Utils.PATH_URL + "entrada" + consecutivo + ".pdf");
        generaPDF(Utils.PATH_URL + "entrada" + consecutivo + ".pdf", consecutivo);
    }

    /**
     * Convert Text to PDF using Apache PDFBox
     *
     * @param fileName
     */
    public void generaPDF(String fileName, int consecutivo) {
        PDDocument pDDocument = null;
        final double MPI = Math.PI / 2;

        try {
            //Create PDDocument

            final List<DetallesRd> detallesRd = detalleRdDao.getAllByConsecutivo(consecutivo);
            final Clientes cliente = clienteDao.findCliente(Integer.parseInt(detallesRd.get(0).getIdCliente()));
            final List<RegEntradas> regEntradas = regEntradasDao.findRegistroRegEntradasByConsecutivo(consecutivo);
            final Inventario inventario = inventarioDao.findByClienteConsecutivo(detallesRd.get(0).getIdCliente(), consecutivo);
            final Vehiculo vehiculo = vehiculoDao.findVehiculoById(detallesRd.get(0).getIdIngresoVehiculo());
            int paginaActual = 1;
            double registrosXpagina = 19;
            double paginaTotal = detallesRd.size() / registrosXpagina;
            double totalKgs = 0;
            double totalPzas = 0;
            //double paginaTotal = 70/registrosXpagina;
            String totalKgsChar = "";
            String totalPzasChar = "";
            for (int i = 0; i < detallesRd.size(); i++) {
                DetallesRd detalle = detallesRd.get(i);
                totalPzas = totalPzas + detalle.getCantidad();
                totalKgs = totalKgs + (detalle.getPesou() * detalle.getCantidad());
                DecimalFormat formateador = new DecimalFormat("###,###,###.##");
                totalKgsChar = formateador.format(totalKgs);
                totalPzasChar = formateador.format(totalPzas);
            }
            detallesRd.get(0).setApertura(vehiculo.getFleje());

            pDDocument = new PDDocument();
            PDPageContentStream contentStream = getPdPageContentStream(pDDocument, inventario, detallesRd, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), totalKgsChar, totalPzasChar, regEntradas);
            final List<String[]> rowListHeader = new ArrayList<String[]>();
            String nombreChofer = "";
            if (regEntradas.get(0).getNomChofer() != null) {
                nombreChofer = nombreChofer = regEntradas.get(0).getNomChofer();
            }
            rowListHeader.add(new String[]{"CLIENTE", cliente.getNombreCliente(), "TEMPERATURA", "ENTRADA TUNEL", "CAMARA INICIAL"});
            rowListHeader.add(new String[]{"CHOFER", nombreChofer, "TEMPERATURA", "NO", "CAMARA FINAL"});
            //PDPage pdpage = new PDPage();
            //Utils.drawTableHorizontal(pdpage, contentStream, 700, 100, rowListHeader);
            double total = 0;

            float iniParrafo = 90;
            float fontSize = 8f;
            //Utils.drawTableHorizontal(pDPage, contentStream, 700, 170, rowList);
            float sigLinea = iniParrafo - 8;
            Utils.setNextRenglonHorizontal("RENG", 1, fontSize, 40, sigLinea, contentStream, 0, MPI);
            Utils.setNextRenglonHorizontal("PZAS", 1, fontSize, 70, sigLinea, contentStream, 0, MPI);
            //Utils.setNextRenglonHorizontal("EMBALAJE", 1, fontSize, 150, sigLinea, contentStream, 0, MPI);
            Utils.setNextRenglonHorizontal("PRODUCTO", 1, fontSize, 125, sigLinea, contentStream, 0, MPI);
            Utils.setNextRenglonHorizontal("CADUCIDAD", 1, fontSize, 430, sigLinea, contentStream, 0, MPI);
            Utils.setNextRenglonHorizontal("MARCA", 1, fontSize, 505, sigLinea, contentStream, 0, MPI);
            Utils.setNextRenglonHorizontal("LOTE", 1, fontSize, 630, sigLinea, contentStream, 0, MPI);
            Utils.setNextRenglonHorizontal("PESO U.", 1, fontSize, 750, sigLinea, contentStream, 0, MPI);
            //Utils.setNextRenglonHorizontal("BULTOS", 1, fontSize, 850, sigLinea, contentStream, 0, MPI);



            iniParrafo = 100;
            sigLinea = iniParrafo - 8;
            int saltosProducto = 0;
            int saltosMarca = 0;


            for (int i = 0; i < detallesRd.size(); i++) {
                //for(int i=0;i<70;i++){

                registrosXpagina = registrosXpagina + i;
                if (sigLinea > 422) {
                    paginaActual = paginaActual + 1;
                    contentStream = getPdPageContentStream(pDDocument, inventario, detallesRd, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), totalKgsChar, totalPzasChar, regEntradas);
                    sigLinea = 82;
                    Utils.setNextRenglonHorizontal("RENG", 1, fontSize, 40, sigLinea, contentStream, 0, MPI);
                    Utils.setNextRenglonHorizontal("PZAS", 1, fontSize, 70, sigLinea, contentStream, 0, MPI);
                    //Utils.setNextRenglonHorizontal("EMBALAJE", 1, fontSize, 150, sigLinea, contentStream, 0, MPI);
                    Utils.setNextRenglonHorizontal("PRODUCTO", 1, fontSize, 125, sigLinea, contentStream, 0, MPI);
                    Utils.setNextRenglonHorizontal("CADUCIDAD", 1, fontSize, 430, sigLinea, contentStream, 0, MPI);
                    Utils.setNextRenglonHorizontal("MARCA", 1, fontSize, 505, sigLinea, contentStream, 0, MPI);
                    Utils.setNextRenglonHorizontal("LOTE", 1, fontSize, 630, sigLinea, contentStream, 0, MPI);
                    Utils.setNextRenglonHorizontal("PESO U.", 1, fontSize, 750, sigLinea, contentStream, 0, MPI);
                    //Utils.setNextRenglonHorizontal("BULTOS", 1, fontSize, 850, sigLinea, contentStream, 0, MPI);

                    sigLinea=92;
                }

                DecimalFormat formateadorre = new DecimalFormat("######");
                DetallesRd detalle = detallesRd.get(i);
                String renglonPrt = formateadorre.format(detalle.getRenglon());
                int saltos = Utils.setNextRenglonHorizontal(renglonPrt, 1, fontSize, 40, sigLinea, contentStream, 0, MPI);
                //int saltos = Utils.setNextRenglonHorizontal(i+"", 1, fontSize, 50, sigLinea, contentStream,0,MPI);
                DecimalFormat formatterSinDec = new DecimalFormat("###,###");
                Utils.setNextRenglonHorizontal(formatterSinDec.format(detalle.getCantidad()).toString(), 1, fontSize, 70, sigLinea, contentStream, 0, MPI);
                //Utils.setNextRenglonHorizontal(detalle.getEmbalaje().toString(), 1, fontSize, 150, sigLinea, contentStream, 0, MPI);
                saltosProducto = Utils.setNextRenglonHorizontal(detalle.getDescripcion(), 1, fontSize, 125, sigLinea, contentStream, 100, MPI);
                Utils.setNextRenglonHorizontal(detalle.getCaducidad(), 1, fontSize, 430, sigLinea, contentStream, 0, MPI);
                saltosMarca = Utils.setNextRenglonHorizontal(detalle.getMarca(), 1, fontSize, 505, sigLinea, contentStream, 40, MPI);
                Utils.setNextRenglonHorizontal(detalle.getLote(), 1, fontSize, 630, sigLinea, contentStream, 0, MPI);
                DecimalFormat formatter = new DecimalFormat("###,###,###.##");
                Utils.setNextRenglonHorizontal(formatter.format(detalle.getPesou()), 1, fontSize, 750, sigLinea, contentStream, 0, MPI);
                //Utils.setNextRenglonHorizontal(detalle.getBultos().toString(), 1, fontSize, 850, sigLinea, contentStream, 0, MPI);
                if (saltosProducto > saltos && saltosProducto > saltosMarca) {
                    sigLinea = sigLinea + 10 + saltosProducto;
                } else if (saltosMarca > saltos) {
                    sigLinea = sigLinea + 10 + saltosMarca;
                } else {
                    sigLinea = sigLinea + 10 + saltos;
                }
                total += detalle.getCantidad();
                //totalKgs = totalKgs + (detalle.getPesou() * detalle.getCantidad());
                DecimalFormat formateador = new DecimalFormat("###,###,###.##");
                totalKgsChar = formateador.format(totalKgs);
            }


            //Save to Document
            pDDocument.save(fileName);

        } catch (IOException ex) {
            logger.error("IOException " + PdfEntradasManagerImpl.class.getName() + " " + ex);
        } catch (COSVisitorException ex) {
            logger.error("COSVisitorException " + PdfEntradasManagerImpl.class.getName() + " " + ex);
        } catch (Exception ex) {
            logger.error("Exception " + PdfEntradasManagerImpl.class.getName() + " " + ex);
        } finally {
            try {
                if (pDDocument != null) {
                    pDDocument.close();
                }
            } catch (IOException ex) {
                logger.error("IOException: " + PdfEntradasManagerImpl.class.getName() + " " + ex.getMessage());
            }
        }
    }

    private PDPageContentStream getPdPageContentStream(PDDocument pDDocument, Inventario inventario, List<DetallesRd> detallesRd, int paginaActual, int paginaTotal, String totalKgs, String totalPzas, List<RegEntradas> regEntradas) throws IOException {
        PDPage pDPage = new PDPage();
        pDPage.setRotation(90);
        pDPage.setMediaBox(PDPage.PAGE_SIZE_A4);
        final double media = (pDPage.findMediaBox().getWidth() / 2);
        final double MPI = Math.PI / 2;
        final Date date = new Date();
        final SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String fecha = formatFecha.format(date);
        final String hora = formatHora.format(date);

        //Write logo
        final InputStream in = new FileInputStream(new File(Utils.PATH_IMG + "arcosa.jpg"));
        final PDJpeg img = new PDJpeg(pDDocument, in);

        final PDPageContentStream contentStream = new PDPageContentStream(pDDocument, pDPage);
        //Create PDPageContentStream


        //SetFont
        PDType1Font font = PDType1Font.getStandardFont("Arial");
        float fontSize11 = 11;
        float fontSize12 = 12;
        contentStream.setFont(font, fontSize11);


        //Write data to Contentstream
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(20, 20);
        contentStream.setTextRotation(MPI, 20, media - 150);
        //logo
        contentStream.drawImage(img, 10, 30);
        contentStream.drawString("FORMATO DE CAPTURA DE INGRESO, CLASIFICACION Y VALIDACION DE PESO DEL");
        contentStream.endText();
        contentStream.close();

        contentStream.beginText();
        contentStream.moveTextPositionByAmount(20, 100);
        contentStream.setTextRotation(Math.PI / 2, 30, media);
        contentStream.drawString("PRODUCTO AL ALMACEN");
        contentStream.endText();
        contentStream.close();

        float fontSize7 = 7;
        contentStream.setFont(font, fontSize7);
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(20, 100);
        contentStream.setTextRotation(MPI, 17, media + 370);
        contentStream.drawString("GRUPO ARCOSA");
        contentStream.endText();
        contentStream.close();

        contentStream.beginText();
        contentStream.moveTextPositionByAmount(20, 100);
        contentStream.setTextRotation(MPI, 24, media + 425);
        contentStream.drawString("PTO-OPT.01.04");
        contentStream.endText();
        contentStream.close();

        contentStream.beginText();
        contentStream.moveTextPositionByAmount(20, 100);
        contentStream.setTextRotation(MPI, 31, media + 435);
        contentStream.drawString("REV. 3");
        contentStream.endText();
        contentStream.close();
        if (detallesRd.size() > 0) {
            contentStream.beginText();
            contentStream.setFont(font, fontSize12);
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(MPI, 43, media + 370);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            if (regEntradas.get(0).getFecha() != null) {
                try {
                    contentStream.drawString(formatter.format(dateFormat.parse(regEntradas.get(0).getFecha().substring(0, 10))));
                } catch (ParseException e) {
                    logger.error("Error al parserar fecha " + e);
                }

            } else {
                contentStream.drawString("");
            }
            contentStream.endText();
            contentStream.close();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(MPI, 53, media + 370);
            String textoCamara = "";
            if (detallesRd.get(0).getCamara() == null) {
                textoCamara = "Tunel "; // + detallesRd.get(0).getTunel();
            } else {
                textoCamara = detallesRd.get(0).getCamara();
            }
            contentStream.drawString("CAMARA: " + textoCamara);
            contentStream.endText();
            contentStream.close();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(MPI, 63, media + 370);
            contentStream.drawString("RD: " + String.valueOf(detallesRd.get(0).getConsecutivo()));
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(MPI, 58, media-180);
            contentStream.setFont(font, 10);
            final Clientes cliente = clienteDao.findCliente(Integer.parseInt(detallesRd.get(0).getIdCliente()));
            contentStream.drawString("CLIENTE :"+detallesRd.get(0).getIdCliente()+" "+cliente.getNombreCliente());
            contentStream.endText();
            contentStream.close();
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(MPI, 70, media -180);
            contentStream.drawString("PEDIMENTO: "+String.valueOf(detallesRd.get(0).getImpedimento()));
            contentStream.endText();
            contentStream.close();
        }
        float fontSize10 = 10;
        contentStream.setFont(font, fontSize10);
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(20, 100);
        contentStream.setTextRotation(MPI, 45, media-180);
        if (inventario.gettAnden() != null && inventario.gettAnden().equalsIgnoreCase("null")) {
            contentStream.drawString("ANDEN DESIGNADO: " + inventario.gettAnden());
        } else {
            contentStream.drawString("ANDEN DESIGNADO: ");
        }

        contentStream.endText();
        contentStream.close();

        //ENCABEZADOS 2
        float iniColumna = 50;
        float fontSize = 9;
        float sigLinea = 490;
        Utils.setNextRenglonHorizontal("OBSERVACIONES:", 1, fontSize, iniColumna, sigLinea - 10, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("_______________________________", 1, 5, iniColumna + 195, sigLinea-8, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("NOMBRE Y FIRMA DEL VALIDADOR: ", 1, 5, iniColumna + 195, sigLinea, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("__________________________________", 1, 5, iniColumna + 305, sigLinea-8, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("NOMBRE Y FIRMA DEL ALMACENISTA: ", 1, 5, iniColumna + 305, sigLinea, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("__________________________________", 1, 5, iniColumna + 425, sigLinea-8, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("NOMBRE Y FIRMA DEL CLIENTE: ", 1, 5, iniColumna + 425, sigLinea, contentStream, 0, MPI);


        Utils.setNextRenglonHorizontal("CANTIDAD TOTAL KGS: " + totalKgs, 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("CANTIDAD TOTAL PZAS: " + totalPzas, 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("POSICIONES OCUPADAS: " + detallesRd.get(0).getPosiciones(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("NIVEL DE ESTIBA: " + detallesRd.get(0).getEstibas(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("ALTURA DE TARIMA: " + detallesRd.get(0).getAltura(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("FECHA Y HORA DE IMPRESION: ", 1, 10, iniColumna + 225, sigLinea, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("TEMPERATURA DEL", 1, 10, iniColumna + 425, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("ALMACENADO EN: " + detallesRd.get(0).getAlmaen(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal(fecha + " " + hora, 1, 10, iniColumna + 225, sigLinea, contentStream, 0, MPI);
        Utils.setNextRenglonHorizontal("PRODUCTO: "+detallesRd.get(0).getTemperaturaAnden(), 1, 10, iniColumna + 425, sigLinea, contentStream, 0, MPI);

        iniColumna = 640;
        sigLinea = 490;
        fontSize = 9;
        Utils.setNextRenglonHorizontal("N° SELLO DE LA " ,1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        //Utils.setNextRenglonHorizontal("APERTURA DE PUERTAS: "+detallesRd.get(0).getApertura(), 1, fontSize, iniColumna,sigLinea, contentStream,0,MPI);


        //ENCABEZADOS 2
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("CAJA/CONTENEDOR: " +detallesRd.get(0).getApertura(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        //Utils.setNextRenglonHorizontal("CIERRE DE PUERTAS: "+detallesRd.get(0).getCierre(), 1, fontSize, iniColumna, sigLinea, contentStream,0,MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("LIBRE DE PLAGAS: " + detallesRd.get(0).getPlaga(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("LIBRE DE VIDRIO: " + detallesRd.get(0).getLibreVidrios(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("VEHICULO LIMPIO: " + detallesRd.get(0).getVehiculoLimpio(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 10;
        Utils.setNextRenglonHorizontal("VEHICULO EN BUEN ESTADO: " + detallesRd.get(0).getVehiculoBuenEstado(), 1, fontSize, iniColumna, sigLinea, contentStream, 0, MPI);
        sigLinea = sigLinea + 30;
        Utils.setNextRenglonHorizontal("Pagina " + paginaActual + " de " + paginaTotal, 1, 6, 730, sigLinea, contentStream, 0, MPI);
        //Add Page to Document
        pDDocument.addPage(pDPage);
        return contentStream;
    }

}
