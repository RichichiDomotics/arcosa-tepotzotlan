$(document).ready(function(){
	$(".VeSeguimientosBtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		veCapturaSeguimiento(idProspecto);
	});


	veCapturaSeguimiento = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("ListaSeguimiento", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				bootbox.hideAll();
				$("#CapSeg").css("font-size","12px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"1000px","heith":"auto","left":"450px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": false
		});


	};



	$(".convierteClienteAutorizabtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		consultaProspectoId = $(this).attr("consultaProspectoId");
		if(confirm("\u00BFDesea confirmar la autorizacion?")){
			agregarClienteAutoriza(idProspecto,consultaProspectoId);
		}else{
			return false;
		}

	});
	$(".rechazarProspecto").click(function(){
		idProspecto = $(this).attr("idProspecto2");
		claveEjecutivo = $("#claveEjecutivo").val();
		consultaProspectoId = $(this).attr("consultaProspectoId2");
		if(confirm("\u00BFDesea rechazar este prospecto?")){
			rechazarProspecto(idProspecto,consultaProspectoId);
		}else{
			return false;
		}

		//bootbox.alert("Acci\u00F3n Completada");
	});


	rechazarProspecto = function(idProspecto,consultaProspectoId){
		$.ajax("rechazaProspecto", {
			"type": "POST",
			/*"beforeSend": function() {
			 //$("#loader").html('<div><img src="resources/img/loader.GIF"/></div>');
			 bootbox.dialog($("#CapSeg").html('<div align="center"><img src="resources/img/loader.GIF"/></div>')
			 ).css({"width":"600","heith":"480","left":"550px","overflow-y": "hidden"});
			 },*/
			"success": function(result){
				//$("#buscaProspectodiv").css("font-size","14px");
				//$("#buscaProspectodiv").html(result);
				bootbox.alert("Rechazo Realizado",function(){
					veCapturaMotivo(idProspecto);
				});
				//return false;
			},
			"error": function(result) {
				bootbox.alert("Error al recuperar la información");
				//showAlert("Error al recuperar la información", result);
			},
			"data": {id:idProspecto,consultaProspectoId:consultaProspectoId},
			"async": true
		});
	}

	agregarClienteAutoriza = function(idProspecto,razonSocial){
		$.ajax("autorizaProspecto", {
			"type": "POST",
			"success": function(result){
				bootbox.alert("Autorizacion Completada",function(){
					window.location.href=window.location;
				});
				//return false;
			},
			"error": function(result) {
				bootbox.alert("Error al recuperar la información");
			},
			"data": {id:idProspecto,razonSocial:razonSocial},
			"async": true
		});
	}

	veCapturaMotivo = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("CapturaMotivoJefe", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"800px","heith":"360px","left":"550px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});


	};


	$(".convierteReAutorizadobtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		consultaProspectoId = $(this).attr("consultaProspectoId");
		if(confirm("\u00BFDesea confirmar la Re-autorizacion?")){
			agregarReAutorizado(idProspecto,consultaProspectoId);
		}else{
			return false;
		}

	});
	$(".rechazarProspectoReAutorizado").click(function(){
		idProspecto = $(this).attr("idProspecto2");
		claveEjecutivo = $("#claveEjecutivo").val();
		consultaProspectoId = $(this).attr("consultaProspectoId2");
		if(confirm("\u00BFDesea rechazar este prospecto?")){
			rechazarProspectoReAutoriza(idProspecto,consultaProspectoId);
		}else{
			return false;
		}
	});


	rechazarProspectoReAutoriza = function(idProspecto,consultaProspectoId){
		$.ajax("rechazaProspecto", {
			"type": "POST",

			"success": function(result){

				bootbox.alert("Autorizacion Completada",function(){
					veCapturaMotivoReAutoriza(idProspecto);
				});
			},
			"error": function(result) {
				bootbox.alert("Error al recuperar la información");
			},
			"data": {id:idProspecto,consultaProspectoId:consultaProspectoId},
			"async": true
		});
	}

	agregarReAutorizado = function(idProspecto,razonSocial){
		$.ajax("autorizaProspecto", {
			"type": "POST",

			"success": function(result){

				bootbox.alert("Autorizacion Completada",function(){
					window.location.href=window.location;
				});
				//return false;
			},
			"error": function(result) {
				bootbox.alert("Error al recuperar la información");
			},
			"data": {id:idProspecto,razonSocial:razonSocial},
			"async": true
		});
	}

	veCapturaMotivoReAutoriza = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("CapturaMotivoJefe", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"800px","heith":"360px","left":"550px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});


	};



	$(".convierteSeguimientoClientebtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		veCapturaSeguimientoEj(idProspecto);
	});


	veCapturaSeguimientoEj = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("CapturaSeguimiento", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"500px","heith":"360px","left":"700px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});


	};

	$(".convierteReactivarbtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		veCapturaReactivarMotivo(idProspecto);
	});


	veCapturaReactivarMotivo = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("CapturaMotivo", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"800px","heith":"360px","left":"550px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});


	};

	$(".convierteClienteSegbtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		veCapturaSeguimientoEjecutivo(idProspecto);
	});


	veCapturaSeguimientoEjecutivo = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("ListaSeguimiento", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"1000px","heith":"auto","left":"450px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});


	};

	$(".desasignar").click(function(){
		idProspecto = $(this).attr("idProspecto");

		bootbox.dialog("Esta seguro de quitar la asignación?", [{
				"label": "Continuar Desasignación!",
				"class": "btn-small btn-success",
				"callback": function () {
					urlenvio = $(".registraValida").attr("urlenvio");
					frmname = $(".registraValida").attr("frmname");
					continuaDesasigna(idProspecto);
				}
			},
			{
				"label": "Cancelar Desasignación!",
				"class": "btn-small btn-danger",
				"callback": function () {
						bootbox.hideAll();
				}
			}]
		);
	});

	continuaDesasigna = function(idProspecto){
		$.ajax("desasignaProspecto", {
			"type": "POST",
			/*"beforeSend": function() {
			 //$("#loader").html('<div><img src="resources/img/loader.GIF"/></div>');
			 bootbox.dialog($("#CapSeg").html('<div align="center"><img src="resources/img/loader.GIF"/></div>')
			 ).css({"width":"600","heith":"480","left":"550px","overflow-y": "hidden"});
			 },*/
			"success": function(result){
				//$("#buscaProspectodiv").css("font-size","14px");
				//$("#buscaProspectodiv").html(result);
				bootbox.alert("Desasignación Completada",function(){
					//Aqui debe refrescar el listado ya no debe aparecer la desasignada
					window.location.href=window.location;
				});
			},
			"error": function(result) {
				bootbox.alert("Error al recuperar la información");
				//showAlert("Error al recuperar la información", result);
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});

	}


	$(".convierteReAsingnarbtn").click(function(){
		idProspecto = $(this).attr("idProspecto");
		veCapturaReAsignacion(idProspecto);
	});


	veCapturaReAsignacion = function(idProspecto){
		if(!$("#CapSeg").length){
			$("#supdiv").append('<div id="CapSeg"></div>')
		}
		$.ajax("CapturaReAsignacion", {
			"type": "POST",
			"beforeSend": function() {
				$("#CapSeg").css("font-size","27px");
				$("#CapSeg").html('Cargando ...');
			},
			"success": function(result){
				$("#CapSeg").css("font-size","14px");
				//$("#detCliente").html(result);
				bootbox.dialog($("#CapSeg").html(result), [{
						"label" : "Cerrar",
						"class" : "btn-small btn-primary",
						"callback": function() {
							$("#CapSeg").html("");
						}
					}]
				).css({"width":"800px","heith":"360px","left":"550px","overflow-y": "hidden"});

				return false;
			},
			"error": function(result) {
				alert("Error al recuperar la información", result);
				$("#CapSeg").html("");
			},
			"data": {idProspecto:idProspecto},
			"async": true
		});


	};


});
