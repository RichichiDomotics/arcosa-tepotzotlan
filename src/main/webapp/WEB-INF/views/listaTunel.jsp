<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<div id="resultadoTabla">
<h3>${lista.idCliente} ${lista.nombreCliente}</h3>
  <c:choose>

    <c:when test="${not empty lista}">
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Id</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Fecha de captura</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Periodo</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cliente</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Kilos</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Solicitante</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Observaciones</th>
          <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Fecha de apartado</th>
        </tr>
        </thead>
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">

          <td  class="center  sorting_1">${lista.folioAsignado}</td>
          <td  class="center  sorting_1">
            ${lista.fecha.substring(0,10)}
          </td>
          <td  class="center  sorting_1">${lista.periodo}</td>
          <td  class="center  sorting_1">${lista.idCliente} ${lista.nombreCliente}</td>
          <td  class="center  sorting_1">${lista.kilos}</td>
          <td  class="center  sorting_1">${lista.solicitante}</td>
          <td  class="center  sorting_1">${lista.observaciones}</td>
          <td  class="center  sorting_1">
            ${lista.fechaCapturada.substring(0,10)}
          </td>
        </tr>
        </tbody>
      </table>
    </c:when>

  </c:choose>
</div>
