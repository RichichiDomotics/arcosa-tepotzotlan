<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FOLIO RETEME</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">RD</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">KILOS</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ID CLIENTE</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">TIPO SERVICIO</th>
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">OBSERVACIONES</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
    <c:forEach items="${retemesReg}" var="e">
      <td class="center  sorting_1">${e.getFolioReteme()}</td>
      <td class="center  sorting_1">
      	<fmt:parseDate value="${e.getFecha()}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
      </td>
      <td class="center  sorting_1">${e.getConsecutivo()}</td>
      <td class="center  sorting_1"><fmt:formatNumber value="${e.getKilos()}" maxFractionDigits="2"/></td>
      <td class="center  sorting_1">${e.getIdCliente()}</td>
      <td class="center  sorting_1">${e.getTipoServicio()}</td>
      <td class="center  sorting_1">${e.getObserv()}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>