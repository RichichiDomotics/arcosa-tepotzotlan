$(document).ready(function(){
	$("#idTipoRecibo").change(function(){
		entrada = $("#idTipoRecibo option:selected").html();
		validaClienteSalida();
		if($.trim(entrada)=="ENTRADA"){
			$('#idManiobra').children('option[value="1002"]').attr('selected','selected');
			$(".solosalidas").hide();
		}else if($.trim(entrada)=="SALIDA"){
			$('#idManiobra').children('option[value="1001"]').attr('selected','selected');
			$(".solosalidas").show();
		}else{
			$('#idManiobra').children('option[value=""]').attr('selected','selected');
		}
	});
	
	$("#idPlanta").change(function(){
		planta = $("#idPlanta option:selected").html();
		plantavar="";
		switch($.trim(planta)){
			case "PLANTA 1":
				plantavar = "P1";
			break;
			case "PLANTA 2":
				plantavar = "P2";
			break;
		}

		$.ajax("getcbopuertas", {
 		   "type": "POST", //usualmente post o get
 		   "success": function(result) {
 			   $("#cboPuerta").html(result);
 		   },
 		   "error": function(result) {
 		     showAlert("Error al recuperar la información", result);
 		   },
 		   "data": {Planta : plantavar},
 		   "async": true
 		});
	});
	
	$("#idCliente").change(function(){
		validaClienteSalida();
	});
	

	
	$("#formEntrada").submit(function(){
		$("#idManiobra").removeAttr('disabled');
	});
	validaClienteSalida = function(){
		idCliente = $("#idCliente").val();
		var valortiporecibo = $("#idTipoRecibo option:selected").html();
		if(idCliente!=""){
			$.ajax("autorizaSalida", {
				"type": "POST", //usualmente post o get
				"success": function(result) {
					if($.trim(result)=="DENEGADA" && $.trim(valortiporecibo)=="SALIDA"){
						$(".btnregistradatos").attr('disabled', true);
						$("#alertaSalida").html("Este cliente tiene DENEGADAS las salidas.");
						$("#alertaSalida").css("width,350px;");
						$("#alertaSalida").css("color","red");
						$("#reg").attr("disabled","disabled");
					}else{
						$(".btnregistradatos").attr('disabled', false);
						$("#alertaSalida").html("");
					}
				},
				"error": function(result) {
					showAlert("Error al consultar la información", result);
				},
				"data": {idCliente : idCliente},
				"async": true
			});
		}else return false;
	};
	
	//REGISTRO DE FORMA DE ENTRADAS (DETALLE)
	/*$(".registradetalle").click(function(){
		alert("valores");
		temperaturathermoking = $("#THERMOKING").val();
		idCliente = $("#idCliente").val();
		tempAnden = $("#TEMPERATURA_ANDEN").val();
		tunel = $("#TUNEL").val();
		renglon = $("#RENGLON").val();
		producto = $("#PRODUCTO").val();
		lote = $("#LOTE").val();
		cantidad = $("#CANTIDAD").val();
		caducidad = $("#CADUCIDAD").val();
		pesou = $("#PESOU").val();
		embalaje = $("#EMBALAJE").val();
		marca = $("#MARCA").val();
		bultos = $("#BULTOS").val();
		
		$.ajax("insDetalle", {
 		   "type": "POST", //usualmente post o get
 		   "success": function(result) {
 			   $(".container").html(result);
 		   },
 		   "error": function(result) {
 		     showAlert("Error al recuperar la información", result);
 		   },
 		   "data": {idRegistroEntrada: 1,thermoking : temperaturathermoking, idCliente : idCliente, temperaturaAnden : tempAnden, tunel : tunel,
 			   		renglon : renglon, producto : producto, lote : lote, cantidad : cantidad, caducidad : caducidad, pesou : pesou,
 			   		embalaje : embalaje, marca : marca, bultos : bultos	},
 		   "async": true
 		});
		
		return false;
	});*/
	
	/*vedetalles = function(){
		$.ajax("veDetallesRd", {
	 		   "type": "GET", //usualmente post o get
	 		   "success": function(result) {
	 			   $("#consultadetalle").html(result);
	 		   },
	 		   "error": function(result) {
	 		     showAlert("Error al recuperar la información", result);
	 		   },
	 		   "data": {},
	 		   "async": true
	 		});
	};
	
	vedetalles();*/
	
	
	
	$(".registradetallecierre").click(function(){
		//alert("registra detalle cierre");
		/*temperaturathermoking = $("#THERMOKING").val();
		idCliente = $("#idCliente").val();
		tempAnden = $("#TEMPERATURA_ANDEN").val();
		tunel = $("#TUNEL").val();
		renglon = $("#RENGLON").val();
		producto = $("#PRODUCTO").val();
		lote = $("#LOTE").val();
		cantidad = $("#CANTIDAD").val();
		caducidad = $("#CADUCIDAD").val();
		pesou = $("#PESOU").val();
		embalaje = $("#EMBALAJE").val();
		marca = $("#MARCA").val();
		bultos = $("#BULTOS").val();
		
		$.ajax("insDetalle", {
 		   "type": "POST", //usualmente post o get
 		   "success": function(result) {
 			   $(".container").html(result);
 		   },
 		   "error": function(result) {
 		     showAlert("Error al recuperar la información", result);
 		   },
 		   "data": {idRegistroEntrada: 1,THERMOKING : temperaturathermoking, ID_CLIENTE : idCliente, TEMPERATURA_ANDEN : tempAnden, TUNEL : tunel,
 			   		RENGLON : renglon, PRODUCTO : producto, LOTE : lote, CANTIDAD : cantidad, CADUCIDAD : caducidad, PESOU : pesou,
 			   		EMBALAJE : embalaje, MARCA : marca, BULTOS : bultos	},
 		   "async": true
 		});*/
		
		return false;
	});
});
