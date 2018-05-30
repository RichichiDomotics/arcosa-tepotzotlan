package com.cbj.almacen;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by jolvera on 10/06/2014.
 */
public class Utils {

    private static final Logger logger = LoggerFactory
            .getLogger(Utils.class);
    final public static Locale REGION_MEXICO= new Locale("es","MX");
    final public static String CLIENTE_ESTATUS_ACTIVO = "ACTIVO";
    final public static String TIPO_MOVIMIENTO_ENTRADA = "1111";
    final public static String TIPO_MOVIMIENTO_SALIDA = "1112";
    //LOCAL CARLOS
    //final public static String PATH_URL ="C:\\WLSoftware\\WorkSpaceArcosa2\\Almacen\\Almacen\\src\\main\\webapp\\resources\\pdf\\";
    //final public static String PATH_IMG ="c:/WLSoftware/WorkSpaceArcosa2/Almacen/Almacen/src/main/webapp/resources/img/";
    //final public static String PATH_MAIL_SALIDA ="c:/WLSoftware/WorkSpaceArcosa2/Almacen/Almacen/src/main/webapp/resources/mail/html/mailSalidatexto.html";

    //LOCAL JORGE
    //final public static String PATH_URL ="C:\\workspace\\Arcosa\\src\\main\\webapp\\resources\\pdf\\";
    /*final public static String PATH_URL ="C:\\xampp\\htdocs\\Ace\\pdf\\";
    final public static String PATH_IMG ="c:/workspace/Arcosa/src/main/webapp/resources/img/";
    final public static String PATH_MAIL_SALIDA ="http://localhost:8080/resources/mail/html/mailSalidatexto.html";*/

    //DESAROLLO
    //final public static String PATH_IMG ="/opt/apache-tomcat-7.0.53/webapps/almacen/resources/img/";
    //final public static String PATH_URL ="/opt/apache-tomcat-7.0.53/webapps/almacen/resources/pdf/";
    //final public static String PATH_MAIL_SALIDA ="/opt/apache-tomcat-7.0.53/webapps/almacen/resources/mail/html/mailSalidatexto.html";

    //PRODUCCION
    /*final public static String PATH_IMG ="/opt/tomcat7/webapps/almacen/resources/img/";
    final public static String PATH_URL ="/opt/tomcat7/webapps/almacen/resources/pdf/";
    final public static String PATH_MAIL_SALIDA ="/opt/tomcat7/webapps/almacen/resources/mail/html/mailSalidatexto.html";*/

    //PRODUCCION servidor nuevo
    final public static String PATH_IMG ="/opt/tomcat/webapps/almacen/resources/img/";
    final public static String PATH_URL ="/opt/tomcat/webapps/almacen/resources/pdf/";
    final public static String PATH_MAIL_SALIDA ="/opt/tomcat/webapps/almacen/resources/mail/html/mailSalidatexto.html";

    //PRODUCCION TEPO 2
    //final public static String PATH_IMG ="/opt/apache-tomcat-8.0.23/webapps/almacen/resources/img/";
    //final public static String PATH_URL ="/opt/apache-tomcat-8.0.23/webapps/almacen/resources/pdf/";
    //final public static String PATH_MAIL_SALIDA ="/opt/apache-tomcat-8.0.23/webapps/almacen/resources/mail/html/mailSalidatexto.html";


    /**
     * @param page
     * @param contentStream
     * @param y the y-coordinate of the first row
     * @param margin the padding on left and right of table
     * @param content a 2d array containing the table data
     * @throws java.io.IOException
     */
    public static void drawTableHorizontal(PDPage page, PDPageContentStream contentStream,
                                           float y, float margin,
                                           List<String[]> content) throws IOException {
        final int rows = content.size();
        final int cols = content.get(0).length;
        final float rowHeight = 20f;
        final float tableWidth = 600;
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth/(float)cols;
        final float cellMargin=5f;

        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= cols; i++) {
            contentStream.drawLine(margin, nexty, margin + tableHeight, nexty);
            nexty-= colWidth;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= rows; i++) {
            contentStream.drawLine(nextx, y, nextx, y - tableWidth);
            nextx += rowHeight;
        }

        //now add the text

        contentStream.setFont(PDType1Font.HELVETICA_BOLD,7);

