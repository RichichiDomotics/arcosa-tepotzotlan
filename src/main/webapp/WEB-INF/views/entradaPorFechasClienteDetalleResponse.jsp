<%@ include file="/WEB-INF/views/include.jsp" %>
<h3 id="item_name">Reporte Detalle de Entrada por Fechas y Cliente : Del ${fechaInicio} al ${fechaFin}</h3>
<c:choose>
	<c:when test="${not empty conteoPorFechasClienteDetalle}">
        <a class="btn btn-primary btn-small" target="_blank" href="exportEntPorFechaClienteDetalle?type=xls&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}&idCliente=${idCliente}&nombreCliente=${clienteDatos.idCliente} ${clienteDatos.nombreCliente}">EXPORTA XLS  <span class="glyphicon glyphicon-list-alt"></span></a>
        <a class="btn btn-primary btn-small" target="_blank" href="exportEntPorFechaClienteDetalle?type=pdf&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}&idCliente=${idCliente}&nombreCliente=${clienteDatos.idCliente} ${clienteDatos.nombreCliente}">EXPORTA PDF  <span class="glyphicon glyphicon-th-list"></span></a>
        <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
          <thead>
        	<tr role="row">
                <td colspan="10" class="trtitulo">
                    <h4 id="item_name">CLIENTE: ${clienteDatos.idCliente} ${clienteDatos.nombreCliente}</h4>
                </td>
            </tr>
            <tr>
                <td align="right" width="20%" class="trtitulo">FECHA</td>
                <td align="right" width="20%" class="trtitulo">RD</td>
                <td align="right" width="20%" class="trtitulo">RENGLON</td>
                <td align="right" width="20%" class="trtitulo">CAMARA</td>
                <td align="right" width="20%" class="trtitulo">PRODUCTO</td>
                <td align="right" width="20%" class="trtitulo">CADUCIDAD</td>
                <td align="right" width="20%" class="trtitulo">LOTE</td>
                <td align="right" width="20%" class="trtitulo">MARCA</td>
                <td align="right" width="20%" class="trtitulo">CANTIDAD (PZAS)</td>
                <td align="right" width="20%" class="trtitulo">TOTAL KGS.</td>
            </tr>
           </thead>
            <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <c:forEach items="${conteoPorFechasClienteDetalle}" var="e">
                    <td align="right" width="20%"><fmt:parseDate value="${e[0]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /></td>
                    <td align="right" width="20%">${e[1]}</td>
                    <td align="right" width="20%"><fmt:formatNumber type="number" maxFractionDigits="0" value="${e[2]}"/></td>
                    <td align="right" width="20%">${e[3]}</td>
                    <td align="right" width="20%">${e[4]}</td>
                    <td align="right" width="20%">${e[5]}</td>
                    <td align="right" width="20%">${e[6]}</td>
                    <td align="right" width="20%">${e[7]}</td>
                    <td align="right" width="20%">${e[8]}</td>
                    <td align="right" width="20%"><fmt:formatNumber value="${e[10]}" maxFractionDigits="2"/> KGS</td>
                </tr>
            </c:forEach>
            </tbody>
            	<tr>
                    <td align="right" width="20%" colspan="8"></td>
                    <td align="right" width="20%">TOTAL PZAS:<fmt:formatNumber value="${conteoPorFechasClienteDetalleTotal[8]}" maxFractionDigits="2"/></td>
					<td align="right" width="20%">TOTAL KILOS:<fmt:formatNumber value="${conteoPorFechasClienteDetalleTotal[10]}" maxFractionDigits="2"/></td>
                </tr>
        </table>
    </c:when>    
	<c:when test="${conteoPorFechasClienteDetalle eq '[]'}">
      <div id="mensaje_reporte_vacio"><h1 id="item_name">No existen registros con esas condiciones.</h1></div>
    </c:when>        
</c:choose>