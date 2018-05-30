//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cbj.almacen.web;

import com.cbj.almacen.Utils;
import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.service.CatalogoManager;
import com.cbj.almacen.service.ClienteManager;
import com.cbj.almacen.service.DetalleRdManager;
import com.cbj.almacen.web.EntradasController;
import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
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

@Controller
@Scope("prototype")
@RequestMapping({"/exportRendimientos"})
public class ExportRendimientosController {
    private static final Logger logger = LoggerFactory.getLogger(EntradasController.class);
    private static final String PARAMETER_ITEM_NAME = "itemName";
    private static final String PARAMETER_TYPE = "type";
    private static final String VALUE_TYPE_PDF = "pdf";
    private static final String VALUE_TYPE_XLS = "xls";
    private String rdAnterior = "";
    private String rdActual = "";
    private BigDecimal suma = new BigDecimal(0.0D);
    private AggregationSubtotalBuilder<Double> quantitySumPza;
    private AggregationSubtotalBuilder<Double> quantitySumKgs;
    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE = new HashMap();
    @Autowired
    private DetalleRdManager detalleRdManager;
    @Autowired
    private CatalogoManager catalogoManager;
    @Autowired
    private ClienteManager clienteManager;

    public ExportRendimientosController() {
    }

    @RequestMapping(
            value = {""},
            method = {RequestMethod.GET}
    )
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestParam("fecini") String fechaInicio, @RequestParam("fecfin") String fechaFin) throws IOException, ServletException {
        String fileType = request.getParameter("type");
        logger.info("Exporting {} report", fileType);
        response.setContentType((String)FILE_TYPE_2_CONTENT_TYPE.get(fileType));
        ServletOutputStream out = response.getOutputStream();

        try {
            JasperReportBuilder e = this.createJasperReport(fechaInicio, fechaFin);
            JasperReportBuilder jrb2 = (JasperReportBuilder)this.createJasperReport(fechaInicio, fechaFin).ignorePagination();
            if("pdf".equals(fileType)) {
                e.toPdf(out);
            } else if("xls".equals(fileType)) {
                jrb2.toExcelApiXls(out);
            }
        } catch (DRException var9) {
            throw new ServletException(var9);
        }

        out.close();
    }

    private JasperReportBuilder createJasperReport(String fechaInicio, String fechaFin) {
        try {
            URL e = new URL("http://localhost:8080/almacen/resources/img/logo-arcosa.png");
            StyleBuilder boldStyle = (StyleBuilder)((StyleBuilder)DynamicReports.stl.style().setFontSize(Integer.valueOf(5))).setBold(Boolean.valueOf(false));
            StyleBuilder boldCenteredStyle = (StyleBuilder)((StyleBuilder)DynamicReports.stl.style(boldStyle).setFontSize(Integer.valueOf(5))).setHorizontalAlignment(HorizontalAlignment.CENTER);
            StyleBuilder columnTitleStyle = (StyleBuilder)((StyleBuilder)((StyleBuilder)DynamicReports.stl.style(boldCenteredStyle).setBorder(DynamicReports.stl.pen1Point())).setFontSize(Integer.valueOf(8))).setBackgroundColor(Color.LIGHT_GRAY);
            StyleBuilder textStyle = (StyleBuilder)((StyleBuilder)((StyleBuilder)DynamicReports.stl.style(boldCenteredStyle).setBorder(DynamicReports.stl.pen1Point())).setFontSize(Integer.valueOf(7))).setBackgroundColor(Color.white);
            StyleBuilder titleStyle = (StyleBuilder)((StyleBuilder)DynamicReports.stl.style(boldCenteredStyle).setVerticalAlignment(VerticalAlignment.MIDDLE)).setFontSize(Integer.valueOf(10));
            StyleBuilder titleStyleMini = (StyleBuilder)((StyleBuilder)DynamicReports.stl.style(boldCenteredStyle).setVerticalAlignment(VerticalAlignment.MIDDLE)).setFontSize(Integer.valueOf(5));
            StyleBuilder textStyle2 = (StyleBuilder)((StyleBuilder)DynamicReports.stl.style(boldCenteredStyle).setFontSize(Integer.valueOf(10))).setBackgroundColor(Color.white);
            return ((JasperReportBuilder)((JasperReportBuilder)((JasperReportBuilder)((JasperReportBuilder)((JasperReportBuilder)DynamicReports.report().setColumnTitleStyle(columnTitleStyle)).setTextStyle(textStyle)).highlightDetailEvenRows()).columns(new ColumnBuilder[]{DynamicReports.col.column("RD", "consecutivo", DynamicReports.type.integerType()).setPattern("##########"), DynamicReports.col.column("Camara", "camara", DynamicReports.type.stringType()), DynamicReports.col.column("Fecha", "horaCaptura", DynamicReports.type.stringType()), DynamicReports.col.column("Kilos", "apertura", DynamicReports.type.stringType()), DynamicReports.col.column("Piezas", "cantidad", DynamicReports.type.doubleType()), DynamicReports.col.column("ID", "idIngresoVehiculo", DynamicReports.type.integerType()), DynamicReports.col.column("Cliente", "almacenista", DynamicReports.type.stringType()), DynamicReports.col.column("Posiciones", "posiciones", DynamicReports.type.stringType()), DynamicReports.col.column("Cuota", "descripcion", DynamicReports.type.stringType()), DynamicReports.col.column("Estiba", "estibas", DynamicReports.type.stringType()), DynamicReports.col.column("Superficie", "servicio", DynamicReports.type.stringType()), DynamicReports.col.column("Base", "pesoBruto", DynamicReports.type.doubleType()), DynamicReports.col.column("Altura", "altura", DynamicReports.type.stringType()), DynamicReports.col.column("Tipo Alm", "almaen", DynamicReports.type.stringType()), DynamicReports.col.column("Forma Cobro", "lote", DynamicReports.type.stringType()), DynamicReports.col.column("Periodo", "lote2", DynamicReports.type.stringType()), DynamicReports.col.column("Produccion en $", "lote3", DynamicReports.type.stringType()), DynamicReports.col.column("Rendimiento en Documento $", "lote4", DynamicReports.type.stringType()) ,DynamicReports.col.column("Produccion Mensualizada en $", "lote3", DynamicReports.type.stringType())})).title(new ComponentBuilder[]{DynamicReports.cmp.horizontalList().add(new ComponentBuilder[]{DynamicReports.cmp.image(e).setFixedDimension(Integer.valueOf(80), Integer.valueOf(80)), DynamicReports.cmp.verticalList().add(new ComponentBuilder[]{((TextFieldBuilder)((TextFieldBuilder)DynamicReports.cmp.text("GRUPO ARCOSA PLANTA TEPOTZOTLAN").setStyle(titleStyle)).setWidth(Integer.valueOf(310))).setHorizontalAlignment(HorizontalAlignment.LEFT), ((TextFieldBuilder)((TextFieldBuilder)DynamicReports.cmp.text("Rendimientos").setStyle(textStyle2)).setWidth(Integer.valueOf(310))).setHorizontalAlignment(HorizontalAlignment.LEFT), ((TextFieldBuilder)((TextFieldBuilder)DynamicReports.cmp.text(Utils.getFechaTexto()).setStyle(textStyle2)).setWidth(Integer.valueOf(310))).setHorizontalAlignment(HorizontalAlignment.LEFT)})}), DynamicReports.cmp.horizontalList().add(new ComponentBuilder[]{((TextFieldBuilder)((TextFieldBuilder)DynamicReports.cmp.text("").setStyle(textStyle2)).setWidth(Integer.valueOf(310))).setHorizontalAlignment(HorizontalAlignment.CENTER)})})).setDataSource(this.createDataSource(fechaInicio, fechaFin)).rebuild();
        } catch (DRException var11) {
            logger.error("Error en DR " + var11);
            var11.printStackTrace();
        } catch (Exception var12) {
            logger.error("Error " + var12);
            var12.printStackTrace();
        }

        return DynamicReports.report();
    }

    private JRDataSource createDataSource(String fechaInicio, String fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfQuery = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateInicio = null;
        Date dateFin = null;

        try {
            dateInicio = sdf.parse(fechaInicio);
            dateFin = sdf.parse(fechaFin);
        } catch (ParseException var16) {
            logger.error(var16.getMessage());
            var16.printStackTrace();
        }

        List oList = this.detalleRdManager.getRendimientos(dateInicio, dateFin);
        ArrayList responseList = new ArrayList();
        Iterator var11 = oList.iterator();

        while(var11.hasNext()) {
            Object[] o = (Object[])var11.next();
            DetallesRd detallesRd = new DetallesRd();

            try {
                Date e = sdfQuery.parse(String.valueOf(o[2]));
                detallesRd.setConsecutivo(Integer.valueOf(Integer.parseInt(o[0].toString())));
                detallesRd.setCamara((String)o[1]);
                detallesRd.setHoraCaptura(df.format(e));
                detallesRd.setApertura(String.valueOf(o[3]));
                detallesRd.setCantidad(Double.valueOf(o[4].toString()));
                detallesRd.setIdIngresoVehiculo((int) Double.parseDouble(o[5].toString()));
                detallesRd.setAlmacenista((String)o[6]);
                detallesRd.setPosiciones((String)o[7]);
                detallesRd.setDescripcion((String)o[8]);
                detallesRd.setEstibas((String)o[9]);
                detallesRd.setServicio(String.valueOf(o[10]));
                detallesRd.setPesoBruto((Double)o[11]);
                detallesRd.setAltura((String)o[12]);
                detallesRd.setAlmaen((String)o[13]);
                detallesRd.setLote((String)o[14]);
                detallesRd.setLote2((String)o[15]);
                detallesRd.setLote3(String.valueOf(o[16]));
                detallesRd.setLote4(String.valueOf(o[17]));
                responseList.add(detallesRd);
            } catch (ParseException var15) {
                logger.error(var15.getMessage());
                var15.printStackTrace();
            }
        }

        return new JRBeanCollectionDataSource(responseList);
    }

    static {
        FILE_TYPE_2_CONTENT_TYPE.put("pdf", "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put("xls", "application/vnd.ms-excel");
    }
}
