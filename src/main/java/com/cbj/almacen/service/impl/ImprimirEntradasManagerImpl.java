package com.cbj.almacen.service.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.repository.ClienteDao;
import com.cbj.almacen.repository.DetalleRdDao;
import com.cbj.almacen.service.ImprimirEntradasManager;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImprimirEntradasManagerImpl implements ImprimirEntradasManager {

    private static final Logger logger = LoggerFactory
            .getLogger(PdfSalidasManagerImpl.class);

    @Autowired
    DetalleRdDao detalleRdDao;
    @Autowired
    private ClienteDao clienteDao;

    @Override
    public boolean imprimirEntrada(int consecutivo) {
        final String IMG = Utils.PATH_IMG+"Logos-Arcosa-01.jpg";

        String pdfFilename = Utils.PATH_URL+"entrada"+consecutivo+".pdf";

        Document doc = new Document();
        PdfWriter docWriter = null;
        String anden = "";
        java.util.List<DetallesRd> detallesRds = detalleRdDao.getAllByConsecutivo(consecutivo);
        Clientes clientes = new Clientes();
        Double totalkgs = 0d;
        Double totalpzas = 0d;
        DecimalFormat df = new DecimalFormat("#.00");

        try {

            final Date date = new Date();
            final SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
            final String fecha1 = formatFecha.format(date);
            final String hora1 = formatHora.format(date);



            float[] columnChck = {15f, 55f, 20f};
            //special font sizes
            Font bfBold12 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.HELVETICA, 6);
            Font paragraphFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));

            PdfPTable datosEmpresa = new PdfPTable(columnChck);


            PdfPCell dato1 = new PdfPCell(new Phrase("", paragraphFont));
            PdfPCell dato2 = new PdfPCell(new Phrase("", paragraphFont));
            PdfPCell dato3 = new PdfPCell(new Phrase("", paragraphFont));
            PdfPCell dato4 = new PdfPCell(new Phrase("", paragraphFont));
            PdfPCell dato5 = new PdfPCell(new Phrase("", paragraphFont));
            PdfPCell dato6 = new PdfPCell(new Phrase("", paragraphFont));

            PdfPCell titulo = new PdfPCell(new Phrase("RECIBO DE DEPOSITO", paragraphFont));
            PdfPCell codificacion1 = new PdfPCell(new Phrase("GRUPO ARCOSA", bf12));
            PdfPCell codificacion2 = new PdfPCell(new Phrase("MNL.SIS.02.02", bf12));
            PdfPCell codificacion3 = new PdfPCell(new Phrase("Revisión 2", bf12));
            PdfPCell codificacion4 = new PdfPCell(new Phrase("Emisión: 27/03/18", bf12));
            PdfPCell extra = new PdfPCell(new Phrase(""));

            dato1.setBorder(0);
            dato2.setBorder(0);
            dato3.setBorder(0);
            dato4.setBorder(0);
            dato5.setBorder(0);
            dato6.setBorder(0);

            titulo.setBorder(0);
            codificacion1.setBorder(0);
            codificacion2.setBorder(0);
            codificacion3.setBorder(0);
            codificacion4.setBorder(0);
            extra.setBorder(0);

            titulo.setHorizontalAlignment(1);
            codificacion1.setHorizontalAlignment(1);
            codificacion2.setHorizontalAlignment(1);
            codificacion3.setHorizontalAlignment(1);
            codificacion4.setHorizontalAlignment(1);

		   /*dato1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   dato2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   dato3.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   dato4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   dato5.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   dato6.setBackgroundColor(BaseColor.LIGHT_GRAY);

		   titulo.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   codificacion1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   codificacion2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   codificacion3.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   codificacion4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   extra.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   */


            datosEmpresa.addCell(dato1);
            datosEmpresa.addCell(extra);
            datosEmpresa.addCell(extra);
            datosEmpresa.addCell(dato2);
            datosEmpresa.addCell(extra);
            datosEmpresa.addCell(codificacion1);
            datosEmpresa.addCell(dato3);
            datosEmpresa.addCell(titulo);
            datosEmpresa.addCell(codificacion2);
            datosEmpresa.addCell(dato4);
            datosEmpresa.addCell(extra);
            datosEmpresa.addCell(codificacion3);
            datosEmpresa.addCell(dato5);
            datosEmpresa.addCell(extra);
            datosEmpresa.addCell(codificacion4);
            datosEmpresa.addCell(dato6);
            datosEmpresa.addCell(extra);
            datosEmpresa.addCell(extra);

            datosEmpresa.setWidthPercentage(82f);
            datosEmpresa.setHorizontalAlignment(2);




            //file path
            String path =  pdfFilename;
            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));

            //document header attributes
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("MySampleCode.com");
            doc.addTitle("Report with Column Headings");
            doc.setPageSize(PageSize.LETTER);

            //open document
            doc.open();

            //create a paragraph
            Paragraph paragraph = new Paragraph("");

            paragraph.add(datosEmpresa);

            Image image;
            try {
                image = Image.getInstance(IMG);
                image.scalePercent(10);
                image.setAbsolutePosition(40, 690);
                paragraph.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" + ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " + ex);
            }


            //specify column widths
            float[] columnWidths = {1f, 1f, 1f, 1f,1f, 1f, 1f, 1f};
            float[] colum2= {10f,10f};
            float[] colum3 = {10f, 10f, 10f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            PdfPTable table2 = new PdfPTable(colum2);

            PdfPTable table3 = new PdfPTable(colum3);

            PdfPTable vacio = new PdfPTable(colum2);
            // set table width a percentage of the page width
            table2.setWidthPercentage(90f);
            table.setWidthPercentage(90f);
            table3.setWidthPercentage(90f);

            //table.setWidths(columnWidths2);


            insertCellSB(vacio, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellSB(vacio, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellSB(vacio, "", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellSB(vacio, "", Element.ALIGN_LEFT, 1, bfBold12);


            //insert column headings
            insertCellH(table, "RENGLON", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "PIEZAS", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "PRODUCTO", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "LOTE", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "MARCA", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "CADUCIDAD", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "PESO U. NETO(KG)", Element.ALIGN_LEFT, 1, bfBold12);
            insertCellH(table, "PESO U. BRUTO(KG)", Element.ALIGN_LEFT, 1, bfBold12);

            table.setHeaderRows(1);

            if(detallesRds.get(0).getCamara()=="2"){
                anden = "REFRIGERACION";
            }else {
                anden = "CONGELACION";
            }


            String [] contenido= {"","","FRIGORIFICO: TEPOTZOTLAN","","ANDEN DESIGNADO: "+anden,"FECHA: "+detallesRds.get(0).getFechaCaptura(),
                    "CLIENTE: "+clienteDao.findCliente(Integer.parseInt(detallesRds.get(0).getIdCliente())).getNombreCliente(),
                    "CAMARA:"+detallesRds.get(0).getCamara(),"PEDIMENTO: "+detallesRds.get(0).getImpedimento(),
                    "RD: " +detallesRds.get(0).getConsecutivo(),"Temperatura del producto: "+detallesRds.get(0).getTemperaturaAnden(),"","",""};
            int bandera=0;

            for(int x=1; x<=colum2.length; x++){
                int  modulo=(x%2);
                if(modulo==0) {

                    bandera=7;
                }



                insertCellSB(table2, contenido[bandera], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table2, contenido[(bandera+1)], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table2, contenido[(bandera+2)], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table2, contenido[(bandera+3)], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table2, contenido[(bandera+4)], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table2, contenido[(bandera+5)], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table2, contenido[(bandera+6)], Element.ALIGN_LEFT, 1, bf12);

            }

            for(DetallesRd detallesRd : detallesRds){

                insertCell(table, detallesRd.getRenglon().toString(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getCantidad().toString(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getDescripcion(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getLote(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getMarca(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getCaducidad(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getPesoBruto().toString(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, detallesRd.getPesou().toString(), Element.ALIGN_LEFT, 1, bf12);
                totalpzas+=detallesRd.getCantidad();
                totalkgs+=detallesRd.getCantidad()*detallesRd.getPesou();

            }

            String [] contenido2= {"","","","","","","Total, kg. brutos:    "+df.format(totalkgs)+" KGS","","","Total piezas:   "+df.format(totalpzas),
                    "     _________________    ","     _________________    ",
                    "Posiciones ocupadas: "+detallesRds.get(0).getPosiciones(),"Nombre y firma","Nombre y firma",
                    "Nivel de estiba: "+detallesRds.get(0).getEstibas(),"Especialista de ingresos"," Jefe de mesa de control",
                    "Altura de tarima: "+detallesRds.get(0).getAltura(),"","","Almacenado en: "+detallesRds.get(0).getAlmaen(),
                    "","","","","","","","","","","","fecha y hora de impresión: "+fecha1+" "+hora1,"","",""};
            int bandera2=0;



            for(int x=1; x<=colum3.length; x++){
                int  modulo=(x%2);
                if(modulo==0) {

                    bandera2=12;
                }
                else if (bandera>0) {
                    bandera2=bandera2*(x-1);
                }



                insertCellSB(table3, contenido2[bandera2], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+1], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+2], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+3], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+4], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+5], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+6], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+7], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+8], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+9], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+10], Element.ALIGN_LEFT, 1, bf12);
                insertCellSB(table3, contenido2[bandera2+11], Element.ALIGN_LEFT, 1, bf12);


            }
            paragraph.add(vacio);
            paragraph.add(table2);
            paragraph.add(table);
            paragraph.add(table3);
            doc.add(paragraph);

        }
        catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null){
                //close the document
                doc.close();
            }
            if (docWriter != null){
                //close the writer
                docWriter.close();
            }
        }

        return false;
    }
    private static void insertCellH(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font

        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        //cell.setBorder(0);
        //cell.setFixedHeight(10f);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

    }

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        String hidden="";
        if(text.trim().equals("espacio")) {

            text="     _";
            hidden="espacio";
        }

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        System.out.println("hidden"+ hidden);
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        else if(hidden.equals("espacio")) {
            cell.setBorder(0);

        }
        //add the call to the table
        //cell.setBorder(0);
        //cell.setFixedHeight(15f);

        table.addCell(cell);

    }

    private static void insertCellSB(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);

        cell.setBorder(0);
        cell.setFixedHeight(10f);
        table.addCell(cell);

    }
}
