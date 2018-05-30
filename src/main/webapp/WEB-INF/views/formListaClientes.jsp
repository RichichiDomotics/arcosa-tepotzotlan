<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script type="text/javascript" src="resources/js/inventarios.js"></script>
<section class="container">
<h1 class="item_name">Consulta Cliente</h1>
<h3 class="item_name">Filtra cliente</h3>
<form:form method="post" action="consultaInventario">
<section class="grupodedatos">
  <div class="bloquedata">
    <div class="form-group">
   		<label for="idCliente">CLIENTE</label>
    	<select id="idCliente" name="idCliente" value='' class="form-control" required>
    		<option value="" > <fmt:message key="inventarios.select.cliente"/> </option>
        		<c:forEach items="${clientes}" var="client">
      				<option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
    			</c:forEach>
    	</select>
    </div>
	<div class="form-group">
  	   <br/>
	   <button class="btn btn-primary btn-small buscainventariosRep" type="button">
			<i class="icon-edit  bigger-125"></i>
			Busca Cliente
		</button>
    </div>
  </div>
</section>
</form:form>
<div id="ListadoClientes"></div>
</section>
