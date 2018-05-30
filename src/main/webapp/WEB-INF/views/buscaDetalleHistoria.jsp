<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />

<h1>Reporte de Movimientos</h1>
<div class="component">
    <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultadoMovimientos">
        <thead>
        <tr role="row">
            <th align="center">PROSPECTO</th>
            <th align="center">TIPO DE MOVIMIENTO</th>
            <th align="center" >FECHA DE MOVIMIENTO</th>
            <th align="center" >DETALLE</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert">
        <c:forEach items="${historial}" var="c">
            <tr class="odd">
                <td align="center">${c[1]}</td>
                <td align="right" >${c[2]}</td>
                <td align="right" >${c[6]}</td>
                <td align="right" >${c[7]}</td>
            </tr>
        </c:forEach>
        </tbody>
        <div id="loader">
        </div>

    </table>
</div>

<script>
    $(document).ready(function() {

        var oTable1 = $('#tablaResultadoMovimientos').dataTable({
            "aoColumns": [
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true },
                { "bSortable": true }
            ],"pagingType": "full_numbers" } );
    });

</script>