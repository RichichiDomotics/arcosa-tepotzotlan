$(document).ready(function(){
	  var x = 0;
	 
	  function progreso(x){
		     $("#consultadetallesRd").css("color","#000");
		     $("#consultadetallesRd").html('Cargando...'+'<div class="progress progress-striped active"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="'+x+'" aria-valuemin="0" aria-valuemax="100" style="width: '+x+'%"><span class="sr-only">40% Complete (success)</span></div></div>');
	  }

	
	$(".buscasalidas").click(function(){
		temperaturathermoking = $("#THERMOKING").val();
		idCliente = $("#idCliente").val();
		numeroRd = $("#numeroRd").val();
		producto = $("#producto").val();
		caducidad = $("#caducidad").val();
		entrgadoA = $("#entregado").val();
		var timeoutid = "";
		$.ajax("consultaSalida", {
 		   "type": "POST", //usualmente post o get
 		  "beforeSend": function() {
 			 //var timeoutId = setTimeout("alert('Pasaron 2 segundos desde el click');",100);
 			 //x = x + 20;
 			 //timeoutid = setTimeout(progreso(20), 800000);
 			 //clearTimeout(timeoutid);
 			 //timeoutid = setTimeout(progreso(40), 100000);
			 //clearTimeout(timeoutid);
			 /*timeoutid = setTimeout(progreso(60), 100000);
			 clearTimeout(timeoutid);
			 timeoutid = setTimeout(progreso(80), 100000);
			 clearTimeout(timeoutid);*/
 			 
 			   $("#consultadetallesRd").css("color","#000");
 			   $("#consultadetallesRd").css("font-size","45px");
 			   /*var x=1;
 			   for(x=1;x<101;x++) {
 				  setInterval(progreso(x),600000);

 				 // $("#consultadetallesRd").html('Cargando ...\n'+'<div class="progress progress-striped"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%"><span class="sr-only">40% Complete (success)</span></div></div>');
 				  //if(x==100) x=1;
 			  }*/
 			   $("#consultadetallesRd").html('Cargando ...');
 		   },
 		  "success": function(result) {
 		       $("#consultadetallesRd").css("font-size","12px");
			   $("#consultadetallesRd").html(result);
		   },
 		   "error": function(result) {
 		     alert("Error al recuperar la información", result);
 		     //showAlert("Error al recuperar la información", result);
 		   },
 		   "data": { numeroRd: numeroRd,idCliente : idCliente, producto : producto, caducidad : caducidad, entregado : entrgadoA},
 		   "async": true
 		});
		
		return false;
	});
	
	$(".RealizaSalida").click(function(){
		var list = new Array();
		entregadoA = $("#entregado").val();
		alert(entrgadoA);
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
		 		   "data": { listaids: list, entregado:entregadoA},
		 		   "async": true
		 		});
		}	
		return false;
		
	});
	
	
});
