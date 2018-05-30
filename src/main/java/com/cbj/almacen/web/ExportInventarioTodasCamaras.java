package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.SalidasDetalle;
import com.cbj.almacen.service.InventarioManager;
import com.cbj.almacen.service.SalidasDetalleManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
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
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
@RequestMapping("/exportInventarioTodasCamaras")
public class ExportInventarioTodasCamaras {
    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    private static final String PARAMETER_ITEM_NAME = "itemName";
    private static final String PARAMETER_TYPE = "type";
    private static final String VALUE_TYPE_PDF = "pdf";
    private static final String VALUE_TYPE_XLS = "xls";
    private String rdAnterior = "";
    private String rdActual = "";
    private BigDecimal suma = new BigDecimal(0.0);
    private AggregationSubtotalBuilder<Double> quantitySumPza;
    private AggregationSubtotalBuilder<Double> quantitySumKgs;
    private AggregationSubtotalBuilder<Double> quantitySumTotKgs;

    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
            new HashMap<String, String>();

    static {
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
    }

    @Autowired
    private InventarioManager inventarioManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fileType = request.getParameter(PARAMETER_TYPE);
        logger.info("Exporting {} report", fileType);

        response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = createJasperReport();

            if (VALUE_TYPE_PDF.equals(fileType)) {
                jrb.toPdf(out);
            } else if (VALUE_TYPE_XLS.equals(fileType)) {
                jrb.toExcelApiXls(out);
            }
        } catch (DRException e) {
            logger.error("Error al crear PDF ",e);
            throw new ServletException(e);
        }
        out.close();
    }

    private JasperReportBuilder createJasperReport() {
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

           // TextColumnBuilder<Double> quantityPza = col.column("Cantidad(Pzas)", "cantidadSalida", type.doubleType()).setWidth(55).setPattern("###,###,###");
            //TextColumnBuilder<Double> quantityKgs = col.column("TOTAL. (Kgs)", "pesou", type.doubleType()).setWidth(55).setPattern("###,###,###.##");
            //quantitySumPza = sbt.sum(quantityPza).setLabel("Total");
            //quantitySumKgs = sbt.sum(quantityKgs).setLabel("Total");
            //quantitySumTotKgs = sbt.sum(quantityTotKgs).setLabel("Total");

            //StyleBuilder estiloPageHeader = new StyleBuilder();
            return report()//create new report design
                    // .setTitleStyle(estiloPageHeader)
                    .setColumnTitleStyle(columnTitleStyle)
                    .setTextStyle(textStyle)
                    .highlightDetailEvenRows()
                    .columns(//add columns
                            //            title,     field name     data type
                            col.column("Camara", "camara", type.stringType()),
                            col.column("ID", "idCliente", type.stringType()),
                            col.column("Cliente", "calle", type.stringType()),
                            col.column("RD", "consecutivo", type.integerType()).setWidth(50).setPattern("#########"),
                            col.column("Renglon", "renglon", type.doubleType()).setWidth(50).setPattern("#########"),
                            col.column("Fecha de Entrada", "fechaCaptura", type.stringType()).setWidth(170),
                            col.column("Cantidad (Pzas)", "cantidadSalida", type.stringType()),
                            col.column("Peso U(Kgs)", "pesou", type.doubleType()),
                            col.column("Peso Total(Kgs)", "pesoBruto", type.stringType()).setWidth(55).setPattern("###,###,###.##"),
                            col.column("Descripcion", "descripcion", type.stringType()),
                            col.column("Embalaje", "embalaje", type.stringType()),
                            col.column("Marca", "marca", type.stringType())
                            //quantityPza,
                            //quantityKgs
                            //,
                            //quantityTotKgs
                    )
                            //col.column("Total por RD", new ExpressionColumn()))
                    .title(cmp.horizontalList()
                            .add(cmp.image(url).setFixedDimension(80, 80),
                                    cmp.verticalList()
                                            .add(
                                                    cmp.text("GRUPO ARCOSA PLANTA TEPOTZOTLAN").setStyle(titleStyle).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT),
                                                    cmp.text("CONSULTA DE INVENTARIOS DE PRODUCTO DE TODAS LAS CAMARAS").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT),
                                                    cmp.text(Utils.getFechaTexto()).setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.LEFT)
                                                    //shows report title
                                            )
                            ),//shows report title
                            cmp.horizontalList()
                                    .add(cmp.text("").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.CENTER))
                    )//shows report title
                    //.subtotalsAtSummary(quantitySumPza, quantitySumKgs)
                    //.pageFooter(cmp.pageXofY()).addSubtotalAtColumnFooter()//shows number of page at page footer
                    .setDataSource(createDataSource())//set datasource
                            //.show();//create and show report
                    .rebuild();
            //.show(false);//create and show report

        } catch (DRException e) {
            logger.error("Error en DR ", e);
            //} catch (MalformedURLException e) {
            //    logger.error("Error en URL " + e);
        } catch (Exception e) {
            logger.error("Error no esperado ", e);
        }
        return report();
    }

    private JRDataSource createDataSource() {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat sdfQuery = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final List<Object[]> oList = this.inventarioManager.getTodasCamaras();
        final List<Inventario> responseList = new ArrayList<Inventario>();
        final List<Object[]> res = oList;
        for (Object[] o : res) {
            final Inventario inventario = new Inventario();
            try {
                inventario.setCamara((String) o[0]);
                inventario.setIdCliente(String.valueOf((Integer) o[1]));
                inventario.setCalle((String) o[2]);
                inventario.setConsecutivo((Integer) o[3]);
                inventario.setRenglon((Double) o[4]);
                if(String.valueOf(o[5])!=null && !String.valueOf(o[5]).equalsIgnoreCase("null")) {
                    final Date fechaDate = sdfQuery.parse(String.valueOf(o[5]));
                    inventario.setFechaCaptura(sdf.format(fechaDate));
                }else{
                    inventario.setFechaCaptura("");
                }
                DecimalFormat dfSinDec = new DecimalFormat("#");
                inventario.setCantidadSalida(String.valueOf(dfSinDec.format((Double) o[6])));
                inventario.setPesou((Double) o[7]);
                DecimalFormat decf = new DecimalFormat("#.00");
                inventario.setPesoBruto(String.valueOf(decf.format((Double) o[8])));
                inventario.setDescripcion((String) o[9]);
                inventario.setEmbalaje((String) o[10]);
                inventario.setMarca((String) o[11]);
                //salidasDetalle.setPesoTotal((Double) o[10]);
                responseList.add(inventario);
            } catch (ParseException e) {
                logger.error("Errro en seteo de Entidad",e);
            }
        }
        return new JRBeanCollectionDataSource(responseList);
    }

}
