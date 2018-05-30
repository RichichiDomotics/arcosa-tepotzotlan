$(document).ready(function(){
	  var x = 0;
	 
	  function progreso(x){
		     $("#consultadetallesRd").css("color","#000");
		     $("#consultadetallesRd").html('Cargando...'+'<div class="progress progress-striped active"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="'+x+'" aria-valuemin="0" aria-valuemax="100" style="width: '+x+'%"><span class="sr-only">40% Complete (success)</span></div></div>');
	  }

	
	$(".buscaentradasImp").click(function(){
		temperaturathermoking = $("#THERMOKING").val();
		//idCliente = $("#idCliente").val();
		numeroRd = $("#numeroRd").val();
		//producto = $("#producto").val();
		//caducidad = $("#caducidad").val();
		var timeoutid = "";
		$.ajax("consultaEntradaImpresion", {
 		   "type": "POST", //usualmente post o get
 		  "beforeSend": function() {
 			   $("#consultadetallesRd").css("color","#000");
 			   $("#consultadetallesRd").css("font-size","45px");
 			   $("#consultadetallesRd").html('Cargando ...');
 		   },
 		  "success": function(result) {
 		       $("#consultadetallesRd").css("font-size","14px");
			   $("#consultadetallesRd").html(result);
		   },
 		   "error": function(result) {
 		     alert("Error al recuperar la información", result);
 		     //showAlert("Error al recuperar la información", result);
 		   },
 		   "data": { numeroRd: numeroRd},
 		   "async": true
 		});
		
		return false;
	});
	
	$(".RealizaSalida").click(function(){
		var list = new Array();
		$("input[type=checkbox]:checked").each(function(){
			//cada elemento seleccionado
			list.push($(this).val());
			console.log("*****************"+list);
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
		 		   "success": function(result) {
		 			   //$("#consultadetallesRd").html(result);
		 		   },
		 		   "error": function(result) {
		 		     alert("Error al realizar la salida", result);
		 		     //showAlert("Error al recuperar la información", result);
		 		   },
		 		   "data": { listaids: list},
		 		   "async": true
		 		});
		}	
		return false;
		
	});
	
	
});
