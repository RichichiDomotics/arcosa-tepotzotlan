$(document).ready(function(){
	$(".vedetalle").click(function(){
		idConsulta = $(this).attr("idconsulta");
		$.ajax("alm_consultaIngresos", {
	 		   "type": "POST", //usualmente post o get
	 		   "success": function(result) {
	 			   $(".container").html(result);
	 		   },
	 		   "error": function(result) {
	 		     alert("Error al realizar la consulta de detalle :" + result);
	 		   },
	 		   "data": {idConsulta : idConsulta},
	 		   "async": true
	 	});
		return false;
	});
	
	$(".capturaIngreso").click(function(){
		alert($(this).attr("idConsulta"));
		idConsulta = $(this).attr("idConsulta");
		idCliente = $(this).attr("idCliente");
		alert("idCliente " + idCliente);
		cliente = $(this).attr("cliente");
		chofer = $(this).attr("chofer");
		$.ajax("capturaIngresos", {
	 		   "type": "POST", //usualmente post o get
	 		   "success": function(result) {
	 			   $(".container").html(result);
	 		   },
	 		   "error": function(result) {
	 		     alert("Error al realizar la consulta de detalle :" + result);
	 		   },
	 		   "data": {idConsulta : idConsulta, cliente : cliente, chofer : chofer, idCliente : idCliente},
	 		   "async": true
	 	});
		return false;
	});
});
