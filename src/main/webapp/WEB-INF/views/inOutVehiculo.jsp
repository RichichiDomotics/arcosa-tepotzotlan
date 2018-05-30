<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="contenidoconsultaInOutVehiculo">
<!--h1><fmt:message key="reportes.tiempos.heading"/></h1>
<h3><fmt:message key="reportes.tiempos.subtitle"/></h3-->
    <br>
    <br>
    <br>
    <br>
  <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
    <thead>
    <tr role="row">
      <th class="center  sorting_1">Cliente</th>
      <th class="center  sorting_1">Tipo Movimiento</th>
      <th class="center  sorting_1">Chofer</th>
      <th class="center  sorting_1">Placas</th>
      <th class="center  sorting_1">Fecha Entrada Vehiculo</th>
      <th class="center  sorting_1">Fecha Salida Vehiculo</th>
      <th class="center  sorting_1">Tiempo Atenci&oacute;n</th>
    </tr>
    </thead>
    <c:set var="contador" value="0" />
      <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
	<c:forEach items="${vehiculos}" var="e">
      <td class="center  sorting_1">${e[0]} ${e[1]}</td>
      <td class="center  sorting_1">
        <c:if test="${e[10]=='1111'}">ENTRADA</c:if>
        <c:if test="${e[10]=='1112'}">SALIDA</c:if>
      </td>
      <td class="center  sorting_1">${e[2]}</td>
      <td class="center  sorting_1">${e[3]}</td>
      <td class="center  sorting_1">
      	<fmt:parseDate value="${e[5]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
      	<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /> ${e[6]}
      </td>
      <td class="center  sorting_1">
      	<fmt:parseDate value="${e[7]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
      	<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" /> ${e[8]}
	  </td>
      <td class="center  sorting_1">${e[9]}</td>
    </tr>
    </c:forEach>
      </tbody>
  </table>
</div>