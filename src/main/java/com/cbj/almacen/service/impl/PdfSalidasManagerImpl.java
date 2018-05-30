package com.cbj.almacen.service.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.*;
import com.cbj.almacen.repository.*;
import com.cbj.almacen.service.PdfSalidasManager;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 22/06/2014.
 */
@Component
public class PdfSalidasManagerImpl implements PdfSalidasManager {

    private static final Logger logger = LoggerFactory
            .getLogger(PdfSalidasManagerImpl.class);

    @Autowired
    private InventarioDao inventarioDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private SalidasDao salidasDao;

    @Autowired
    private RegEntradasDao regEntradasDao;
    @Autowired
    private SalidasDetalleDao salidasDetalleDao;

    @Autowired
    private  SalidasAlmacenDao salidasAlmacenDao;

    @Autowired
    private DetalleRdDao detalleRdDao;


    @Autowired
    private ServletContext servletContext;


    @Override
    public void generaPdfSalidas(int idSalida) {
        final Salidas salidas = salidasDao.findSalidaByIdFolioSalida(idSalida);
        generaPDF(Utils.PATH_URL + "salida" + salidas.getConsecutivo() + ".pdf", salidas.getFolioSalida(), salidas.getConsecutivo(), salidas.getIdCliente(), salidas);
    }

