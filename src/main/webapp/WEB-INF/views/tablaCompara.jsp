<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="contenidoconsultadetallerd">
<!--h1><fmt:message key="reportes.compara.heading"/></h1>
<h3><fmt:message key="reportes.compara.subtitle"/></h3-->
    <br>
    <br>
    <br>
    <br>
  <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
      <thead>
      <tr role="row">
      <th class="center  sorting_1">Cliente</th>
      <th class="center  sorting_1">Chofer</th>
      <th class="center  sorting_1">Placas</th>
      <th class="center  sorting_1">Fecha Registro Vehiculo</th>
      <th class="center  sorting_1">Fecha Ingreso Almacen</th>
      <th class="center  sorting_1">Peso Registro Vehiculo</th>
      <th class="center  sorting_1">Peso Ingreso Almacen</th>
    </tr>
    </thead>
    <c:set var="contador" value="0" />
	<c:forEach items="${comparaciones}" var="e">
	<tbody aria-relevant="all" aria-live="polite" role="alert">
     <c:if test="${e[12] ne e[11]}">
     <tr class="alert alert-block alert-danger odd">  
     </c:if>
     <c:if test="${e[12] eq e[11]}">
     <tr class="">  
     </c:if>
      <td>${e[0]} ${e[1]}</td>
      <td>${e[9]}</td>
      <td>${e[10]}</td>
      <td>
      	<fmt:parseDate value="${e[7]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
      	<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
      </td>
      <td>
      	<fmt:parseDate value="${e[4]}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
      	<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedEmpDate}" />
	  </td>
      <td><fmt:formatNumber value="${e[12]}" maxFractionDigits="0"/></td>
      <td><fmt:formatNumber value="${e[11]}" maxFractionDigits="0"/></td>
    </tr>
    </tbody>
    </c:forEach>
  </table>
</div>