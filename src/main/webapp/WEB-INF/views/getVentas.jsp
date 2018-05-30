<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="resources/js/manejoTablasVentas.js"></script>
<script>
    $(document).ready(function(){
        var oTable1 = $('#sample-table-2').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ],"pagingType": "full_numbers" } );
        var oTable2 = $('#sample-table-4').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ] } );
        var oTable3 = $('#sample-table-1').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true }
            ]});
        var oTable4 = $('#sample-table-3').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true }
            ]});
        $("#reporte1").show();
        $("#reporte2").hide();
        $("#reporte3").hide();
        $("#reporte4").hide();
       /* $(".factura").click(function(){
            factura1=$(this).attr("factura");
            idCliente=$(this).attr("idCliente");
            factura(factura1,idCliente);
        });

        factura = function(factura1,idCliente){
            if($("#CapSeg").length){
                $("#supdiv").append('<div id="CapSeg"></div>')
            }

            //bootbox.dialog($("#CapSeg").html("<iframe src='http://"+window.location.hostname+":8080/almacen/resources/pdf/"+factura1+"_"+idCliente+".pdf'" +
            bootbox.dialog($("#CapSeg").html("<iframe src='http://"+window.location.hostname+":8081/Ace/pdf/"+factura1+"_"+idCliente+".pdf'" +
                            "width='100%' height='900px' frameborder='no' tabindex='0'>" +
                            "</iframe>"), [{
                        "label" : "Cerrar",
                        "class" : "btn-small btn-primary",
                        "callback": function() {
                            $("#CapSeg").html("");
                        }
                    }]
            ).css({"width":"1000px","height":"auto","left":"450px","overflow-y": "hidden"});
        };

        /*var color;

        color = ['#68BC31', '#2091CF', '#AF4E96', '#DA5430', '#FEE074', '#FF8000', '#996600', '#b1d035', '#DA5430',
            '#FFFF00', '#66FFFF', '#996600',  '#FF8000', '#F5A9E1', '#A4A4A4', '#868A08', '#FFFF00', '#58FA82'];*/

        <c:set var="contador" value="0"/>

        var placeholder = $('#piechart-placeholder').css({'width':'95%' , 'min-height':'250px'});
        var placeholder2 = $('#piechart-placeholder2').css({'width':'95%' , 'min-height':'250px','height':'250px'});
        <c:set var="contador1" value="0"/>
        var data = [
            <c:forEach items="${ventas}" var="a">
            { label: "<c:out value="${a[1]}"/>", data: <c:out value="${a[0]}"/>, color: "#" + Math.random().toString(16).slice(2, 8)},
            <c:set var="contador1" value="${contador1+1}"/>
            </c:forEach>
        ]

        <c:set var="contador2" value="0"/>
        var data2 = [
            <c:forEach items="${notas}" var="b">
            { label: "<c:out value="${b[1]}"/>", data: <c:out value="${b[0]}"/>, color: "#" + Math.random().toString(16).slice(2, 8)},
            <c:set var="contador2" value="${contador2+1}"/>
            </c:forEach>
        ]

        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt:0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin:[-30,15]
                }
                ,
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }
        drawPieChart(placeholder, data);
        drawPieChart(placeholder2, data2);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);
        placeholder2.data('chart', data2);
        placeholder2.data('draw', drawPieChart);

        var $tooltip = $("<div class='tooltip top in hide'><div class='tooltip-inner'></div></div>").appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if(item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : "+ item.series['percent'].toFixed(2)+"%";
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        placeholder2.on('plothover', function (event, pos, item) {
            if(item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : "+ item.series['percent'].toFixed(2)+"%";
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        $(".boton1").click(function(){
            $("#reporte1").show();
            $("#reporte2").hide();
            $("#reporte3").hide();
            $("#reporte4").hide();
        });
        $(".boton2").click(function(){
            $("#reporte1").hide();
            $("#reporte2").show();
            $("#reporte3").hide();
            $("#reporte4").hide();
        });
        $(".boton3").click(function(){
            $("#reporte1").hide();
            $("#reporte2").hide();
            $("#reporte3").show();
            $("#reporte4").hide();
        });
        $(".boton4").click(function(){
            $("#reporte1").hide();
            $("#reporte2").hide();
            $("#reporte3").hide();
            $("#reporte4").show();
        });
    });

</script>
<style>
    .infobox{
        width: 500px;
    }
</style>
<div class="btn-toolbar">
    <div class="btn-group">
        <button class="btn boton1">1</button>
        <button class="btn boton2">2</button>
        <button class="btn boton3">3</button>
        <button class="btn boton4">4</button>
    </div>
</div>
<div id="reporte1">
    <h5>REPORTE DE FACTURAS</h5>
    <table aria-describedby="sample-table-2_info" id="sample-table-1" class="table table-striped tabla1 table-bordered table-hover dataTable">
            <thead>
                <tr role="row">
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NOMBRE DE EJCUTIVO</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">TOTAL DE FACTURAS YA EMITIDAS</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
        <c:forEach items="${ventas}" var="e">
          <tr>
              <td  class="center  sorting_1">${e[1]}</td>
              <td  class="center  sorting_1">$ <fmt:formatNumber value="${e[0]}" maxFractionDigits="2"/></td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
        <div class="row-fluid contgrafi">
            <div class="span4">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>Gr&aacute;fica Ventas Ejecutivo</h4>
                            <span class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="icon-chevron-up"></i>
                                </a>
                            </span>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="row-fluid">
                                <div id="piechart-placeholder"></div>
                                <div class="hr hr8 hr-double"></div>

                                <div class="clearfix">
                                    <div class="grid3">
                                        <span class="grey">
                                            <div class="infobox infobox-blue">
                                                <div class="infobox-icon">
                                                    <i class="icon-shopping-cart"></i>
                                                </div>
                                                <div class="infobox-data">
                                                    <span class="infobox-data-number">$ <fmt:formatNumber value="${total}" maxFractionDigits="2"/></span>
                                                    <div class="infobox-content">Subtotal Ventas emitidas</div>
                                                </div>
                                            </div>
                                            <!--&nbsp; Subtotal Ventas emitidas-->
                                        </span>
                                        <!--<h6 class="bigger pull-right"></h6>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <br><br>
</div>
<div id="reporte2">
    <h5>DETALLE DE VENTAS NETAS POR CLIENTE</h5>
    <h6><strong>IMPORTANTE: </strong>ESTE REPORTE CONTIENE LAS VENTAS NETAS(FACTURAS EMITIDAS Y POR FACTURAR)</h6>
    <table aria-describedby="sample-table-2_info"  id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ID CLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NOMBRE DEL CLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NOMBRE DEL EJECUTIVO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">MONTO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FACTURA</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
        <c:forEach items="${ventasNetas}" var="f">
            <tr>
                <td  class="center  sorting_1">${f[1]}</td>
                <td  class="center  sorting_1">${f[2]}</td>
                <td  class="center  sorting_1">${f[4]}</td>
                <td  class="center  sorting_1">$ <fmt:formatNumber value="${f[0]}" maxFractionDigits="2"/></td>
                <td  class="center  sorting_1">
                <c:if test="${f[3] ne ''}">
                    <button class="btn btn-primary btn-mini factura" type="submit" factura="${f[3]}" idCliente="${f[1]}">
                    <i class="icon-edit  bigger-125"></i>${f[3]}</button>
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <div class="infobox infobox-red" style="float: right; width: 300px">
        <div class="infobox-icon">
            <i class="icon-briefcase"></i>
        </div>
        <div class="infobox-data">
            <span class="infobox-data-number">$ <fmt:formatNumber value="${totalnetas}" maxFractionDigits="2"/></span>
            <div class="infobox-content">Subtotal Ventas Netas</div>
        </div>
    </div>
    <br>
</div>
<div id="reporte3">
    <h5>REPORTE DE NOTAS DE CREDITO</h5>
    <table aria-describedby="sample-table-2_info" id="sample-table-3" class="table table-striped tabla3 table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NOMBRE DE EJCUTIVO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SUBTOTAL DE NOTAS</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
        <c:forEach items="${notas}" var="g">
            <c:if test="${g[1] ne '' && g[0] ne '0'}">
            <tr class="odd">
                <td  class="center  sorting_1">${g[1]}</td>
                <td  class="center  sorting_1">$ <fmt:formatNumber value="${g[0]}" maxFractionDigits="2"/></td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <div class="row-fluid contgrafi">
        <div class="span4">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>Gr&aacute;fica Notas de Cr&eacute;dito</h4>
                            <span class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="icon-chevron-up"></i>
                                </a>
                            </span>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <div class="row-fluid">
                            <div id="piechart-placeholder2"></div>
                            <div class="hr hr8 hr-double"></div>

                            <div class="clearfix">
                                <div class="grid3">
                                        <span class="grey">
                                            <div class="infobox infobox-green">
                                                <div class="infobox-icon">
                                                    <i class="icon-credit-card"></i>
                                                </div>
                                                <div class="infobox-data">
                                                    <span class="infobox-data-number">$ <fmt:formatNumber value="${totalnotas}" maxFractionDigits="2"/></span>
                                                    <div class="infobox-content">Subtotal Notas de Credito</div>
                                                </div>
                                            </div>
                                            <!--&nbsp; Subtotal Ventas emitidas-->
                                        </span>
                                    <!--<h6 class="bigger pull-right"></h6>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
</div>
<div id="reporte4">
    <h5>REPORTE DE DETALLES NOTAS DE CREDITO</h5>
    <table aria-describedby="sample-table-2_info" id="sample-table-4" class="table table-striped tabla4 table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NO. NOTA</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">NUMERO Y NOMBRE DEL CLIENTE</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">MOTIVO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">EJECUTIVO</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SUBTOTAL</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FACTURA ANTERIOR</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">REFACTURA</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
        <c:forEach items="${notasDetalle}" var="h">
            <tr class="odd">
                <td  class="center  sorting_1">${h[1]}</td>
                <td  class="center  sorting_1"><fmt:parseDate value="${h[2]}" var="parsedEmpDate" pattern="yyyy-MM-dd" /><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" /></td>
                <td  class="center  sorting_1">${h[3]} ${h[7]}</td>
                <td  class="center  sorting_1">${h[8]}</td>
                <td  class="center  sorting_1">${h[9]}</td>
                <td  class="center  sorting_1">$ <fmt:formatNumber value="${h[0]}" maxFractionDigits="2"/></td>
                <td  class="center  sorting_1">${h[4]}</td>
                <td  class="center  sorting_1">${h[5]}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <div class="infobox infobox-pink" style="float: right">
        <div class="infobox-icon">
            <i class="icon-shopping-cart"></i>
        </div>
        <div class="infobox-data">
            <span class="infobox-data-number">$ <fmt:formatNumber value="${totalventas}" maxFractionDigits="2"/> no incluye IVA</span>
            <div class="infobox-content"><h4>$ <fmt:formatNumber value="${totalnetas}" maxFractionDigits="2"/> - $ <fmt:formatNumber value="${totalnotas}" maxFractionDigits="2"/></h4></div>
            <div class="infobox-content">Total Ventas (Ventas Netas) - (Notas de Cr&eacute;dito)</div>
        </div>
    </div>
</div>
<div class="btn-toolbar">
    <div class="btn-group">
        <button class="btn boton1">1</button>
        <button class="btn boton2">2</button>
        <button class="btn boton3">3</button>
        <button class="btn boton4">4</button>
    </div>
</div>
<div id="supdiv"><div id="CapSeg"></div></div>
