<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<style>
  .component{
    width: 1000px;
  }
  td,th{
    padding: 0.2em 1em;
  }
  .dataTable th[class*="sorting_"]{
    color: #FFFFFF;
  }
</style>
<h2>Clientes con bloqueo de salidas</h2>
<div class="component">
  <table>
    <thead>
      <tr>
        <th>ID CLIENTE</th>
        <th>NOMBRE DEL CLIENTE</th>
        <th>PERMISO</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${clientes}" var="cl">
        <tr>
          <td>${cl.idCliente}</td>
          <td>${cl.nombreCliente}</td>
          <td>${cl.salidaProducto}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>