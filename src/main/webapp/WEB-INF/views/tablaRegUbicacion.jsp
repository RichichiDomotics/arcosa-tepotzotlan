<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="resources/js/salidas.js"></script>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
              <th>Camara</th>
              <th>Perta</th>
              <th>Pasillo</th>
              <th>Fila o Calle</th>
              <th>Posicion</th>
              <th>Nivel</th>
              <th>Tama&ntilde;o Tarima</th>
              <th>RD</th>
           </tr>
          </thead>
    <!--c:forEach items="${registro}" var="det"-->
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <td><fmt:formatNumber value="${registro.camara}" maxFractionDigits="0"/></td>
            <td>${registro.puerta}</td>
            <td>${registro.pasillo}</td>
            <td><fmt:formatNumber value="${registro.filaCalle}" maxFractionDigits="0"/></td>
            <td><fmt:formatNumber value="${registro.posicion}" maxFractionDigits="0"/></td>
            <td><fmt:formatNumber value="${registro.nivel}" maxFractionDigits="0"/></td>
            <td>${registro.tipoTarima}</td>
            <td>${registro.consecutivo}</td>

        </tr>
        </tbody>
    <!--/c:forEach-->
</table>
