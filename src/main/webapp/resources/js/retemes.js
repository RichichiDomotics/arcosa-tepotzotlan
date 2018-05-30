$(document).ready(function(){
	$("#ID_CLIENTE").change(function(){
		idCliente = $("#ID_CLIENTE").val();
		$.ajax("cboServicios", {
	 		   "type": "POST", 
	 		  "beforeSend": function() {
	 			   $("#comboServicios").css("font-size","0.875em");
	 			   $("#comboServicios").html('Cargando ...');
	 		   },
	 		  "success": function(result){
	 		       $("#comboServicios").css("font-size","0.875em");
				   $("#comboServicios").html(result);
				   return false;
			   },
	 		   "error": function(result) {
	 		     alert("Error al recuperar la información", result);
	 		   },
	 		   "data": { idCliente : idCliente },
	 		   "async": true
	 		});
	});
	
	$(".insertaRetemes").click(function(){
		ID_CLIENTE = $("#ID_CLIENTE").val();
		FECHA = $("#FECHA").val();
		FOLIORETEME = $("#FOLIORETEME").val();
		CONSECUTIVO = $("#CONSECUTIVO").val();
		REFEREN = $("#REFEREN").val();
		CAMARA = $("#CAMARA").val();
		FIRMA_RETEME = $("#FIRMA_RETEME").val();
		TIPOSERVICIO = $("#TIPOSERVICIO").val();
		FIRMA_RETEME = $("#FIRMA_RETEME").val();
		KILOS = $("#KILOS").val();
		ALTURA = $("#ALTURA").val();
		OBSERV = $("#OBSERV").val();


		idCliente = $("#ID_CLIENTE").val();
		$.ajax("insertaReteme", {
			"type": "POST",
			"beforeSend": function() {
				$("#buscaretemes").css("font-size","0.875em");
				$("#buscaretemes").html('Cargando ...');
			},
			"success": function(result){
				$("#buscaretemes").css("font-size","0.875em");
				$("#buscaretemes").html(result);
				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
			},
			"data": { ID_CLIENTE:ID_CLIENTE, FECHA:FECHA, CONSECUTIVO:CONSECUTIVO, REFEREN:REFEREN, CAMARA:CAMARA, FIRMA_RETEME:FIRMA_RETEME, TIPOSERVICIO:TIPOSERVICIO, ALTURA:ALTURA, OBSERV:OBSERV, KILOS:KILOS, FOLIORETEME:FOLIORETEME },
			"async": true
		});
	});

	$(".insertaRetemesTIF").click(function(){
		ID_CLIENTE = $("#ID_CLIENTE").val();
		FECHA = $("#FECHA").val();
		FOLIORETEME = $("#FOLIORETEME").val();
		CONSECUTIVO = $("#CONSECUTIVO").val();
		REFEREN = $("#REFEREN").val();
		CAMARA = $("#CAMARA").val();
		FIRMA_RETEME = $("#FIRMA_RETEME").val();
		TIPOSERVICIO = $("#TIPOSERVICIO").val();
		FIRMA_RETEME = $("#FIRMA_RETEME").val();
		KILOS = $("#KILOS").val();
		ALTURA = $("#ALTURA").val();
		OBSERV = $("#OBSERV").val();


		idCliente = $("#ID_CLIENTE").val();
		$.ajax("insertaRetemeTIF", {
			"type": "POST",
			"beforeSend": function() {
				$("#buscaretemes").css("font-size","0.875em");
				$("#buscaretemes").html('Cargando ...');
			},
			"success": function(result){
				$("#buscaretemes").css("font-size","0.875em");
				$("#buscaretemes").html(result);
				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
			},
			"data": { ID_CLIENTE:ID_CLIENTE, FECHA:FECHA, CONSECUTIVO:CONSECUTIVO, REFEREN:REFEREN, CAMARA:CAMARA, FIRMA_RETEME:FIRMA_RETEME, TIPOSERVICIO:TIPOSERVICIO, ALTURA:ALTURA, OBSERV:OBSERV, KILOS:KILOS, FOLIORETEME:FOLIORETEME },
			"async": true
		});
	});
	$(".consultaRetemes").click(function(){
		idCliente = $("#ID_CLIENTEC").val();
		//fecha = $("#FECHAenvio").val();
		fecini = $("#fecini").val();
		fecfin = $("#fecfin").val();
		folioReteme = $("#FOLIORETEME").val();
		consecutivo= $("#consecutivo").val();
		//servicio = $("#TIPOSERVICIO").val();
		/*if(fecini==""){
			alert("Favor de ingresar una fecha de inicio");
			$("#fecini").focus();
			return false;
	 	}*/

		/*if(fecfin==""){
			alert("Favor de ingresar una fecha final");
			$("#fecfin").focus();
			return false;
		}*/

		$.ajax("getRetemes", {
	 		   "type": "POST", 
	 		  "beforeSend": function() {
	 			   $("#buscaretemes").css("font-size","0.875em");
	 			   $("#buscaretemes").html('Cargando ...');
	 		   },
	 		  "success": function(result){
	 		       $("#buscaretemes").css("font-size","0.875em");
				   $("#buscaretemes").html(result);
				   return false;
			   },
	 		   "error": function(result) {
	 		     alert("Error al recuperar la información", result);
	 		   },
	 		   "data": { idCliente:idCliente , fecini:fecini, fecfin:fecfin , folioReteme:folioReteme,consecutivo:consecutivo},
	 		   "async": true
	 		});
	});
	
	
	$(".finRetemes").click(function(){
		window.location.href = "./terminaCapturaReteme";
	});

	$(".finRetemesTIF").click(function(){
		window.location.href = "./terminaCapturaRetemeTIF";
	});
	
	
});
