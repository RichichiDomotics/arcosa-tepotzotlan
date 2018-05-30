<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="resources/assets/css/reportecrm.css" />
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function(){
        var oTable1 = $('#sample-table-2').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ],
            "scrollY":        "595px",
            "scrollCollapse": true,
            "paging":         false } );
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
        $(".factura").click(function(){
            factura1=$(this).attr("factura");
            idCliente=$(this).attr("idCliente");
            factura(factura1,idCliente);
        });

        factura = function(factura1,idCliente){
            if(!$("#CapSeg").length){
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
    });

</script>
<style>
    .infobox{
        width: 500px;
    }
</style>
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
                    <!--<a href="http://localhost:8081/Ace/pdf/${f[3]}_${f[1]}.pdf" target="_new" class="btn btn-primary btn-mini factura"  factura="${f[3]}" idCliente="${f[1]}">
                    <i class="icon-edit  bigger-125"></i>${f[3]}</a>-->
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
<br><br>
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
<div id="supdiv"><div id="CapSeg"></div></div>
