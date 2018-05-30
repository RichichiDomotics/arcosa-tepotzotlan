package com.cbj.almacen.service.impl;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Clientes;
import com.cbj.almacen.domain.SalidasAlmacen;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.repository.*;
import com.cbj.almacen.service.ImprimirSalidasManager;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class ImprimirSalidasManagerImpl implements ImprimirSalidasManager {

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
    private SalidasAlmacenDao salidasAlmacenDao;

    @Autowired
    private DetalleRdDao detalleRdDao;

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font chapterFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD,8, Font.BOLD);
    private static final Font paragraphFontLeyenda = FontFactory.getFont(FontFactory.HELVETICA, 4, Font.NORMAL);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
    private static final Font paragraphFont2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    private static final String iTextExampleImage = Utils.PATH_IMG+"Logos-Arcosa-01.jpg";

    @Override
    public boolean imprimirSalida(int salida){
        File pdfNewFile = new File(Utils.PATH_URL+"salida"+salida+".pdf");
        PdfWriter writer = null;
        try {
            Document document = new Document();
            try {

                writer = PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }

            final Date date = new Date();
            final SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
            final String fecha1 = formatFecha.format(date);
            final String hora1 = formatHora.format(date);
            DecimalFormat df = new DecimalFormat("#.00");


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
            SalidasAlmacen salidasAlmacen = new SalidasAlmacen();
            Clientes clientes = new Clientes();
            Double totalkgs = 0d;
            Double totalneto = 0d;
            Double totalpzas = 0d;

            try {
                salidasDetalleList = salidasDetalleDao.findSalidaByFolioSalida(salida);
                salidasAlmacen = salidasAlmacenDao.findSalidabyFolioSalida(String.valueOf(salida));
                clientes = clienteDao.findCliente(Integer.parseInt(salidasDetalleList.get(0).getIdCliente()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getCause() + " " + e.getLocalizedMessage());
            }

            // First page
            // Primera página
            //Chunk chunk = new Chunk("This is the title", chapterFont);
            //chunk.setBackground(BaseColor.GRAY);
            // Let's create de first Chapter (Creemos el primer capítulo)
            Chapter chapter = new Chapter(1);
            chapter.setNumberDepth(0);

            Image image;
            try {
                image = Image.getInstance(iTextExampleImage);
                image.scalePercent(10);
                image.setAbsolutePosition(40, 690);
                chapter.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" + ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " + ex);
            }
            //chapter.add(new Paragraph("This is the paragraph", paragraphFont));
            // We add an image (Añadimos una imagen)
            PdfPTable datosEmpresa = new PdfPTable(3);


            PdfPCell dato1 = new PdfPCell(new Phrase("ARCOSA TEPOTZOTLAN", paragraphFont));
            PdfPCell dato2 = new PdfPCell(new Phrase("CALLE FRESNOS #4", paragraphFont));
            PdfPCell dato3 = new PdfPCell(new Phrase("SAN MATEO XOLOC", paragraphFont));
            PdfPCell dato4 = new PdfPCell(new Phrase("C.P. 54600", paragraphFont));
            PdfPCell dato5 = new PdfPCell(new Phrase("TEPOTZOTLAN", paragraphFont));
            PdfPCell dato6 = new PdfPCell(new Phrase("ESTADO DE MEXICO", paragraphFont));
            PdfPCell titulo = new PdfPCell(new Phrase("ORDEN DE SALIDA", paragraphFont));
            PdfPCell codificacion1 = new PdfPCell(new Phrase("GRUPO ARCOSA", paragraphFont));
            PdfPCell codificacion2 = new PdfPCell(new Phrase("PTO.OPER.02.01", paragraphFont));
            PdfPCell codificacion3 = new PdfPCell(new Phrase("Revisión 1", paragraphFont));
            PdfPCell codificacion4 = new PdfPCell(new Phrase("Emisión: 31/01/18", paragraphFont));
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
            Paragraph paragraph1 = new Paragraph("DESCRIPCIÓN DE LA SALIDA", paragraphFont);

            chapter .add(paragraph);
            chapter.add(paragraph);

            PdfPTable primeraParte = new PdfPTable(2);
            PdfPCell fecha = new PdfPCell(new Phrase("FECHA: " + fecha1, paragraphFont2));
            PdfPCell hora = new PdfPCell(new Phrase("HORA: " + hora1, paragraphFont2));
            PdfPCell cliente = new PdfPCell(new Phrase("CLIENTE:" + clientes.getIdCliente() + " " + clientes.getNombreCliente(), paragraphFont2));
            PdfPCell entregara = new PdfPCell(new Phrase("ENTREGADO A: " + salidasAlmacen.getEntregara(), paragraphFont2));
            PdfPCell folio = new PdfPCell(new Phrase("FOLIO DE SALIDA: " + salida, chapterFontBold));
            PdfPCell vencimiento = new PdfPCell(new Phrase("VENCIMIENTO: " + fecha1, paragraphFont2));
            PdfPCell leyenda = new PdfPCell(new Phrase("LOS PRODUCTOS SE ENTREGAN  A LA TEMPERATURA DE:_____°C", paragraphFont2));
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
            chapter.add(paragraph);

            PdfPTable detalle = new PdfPTable(10);

            PdfPCell rd = new PdfPCell(new Phrase("RD", paragraphFont));
            PdfPCell cantidad = new PdfPCell(new Phrase("CANTIDAD", paragraphFont));
            PdfPCell camara = new PdfPCell(new Phrase("CAMARA", paragraphFont));
            PdfPCell renglon = new PdfPCell(new Phrase("RENGLON", paragraphFont));
            PdfPCell producto = new PdfPCell(new Phrase("PRODUCTO", paragraphFont));
            PdfPCell lote = new PdfPCell(new Phrase("LOTE", paragraphFont));
            PdfPCell marca = new PdfPCell(new Phrase("MARCA", paragraphFont));
            PdfPCell caducidad = new PdfPCell(new Phrase("CADUCIDAD", paragraphFont));
            PdfPCell bruto = new PdfPCell(new Phrase("PESO U. BRUTO(KG)", paragraphFont));
            PdfPCell neto = new PdfPCell(new Phrase("PESO U. NETO(KG)", paragraphFont));

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

            for (SalidasDetalle salidalista : salidasDetalleList) {
                rd = new PdfPCell(new Phrase(salidalista.getConsecutivo().toString(), paragraphFont));
                detalle.addCell(rd);
                cantidad = new PdfPCell(new Phrase(salidalista.getCantidadSalida().toString(), paragraphFont));
                detalle.addCell(cantidad);
                camara = new PdfPCell(new Phrase(salidalista.getCamara().toString(), paragraphFont));
                detalle.addCell(camara);
                renglon = new PdfPCell(new Phrase(salidalista.getRenglon().toString(), paragraphFont));
                detalle.addCell(renglon);
                producto = new PdfPCell(new Phrase(salidalista.getDescripcion().toString(), paragraphFont));
                detalle.addCell(producto);
                lote = new PdfPCell(new Phrase(salidalista.getLote().toString(), paragraphFont));
                detalle.addCell(lote);
                marca = new PdfPCell(new Phrase(salidalista.getMarca().toString(), paragraphFont));
                detalle.addCell(marca);
                caducidad = new PdfPCell(new Phrase(salidalista.getCaducidad().toString(), paragraphFont));
                detalle.addCell(caducidad);
                bruto = new PdfPCell(new Phrase(salidalista.getPesou().toString(), paragraphFont));
                detalle.addCell(bruto);
                neto = new PdfPCell(new Phrase(salidalista.getPesoNeto().toString(), paragraphFont));
                detalle.addCell(neto);
                totalpzas+=salidalista.getCantidadSalida();
                totalkgs+=salidalista.getPesou()*salidalista.getCantidadSalida();
                totalneto+=salidalista.getPesoNeto()*salidalista.getCantidadSalida();
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

            rd = new PdfPCell(new Phrase("",paragraphFont));
            cantidad = new PdfPCell(new Phrase("",paragraphFont));
            camara = new PdfPCell(new Phrase("",paragraphFont));
            renglon = new PdfPCell(new Phrase("",paragraphFont));
            producto = new PdfPCell(new Phrase("",paragraphFont));
            lote = new PdfPCell(new Phrase("",paragraphFont));
            marca = new PdfPCell(new Phrase("",paragraphFont));
            caducidad = new PdfPCell(new Phrase("TOTALES",paragraphFont));
            bruto = new PdfPCell(new Phrase(df.format(totalkgs),paragraphFont));
            neto = new PdfPCell(new Phrase(df.format(totalneto),paragraphFont));

            rd.setBorder(0);
            cantidad.setBorder(0);
            camara.setBorder(0);
            renglon.setBorder(0);
            producto.setBorder(0);
            lote.setBorder(0);
            marca.setBorder(0);
            caducidad.setBorder(0);

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



            detalle.setWidthPercentage(100f);

            chapter.add(detalle);
            chapter.add(paragraph);
            Paragraph paragraph2 = new Paragraph("TOTAL DE PIEZAS: "+totalpzas+"     TARIMAS CHEP:_____________",paragraphFont);

            chapter.add(paragraph2);
            chapter.add(paragraph);


            Paragraph aviso = new Paragraph("LA PRESENTE ORDEN SURTE EFECTO DE ENTREGA DE LA MERCANCÍA A LA PERSONA SEÑALADA EN LA CARTA DE RETIRO, ASÍ MISMO LE PEDIMOS VERIFICAR LA MERCANCÍA A SU RECIBO CON RESPECTO A LO DECLARADO EN LA PRESENTE ORDEN, YA QUE GRUPO ARCOSA NO SE HARÁ RESPONSABLE DE FALTANTES O DAÑOS UNA VEZ QUE ESTA HA SALIDO DEL ALMACEN.", paragraphFontLeyenda);
            chapter.add(aviso);

            PdfPTable checklist = new PdfPTable(10);
            PdfPCell entrego = new PdfPCell(new Phrase("ENTREGO", paragraphFont));
            PdfPCell recibo = new PdfPCell(new Phrase("RECIBO DE CONFORMIDAD", paragraphFont));
            PdfPCell check = new PdfPCell(new Phrase("CHECK LIST:", paragraphFont));
            PdfPCell olores = new PdfPCell(new Phrase("LIBRE DE OLORES", paragraphFont));
            PdfPCell plagas = new PdfPCell(new Phrase("LIBRE DE PLAGAS", paragraphFont));
            PdfPCell vidrio = new PdfPCell(new Phrase("LIBRE DE VIDRIO/PD", paragraphFont));
            PdfPCell gral = new PdfPCell(new Phrase("LIMPIEZA EN GRAL.", paragraphFont));
            PdfPCell firma = new PdfPCell(new Phrase("NOMBRE Y FIRMA", paragraphFont));
            PdfPCell si = new PdfPCell(new Phrase("SI", paragraphFont));
            PdfPCell no = new PdfPCell(new Phrase("NO", paragraphFont));
            PdfPCell linea = new PdfPCell(new Phrase("__________________________", paragraphFont));
            PdfPCell vacio = new PdfPCell(new Phrase(" "));

            check.setBackgroundColor(BaseColor.LIGHT_GRAY);
            check.setColspan(3);
            check.setHorizontalAlignment(1);
            entrego.setColspan(2);
            recibo.setColspan(2);
            firma.setColspan(2);
            linea.setColspan(2);
            entrego.setBorder(0);
            recibo.setBorder(0);
            firma.setBorder(0);
            linea.setBorder(0);
            vacio.setBorder(0);

            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(check);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(olores);
            checklist.addCell(si);
            checklist.addCell(no);
            checklist.addCell(vacio);
            checklist.addCell(entrego);
            checklist.addCell(recibo);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(plagas);
            checklist.addCell(si);
            checklist.addCell(no);
            checklist.addCell(vacio);
            checklist.addCell(linea);
            checklist.addCell(linea);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(vidrio);
            checklist.addCell(si);
            checklist.addCell(no);
            checklist.addCell(vacio);
            checklist.addCell(firma);
            checklist.addCell(firma);
            checklist.addCell(vacio);
            checklist.addCell(vacio);
            checklist.addCell(gral);
            checklist.addCell(si);
            checklist.addCell(no);

            checklist.setWidthPercentage(100f);

            chapter.add(checklist);

            chapter.add(paragraph);

            PdfPTable reteme = new PdfPTable(11);
            PdfPCell title = new PdfPCell(new Phrase("Reporte de Tiempo extra y maniobras especiales",paragraphFont));
            PdfPCell emplayado = new PdfPCell(new Phrase("Emplayado",paragraphFont));
            PdfPCell rplayo150 = new PdfPCell(new Phrase("Tarima hasta 1.5",paragraphFont));
            PdfPCell rplayo180 = new PdfPCell(new Phrase("Tarima hasta 1.8",paragraphFont));
            PdfPCell rplayo220 = new PdfPCell(new Phrase("Tarima mas de 1.8",paragraphFont));
            PdfPCell textra = new PdfPCell(new Phrase("Horas Extras",paragraphFont));
            PdfPCell doble = new PdfPCell(new Phrase("HE Dobles",paragraphFont));
            PdfPCell hetr = new PdfPCell(new Phrase("He Triples",paragraphFont));
            PdfPCell seleccion = new PdfPCell(new Phrase("seleccion de producto",paragraphFont));
            PdfPCell tif = new PdfPCell(new Phrase("Servicio TIF",paragraphFont));
            PdfPCell escaneo = new PdfPCell(new Phrase("Escaneo",paragraphFont));
            PdfPCell etiq = new PdfPCell(new Phrase("Etiquetado",paragraphFont));
            PdfPCell usoanden = new PdfPCell(new Phrase("Uso de Anden",paragraphFont));
            PdfPCell picking = new PdfPCell(new Phrase("Preparacion de pedidos",paragraphFont));
            PdfPCell rentarimado = new PdfPCell(new Phrase("Re Entarimado",paragraphFont));
            PdfPCell otros = new PdfPCell(new Phrase("Otros",paragraphFont));
            PdfPCell otraborder = new PdfPCell(new Phrase(" ",paragraphFont));
            PdfPCell otrasinborder = new PdfPCell(new Phrase(" ",paragraphFont));

            title.setBackgroundColor(BaseColor.LIGHT_GRAY);
            title.setColspan(11);
            title.setHorizontalAlignment(1);
            rplayo150.setColspan(2);
            rplayo180.setColspan(2);
            rplayo220.setColspan(2);
            seleccion.setColspan(2);
            otros.setColspan(2);
            otrasinborder.setBorder(0);

            reteme.addCell(title);
            reteme.addCell(emplayado);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(textra);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(tif);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(picking);
            reteme.addCell(otraborder);

            reteme.addCell(rplayo150);
            reteme.addCell(otrasinborder);
            reteme.addCell(doble);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(escaneo);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(rentarimado);
            reteme.addCell(otraborder);

            reteme.addCell(rplayo180);
            reteme.addCell(otrasinborder);
            reteme.addCell(hetr);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(etiq);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(otros);


            reteme.addCell(rplayo220);
            reteme.addCell(otrasinborder);
            reteme.addCell(seleccion);
            reteme.addCell(otrasinborder);
            reteme.addCell(usoanden);
            reteme.addCell(otraborder);
            reteme.addCell(otrasinborder);
            reteme.addCell(otraborder);
            reteme.addCell(otraborder);

            reteme.setWidthPercentage(100f);

            chapter.add(reteme);

            document.add(chapter);

            document.close();
            logger.info("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!)");

            return true;
        } catch (DocumentException documentException) {
            logger.info("The file not exists (Se ha producido un error al generar un documento): " + documentException);
            return false;
        }
    }

}
