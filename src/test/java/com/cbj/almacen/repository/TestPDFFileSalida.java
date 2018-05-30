package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Inventario;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jolvera on 18/06/2014.
 */
public class TestPDFFileSalida {

    private static final Logger logger = LoggerFactory
            .getLogger(TestInventario.class);

    private ApplicationContext context;

    private InventarioDao inventarioDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "classpath:test-context.xml");
        inventarioDao = (InventarioDao) context.getBean("inventarioDao");
    }

    @Test
    public void testCreaPdf() {
        //convertTextToPDF("PDFJL.pdf");
        convertTextToPDFSalidas("PDFSalidasPrueba.pdf");
    }

    /**
     * Convert Text to PDF using Apache PDFBox
     * @param fileName
     */
    public void convertTextToPDF(String fileName) {
        System.out.println("Save Text to PDF File: "+fileName);
        PDDocument pDDocument = null;
        PDPage pDPage = null;

        try {
            //Create PDDocument
            pDDocument = new PDDocument();
            pDPage = new PDPage();
            pDPage.setRotation(90);
            pDPage.setMediaBox(PDPage.PAGE_SIZE_A4);

            //Add Page to Document
            pDDocument.addPage(pDPage);

            //Write logo
            InputStream in = new FileInputStream(new File("c:/Users/jolvera/Documents/GitHub/Almacen/src/main/webapp/resources/img/logoarcosaPDF.jpg"));
            PDJpeg img = new PDJpeg(pDDocument, in);

            //Create PDPageContentStream
            PDPageContentStream contentStream = new PDPageContentStream(pDDocument, pDPage);


            //SetFont
            PDType1Font font = PDType1Font.COURIER;
            float fontSize11 = 11;
            contentStream.setFont(font, fontSize11);


            //Write data to Contentstream
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 20);
            contentStream.setTextRotation(20, 0, (pDPage.findMediaBox().getWidth()/2)-50);
            //logo
            contentStream.drawImage(img, 10, 30);
            contentStream.drawString("FORMATO DE CAPTURA DE INGRESO, CLASIFICACION Y VALIDACION DE PESO DEL");
            contentStream.endText();
            contentStream.close();

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(Math.PI / 2, 30, (pDPage.findMediaBox().getWidth() / 2));
            contentStream.drawString("PRODUCTO AL ALMACEN");
            contentStream.endText();
            contentStream.close();

            float fontSize7 = 7;
            contentStream.setFont(font, fontSize7);
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(Math.PI/2, 25, (pDPage.findMediaBox().getWidth()/2)+370);
            contentStream.drawString("ALMACENES REFRIGERADOS CONSOLIDADOS");
            contentStream.endText();
            contentStream.close();

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(Math.PI/2, 32, (pDPage.findMediaBox().getWidth()/2)+425);
            contentStream.drawString("PTO-OPT.01.04");
            contentStream.endText();
            contentStream.close();

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(Math.PI/2, 39, (pDPage.findMediaBox().getWidth()/2)+435);
            contentStream.drawString("REV. 3");
            contentStream.endText();
            contentStream.close();

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(Math.PI/2, 46, (pDPage.findMediaBox().getWidth()/2)+428);
            contentStream.drawString("09/07/2014");
            contentStream.endText();
            contentStream.close();

            int idInventario = 790;
            Inventario inventario = inventarioDao.findInventario(idInventario);

            float fontSize10 = 10;
            contentStream.setFont(font, fontSize10);
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20, 100);
            contentStream.setTextRotation(Math.PI/2, 50, (pDPage.findMediaBox().getWidth()/2));
            contentStream.drawString("ANDEN DESIGNADO: "+inventario.gettAnden());
            contentStream.endText();
            contentStream.close();


            //Save to Document
            pDDocument.save(fileName);

        } catch (IOException ex) {
            logger.info(TestPDFFile.class.getName());
        } catch (COSVisitorException ex) {
            logger.info(TestPDFFile.class.getName());
        } finally {
            try {
                if (pDDocument != null) {
                    pDDocument.close();
                }
            } catch (IOException ex) {
                logger.info(TestPDFFile.class.getName());
            }
        }
    }
    
    /**
     * Convert Text to PDF using Apache PDFBox
     * @param fileName
     */
    public void convertTextToPDFSalidas(String fileName) {
        System.out.println("Save Text to PDF File: "+fileName);
        PDDocument pDDocument = null;
        PDPage pDPage = null;

        try {
            //Create PDDocument
            pDDocument = new PDDocument();
            pDPage = new PDPage();
            //pDPage.setRotation(0);
            pDPage.setMediaBox(PDPage.PAGE_SIZE_LETTER);

            //Add Page to Document
            pDDocument.addPage(pDPage);

            //Write logo
            InputStream in = new FileInputStream(new File("C:/Users/bvelasco/git/Almacen/src/main/webapp/resources/img/logoarcosaPDFSalidas.jpg"));
            PDJpeg img = new PDJpeg(pDDocument, in);

            //Create PDPageContentStream
            PDPageContentStream contentStream = new PDPageContentStream(pDDocument, pDPage);


            //SetFont
            PDType1Font font = PDType1Font.HELVETICA_BOLD;
            float fontSize11 = 11;
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
            String idOrden=new Integer("12345").toString();
            
            
            //Imprimir
            setNextRenglon("SALIDA A IMPRIMIR No.: "+idOrden, 1, fontSize, 70, iniParrafo, contentStream);
            setNextRenglon("Pagina x de n", 1, fontSize, 530, iniParrafo, contentStream);
            
            
            //RAZON SOCIAL
            fontSize = 8;
            iniParrafo = 730;
            
            setNextRenglon("GRUPO ARCOSA.", 2, fontSize, 140, iniParrafo, contentStream);
            iniParrafo = 720;
            fontSize = 6.5f;
            iniMargen = 140;
            setNextRenglon("CALLE FRESNOS S/N", 1, fontSize, iniMargen, iniParrafo, contentStream);
            float sigLinea = iniParrafo-8;
            setNextRenglon("SAN MATEO XOLOC", 1, fontSize, iniMargen, sigLinea, contentStream);
            sigLinea = sigLinea-8;
            setNextRenglon("C.P. 08370", 1, fontSize, iniMargen, sigLinea, contentStream);
            sigLinea = sigLinea-8;
            setNextRenglon("TEPOZOTLAN", 1, fontSize, iniMargen, sigLinea, contentStream);
            sigLinea = sigLinea-8;
            setNextRenglon("ESTADO DE MEXICO", 1, fontSize, iniMargen, sigLinea, contentStream);
            
            //TIPO FORMATO	
            fontSize = 8;
            setNextRenglon("PTO-OPT.01.03", 2, fontSize, 485, 730, contentStream);
            
            
            //TITULO FORMATO	
            iniParrafo = sigLinea-50;
            fontSize = 10;
            setNextRenglon("ORDEN DE SALIDA ARCOSA No.: "+idOrden, 2, fontSize, 300, iniParrafo, contentStream);
            sigLinea = iniParrafo;
            //CLIENTE
            
            iniParrafo = sigLinea-30;
            fontSize = 9;
            iniMargen = 70;
            String idCamara=new Integer("7").toString();
            setNextRenglon("ORDEN DE SALIDA DEL CLIENTE: "+idOrden, 2, fontSize, iniMargen, iniParrafo, contentStream);
            setNextRenglon("CAMARA: "+idCamara, 2, fontSize, 450, iniParrafo, contentStream);
            sigLinea = iniParrafo-30;
            //COLUMNAS IZQUIERDA
            fontSize = 9;
            int iniMargenDatos = 150;
            setNextRenglon("CLIENTE: ", 1, fontSize, iniMargen, sigLinea, contentStream);
            String cliente = "MERCANTIL DE ALIMENTOS DEL MAR S.A DE C.V.";
            setNextRenglon(cliente, 2, fontSize, iniMargenDatos, sigLinea, contentStream);
            sigLinea = sigLinea-15;
            setNextRenglon("ENTREGAR A: ", 1, fontSize, iniMargen, sigLinea, contentStream);
            String nombre = "ALIMENTOS DEL MAR";
            setNextRenglon(nombre, 2, fontSize, iniMargenDatos, sigLinea, contentStream);
            sigLinea = sigLinea-15;
            setNextRenglon("RECIBO: ", 1, fontSize, iniMargen, sigLinea, contentStream);
            String recibo = "33360";
            setNextRenglon(recibo, 2, fontSize, iniMargenDatos, sigLinea, contentStream);
            
            
            //COLUMNAS DERECHA
            iniMargen = 410;
            iniMargenDatos = 495;
            sigLinea = iniParrafo-30;
            
            final Date date = new Date();
            final SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
            final String fecha = formatFecha.format(date);
            final String hora = formatHora.format(date);

            
            setNextRenglon("FECHA: ", 1, fontSize, iniMargen, sigLinea, contentStream);
            setNextRenglon(fecha, 2, fontSize, iniMargenDatos, sigLinea, contentStream);
            sigLinea = sigLinea-15;
            setNextRenglon("HORA: ", 1, fontSize, iniMargen, sigLinea, contentStream);
            setNextRenglon(hora, 2, fontSize, iniMargenDatos, sigLinea, contentStream);
            sigLinea = sigLinea-15;
            setNextRenglon("LA SALIDA VENCE: ", 1, fontSize, iniMargen, sigLinea, contentStream);
            setNextRenglon(fecha, 2, fontSize, iniMargenDatos, sigLinea, contentStream);
            
            //TEMPERATURA
            sigLinea = sigLinea-30;
            fontSize = 7;
            String temperatura=new Integer("20").toString();
            setNextRenglon("LOS PRODUTOS SE ENTREGAN A LA TEMPERATURA DE: "+temperatura+" °C", 2, fontSize, 300, sigLinea, contentStream);
            
            //DESCRIPCION
            iniParrafo = 480;
            iniMargen=70;
            fontSize = 10;
            setNextRenglon("DESCRIPCION DE LA SALIDA No.: "+idOrden, 2, fontSize, iniMargen, iniParrafo, contentStream);
            
            //ENCABEZADOS
            iniParrafo = 450;
            fontSize = 7;
            setNextRenglon("R", 2, fontSize, 70, iniParrafo, contentStream);
            setNextRenglon("CANTIDAD", 2, fontSize, 85, iniParrafo, contentStream);
            setNextRenglon("EMBALAJE", 2, fontSize, 125, iniParrafo, contentStream);
            setNextRenglon("PRODUCTO", 2, fontSize, 235, iniParrafo, contentStream);
            setNextRenglon("LOTE", 2, fontSize, 400, iniParrafo, contentStream);
            setNextRenglon("MARCA", 2, fontSize, 440, iniParrafo, contentStream);
            setNextRenglon("CADUCIDAD", 2, fontSize, 490, iniParrafo, contentStream);
            setNextRenglon("PESO (KG)", 2, fontSize, 535, iniParrafo, contentStream);
            Inventario inventario = inventarioDao.findInventario(54);
            sigLinea = iniParrafo-10;
            while((sigLinea-10) > 240){
            
            setNextRenglon("1", 1, fontSize, 70, sigLinea, contentStream);
            setNextRenglon("32", 1, fontSize, 95, sigLinea, contentStream);
            setNextRenglon("CAJAS DE CARTON MARCADAS", 1, fontSize, 125, sigLinea, contentStream);
            setNextRenglon("IMITACION CANGREJO BARRA 20X500 GR", 1, fontSize, 235, sigLinea, contentStream);
            setNextRenglon("L4635MO11", 1, fontSize, 400, sigLinea, contentStream);
            setNextRenglon("NEPTUNO", 1, fontSize, 440, sigLinea, contentStream);
            setNextRenglon("13/05/2015", 1, fontSize, 493, sigLinea, contentStream);
            setNextRenglon("11 KG", 1, fontSize, 539, sigLinea, contentStream);
            sigLinea = sigLinea-10;
            }
 
            //DESCRIPCION
            iniParrafo = 230;
            fontSize = 10;
            String totalPzas = new Integer("170").toString();
            setNextRenglon("TOTAL PIEZAS: "+totalPzas+" PZAS", 2, fontSize, 70, iniParrafo, contentStream);
            String totalKgs = new Integer("2170").toString();
            setNextRenglon("TOTAL KILOS: "+totalKgs+" KGS", 2, fontSize, 430, iniParrafo, contentStream);
            
            //COMENTARIOS
            iniParrafo = 200;
            fontSize = 7;
            setNextRenglon("ESTA ORDEN SURTE EFECTOS DE ENTREGA DE LA MERCANCIA DE QUE SE TRATA AL TITULAR DE LA ORDEN DE DEPOSITO No."+recibo+" Y POR TANTO ", 1, fontSize, 70, iniParrafo, contentStream);
            sigLinea = iniParrafo-10;
            setNextRenglon("DEBERA PRESENTARSE PARA RECOGER DICHA MERCANCIA, EN ESTA VIRTUD LA ENTREGA SE HARA PRECISAMENTE CONTRA LA PRESENTACION", 1, fontSize, 70, sigLinea, contentStream);
            sigLinea = sigLinea-10;
            setNextRenglon("DE ESTA ORDEN SIN NINGUN OTRO REQUISITO, POR LO QUE AL REFERIDO TITULAR DEBERA CUIDAR Y CONSERVAR LA ORDEN Y DE IMPEDIR QUE", 1, fontSize, 70, sigLinea, contentStream);
            sigLinea = sigLinea-10;
            setNextRenglon("SEA INDEBIDAMENTE PRESENTADA POR TERCERAS PERSONAS. ARCOSA NO SE HACE RESPONSABLE DELMAL USO QUE SE HAGA DE LA PROPIA", 1, fontSize, 70, sigLinea, contentStream);
            sigLinea = sigLinea-10;
            setNextRenglon("ORDEN.  LAS MERCANCIAS QUE  AMPARA ESTA ORDEN DE SALIDA SERAN ENTREGADAS PRECISAMENTE EN NUESTRAS BODEGAS POR LO QUE", 1, fontSize, 70, sigLinea, contentStream);
            sigLinea = sigLinea-10;
            setNextRenglon("RECOMENDAMOS QUE SIRVA  REVISAR  CUIDADOSAMENTE DICHAS MERCANCIAS AL RECIBIRLAS, YA QUE POR ESTA RAZON NO NOS HACEMOS", 1, fontSize, 70, sigLinea, contentStream);
            sigLinea = sigLinea-10;
            setNextRenglon("RESPONSABLES.", 1, fontSize, 70, sigLinea, contentStream);
            
            sigLinea = sigLinea-20;
            setNextRenglon("OBSERVACIONES:", 1, fontSize, 70, sigLinea, contentStream);
            

            //ENCABEZADOS 2
            sigLinea = sigLinea-20;
            fontSize = 8;
            setNextRenglon("FECHA", 1, fontSize, 70, sigLinea, contentStream);
            setNextRenglon("BULTOS", 1, fontSize, 135, sigLinea, contentStream);
            setNextRenglon("KILOGRAMOS", 1, fontSize, 200, sigLinea, contentStream);
            setNextRenglon("ENTREGO", 1, fontSize, 350, sigLinea, contentStream);
            setNextRenglon("RECIBI DE CONFORMIDAD", 1, fontSize,460, sigLinea, contentStream);
            
            sigLinea = sigLinea-15;
            setNextRenglon("___________", 1, fontSize, 127, sigLinea, contentStream);
            setNextRenglon("________________", 1, fontSize, 190, sigLinea, contentStream);
            
            //OTROS DATOS
            sigLinea = sigLinea-15;
            fontSize = 7;
            setNextRenglon("LIBRE DE PLAGAS:___", 1, fontSize, 70, sigLinea, contentStream);
            setNextRenglon("APERTURA DE PUERTAS:___", 1, fontSize, 170, sigLinea, contentStream);
            
            setNextRenglon("_________________________________", 1, fontSize, 300, sigLinea, contentStream);
            setNextRenglon("_________________________________", 1, fontSize, 450, sigLinea, contentStream);
            
            sigLinea = sigLinea-10;
            setNextRenglon("LIBRE DE VIDRIO:___", 1, fontSize, 70, sigLinea, contentStream);
            setNextRenglon("CIERRE DE PUERTAS:___", 1, fontSize, 170, sigLinea, contentStream);
            
            setNextRenglon("NOMBRE Y FIRMA", 1, fontSize, 340, sigLinea, contentStream);
            setNextRenglon("NOMBRE Y FIRMA", 1, fontSize, 480, sigLinea, contentStream);
            
            
            //Save to Document
            pDDocument.save(fileName);

        } catch (IOException ex) {
            logger.info(TestPDFFile.class.getName());
        } catch (COSVisitorException ex) {
            logger.info(TestPDFFile.class.getName());
        } finally {
            try {
                if (pDDocument != null) {
                    pDDocument.close();
                }
            } catch (IOException ex) {
                logger.info(TestPDFFile.class.getName());
            }
        }
    }
    
    private void setNextRenglon(String text, int fontType, float font2, float p1, float p2, PDPageContentStream parrafo){
    	PDType1Font font = PDType1Font.HELVETICA_BOLD;
        
    	try{
    	switch (fontType) {
    	case 1:
    			parrafo.setFont(PDType1Font.HELVETICA, font2);
    			break;
    	case 2:
    			parrafo.setFont(PDType1Font.HELVETICA_BOLD, font2);
    			break;
    	}
        parrafo.beginText();
        parrafo.moveTextPositionByAmount(p1, p2);
        parrafo.drawString(text);
        parrafo.endText();
        parrafo.close();
    	} catch (IOException ex) {
            logger.info(TestPDFFile.class.getName());
        } 
    }
    
    
}