        float texty = margin+cellMargin+10;
        float textx = y-tableWidth+15;
        for(String[] reng : content){
            for(String columna : reng){
                String text = columna;
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(texty, textx);
                contentStream.setTextRotation(Math.PI/2,texty,textx);
                contentStream.drawString(text);
                contentStream.endText();
                textx += colWidth;
            }
            texty+=rowHeight;
            textx = y-tableWidth+15;
        }
    }


    /**
     * @param page
     * @param contentStream
     * @param y the y-coordinate of the first row
     * @param margin the padding on left and right of table
     * @param content a 2d array containing the table data
     * @throws IOException
     */
    public static void drawTableVertical(PDPage page, PDPageContentStream contentStream,
                                         float y, float margin,
                                         String[][] content) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float rowHeight = 20f;
        final float tableWidth = page.findMediaBox().getWidth()-(2*margin);
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth/(float)cols;
        final float cellMargin=5f;

        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= rows; i++) {
            contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
            nexty-= rowHeight;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.drawLine(nextx,y,nextx,y-tableHeight);
            nextx += colWidth;
        }

        //now add the text
        contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);

        float textx = margin+cellMargin;
        float texty = y-15;
        for(int i = 0; i < content.length; i++){
            for(int j = 0 ; j < content[i].length; j++){
                String text = content[i][j];
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(textx,texty);
                contentStream.drawString(text);
                contentStream.endText();
                textx += colWidth;
            }
            texty-=rowHeight;
            textx = margin+cellMargin;
        }
    }

    public static int setNextRenglon(String text, int fontType, float font2, float p1, float p2, PDPageContentStream parrafo, int longitud){
        int saltos = 0;
        int cantSaltos = 8;
        if(longitud>0 && text.length()>longitud){
            while(text.length()>longitud){
                setNextRenglonExtended(text.substring(0,longitud), fontType, font2, p1, p2,parrafo);
                p2 = p2-cantSaltos;
                text = text.substring(longitud);
                saltos += cantSaltos;
            }
        }
        final PDType1Font font = PDType1Font.HELVETICA_BOLD;
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
            logger.info(Utils.class.getName() +" "+ ex.getMessage());
        }
        return saltos;
    }

    public static void setNextRenglonExtended(String text, int fontType, float font2, float p1, float p2, PDPageContentStream parrafo){
        final PDType1Font font = PDType1Font.HELVETICA_BOLD;
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
            logger.info(Utils.class.getName() +" "+ ex.getMessage());
        }
    }

    public static int setNextRenglonHorizontal(String text, int fontType, float font2, float p1, float p2, PDPageContentStream parrafo, int longitud,double mpi){
        int saltos = 0;
        int cantSaltos = 8;
        try{
        if(longitud>0 && text.length()>longitud){
            while(text.length()>longitud){
                setNextRenglonHorizontalExtended(text.substring(0,longitud), fontType, font2, p1, p2,parrafo,mpi);
                p2 = p2+cantSaltos;
                text = text.substring(longitud);
                saltos += cantSaltos;
            }
        }
        final PDType1Font font = PDType1Font.HELVETICA_BOLD;
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
            parrafo.setTextRotation(mpi, p2, p1);

            parrafo.drawString(text);
            parrafo.endText();
            parrafo.close();
        } catch (IOException ex) {
            logger.error(Utils.class.getName() +" "+ ex);
        } catch (Exception ex) {
            logger.error(Utils.class.getName() +" "+ ex);
        }
        return saltos;
    }


    public static void setNextRenglonHorizontalExtended(String text, int fontType, float font2, float p1, float p2, PDPageContentStream parrafo,double mpi){
        final PDType1Font font = PDType1Font.HELVETICA_BOLD;
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
            parrafo.setTextRotation(mpi, p2, p1);
            parrafo.drawString(text);
            parrafo.endText();
            parrafo.close();
        } catch (IOException ex) {
            logger.info(Utils.class.getName() +" "+ ex.getMessage());
        }
    }

    public static String getFechaTexto() {
        Locale loc = new Locale("es","MX");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, loc);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");
        DateFormat timedayFormat = new SimpleDateFormat("a");
        Calendar cal = Calendar.getInstance();
        String diasTardes = "Buenos dias";
        if(!timedayFormat.format(cal.getTime()).equalsIgnoreCase("AM")){
            diasTardes = "Buenas tardes";
        }
        return diasTardes+", son las "+timeFormat.format(cal.getTime())+ " del día "+dateFormat.format(cal.getTime());
    }

}
