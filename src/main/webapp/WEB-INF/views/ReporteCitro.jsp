<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/component.css" />
<style>
  .component{
    width: 1000px;
  }
  td,th{
    padding: 0.2em 1em;
  }
</style>
<script src="resources/js/exportarExcel.js"></script>
<!--form action="http://localhost:8081/Ace/ficheroExcel.php" method="post" target="_blank" id="FormularioExportacion"-->
<form method="post" target="_blank" id="FormularioExportacion">
  <button class="btn btn-primary btn-small botonExcel">Exportar a excel</button>
  <input type="hidden" id="datos_a_enviar" name="datos_a_enviar" />
</form>
<div class="component">
  <table class="Exportar_a_Excel">
    <thead>
    <tr bgcolor="438eba" style="color: #FFFFFF;">
      <th>ID</th>
      <th>RD</th>
      <th>Marca</th>
      <th>Lote</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${inventarios}" var="det">
      <tr>
        <td>${det.idCliente}</td>
        <td>${det.consecutivo}</td>
        <td>${det.marca}</td>
        <td>${det.lote}</td>
      </tr>
      <!--script>conteo++;</script-->
    </c:forEach>
    </tbody>
  </table>
</div>
