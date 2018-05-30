package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventarioview;
import com.cbj.almacen.service.ClienteManager;
import com.cbj.almacen.service.DetalleRdManager;
import com.cbj.almacen.service.InventarioManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.AbstractScriptlet;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
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
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Created by jolvera on 07/07/2014.
 */
@Controller
@Scope("prototype")
@RequestMapping("/exportRD")
public class ExportSearchRDController {

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
    private AggregationSubtotalBuilder<BigDecimal> quantitySumKgs;
    java.util.List<DetallesRd> datosResponse = new ArrayList<DetallesRd>();
    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
            new HashMap<String, String>();

    static {
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
    }

    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private ClienteManager clienteManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "consecutivo") String consecutivo,
                       @RequestParam(value = "camara") String camara,
                       @RequestParam(value = "idCliente") String idCliente,
                       @RequestParam(value = "claveProducto") String claveProducto,
                       @RequestParam(value = "tunel") String tunel,
                       @RequestParam(value = "fechaIngreso") String fechaIngreso,
                       @RequestParam(value = "consecutivoCliente") String consecutivoCliente) throws IOException, ServletException {
        String fileType = request.getParameter(PARAMETER_TYPE);
        logger.info("Exporting {} report", fileType);

        datosResponse = this.detalleRdManager.getAllByConsecutivo(Integer.parseInt(consecutivo));
        String nomCliente = this.clienteManager.getByIdCliente(Integer.parseInt(datosResponse.get(0).getIdCliente())).getNombreCliente();
        response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = createJasperReport(nomCliente,consecutivo, camara, datosResponse.get(0).getIdCliente(), claveProducto, tunel, fechaIngreso, consecutivoCliente);

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

    private JasperReportBuilder createJasperReport(String nombreCliente,String consecutivo, String camara, String idCliente, String claveProducto, String tunel, String fechaIngreso, String consecutivoCliente) {
        // Here use DynamicReports API to build a report
        // and fill it with a JRDataSource.
        // I used SearchController session bean
        // to get required search results data.
        try {


            URL url = new URL("http://localhost:8080/almacen/resources/img/logo-arcosa.png");
            StyleBuilder boldStyle = stl.style().setFontSize(5).setBold(false);
            StyleBuilder boldHeaderStyle = stl.style().setFontSize(15).setBold(true);
            StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                    .setFontSize(5)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            StyleBuilder columnHeaderStyle = stl.style(boldHeaderStyle)
                    .setBorder(stl.pen2Point())
                    .setFontSize(15)
                    .setBackgroundColor(Color.LIGHT_GRAY)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER)
                    .setTabStopWidth(1);
            StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setFontSize(8)
                    .setBackgroundColor(Color.LIGHT_GRAY);
            StyleBuilder textStyle = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setFontSize(7)
                    .setBackgroundColor(Color.white);
            StyleBuilder textStyle2 = stl.style(boldCenteredStyle)
                    .setFontSize(10)
                    .setBackgroundColor(Color.white);
            StyleBuilder titleStyle = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(10);
            StyleBuilder titleStyleMini = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(5);

            TextColumnBuilder<Double> quantityPza = col.column("Cantidad Pza.", "cantidad", type.doubleType()).setPattern("###,###");
            TextColumnBuilder<BigDecimal> quantityKgs = col.column("Total Renglon", new TotalPorRd()).setPattern("###,###.00");
            quantitySumPza = sbt.sum(quantityPza).setLabel("Tot. Pza");
            quantitySumKgs = sbt.sum(quantityKgs).setLabel("Tot. Kgs");


            TextColumnBuilder<Integer> col_rd = col.column("RD", "consecutivo", type.integerType()).setFixedWidth(35).setPattern("######");
            TextColumnBuilder<Double> col_renglon = col.column("Renglon", "renglon", type.doubleType());
            TextColumnBuilder<Date> col_fecha = col.column("Fecha Entrada", "fechaCaptura", type.dateType());
            TextColumnBuilder<String> col_camara = col.column("Camara", "camara", type.stringType());
            TextColumnBuilder<String> col_lote = col.column("Lote", "lote", type.stringType());
            TextColumnBuilder<Double> col_qPza = quantityPza;
            TextColumnBuilder<String> col_caducidad = col.column("Caducidad", "caducidad", type.stringType());
            TextColumnBuilder<Double> col_pesU = col.column("Peso U Kg.", "pesou", type.doubleType()).setPattern("###,###,###.00");
            TextColumnBuilder<String> col_producto = col.column("Producto", "descripcion", type.stringType());
            TextColumnBuilder<String> col_embalaje = col.column("Embalaje", "embalaje", type.stringType());
            TextColumnBuilder<String> col_marca = col.column("Marca", "marca", type.stringType());
            TextColumnBuilder<Double> col_valorTotal = col.column("Valor Total", "valorTotal", type.doubleType()).setPattern("###,###,###.00");
            TextColumnBuilder<BigDecimal> col_qKgs = quantityKgs;
            //TextColumnBuilder<String> col_observaciones = col.column("Observaciones", "observaciones", type.stringType()).setHorizontalAlignment(HorizontalAlignment.LEFT);

                //StyleBuilder estiloPageHeader = new StyleBuilder();
                return report()//create new report design
                        // .setTitleStyle(estiloPageHeader)
                        .setColumnTitleStyle(columnTitleStyle)
                        .setTextStyle(textStyle)
                        .highlightDetailEvenRows()
                        .setColumnHeaderStyle(columnHeaderStyle)
                        .columns(//add columns
                                //            title,     field name     data type
                                //col_cliente,
                                col_rd,
                                col_renglon,
                                col_fecha,
                                col_camara,
                                col_producto,
                                col_marca,
                                col_lote,
                                col_embalaje,
                                col_caducidad,
                                col_valorTotal,
                                col_pesU,
                                col_qPza,
                                col_qKgs
                                //col_observaciones.setHorizontalAlignment(HorizontalAlignment.LEFT)
                        )
                                //col.column("Total por RD", new ExpressionColumn()))
                        .columnGrid(
                                grid.verticalColumnGridList(
                                        grid.horizontalColumnGridList(
                                                //col_cliente,
                                                col_rd,
                                                col_renglon,
                                                col_fecha,
                                                col_camara,
                                                col_producto,
                                                col_marca,
                                                col_lote,
                                                col_embalaje,
                                                col_caducidad,
                                                col_valorTotal,
                                                col_pesU,
                                                col_qPza,
                                                col_qKgs)
                                        //grid.horizontalColumnGridList (col_observaciones.setHorizontalAlignment(HorizontalAlignment.LEFT))
                                )
                        )
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
                                        .add(cmp.text("").setStyle(textStyle2).setWidth(310).setHorizontalAlignment(HorizontalAlignment.CENTER)),
                                cmp.horizontalList()
                                        .add(cmp.text(idCliente+" "+nombreCliente).setStyle(columnHeaderStyle).setWidth(310).setHorizontalAlignment(HorizontalAlignment.CENTER))
                        )
                        .subtotalsAtSummary(quantitySumPza, quantitySumKgs)
                        //.pageFooter(cmp.pageXofY()).addSubtotalAtColumnFooter()//shows number of page at page footer
                        .setDataSource(createDataSource(consecutivo, camara, idCliente, claveProducto, tunel))//set datasource
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


    private JRDataSource createDataSource(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        return new JRBeanCollectionDataSource(datosResponse);
    }

    private class ExpressionColumn extends AbstractSimpleExpression<String> {
        public String evaluate(ReportParameters reportParameters) {
            final String cantidad = String.valueOf(reportParameters.getFieldValue("cantidad"));
            final String pesou = String.valueOf(reportParameters.getFieldValue("pesou"));
            final BigDecimal valor;
            if (!cantidad.equalsIgnoreCase("null") && !pesou.equalsIgnoreCase("null")) {
                BigDecimal cantidadBIG = new BigDecimal(cantidad);
                BigDecimal pesoBIG = new BigDecimal(pesou);
                valor = (cantidadBIG.multiply(pesoBIG)).setScale(2, RoundingMode.UP);
                //valor = BigDecimal.valueOf(100);
                return String.valueOf(valor);
            } else {
                return "0";
            }


        }
    }

    private class TotalPorRd extends AbstractSimpleExpression<BigDecimal> {
        public BigDecimal evaluate(ReportParameters reportParameters) {
            BigDecimal respuesta = new BigDecimal(0);
            rdActual = String.valueOf(reportParameters.getFieldValue("consecutivo"));
            if (rdAnterior.equalsIgnoreCase("") || rdAnterior.equalsIgnoreCase(String.valueOf(reportParameters.getFieldValue("consecutivo")))) {
                final String cantidad = String.valueOf(reportParameters.getFieldValue("cantidad"));
                final String pesou = String.valueOf(reportParameters.getFieldValue("pesou"));
                BigDecimal cantidadBIG = new BigDecimal(cantidad);
                BigDecimal pesoBIG = new BigDecimal(pesou);
                BigDecimal valor = (cantidadBIG.multiply(pesoBIG)).setScale(2, RoundingMode.UP);
                respuesta = valor;
            } else {
                rdAnterior = String.valueOf(reportParameters.getFieldValue("consecutivo"));
                logger.info("suma >> " + suma);
                respuesta = suma;
                logger.info("respuesta >> " + respuesta);
                suma = new BigDecimal(0);
            }
            return respuesta;
        }
    }


    private class ReportScriptlet extends AbstractScriptlet {
        @Override
        public void afterDetailEval(ReportParameters reportParameters) {
            super.afterDetailEval(reportParameters);
        }
    }

}