<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
$(document).ready(function(){
 $(".RealizaSalida").click(function(){
	//var list = new Array();
	idCliente = $("#idCliente").val();
	numeroRd = $("#numeroRd").val();
	producto = $("#producto").val();
	caducidad = $("#caducidad").val();
	var list = "";
	var listacantidad = "";
	$("#selecttodo").prop('checked', false);
	$("input[type=checkbox]:checked").each(function(){
		//cada elemento seleccionado
		//list.push($(this).val());
		list=list+","+$(this).val();
		listacantidad = listacantidad+","+$("."+$(this).val()).val();
		console.log("*****************"+listacantidad);
	});	
	if(list.length==0){
		alert("Favor de seleccionar una salida.");
		return false;
	}else{
		/*for ( var i = 0; i < list.length; i = i + 1 ) {
			alert(list[i]);
			console.log( list[i] );
		}*/
		//HACE EL LLAMADO DE AJAX PARA ENVIAR LOS IDS DE LOS REGISTROS DE DETALLE RD QUE SERAN ANEXADOS A LA SALIDA
		$.ajax("realizaSalida", {
	 		   "type": "POST", //usualmente post o get
	 		  "beforeSend": function() {
	  			 $("#consultadetallesRd").css("color","#000");
	  			 $("#consultadetallesRd").css("font-size","45px");
	  			 $("#consultadetallesRd").html('Procesando Salida ...');
	  		   },
	 		   "success": function(result) {
	 			   $("#consultadetallesRd").css("font-size","0.875em");
	 			   $("#consultadetallesRd").html(result);
	 		   },
	 		   "error": function(result) {
	 		     alert("Error al realizar la salida", result);
	 		     //showAlert("Error al recuperar la informaci�n", result);
	 		   },
	 		   "data": { listaids: list, listacantidad: listacantidad, idCliente:idCliente, numeroRd:numeroRd, producto:producto, caducidad:caducidad},
	 		   "async": true
	 		});
	}	
	return false;
	
 });
 
 $(".campoInventario").change(function(){
	var nombreIdCampo = $(this).attr("campo");
	var valorFijo = $("#"+nombreIdCampo).val();
	var valorcambiado = $(this).val();
	if(parseFloat(valorcambiado)>parseFloat(valorFijo)){
		alert("El valor no puede ser mayor que lo existente en inventario");
		$(this).val(valorFijo);
		$(this).focus();
		return false;
	}
 });
 
 $(".terminasalida").click(function(){
	 idCliente = $("#idCliente").val();
	 $.ajax("terminaSalida", {
		   "type": "POST", //usualmente post o get
		  "beforeSend": function() {
			 $("#terminandoSalida").css("color","#000");
			 $("#terminandoSalida").css("font-size","45px");
			 $("#terminandoSalida").html('Terminando Salida ...');
		   },
		   "success": function(result) {
			  /* $("#consultadetallesRd").css("font-size","0.875em");
			   $("#consultadetallesRd").html("");*/
			   var bandera=$("#bandera").val();
			   alert("La salida ha sido concluida");
			   $(location).attr('href','/almacen/formRetemes?bandera='+bandera);
		   },
		   "error": function(result) {
		    alert("Error al Terminar la salida", result);
		    $(location).attr('href','alm_consultaSalidas');
		   },
		   "data": { idCliente: idCliente},
		   "async": true
		}); 
 });
});
</script>
<input type="hidden" path="bandera" name="bandera" id="bandera" value="1"/>
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
	<c:forEach items="${detalle}" var="e">
     <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
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
    </tbody>
    <c:set var="contador" value="${contador+1}" />
    </c:forEach>
       <tr>
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

    </tr>
  </table>
  <div id="terminandoSalida"></div>

