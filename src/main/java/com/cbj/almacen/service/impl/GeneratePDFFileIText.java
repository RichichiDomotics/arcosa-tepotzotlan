package com.cbj.almacen.service.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.repository.SalidasDetalleDao;
import com.cbj.almacen.service.SalidasDetalleManager;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.lowagie.text.Cell;
import com.lowagie.text.pdf.PdfTable;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import java.io.*;

/**
 * Example of using the iText library to work with PDF documents on Java,
 * lets you create, analyze, modify and maintain documents in this format.
 * Ejemplo de uso de la librería iText para trabajar con documentos PDF en Java,
 * nos permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org
 */
public class GeneratePDFFileIText {
    // Fonts definitions (Definición de fuentes).
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font chapterFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD,8, Font.BOLD);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
    private static final Font paragraphFont2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    private static final String iTextExampleImage = Utils.PATH_IMG+"Logos-Arcosa-01.jpg";

    @Autowired
    private SalidasDetalleDao salidasDetalleDao;
    /**
     * We create a PDF document with iText using different elements to learn
     * to use this library.
     * Creamos un documento PDF con iText usando diferentes elementos para aprender
     * a usar esta librería.
     * @param pdfNewFile  <code>String</code>
     *      pdf File we are going to write.
     *      Fichero pdf en el que vamos a escribir.
     */
    public void createPDF(File pdfNewFile) {
        // We create the document and set the file name.
        // Creamos el documento e indicamos el nombre del fichero.
        PdfWriter writer = null;
        try {
            Document document = new Document();
            try {

                writer= PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }


            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
            document.addSubject("Using iText (usando iText)");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Código Xules");
            document.addCreator("Código Xules");
            document.setPageSize(PageSize.LETTER);
            java.util.List<SalidasDetalle> salidasDetalleList = new ArrayList<SalidasDetalle>();

            try {
                salidasDetalleList = salidasDetalleDao.findSalidaByClienteConsecutivo("485",26050);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getCause()+" "+e.getLocalizedMessage());
            }

            // First page
            // Primera página
            //Chunk chunk = new Chunk("This is the title", chapterFont);
            //chunk.setBackground(BaseColor.GRAY);
            // Let's create de first Chapter (Creemos el primer capítulo)
            Chapter chapter = new Chapter(1);
            chapter.setNumberDepth(0);
            //chapter.add(new Paragraph("This is the paragraph", paragraphFont));
            // We add an image (Añadimos una imagen)
            PdfPTable datosEmpresa = new PdfPTable(3);


            PdfPCell dato1 = new PdfPCell(new Phrase("ARCOSA JUQUILA",paragraphFont));
            PdfPCell dato2 = new PdfPCell(new Phrase("CALLE SIN NOMBRE S/N",paragraphFont));
            PdfPCell dato3 = new PdfPCell(new Phrase("BARRIO CAPULA",paragraphFont));
            PdfPCell dato4 = new PdfPCell(new Phrase("C.P. 54608",paragraphFont));
            PdfPCell dato5 = new PdfPCell(new Phrase("TEPOTZOTLAN",paragraphFont));
            PdfPCell dato6 = new PdfPCell(new Phrase("ESTADO DE MEXICO",paragraphFont));
            PdfPCell titulo = new PdfPCell(new Phrase("ORDEN DE SALIDA",paragraphFont));
            PdfPCell codificacion1 = new PdfPCell(new Phrase("GRUPO ARCOSA",paragraphFont));
            PdfPCell codificacion2 = new PdfPCell(new Phrase("PTO.OPER.02.01",paragraphFont));
            PdfPCell codificacion3 = new PdfPCell(new Phrase("Revisión 1",paragraphFont));
            PdfPCell codificacion4 = new PdfPCell(new Phrase("Emisión: 31/01/18",paragraphFont));
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

            dato1.setBackgroundColor(BaseColor.LIGHT_GRAY);
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

            chapter.add(datosEmpresa);

            Paragraph paragraph = new Paragraph(" ");
            Paragraph paragraph1 = new Paragraph("DESCRIPCIÓN DE LA SALIDA",paragraphFont);

            chapter.add(paragraph);
            chapter.add(paragraph);

            PdfPTable primeraParte = new PdfPTable(2);
            PdfPCell fecha = new PdfPCell(new Phrase("FECHA:",paragraphFont2));
            PdfPCell hora = new PdfPCell(new Phrase("HORA:",paragraphFont2));
            PdfPCell cliente = new PdfPCell(new Phrase("CLIENTE:",paragraphFont2));
            PdfPCell entregara= new PdfPCell(new Phrase("ENTREGADO A:",paragraphFont2));
            PdfPCell folio= new PdfPCell(new Phrase("FOLIO DE SALIDA:",chapterFontBold));
            PdfPCell vencimiento= new PdfPCell(new Phrase("VENCIMIENTO:",paragraphFont2));
            PdfPCell leyenda = new PdfPCell(new Phrase("LOS PRODUCTOS SE ENTREGAN  A LA TEMPERATURA DE:_____°C",chapterFontBold));
            PdfPCell otro = new PdfPCell(new Phrase(""));

            fecha.setBorder(0);
            hora.setBorder(0);
            cliente.setBorder(0);
            entregara.setBorder(0);
            folio.setBorder(0);
            vencimiento.setBorder(0);
            leyenda.setBorder(0);
            otro.setBorder(0);

            primeraParte.addCell(fecha);
            primeraParte.addCell(hora);
            primeraParte.addCell(cliente);
            primeraParte.addCell(otro);
            primeraParte.addCell(otro);
            primeraParte.addCell(otro);
            primeraParte.addCell(entregara);
            primeraParte.addCell(folio);
            primeraParte.addCell(vencimiento);
            primeraParte.addCell(leyenda);

            primeraParte.setWidthPercentage(100f);
            chapter.add(primeraParte);
            chapter.add(paragraph);
            chapter.add(paragraph1);

            PdfPTable detalle = new PdfPTable(10);

            PdfPCell rd = new PdfPCell(new Phrase("RD",paragraphFont));
            PdfPCell cantidad = new PdfPCell(new Phrase("CANTIDAD",paragraphFont));
            PdfPCell camara = new PdfPCell(new Phrase("CAMARA",paragraphFont));
            PdfPCell renglon = new PdfPCell(new Phrase("RENGLON",paragraphFont));
            PdfPCell producto = new PdfPCell(new Phrase("PRODUCTO",paragraphFont));
            PdfPCell lote = new PdfPCell(new Phrase("LOTE",paragraphFont));
            PdfPCell marca = new PdfPCell(new Phrase("MARCA",paragraphFont));
            PdfPCell caducidad = new PdfPCell(new Phrase("CADUCIDAD",paragraphFont));
            PdfPCell bruto = new PdfPCell(new Phrase("PESO U. BRUTO(KG)",paragraphFont));
            PdfPCell neto = new PdfPCell(new Phrase("PESO U. NETO(KG)",paragraphFont));

            rd.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cantidad.setBackgroundColor(BaseColor.LIGHT_GRAY);
            camara.setBackgroundColor(BaseColor.LIGHT_GRAY);
            renglon.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto.setBackgroundColor(BaseColor.LIGHT_GRAY);
            lote.setBackgroundColor(BaseColor.LIGHT_GRAY);
            marca.setBackgroundColor(BaseColor.LIGHT_GRAY);
            caducidad.setBackgroundColor(BaseColor.LIGHT_GRAY);
            bruto.setBackgroundColor(BaseColor.LIGHT_GRAY);
            neto.setBackgroundColor(BaseColor.LIGHT_GRAY);

            detalle.addCell(rd);
            detalle.addCell(cantidad);
            detalle.addCell(camara);
            detalle.addCell(renglon);
            detalle.addCell(producto);
            detalle.addCell(lote);
            detalle.addCell(marca);
            detalle.addCell(caducidad);
            detalle.addCell(bruto);
            detalle.addCell(neto);

            detalle.setHeaderRows(1);

            for (SalidasDetalle salidalista: salidasDetalleList){
                rd = new PdfPCell(new Phrase(salidalista.getConsecutivo().toString(),paragraphFont));
                detalle.addCell(rd);
                cantidad = new PdfPCell(new Phrase(salidalista.getCantidadSalida().toString(),paragraphFont));
                detalle.addCell(cantidad);
                camara = new PdfPCell(new Phrase(salidalista.getCamara().toString(),paragraphFont));
                detalle.addCell(camara);
                renglon = new PdfPCell(new Phrase(salidalista.getRenglon().toString(),paragraphFont));
                detalle.addCell(renglon);
                producto = new PdfPCell(new Phrase(salidalista.getDescripcion().toString(),paragraphFont));
                detalle.addCell(producto);
                lote = new PdfPCell(new Phrase(salidalista.getLote().toString(),paragraphFont));
                detalle.addCell(lote);
                marca = new PdfPCell(new Phrase(salidalista.getMarca().toString(),paragraphFont));
                detalle.addCell(marca);
                caducidad = new PdfPCell(new Phrase(salidalista.getCaducidad().toString(),paragraphFont));
                detalle.addCell(caducidad);
                bruto = new PdfPCell(new Phrase(salidalista.getPesou().toString(),paragraphFont));
                detalle.addCell(bruto);
                neto = new PdfPCell(new Phrase(salidalista.getPesou().toString(),paragraphFont));
                detalle.addCell(neto);
            }

            rd.setBackgroundColor(BaseColor.WHITE);
            cantidad.setBackgroundColor(BaseColor.WHITE);
            camara.setBackgroundColor(BaseColor.WHITE);
            renglon.setBackgroundColor(BaseColor.WHITE);
            producto.setBackgroundColor(BaseColor.WHITE);
            lote.setBackgroundColor(BaseColor.WHITE);
            marca.setBackgroundColor(BaseColor.WHITE);
            caducidad.setBackgroundColor(BaseColor.WHITE);
            bruto.setBackgroundColor(BaseColor.WHITE);
            neto.setBackgroundColor(BaseColor.WHITE);

            detalle.setWidthPercentage(100f);

            chapter.add(detalle);


            Image image;
            try {
                image = Image.getInstance(iTextExampleImage);
                image.scalePercent(10);
                image.setAbsolutePosition(40, 690);
                chapter.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
            document.add(chapter);


            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!)");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
        generatePDFFileIText.createPDF(new File(Utils.PATH_URL+"ejemplo.pdf"));
    }
}