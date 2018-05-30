<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
  <thead>
  <tr role="row">
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FOLIO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ID CLIENTE</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">HORA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">RD</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SALIDA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">MODIFICACION</th>

    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SOLICITADO POR</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ATENDIDO POR</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">SOLUCION</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">TIPO</th>

  </tr>
  </thead>
  <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
    <c:forEach items="${modificaciones}" var="e">
    <td  class="center  sorting_1">${e.folio}</td>
    <td  class="center  sorting_1">
      <c:forEach items="${clientes}" var="c">
        <c:if test="${c.idCliente==e.idCliente}">
          ${c.idCliente} ${c.nombreCliente}
        </c:if>
      </c:forEach>
    </td>
    <td  class="center  sorting_1">
      <fmt:parseDate value="${e.fecha_captura}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
      <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
    </td>
    <td  class="center  sorting_1">${e.hora_captura}</td>
    <td  class="center  sorting_1">${e.rd_afectado}</td>
      <td  class="center  sorting_1">${e.salida_afectada}</td>

    <td  class="center  sorting_1">${e.cambio_solicitado}</td>
    <td  class="center  sorting_1">${e.nombre_solicitante}</td>
      <td  class="center  sorting_1">${e.atendidopor}</td>
      <td  class="center  sorting_1">${e.solucion}</td>
      <td  class="center  sorting_1">${e.tipomodificacion}</td>
  </tr>
  </c:forEach>
  </tbody>
</table>