<%@ include file="/WEB-INF/views/include.jsp" %>
<script>
  $(document).ready(function() {
    var oTable1 = $('#sample-table-2').dataTable();
  });
</script>
<h1>Recibos de Entrada Cancelados</h1>

<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
  <thead>
  <tr role="row">
    <th>
      RD
    </th>
    <th>CLIENTE</th>
    <th>FECHA</th>
    <th>ESTATUS</th>
  </tr>
  </thead>
  <tbody aria-relevant="all" aria-live="polite" role="alert">
  <c:forEach items="${cancelados}" var="c">
    <tr class="odd">
      <td align="center" >
        ${c.consecutivo}

      </td>
      <td><c:forEach items="${clientes}" var="cl">
        <c:if test="${c.idCliente==cl.idCliente}">
          ${cl.idCliente} ${cl.nombreCliente}
        </c:if>
      </c:forEach></td>
      <td><c:forEach items="${vehiculos}" var="v">
        <c:if test="${c.idIngresoVehiculo==v.idIngresoVehiculo}">
          <fmt:parseDate value="${v.fechaEntrada}" var="parsedEmpDate" pattern="yyyy-MM-dd" />
          <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" />
        </c:if>
      </c:forEach> </td>
      <td>${c.observaciones}</td>

    </tr>
  </c:forEach>
  </tbody>
  <!--tr>
<td colspan="2" align="center"><input type="submit" value="Consultar"></td>
<td colspan="2" align="center"><input type="submit" value="Modificar"></td>
<td colspan="2" align="center"><input type="submit" value="Eliminar"></td>
</tr-->
</table>
