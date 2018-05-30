<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:forEach items="${clientes}" var="c">
  <c:if test="${c.idCliente == idCliente}">
    <div class="alert alert-block alert-info">
      <strong class="blue">
        <table border="0">
          <tr>
            <td align="right">
              <i class="icon-cogs blue"></i>
            </td>
            <td>
              CLIENTE:</td>
            <td>${c.idCliente} ${c.nombreCliente}</td>
          </tr>
        </table>
      </strong>
    </div>
  </c:if>
</c:forEach>

<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
  <thead>
  <tr role="row">
      <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">ID CLIENTE</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">RD</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA INICIO PERIODO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA FIN PERIODO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">CAMARA</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">KILOS</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">TIPO SERVICIO</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">TOTAL</th>
    <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">FECHA FACTURA</th>
  </tr>
  </thead>
  <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
    <c:forEach items="${prefacturas}" var="e">
    <tr>
      <td  class="center  sorting_1">${e[0]}</td>
      <td  class="center  sorting_1">${e[1]}</td>
      <td  class="center  sorting_1">
        <fmt:parseDate value="${e[2]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
      </td>
      <td  class="center  sorting_1">
        <fmt:parseDate value="${e[3]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
      </td>
      <td  class="center  sorting_1">${e[4]}</td>
      <td  class="center  sorting_1"><fmt:formatNumber value="${e[5]}" maxFractionDigits="2"/></td>
      <td  class="center  sorting_1">${e[6]}</td>
      <td  class="center  sorting_1"><fmt:formatNumber value="${e[7]}" maxFractionDigits="2"/></td>
      <td  class="center  sorting_1">
        <fmt:parseDate value="${e[8]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
        <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>