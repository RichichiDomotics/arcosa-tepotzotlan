$(document).ready(function(){
	var UserAvailable;
	
	$(".botonregistrate").click(function(){
		preregistrar();
	});
	
	$(".iralogin").click(function(){
		loginconfirma();
	});
    
		jQuery.validator.setDefaults({
			debug: true,
			success: "valid"
			});
		
	
	preregistrar = function(){
		if(location.hash!="#preregistro")
	   		location.hash ="#preregistro";
	   $.ajax({dataType:"html", 
			beforeSend: function(jqXHR,settings){
				$("#jumbotron").html("");
				loadingpage($("#jumbotron"));
			},
			success:function (data, textStatus){
				$("#jumbotron").html(data);
			}, 
			done:function (data, textStatus, jqXHR){
 				alert("hecho"+textStatus);
			},
			error:function (data, textStatus, jqXHR){
 				alert("error"+data);
			},
			url:"../../users/preregistrousuario"
		});
	};
	
	reglas_validacion = function(form){
		form.validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				usuario: "required",
				contrasena: {
					required: true,
					minlength:8
				},
				contrasena_confirma: {
					required: true,
					equalTo: "#contrasena"
				}
			},
			messages: {
				email: {
						required: "Por favor ingresa una direcci&oacute;n de correo.",
						email:"Ingresa una direcci&oacute;n de correo valida."
				},
				usuario: "Por favor ingresa tu usuario",
				contrasena: {
					required: "Por favor ingresa una contrase&ntilde;a.",
					minlength:"Como minimo 8 caracteres."
				},
				contrasena_confirma: {
					required: "Confirma la contrase&ntilde;a.",
					equalTo:"Debe ser igual a la contrase&ntilde;a."
				}
			}
		});
	};

	registrandoforma = function(form){
		tipo_usuario = $("#tipo_usuario").val();
		email = $("#email").val();
		usuario = $("#usuario").val();
		contrasena = $("#contrasena").val();
		reglas_validacion(form);
		  form.validate();
		  if(form.valid()){
			  resultado = validausuarioexiste(usuario);
			  if(UserAvailable=='fail'){
				  return false;
			  }
			  continuaconregistro(tipo_usuario,email,usuario,contrasena);
		  }
	};
	
	validausuarioexiste = function(usuario){
		//usuario = $("usuario").val();
		$.ajax({
			 type: "POST",
			 async:false,
			 url: "..\/..\/users\/validausuarioexiste",
			 data: { usuario : usuario },
			 dataType: 'json',
			 success: function(data) {
				    UserAvailable = data.Status;//$data_= eval($data);
				    if(UserAvailable == 'fail'){
		    			//console.log(UserAvailable);
		    			$("#usuario").after("<label for='usuario' class='error'>Este usuario ya existe. Ingresar otro</label>");
		    			return false;
		    		}
	            }
		});
	};
	
	continuaconregistro = function(tipo_usuario,email,usuario,contrasena){
		$.ajax({
			 type: "POST",
			 async:true,
			 url: "..\/..\/users\/registrandopreregistro",
			 data: { tipo_usuario : tipo_usuario, email : email, usuario : usuario, contrasena : contrasena },
			 success: function(data) {
				 $("#jumbotron").html(data);
	         },
			 beforeSend: function(){
		    	 
			 }
		});
	};
	
	loginconfirma = function(){
		$.ajax({
			 type: "POST",
			 async:true,
			 url: "..\/..\/users\/login",
			 data: { },
			 success: function(data) {
				 $("#jumbotron").html(data);
	         },
			 beforeSend: function(){
		    	 
			 }
		});
	};
	
	function loadingpage(divLoad){
		divLoad.html('<img src="/img/ajax-loader.gif" border="0" style="position:absolute;left:50%" /><br><br><br><br><p style="position:absolute;left:50%;">CARGANDO...</p><br>');	
	}
	
	newHash = window.location.hash.substring(0);
	switch(newHash){
					case "#preregistro": preregistrar();break;
				}

	$(window).hashchange();
});
