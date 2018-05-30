<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script>
$(document).ready(function(){
    var placeholderEstatusEjecutivo = $('#piechart-placeholderEjecutivo').css({'width':'95%' , 'min-height':'200px'});


    <c:set var="contadorEtapaEjecutivo" value="0"/>
    var dataEtapaEjecutivo = [
        <c:forEach items="${repEjecutivoEstatusEjecutivo}" var="e">
        { label: "<c:out value="${e[1]}"/> <c:out value="${e[2]}"/>", data: '<c:out value="${e[2]}"/>', color: "#" + Math.random().toString(16).slice(2, 8)},
        <c:set var="contadorEtapaEjecutivo" value="${contadorEtapaEjecutivo+1}"/>
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

    drawPieChart(placeholderEstatusEjecutivo, dataEtapaEjecutivo);


    placeholderEstatusEjecutivo.data('chart', dataEtapaEjecutivo);
    placeholderEstatusEjecutivo.data('draw', drawPieChart);

    var $tooltip = $("<div class='tooltip top in hide'><div class='tooltip-inner'></div></div>").appendTo('body');
    var previousPoint = null;

    placeholderEstatusEjecutivo.on('plothover', function (event, pos, item) {
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


   /* visualizaEtapaEjecutivo = function(){
        if($(".contenidoReporteEtapasEjecutivo").is(":visible")){
            $(".veEtapasEjecutivo").html("<i class='icon-eye-open'> Ver Reporte Etapas por Ejecutivo");
        }else{
            $(".veEtapasEjecutivo").html("<i class='icon-eye-close'> Ocultar Reporte Etapas por Ejecutivo");
        }
        $(".contenidoReporteEtapasEjecutivo").is(":visible")
        $(".contenidoReporteEtapasEjecutivo").toggle();
    }*/

});

</script>
<div class="contenidoGeneral">
    <!-- INICIO REPORTE GENERAL POR ESTATUS Ejecutivo -->
    <div class="contenidoReporteEtapasEjecutivo">
        <div class="contenidoTablaEtapaEjecutivo">
            <!------------------------------------------------------------------->
            <div class="span4">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>${repEjecutivoEstatusEjecutivo[0][0]}</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                <tr role="row">
                                    <th align="right" >Estatus</th>
                                    <th align="right" >Total</th>
                                </tr>
                                </thead>
                                <c:set var="TotalTotalEt" value="${0}"/>
                                <tbody aria-relevant="all" aria-live="polite" role="alert">
                                <c:forEach items="${repEjecutivoEstatusEjecutivo}" var="e">
                                <tr class="odd">
                                    <td align="right" >${e[1]}</td>
                                    <td align="right" >${e[2]}</td>
                                    <c:set var="TotalTotalEt" value="${TotalTotalEt+(e[2])}"/>
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
                            <h4>Gr&aacute;fica Estatus</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row-fluid">
                                    <div id="piechart-placeholderEjecutivo"></div>
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
</div>