    /**
     * Convert Text to PDF using Apache PDFBox
     *
     * @param fileName
     */
    public void generaPDF(String fileName, int folioSalida, int consecutivo, String idCliente, Salidas salida) {
        PDDocument pDDocument = null;
        try {
            int paginaActual = 1;
            double registrosXpagina = 20;
            //Create PDDocument
            pDDocument = new PDDocument();
            final List<DetallesRd> detallesRd = detalleRdDao.getAllByFolioSalida(folioSalida, consecutivo, idCliente);
            final List<SalidasDetalle> salidasDetalles = salidasDetalleDao.findSalidaByClienteConsecutivo(detallesRd.get(0).getIdCliente(), folioSalida);
            final SalidasAlmacen salidasAlmacen= salidasAlmacenDao.findSalidabyFolioSalida(salidasDetalles.get(0).getFolioSalida().toString());
            double paginaTotal = salidasDetalles.size()/registrosXpagina;
            //int REGISTROS = 35;
            //double paginaTotal = REGISTROS / registrosXpagina;
            double totalPzas = 0;
            double totalKgs = 0;
            float iniParrafo = 550;
            float sigLinea = iniParrafo - 10;
            PDPageContentStream contentStream = getPdPageContentStream(pDDocument, salida, detallesRd, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), sigLinea, salidasAlmacen.getEntregara());

            //ENCABEZADOS

            float fontSize = 6;
            Utils.setNextRenglon("DESCRIPCION DE LA SALIDA ", 1, fontSize, 50, iniParrafo+10, contentStream, 0);
            //Utils.setNextRenglon(String.valueOf(salida.getFolioSalida()), 2, fontSize, 190, iniParrafo+10, contentStream, 0);
            //Utils.setNextRenglon("R", 2, fontSize, 70, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("RD", 2, fontSize, 50, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("CANTIDAD", 2, fontSize, 75, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("RENGLON",2,fontSize,150,iniParrafo,contentStream,0);
            //Utils.setNextRenglon("EMBALAJE", 2, fontSize, 115, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("CAMARA", 2, fontSize, 115, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("PRODUCTO", 2, fontSize, 212, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("LOTE", 2, fontSize, 336, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("MARCA", 2, fontSize, 435, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("CADUCIDAD", 2, fontSize, 492, iniParrafo, contentStream, 0);
            Utils.setNextRenglon("PESO U. (KG)", 2, fontSize, 540, iniParrafo, contentStream, 0);
            DecimalFormat formatterSinDec = new DecimalFormat("###,###,###");
            DecimalFormat formatterConDec = new DecimalFormat("###,###,###.00");
            int saltosMarca = 0;
            int saltosEmbalaje = 0;
            int saltosLote = 0;
            int saltos = 0;
            logger.info("Save Text to PDF File: 13");
            String salidaDetalleProducto = "";
            DecimalFormat formateador = new DecimalFormat("###,###,###.##");
            for(SalidasDetalle salidas:salidasDetalles){
                //for (int i = 0; i < REGISTROS; i++) {
                //  SalidasDetalle salidas = salidasDetalles.get(0);
                /*if(salidas.getConsecutivo()!=null && salidas.getConsecutivo()!=null) {
                    String RD =  salidas.getConsecutivo();
                }*/
                totalPzas = totalPzas + salidas.getCantidadSalida();
                if(salidas.getPesou()!=null && salidas.getCantidadSalida()!=null) {
                    totalKgs = totalKgs + (salidas.getPesou() * salidas.getCantidadSalida());
                }
                if (sigLinea < 100) {
                    paginaActual = paginaActual + 1;
                    contentStream = getPdPageContentStream(pDDocument, salida, detallesRd, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), sigLinea, salidasAlmacen.getEntregara());
                    sigLinea = iniParrafo - 10;
                }
                //Utils.setNextRenglon(ifNullEmpty(String.valueOf(salidas.getRenglon())), 1, fontSize, 70, sigLinea, contentStream,0);
                //Utils.setNextRenglon(sigLinea + "", 1, fontSize, 70, sigLinea, contentStream, 0);
                Utils.setNextRenglon(ifNullEmpty(String.valueOf(salidas.getConsecutivo())),1, fontSize, 50, sigLinea, contentStream, 0);
                Utils.setNextRenglon(ifNullEmpty(String.valueOf(formatterSinDec.format(salidas.getCantidadSalida()))), 1, fontSize, 80, sigLinea, contentStream, 0);
                Utils.setNextRenglon(String.valueOf(salidas.getRenglon()),1,fontSize,160,sigLinea,contentStream,0);
                //saltosEmbalaje = Utils.setNextRenglon(salidas.getEmbalaje(), 1, fontSize-1, 115, sigLinea, contentStream, 24);
                if (salidas.getProducto() == null) {
                    salidaDetalleProducto = "";
                } else {
                    salidaDetalleProducto = salidas.getProducto();
                }
                Utils.setNextRenglon(ifNullEmpty(String.valueOf(salidas.getCamara())), 1, fontSize, 123, sigLinea, contentStream, 0);
                saltos = Utils.setNextRenglon(salidaDetalleProducto, 1, fontSize-1, 212, sigLinea, contentStream, 37);
                int longitudCadena= salidas.getLote().length();
                saltosLote = Utils.setNextRenglon(salidas.getLote(), 1, fontSize, 336, sigLinea, contentStream, 24);
                saltosMarca = Utils.setNextRenglon(salidas.getMarca(), 1, fontSize, 435, sigLinea, contentStream, 12);
                Utils.setNextRenglon(salidas.getCaducidad(), 1, fontSize, 500, sigLinea, contentStream, 0);
                Utils.setNextRenglon(String.valueOf(formatterConDec.format(salidas.getPesou())), 1, fontSize, 543, sigLinea, contentStream, 0);
                if (saltosEmbalaje > saltos && saltosEmbalaje > saltosMarca) {
                    sigLinea = sigLinea - 10 - saltosEmbalaje;
                } else if (saltosMarca > saltos) {
                    sigLinea = sigLinea - 10 - saltosMarca;
                } else if (saltosLote > saltos) {
                    sigLinea = sigLinea - 10 - saltosLote;
                }else {
                    sigLinea = sigLinea - 10 - saltos;
                }
            }

            contentStream = totalesConteo(paginaActual, paginaTotal, totalPzas, totalKgs, contentStream,sigLinea,detallesRd,salida,pDDocument, salidasAlmacen.getEntregara());

