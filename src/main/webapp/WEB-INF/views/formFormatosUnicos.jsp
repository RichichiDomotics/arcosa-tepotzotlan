<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/inventario.css" />
<script type="text/javascript" src="resources/js/inventarios.js"></script>
<style>
	#buscainventario{
		width: 1000px;
	}
</style>
<script>
	$(document).ready(function(){
		$("#camara").change(function(){
			if($("#camara").val()!=""){
				$("#tunel").val("");
			}
		});

		$("#tunel").change(function(){
			if($("#tunel").val()!=""){
				$("#camara").val("");
			}
		});
	});
</script>
<section class="container">
<h1 class="item_name"><fmt:message key="formatosunicos.heading"/></h1>
<h3 class="item_name"><fmt:message key="formatosunicos.subtitle"/></h3>
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
  	    <label for="camara">CAMARA</label>
  	    <select id="camara" name="camara" class="form-control" required>
  	    	<option value="">Todas las Camaras</option>
	   		<option value="1">1</option>
	   		<option value="2">2</option>
	   	</select>
  	</div>
  </div>
  <div class="bloquedata">
    <div class="form-group">
    	<label>RECIBO DE DEPOSITO(RD)</label>
    	<input type='text' placeholder="Recibo de Deposito" id="consecutivo" name='consecutivo' value='' class="form-control" required>
  	</div>
  	<div class="form-group">
    	<label for="Producto">PRODUCTO</label>
    	<input type='text' path="claveProducto" placeholder="claveProducto" id="claveProducto" name='claveProducto' value='' class="form-control" required>
  	</div>
  </div>
  <div class="bloquedata">
  <div class="form-group">
    <label for="tunel">TUNEL</label>
	<select id="tunel" name="tunel" class="form-control" required>
		<option value="">Selecciona un Tunel</option>
		<option value="12">12</option>
		<!--option value="2">2</option-->
	</select>
  </div>			    	   				
  <div class="form-group">
  	<br/>
	<button class="btn btn-primary btn-small buscaFormatosUnicos" type="button">
			<i class="icon-edit  bigger-125"></i>
			Consulta Inventario
		</button>
  </div>
  </div>
</section>
 <input type='hidden' name='fechaEntrada' value='${fecha}'>
 <input type='hidden' name='horaEntrada' value='${hora}'>
</form:form>
<div id="buscainventario"></div>
</section>
