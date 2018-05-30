package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.service.SalidasDetalleManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Created by jolvera on 07/07/2014.
 */
@Controller
@Scope("prototype")
@RequestMapping("/exportSalArrastre")
public class ExportSalArrastreController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    private static final String PARAMETER_ITEM_NAME = "itemName";
    private static final String PARAMETER_TYPE = "type";
    private static final String VALUE_TYPE_PDF = "pdf";
    private static final String VALUE_TYPE_XLS = "xls";

    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
            new HashMap<String, String>();

    static {
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
    }

    @Autowired
    private SalidasDetalleManager salidasDetalleManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "reciboEntrada") String reciboEntrada
    ) throws IOException, ServletException {
        String fileType = request.getParameter(PARAMETER_TYPE);
        logger.info("Exporting {} report", fileType);

        response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = createJasperReport(reciboEntrada);

            if (VALUE_TYPE_PDF.equals(fileType)) {
                jrb.toPdf(out);
            } else if (VALUE_TYPE_XLS.equals(fileType)) {
                jrb.toExcelApiXls(out);
            }
        } catch (DRException e) {
            throw new ServletException(e);
        }
        out.close();
    }

    private JasperReportBuilder createJasperReport(String reciboEntrada) {
        // Here use DynamicReports API to build a report
        // and fill it with a JRDataSource.
        // I used SearchController session bean
        // to get required search results data.

        try {
            URL url = new URL("http://localhost:8080/almacen/resources/img/logo-arcosa.png");
            StyleBuilder boldStyle = stl.style().setFontSize(5).setBold(false);
            StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                    .setFontSize(5)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setFontSize(8)
                    .setBackgroundColor(Color.LIGHT_GRAY);
            StyleBuilder textStyle = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setFontSize(7)
                    .setBackgroundColor(Color.white);
            StyleBuilder titleStyle = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(10);
            StyleBuilder titleStyleMini = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(5);
            StyleBuilder textStyle2 = stl.style(boldCenteredStyle)
                    .setFontSize(10)
                    .setBackgroundColor(Color.white);


            //StyleBuilder estiloPageHeader = new StyleBuilder();
            return report()//create new report design
                    // .setTitleStyle(estiloPageHeader)
                    .setColumnTitleStyle(columnTitleStyle)
                    .setTextStyle(textStyle)
                    .highlightDetailEvenRows()
                    .columns(//add columns
                            //            title,     field name     data type
                            col.column("Folio Salida", "folioSalida", type.integerType()),
                            col.column("Fecha Salida", "fechaSalida", type.dateType()),
                            col.column("Renglon", "renglon", type.stringType()).setPattern("###,###,###"),
                            col.column("Prodcucto Salio", "descripcion", type.stringType()),
                            col.column("Piezas", "cantidadSalida", type.doubleType()).setPattern("###,###,###"),
                            col.column("Peso U(Kgs)", "pesou", type.doubleType()).setPattern("###,###,###.##")
                    )
                            //col.column("Total por RD", new ExpressionColumn()))
                    .title(cmp.horizontalList()
                            .add(cmp.image(url).setFixedDimension(80, 80),
                                    cmp.verticalList()
                                            .add(
                                                    cmp.text("GRUPO ARCOSA PLANTA TEPOTZOTLAN").setStyle(titleStyle).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT),
                                                    cmp.text("Operaciones - Inventario del recibo de Deposito").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT),
                                                    cmp.text(Utils.getFechaTexto()).setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT)
                                                    //shows report title
                                            )
                            ),//shows report title
                            cmp.horizontalList()
                                    .add(cmp.text("").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.CENTER))
                    )//shows report title
                    .pageFooter(cmp.pageXofY()).addSubtotalAtColumnFooter()//shows number of page at page footer
                    .setDataSource(createDataSource(reciboEntrada))//set datasource
                            //.show();//create and show report
                    .rebuild();
            //.show(false);//create and show report

        } catch (DRException e) {
            logger.error("Error en DR " + e);
            //} catch (MalformedURLException e) {
            //    logger.error("Error en URL " + e);
        } catch (Exception e) {
            logger.error("Error " + e);
        }
        return report();
    }

    private JRDataSource createDataSource(String reciboEntrada) {
        final SimpleDateFormat sdfQuery = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final List<Object[]> oList = this.salidasDetalleManager.getArrastreSaldosDetalle(Integer.parseInt(reciboEntrada));
        final List<SalidasDetalle> responseList = new ArrayList<SalidasDetalle>();
        for (Object[] o : oList) {
            final SalidasDetalle salidasDetalle = new SalidasDetalle();
            try{
                salidasDetalle.setFolioSalida((Integer) o[0]);
                final Date fechaDate = sdfQuery.parse(String.valueOf(o[1]));
                salidasDetalle.setFechaSalida(fechaDate);
                salidasDetalle.setRenglon((String) o[2]);
                salidasDetalle.setDescripcion((String) o[3]);
                salidasDetalle.setCantidadSalida((Double) o[4]);
                salidasDetalle.setPesou((Double) o[5]);
                responseList.add(salidasDetalle);
            } catch (ParseException e) {
                logger.error("createDataSource "+e);
            }

        }
        return new JRBeanCollectionDataSource(responseList);
    }

}