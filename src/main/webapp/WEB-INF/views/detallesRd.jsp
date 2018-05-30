<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="resources/js/RealizarSalidas.js"></script>
<script>
	$(document).ready(function(){
		$("#sample-table-2").dataTable();
	});
</script>
<style>
	.btn-primary{
		display: block;
	}
</style>
<input type="hidden" path="bandera" name="bandera" id="bandera" value="1"/>
<input type="hidden" id="entregado" value="${entregado}" disabled>
<!-- h1 class="titulotabla alert-success"><fmt:message key="consultas.ingreso.heading"/></h1-->
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
	<thead>
      <tr role="row">
      <th>
      	<input type="checkbox" name="selecttodo" id="selecttodo" onclick="javascript:selectall()"/>
      	<span class="lbl">Selecciona todos</span>
      </th>
      <th>Numero RD</th>
      <th>Rengl&oacute;n</th>
      <th>Producto</th>
      <th>Embalaje</th>
	  <th>Caducidad</th>
	  <th>Marca</th>
	  <th>Lote</th>
      <th>Cantidad</th>
    </tr>
    </thead>
    <c:set var="contador" value="0" />
	<tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
	<c:forEach items="${detalle}" var="e">
      <td>
      	<!-- input type="radio" name="idConsulta" value="${e.idCliente}"-->
      	<input type="checkbox" id="iddetallerd" name="iddetallerd" value="${e.idInventario}"><span class="lbl"></span>
      </td>
      <td>${e.consecutivo}</td>
      <td><fmt:formatNumber value="${e.renglon}" maxFractionDigits="0"/></td>
      <td>${e.descripcion}</td>
      <td>${e.embalaje}</td>
		 <td>${e.caducidad}</td>
		 <td>${e.marca}</td>
		 <td>${e.lote}</td>
      <td>
      	<input type="text" value="${e.cantidadInventario}" name="cantidad" id="cantidad" campo="cantidad${contador}" class="campoInventario ${e.idInventario}"/>
      	<input type="hidden" value="${e.cantidadInventario}" name="cantidad${contador}" id="cantidad${contador}" class="${e.idInventario}"/>
      </td>
    </tr>
    <c:set var="contador" value="${contador+1}" />
    </c:forEach>
	</tbody>

       <!--tr>
    <td colspan="5" align="center">
    	<button class="btn btn-primary btn-small RealizaSalida" type="button">
			<i class="icon-edit  bigger-125"></i>
			Realizar Salida
		</button>
    </td>
    <td colspan="4" align="center">
        <form:form method="post" role="form" action="terminaSalida">
        	<button class="btn btn-primary btn-small terminasalida" type="button">
			<i class="icon-edit  bigger-125"></i>
			Terminar Salida
		</button>
            <input type="hidden" value="${idCliente}" name="idCliente" id="idCliente"/>
        </form:form>
    </td>

    </tr-->
  </table>
<button class="btn btn-primary btn-small RealizaSalida" type="button">
	<i class="icon-edit  bigger-125"></i>
	Realizar Salida
</button>
<br>
<form:form method="post" role="form" action="terminaSalida">
	<button class="btn btn-primary btn-small terminasalida" type="button">
		<i class="icon-edit  bigger-125"></i>
		Terminar Salida
	</button>
	<input type="hidden" value="${idCliente}" name="idCliente" id="idCliente"/>
</form:form>
  <div id="terminandoSalida"></div>


