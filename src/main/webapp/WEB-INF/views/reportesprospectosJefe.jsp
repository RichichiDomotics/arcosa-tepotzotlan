<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script>
    $(document).ready(function(){
        var placeholderEtapas = $('#piechart-placeholder4').css({'width':'95%' , 'min-height':'200px'});
        var placeholderEstatus = $('#piechart-placeholderEstatus').css({'width':'95%' , 'min-height':'200px'});


        <c:set var="contadorEtapa" value="0"/>
        var dataEtapa = [
            <c:forEach items="${repEtapas}" var="e">
            { label: "<c:out value="${e[0]}"/> <c:out value="${e[1]}"/>", data: <c:out value="${e[1]}"/>, color: "#" + Math.random().toString(16).slice(2, 8)},
            <c:set var="contadorEtapa" value="${contadorEtapa+1}"/>
            </c:forEach>
        ]

        <c:set var="contadorEstatus" value="0"/>
        var dataEstatus = [
            <c:forEach items="${repEstatus}" var="e">
            { label: "<c:out value="${e[0]}"/> <c:out value="${e[1]}"/>", data: <c:out value="${e[1]}"/>, color: "#" + Math.random().toString(16).slice(2, 8)},
            <c:set var="contadorEstatus" value="${contadorEstatus+1}"/>
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

        drawPieChart(placeholderEtapas, dataEtapa);
        drawPieChart(placeholderEstatus, dataEstatus);


        placeholderEtapas.data('chart', dataEtapa);
        placeholderEtapas.data('draw', drawPieChart);

        placeholderEstatus.data('chart', dataEstatus);
        placeholderEstatus.data('draw', drawPieChart);

        var $tooltip = $("<div class='tooltip top in hide'><div class='tooltip-inner'></div></div>").appendTo('body');
        var previousPoint = null;

        placeholderEtapas.on('plothover', function (event, pos, item) {
            if(item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : " + Math.round(item.series['percent']).toFixed(2)+'%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        placeholderEstatus.on('plothover', function (event, pos, item) {
            if(item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : " + Math.round(item.series['percent']).toFixed(2)+'%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        $( ".contenidoReporteEtapas" ).toggle();
        visualizaEtapa = function(){
            var posicionBarraEtapa = $(".veEtapas").position();
            if($(".contenidoReporteEtapas").is(":visible")){
                $(".veEtapas").html("<i class='icon-eye-open'> Ver Reporte por Etapas");
            }else{
                $(".veEtapas").html("<i class='icon-eye-close'> Ocultar Reporte por Etapas");
            }
            $(".contenidoReporteEtapas").is(":visible")
            $(".contenidoReporteEtapas").toggle();
            $(window).scrollTop(posicionBarraEtapa.top);
        }

        $( ".contenidoReporteEstatus" ).toggle();
        visualizaEstatus = function(){
            var posicionBarraEstatus = $(".veEstatus").position();
            if($(".contenidoReporteEstatus").is(":visible")){
                $(".veEstatus").html("<i class='icon-eye-open'> Ver Reporte por Estatus");
            }else{
                $(".veEstatus").html("<i class='icon-eye-close'> Ocultar Reporte por Estatus");
            }
            $(".contenidoReporteEstatus").is(":visible")
            $(".contenidoReporteEstatus").toggle();
            $(window).scrollTop(posicionBarraEstatus.top);
        }

        $( ".contenidoReporteEjecutivos" ).toggle();
        visualizaEjecutivos = function(){
            var posicionBarraEjecutivo = $(".veEjecutivos").position();
            if($(".contenidoReporteEjecutivos").is(":visible")){
                $(".veEjecutivos").html("<i class='icon-eye-open'> Ver Reporte por Ejecutivos");
            }else{
                $(".veEjecutivos").html("<i class='icon-eye-close'> Ocultar Reporte por Ejecutivos");
            }
            $(".contenidoReporteEjecutivos").is(":visible")
            $(".contenidoReporteEjecutivos").toggle();
            $(window).scrollTop(posicionBarraEjecutivo.top);
        }


        $(".veDetEtapa").click(function(){
            claveEjecutivo = $(this).attr("claveEjecutivo");
            $.ajax("RecDetEtapaEjecutivo", {
                "type": "POST",
                "success": function(result){
                    $("#graficasDetalle").html(result);
                },
                "error": function(result) {
                    bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n"+result.error())
                },
                "data": {cveEjecutivo:claveEjecutivo},
                "async": false
            });
        });

        $(".veDetEstatus").click(function(){
            claveEjecutivo = $(this).attr("claveEjecutivo");
            $.ajax("RecDetEstatusEjecutivo", {
                "type": "POST",

                "success": function(result){
                    $("#graficasDetalle").html(result);
                },
                "error": function(result) {
                    bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n"+result.error())
                },
                "data": {cveEjecutivo:claveEjecutivo},
                "async": false
            });
        });




    });

</script>
<div class="contenidoGeneral">
    <!-- INICIO REPORTE GENERAL POR ETAPAS -->
    <a href="javascript:visualizaEtapa();" class="btn btn-block btn-info veEtapas"><i class="icon-eye-open"></i> Ver Reporte por Etapas</a>
    <div class="contenidoReporteEtapas">
        <div class="contenidoTablaEtapa">
            <!------------------------------------------------------------------->
            <div class="span4">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>Reporte Total por Etapas</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr role="row">
                                    <th align="right" >Etapas</th>
                                    <th align="right" >Total</th>
                                </tr>
                                </thead>
                                <c:set var="TotalTotalEt" value="${0}"/>
                                <tbody aria-relevant="all" aria-live="polite" role="alert">
                                <c:forEach items="${repEtapas}" var="e">
                                    <tr class="odd">
                                        <td align="right" >${e[0]}</td>
                                        <td align="right" >${e[1]}</td>
                                        <c:set var="TotalTotalEt" value="${TotalTotalEt+(e[1])}"/>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td align="right">Total: </td>
                                    <td align="right" >${TotalTotalEt}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-------------------------------------------->
        <!--********************************************-->
        <div class="graficas4">
            <div class="row-fluid contgrafi">
                <div class="span4">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4>Gr&aacute;fica</h4>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row-fluid">
                                    <div id="piechart-placeholder4"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--*******************************************-->
    </div>
    <!-- FIN REPORTE GENERAL POR ETAPAS -->
    <hr>
    <!-- INICIO REPORTE GENERAL POR ESTATUS -->
    <a href="javascript:visualizaEstatus();" class="btn btn-block btn-info veEstatus"><i class="icon-eye-open"></i> Ver Reporte por Estatus</a>
    <div class="contenidoReporteEstatus">
        <div class="contenidoTablaEstatus">
            <!------------------------------------------------------------------->
            <div class="span4">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>Reporte Total por Estatus</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr role="row">
                                    <th align="right" >Etapas</th>
                                    <th align="right" >Total</th>
                                </tr>
                                </thead>
                                <c:set var="TotalTotalEt" value="${0}"/>
                                <tbody aria-relevant="all" aria-live="polite" role="alert">
                                <c:forEach items="${repEstatus}" var="e">
                                    <tr class="odd">
                                        <td align="right" >${e[0]}</td>
                                        <td align="right" >${e[1]}</td>
                                        <c:set var="TotalTotalEst" value="${TotalTotalEst+(e[1])}"/>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td align="right">Total: </td>
                                    <td align="right" >${TotalTotalEst}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-------------------------------------------->
        <!--********************************************-->
        <div class="graficas4">
            <div class="row-fluid contgrafi">
                <div class="span4">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4>Gr&aacute;fica</h4>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row-fluid">
                                    <div id="piechart-placeholderEstatus"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--*******************************************-->
    </div>
    <!-- FIN REPORTE GENERAL POR ESTATUS -->
    <hr>
    <!-- INICIO REPORTE POR EJECUTIVO -->
    <a href="javascript:visualizaEjecutivos();" class="btn btn-block btn-info veEjecutivos"><i class="icon-eye-open"></i> Ver Reporte por Ejecutivos</a>
    <div class="contenidoReporteEjecutivos">
        <div class="contenidoTablaEjecutivos">
            <!------------------------------------------------------------------->
            <div class="span4">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>Ejecutivos</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr role="row">
                                    <th>Nombre Ejecutivo</th>
                                    <th>Clave Ejecutivo</th>
                                    <th>Detalle</th>
                                </tr>
                                </thead>
                                <c:set var="TotalTotalEt" value="${0}"/>
                                <tbody aria-relevant="all" aria-live="polite" role="alert">
                                <c:forEach items="${ejecutivos}" var="e">
                                    <tr class="odd">
                                        <td align="right" >${e.nombreEjecutivo}</td>
                                        <td align="right" >${e.claveEjecutivo}</td>
                                        <td>
                                            <div class="btn-group">
                                                <a href="javascript:void(0);" class="btn btn-mini btn-info veDetEtapa" claveEjecutivo="${e.claveEjecutivo}">
                                                    <i class="icon-signal"></i> Por Etapa
                                                </a>
                                                <a href="javascript:void(0);" class="btn btn-mini btn-info veDetEstatus" claveEjecutivo="${e.claveEjecutivo}">
                                                    <i class="icon-signal"></i> Por Estatus
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-------------------------------------------->
        <!--********************************************-->
        <div class="graficasDetalle" id="graficasDetalle">

        </div>
        <!--*******************************************-->
    </div>
    <!-- FIN REPORTE GENERAL POR ESTATUS -->
</div>