            //Save to Document
            pDDocument.save(fileName);
        }  catch (IOException ex) {
            logger.info(PdfSalidasManagerImpl.class.getName() + ex.getMessage());
            ex.printStackTrace();
        } catch (COSVisitorException ex) {
            logger.info(PdfSalidasManagerImpl.class.getName() + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.info(PdfSalidasManagerImpl.class.getName() + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (pDDocument != null) {
                    pDDocument.close();
                }
            } catch (IOException ex) {
                logger.info(PdfSalidasManagerImpl.class.getName() + ex.getMessage());
            }
        }
    }

    private PDPageContentStream totalesConteo(int paginaActual, double paginaTotal, double totalPzas, double totalKgs, PDPageContentStream contentStream, float sigLinea, List<DetallesRd> detallesRd, Salidas salida, PDDocument pDDocument, String entregado) {
        float iniParrafo;
        float fontSize;
        logger.info(">>><<< sigLinea "+sigLinea);
        if (paginaActual == new Double(Math.ceil(paginaTotal)).intValue() && sigLinea > 242) {
            //DESCRIPCION
            logger.info("primera condicion");
            iniParrafo = 230;
            fontSize = 8;
            sigLinea = iniParrafo;
            DecimalFormat formatter1 = new DecimalFormat("###,###,###");
            Utils.setNextRenglon("TOTAL PIEZAS: " + formatter1.format(totalPzas) + " PZAS", 2, fontSize, 60, sigLinea, contentStream, 0);
            DecimalFormat formatter = new DecimalFormat("###,###,###.00");
            Utils.setNextRenglon("TOTAL KILOS: " + formatter.format(totalKgs) + " KGS", 2, fontSize, 430, sigLinea, contentStream, 0);
            contentStream = footerPrint(detallesRd, contentStream, salida.getFolioSalida(), 440, salida, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), pDDocument, false, entregado);
        }else if (paginaActual == new Double(Math.floor(paginaTotal)).intValue() && sigLinea > 242) {
            logger.info("segunda condicion");
            iniParrafo = 230;
            fontSize = 8;
            sigLinea = iniParrafo;
            DecimalFormat formatter1 = new DecimalFormat("###,###,###");
            Utils.setNextRenglon("TOTAL PIEZAS: " + formatter1.format(totalPzas) + " PZAS", 2, fontSize, 60, sigLinea, contentStream, 0);
            DecimalFormat formatter = new DecimalFormat("###,###,###.00");
            Utils.setNextRenglon("TOTAL KILOS: " + formatter.format(totalKgs) + " KGS", 2, fontSize, 430, sigLinea, contentStream, 0);
            contentStream = footerPrint(detallesRd, contentStream, salida.getFolioSalida(), 440, salida, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), pDDocument, false, entregado);
        }else if(paginaActual+1 == new Double(Math.ceil(paginaTotal)).intValue() && sigLinea < 242){
            logger.info("tercera condicion");
            contentStream = footerPrint(detallesRd, contentStream, salida.getFolioSalida(), 440, salida, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), pDDocument, true,entregado);
            iniParrafo = 230;
            fontSize = 8;
            sigLinea = iniParrafo;
            DecimalFormat formatter1 = new DecimalFormat("###,###,###");
            Utils.setNextRenglon("TOTAL PIEZAS: " + formatter1.format(totalPzas) + " PZAS", 2, fontSize, 60, sigLinea, contentStream, 0);
            DecimalFormat formatter = new DecimalFormat("###,###,###.00");
            Utils.setNextRenglon("TOTAL KILOS: " + formatter.format(totalKgs)+ " KGS", 2, fontSize, 430, sigLinea, contentStream, 0);

        }else{
            logger.info("cuarta condicion");
            contentStream = footerPrint(detallesRd, contentStream, salida.getFolioSalida(), 440, salida, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), pDDocument, true,entregado);
            iniParrafo = 230;
            fontSize = 8;
            sigLinea = iniParrafo;
            DecimalFormat formatter1 = new DecimalFormat("###,###,###");
            Utils.setNextRenglon("TOTAL PIEZAS: " + formatter1.format(totalPzas) + " PZAS", 2, fontSize, 60, sigLinea, contentStream, 0);
            DecimalFormat formatter = new DecimalFormat("###,###,###.00");
            Utils.setNextRenglon("TOTAL KILOS: " + formatter.format(totalKgs)+ " KGS", 2, fontSize, 430, sigLinea, contentStream, 0);
                contentStream = footerPrint(detallesRd, contentStream, salida.getFolioSalida(), 440, salida, paginaActual,  new Double(Math.ceil(paginaTotal)).intValue(), pDDocument,true,entregado);
        }
        return contentStream;
    }

    private PDPageContentStream getPdPageContentStream(PDDocument pDDocument, Salidas salida, List<DetallesRd> detallesRd, int paginaActual, int paginaTotal, float sigLineaLista, String Entregado) throws IOException {
        PDPage pDPage = new PDPage();
        int consecutivo = salida.getConsecutivo();
        int folioSalida = salida.getFolioSalida();
        String idCliente = salida.getIdCliente();
        //pDPage.setRotation(0);
        pDPage.setMediaBox(PDPage.PAGE_SIZE_LETTER);

        //Add Page to Document
        pDDocument.addPage(pDPage);

        //Write logo
        final InputStream in = new FileInputStream(new File(Utils.PATH_IMG + "arcosa-salida.jpg"));
        final PDJpeg img = new PDJpeg(pDDocument, in);
        //Create PDPageContentStream
        PDPageContentStream contentStream = new PDPageContentStream(pDDocument, pDPage);


        //SetFont
        final PDType1Font font = PDType1Font.HELVETICA_BOLD;
        final float fontSize11 = 11;
        contentStream.setFont(font, fontSize11);


        //Write data to Contentstream
        contentStream.beginText();
        //logo
        contentStream.drawImage(img, 50, 680);
        contentStream.endText();
        contentStream.close();

        float fontSize = 6.5f;
        float iniParrafo = 30;
        float iniMargen = 0;
        final String idOrden = String.valueOf(consecutivo);

        final Clientes cliente = clienteDao.findCliente(Integer.parseInt(detallesRd.get(0).getIdCliente()));
        final List<RegEntradas> regEntradas = regEntradasDao.findRegistroRegEntradasByConsecutivo(consecutivo);
        final Inventario inventario = inventarioDao.findByClienteConsecutivo(detallesRd.get(0).getIdCliente(), consecutivo);
        //Imprimir
        Utils.setNextRenglon("SALIDA A IMPRIMIR No.: " + folioSalida, 1, fontSize, 60, iniParrafo, contentStream, 0);
        Utils.setNextRenglon("Pagina " + paginaActual + " de " + paginaTotal, 1, fontSize, 530, iniParrafo, contentStream, 0);


        //RAZON SOCIAL
        fontSize = 8;
        iniParrafo = 730;

        Utils.setNextRenglon("GRUPO ARCOSA.", 2, fontSize, 140, iniParrafo, contentStream, 0);
        iniParrafo = 720;
        fontSize = 6.5f;
        iniMargen = 140;
        Utils.setNextRenglon("CALLE FRESNOS S/N", 1, fontSize, iniMargen, iniParrafo, contentStream, 0);
        float sigLinea = iniParrafo - 8;
        Utils.setNextRenglon("SAN MATEO XOLOC", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 8;
        Utils.setNextRenglon("C.P. 08370", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 8;
        Utils.setNextRenglon("TEPOZOTLAN", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 8;
        Utils.setNextRenglon("ESTADO DE MEXICO", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        //TIPO FORMATO
        fontSize = 8;
        Utils.setNextRenglon("PTO-OPT.03.01", 2, fontSize, 485, 730, contentStream, 0);

        int recibo = 0;
        if (salida.getFolioSalida() != null) {
            recibo = salida.getFolioSalida();
        }
        final SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = new Date();
        final String fecha = formatFecha.format(date);
        final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        final String hora = formatHora.format(date);

        int iniMargenDatos = 150;
        //TITULO FORMATO
        iniParrafo = sigLinea - 50;
        fontSize = 10;
        iniMargen = 60;
        Utils.setNextRenglon("FECHA: ", 1, fontSize, iniMargen, iniParrafo, contentStream, 0);
        Utils.setNextRenglon(fecha, 2, fontSize, iniMargen+75, iniParrafo, contentStream, 0);
        iniMargen = 300;
        Utils.setNextRenglon("HORA: ", 1, fontSize, iniMargen, iniParrafo, contentStream, 0);
        Utils.setNextRenglon(hora, 2, fontSize, iniMargen+75, iniParrafo, contentStream, 0);
        sigLinea = iniParrafo - 10;
        iniMargen = 60;
        Utils.setNextRenglon("CLIENTE: ", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        String clienteStr = cliente.getNombreCliente();
        String idClienteStr = cliente.getIdCliente().toString();
        int saltos = Utils.setNextRenglon(idClienteStr+" "+clienteStr, 2, fontSize, iniMargen+75, sigLinea, contentStream, 60);
        sigLinea = iniParrafo - 30;
        iniMargen = 60;
        Utils.setNextRenglon("ENTREGADO A: ", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        Utils.setNextRenglon(Entregado, 2, fontSize, iniMargen+75, sigLinea, contentStream, 60);
        sigLinea = iniParrafo - 50;
        iniMargen = 60;
        Utils.setNextRenglon("VENCIMIENTO: ", 1, fontSize, iniMargen, sigLinea, contentStream, 0);
        Utils.setNextRenglon(fecha, 2, fontSize, iniMargen+75, sigLinea, contentStream, 0);
        fontSize = 7;
        iniMargen = 300;
        String temperatura = "___________ °C";
        Utils.setNextRenglon("LOS PRODUCTOS SE ENTREGAN A LA TEMPERATURA DE: " + temperatura, 2, fontSize, iniMargen, sigLinea, contentStream, 0);
        sigLinea = iniParrafo - 60;
        Utils.setNextRenglon("FOLIO DE SALIDA :"+ recibo, 2, 12, iniMargen, sigLinea, contentStream, 0);
        return contentStream;
    }

    private PDPageContentStream footerPrint(List<DetallesRd> detallesRd, PDPageContentStream contentStream, int recibo, float sigLineaLista, Salidas salida, int paginaActual, int paginaTotal, PDDocument pDDocument,boolean newPage, String entregado) {
            try {
                if(newPage) {
                    paginaTotal = paginaTotal + 1;
                    paginaActual = paginaActual + 1;
                    contentStream = getPdPageContentStream(pDDocument, salida, detallesRd, paginaActual, new Double(Math.ceil(paginaTotal)).intValue(), 440,entregado);
                    contentStream = imprimeFooter(detallesRd, contentStream, recibo);
                }else{
                    contentStream = imprimeFooter(detallesRd, contentStream, recibo);
                }
            } catch (IOException e) {
                logger.error("ERROR al crear doc pdf " + e.getMessage());
            }
        return contentStream;
    }

    private PDPageContentStream imprimeFooter(List<DetallesRd> detallesRd, PDPageContentStream contentStream, int recibo) {
        float iniParrafo;
        float sigLinea;
        float fontSize;//COMENTARIOS
        iniParrafo = 200;
        sigLinea = iniParrafo;
        fontSize = 7;
        Utils.setNextRenglon("LA PRESENTE ORDEN SURTE EFECTO DE ENTREGA DE LA MERCANCÍA A LA PERSONA SEÑALADA EN LA CARTA DE RETIRO, ASÍ MISMO LE PEDIMOS", 1, fontSize, 60, sigLinea, contentStream, 0);
        sigLinea = iniParrafo - 10;
        Utils.setNextRenglon("VERIFICAR LA MERCANCÍA A SU RECIBO CON RESPECTO A LO DECLARADO EN LA PRESENTE ORDEN, YA QUE GRUPO ARCOSA NO SE HARÁ ", 1, fontSize, 60, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 10;
        Utils.setNextRenglon("RESPONSABLE DE FALTANTES O DAÑOS UNA VEZ QUE ESTA HA SALIDO DEL ALMACEN.", 1, fontSize, 60, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 10;
        /*Utils.setNextRenglon("SEA INDEBIDAMENTE PRESENTADA POR TERCERAS PERSONAS. ARCOSA NO SE HACE RESPONSABLE DELMAL USO QUE SE HAGA DE LA PROPIA", 1, fontSize, 60, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 10;
        Utils.setNextRenglon("ORDEN.  LAS MERCANCIAS QUE  AMPARA ESTA ORDEN DE SALIDA SERAN ENTREGADAS PRECISAMENTE EN NUESTRAS BODEGAS POR LO QUE", 1, fontSize, 60, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 10;
        Utils.setNextRenglon("RECOMENDAMOS QUE SIRVA  REVISAR  CUIDADOSAMENTE DICHAS MERCANCIAS AL RECIBIRLAS, YA QUE POR ESTA RAZON NO NOS HACEMOS", 1, fontSize, 60, sigLinea, contentStream, 0);
        sigLinea = sigLinea - 10;
        Utils.setNextRenglon("RESPONSABLES.", 1, fontSize, 60, sigLinea, contentStream, 0);
        logger.info("saving file >> 2");
        sigLinea = sigLinea - 20;
        Utils.setNextRenglon("OBSERVACIONES:", 1, fontSize, 60, sigLinea, contentStream, 0);*/


        //ENCABEZADOS 2
        sigLinea = sigLinea - 20;
        fontSize = 8;
        /*Utils.setNextRenglon("FECHA", 1, fontSize, 60, sigLinea, contentStream, 0);
        Utils.setNextRenglon("BULTOS", 1, fontSize, 135, sigLinea, contentStream, 0);
        Utils.setNextRenglon("KILOGRAMOS", 1, fontSize, 200, sigLinea, contentStream, 0);*/
        Utils.setNextRenglon("ENTREGO", 1, fontSize, 350, sigLinea, contentStream, 0);
        Utils.setNextRenglon("RECIBI DE CONFORMIDAD", 1, fontSize, 460, sigLinea, contentStream, 0);

        sigLinea = sigLinea - 15;
        //Utils.setNextRenglon(ifNullEmpty(String.valueOf(detallesRd.get(0).getBultos())), 1, fontSize, 127, sigLinea, contentStream, 0);
        Utils.setNextRenglon("________________", 1, fontSize, 190, sigLinea, contentStream, 0);

        //OTROS DATOS
        sigLinea = sigLinea - 15;
        fontSize = 7;
        Utils.setNextRenglon("LIBRE DE PLAGAS: " + ifNullEmpty(detallesRd.get(0).getPlaga()), 1, fontSize, 60, sigLinea, contentStream, 0);
        Utils.setNextRenglon("APERTURA DE PUERTAS:" , 1, fontSize, 170, sigLinea, contentStream, 0);

        Utils.setNextRenglon("_________________________________", 1, fontSize, 300, sigLinea, contentStream, 0);
        Utils.setNextRenglon("_________________________________", 1, fontSize, 450, sigLinea, contentStream, 0);

        sigLinea = sigLinea - 10;
        Utils.setNextRenglon("LIBRE DE VIDRIO: " + ifNullEmpty(detallesRd.get(0).getLibreVidrios()), 1, fontSize, 60, sigLinea, contentStream, 0);
        Utils.setNextRenglon("CIERRE DE PUERTAS: " , 1, fontSize, 170, sigLinea, contentStream, 0);

        Utils.setNextRenglon("NOMBRE Y FIRMA", 1, fontSize, 340, sigLinea, contentStream, 0);
        Utils.setNextRenglon("NOMBRE Y FIRMA", 1, fontSize, 480, sigLinea, contentStream, 0);

        return contentStream;
    }

    private String ifNullEmpty(String valor) {
        if (null == valor || valor.equalsIgnoreCase("null")) {
            return "";
        } else{
            return valor;
        }
    }

}
