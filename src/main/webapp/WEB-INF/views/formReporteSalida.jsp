<%--
  Created by IntelliJ IDEA.
  User: jolvera
  Date: 20/08/2014
  Time: 09:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="resources/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="resources/assets/js/chosen.jquery.min.js"></script>
<script src="resources/assets/js/fuelux/fuelux.spinner.min.js"></script>
<script src="resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="resources/assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="resources/assets/js/date-time/moment.min.js"></script>
<script src="resources/assets/js/date-time/daterangepicker.min.js"></script>
<script src="resources/assets/js/bootstrap-colorpicker.min.js"></script>
<script src="resources/assets/js/jquery.knob.min.js"></script>
<script src="resources/assets/js/jquery.autosize-min.js"></script>
<script src="resources/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="resources/assets/js/jquery.maskedinput.min.js"></script>
<script src="resources/assets/js/bootstrap-tag.min.js"></script>


<script>
$(document).ready(function(){
	$('#id-disable-check').on('click', function() {
		var inp = $('#form-input-readonly').get(0);
		if(inp.hasAttribute('disabled')) {
			inp.setAttribute('readonly' , 'true');
			inp.removeAttribute('disabled');
			inp.value="This text field is readonly!";
		}
		else {
			inp.setAttribute('disabled' , 'disabled');
			inp.removeAttribute('readonly');
			inp.value="This text field is disabled!";
		}
	});


	$(".chzn-select").chosen(); 
	
	$('[data-rel=tooltip]').tooltip({container:'body'});
	$('[data-rel=popover]').popover({container:'body'});
	
	$('textarea[class*=autosize]').autosize({append: "\n"});
	$('textarea[class*=limited]').each(function() {
		var limit = parseInt($(this).attr('data-maxlength')) || 100;
		$(this).inputlimiter({
			"limit": limit,
			remText: '%n character%s remaining...',
			limitText: 'max allowed : %n.'
		});
	});
	
	$.mask.definitions['~']='[+-]';
	$('.input-mask-date').mask('99/99/9999');
	$('.input-mask-phone').mask('(999) 999-9999');
	$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
	$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
	
	
	
	$( "#input-size-slider" ).css('width','200px').slider({
		value:1,
		range: "min",
		min: 1,
		max: 6,
		step: 1,
		slide: function( event, ui ) {
			var sizing = ['', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
			var val = parseInt(ui.value);
			$('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
		}
	});

	$( "#input-span-slider" ).slider({
		value:1,
		range: "min",
		min: 1,
		max: 11,
		step: 1,
		slide: function( event, ui ) {
			var val = parseInt(ui.value);
			$('#form-field-5').attr('class', 'span'+val).val('.span'+val).next().attr('class', 'span'+(12-val)).val('.span'+(12-val));
		}
	});
	
	
	$( "#slider-range" ).css('height','200px').slider({
		orientation: "vertical",
		range: true,
		min: 0,
		max: 100,
		values: [ 17, 67 ],
		slide: function( event, ui ) {
			var val = ui.values[$(ui.handle).index()-1]+"";

			if(! ui.handle.firstChild ) {
				$(ui.handle).append("<div class='tooltip right in' style='display:none;left:15px;top:-8px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>");
			}
			$(ui.handle.firstChild).show().children().eq(1).text(val);
		}
	}).find('a').on('blur', function(){
		$(this.firstChild).hide();
	});
	
	$( "#slider-range-max" ).slider({
		range: "max",
		min: 1,
		max: 10,
		value: 2
	});
	
	$( "#eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
		// read initial values from markup and remove that
		var value = parseInt( $( this ).text(), 10 );
		$( this ).empty().slider({
			value: value,
			range: "min",
			animate: true
			
		});
	});

	
	$('#id-input-file-1 , #id-input-file-2').ace_file_input({
		no_file:'No File ...',
		btn_choose:'Choose',
		btn_change:'Change',
		droppable:false,
		onchange:null,
		thumbnail:false //| true | large
		//whitelist:'gif|png|jpg|jpeg'
		//blacklist:'exe|php'
		//onchange:''
		//
	});
	
	$('#id-input-file-3').ace_file_input({
		style:'well',
		btn_choose:'Drop files here or click to choose',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		thumbnail:'small'
		//,icon_remove:null//set null, to hide remove/reset button
		/**,before_change:function(files, dropped) {
			//Check an example below
			//or examples/file-upload.html
			return true;
		}*/
		/**,before_remove : function() {
			return true;
		}*/
		,
		preview_error : function(filename, error_code) {
			//name of the file that failed
			//error_code values
			//1 = 'FILE_LOAD_FAILED',
			//2 = 'IMAGE_LOAD_FAILED',
			//3 = 'THUMBNAIL_FAILED'
			//alert(error_code);
		}

	}).on('change', function(){
		//console.log($(this).data('ace_input_files'));
		//console.log($(this).data('ace_input_method'));
	});
	

	//dynamically change allowed formats by changing before_change callback function
	$('#id-file-format').removeAttr('checked').on('change', function() {
		var before_change
		var btn_choose
		var no_icon
		if(this.checked) {
			btn_choose = "Drop images here or click to choose";
			no_icon = "icon-picture";
			before_change = function(files, dropped) {
				var allowed_files = [];
				for(var i = 0 ; i < files.length; i++) {
					var file = files[i];
					if(typeof file === "string") {
						//IE8 and browsers that don't support File Object
						if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
					}
					else {
						var type = $.trim(file.type);
						if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
								|| ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
							) continue;//not an image so don't keep this file
					}
					
					allowed_files.push(file);
				}
				if(allowed_files.length == 0) return false;

				return allowed_files;
			}
		}
		else {
			btn_choose = "Drop files here or click to choose";
			no_icon = "icon-cloud-upload";
			before_change = function(files, dropped) {
				return files;
			}
		}
		var file_input = $('#id-input-file-3');
		file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
		file_input.ace_file_input('reset_input');
	});




	$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
	.on('change', function(){
		//alert(this.value)
	});
	$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, icon_up:'icon-caret-up', icon_down:'icon-caret-down'});
	$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, icon_up:'icon-plus', icon_down:'icon-minus', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});

	$.fn.datepicker.dates['es'] = {
		    days: ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"],
		    daysShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"],
		    daysMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"],
		    months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
		    monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
		    today: "Hoy",
		    clear: "Limpiar"
		};
	
	$('.date-picker').datepicker({
	    language: 'es'
	});
	
	$('.date-picker').datepicker().next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#id-date-range-picker-1').daterangepicker().prev().on(ace.click_event, function(){
		$(this).next().focus();
	});
	
	$('#timepicker1').timepicker({
		minuteStep: 1,
		showSeconds: true,
		showMeridian: false
	})
	
	$('#colorpicker1').colorpicker();
	$('#simple-colorpicker-1').ace_colorpicker();

	
	$(".knob").knob();
	
	
	//we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
	var tag_input = $('#form-field-tags');
	if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) ) 
		tag_input.tag({placeholder:tag_input.attr('placeholder')});
	else {
		//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
		tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
		//$('#form-field-tags').autosize({append: "\n"});
	}


	/////////
	$('#modal-form input[type=file]').ace_file_input({
		style:'well',
		btn_choose:'Drop files here or click to choose',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		thumbnail:'large'
	})
	
	//chosen plugin inside a modal will have a zero width because the select element is originally hidden
	//and its width cannot be determined.
	//so we set the width after modal is show
	$('#modal-form').on('show', function () {
		$(this).find('.chzn-container').each(function(){
			$(this).find('a:first-child').css('width' , '200px');
			$(this).find('.chzn-drop').css('width' , '210px');
			$(this).find('.chzn-search input').css('width' , '200px');
		});
	})
	/**
	//or you can activate the chosen plugin after modal is shown
	//this way select element has a width now and chosen works as expected
	$('#modal-form').on('shown', function () {
		$(this).find('.modal-chosen').chosen();
	})
	*/
	$(".widget-body").hide();
	$(".otraBusqueda").hide();
	
	$(".widget-toolbar").click(function(){
		//$(".widget-body").hide()
		numerowidget = $(this).attr("numwidget")
		if ($("."+numerowidget).is(':hidden')){
			$(".widget-body").hide()
			$("."+numerowidget).show();
		}else{
			$("."+numerowidget).hide();
		}
			
	});
	
	$(".otraBusqueda").click(function(){
		$(".menubusquedas").toggle();
		$("#resultadoBusca").html("");
		$(".otraBusqueda").toggle();
	});

	$(".busca").click(function(){
		$(".menubusquedas").toggle();
		$("#resultadoBusca").html("");
		$(".otraBusqueda").show();
		urlenvio= $(this).attr("urlenvio");
		frmname= $(this).attr("frmname");
		   var options = { 
			        target:    '#resultadoBusca',   // target element(s) to be updated with server response
			        url:       urlenvio,
			        beforeSubmit:  showRequest,  // pre-submit callback 
			        success:       showResponse,  // post-submit callback 
			    }; 
			 
			    // bind form using 'ajaxForm' 
			    $("#"+frmname).ajaxForm(options);
			    $("#"+frmname).submit();
		   return false;
	   });
	
	   showRequest = function(){
		  $("#resultadoBusca").css("color","#000");
		  $("#resultadoBusca").css("font-size","35px");  
	      $('#resultadoBusca').html("Consultando...");  
	   };
	   
	   showResponse = function(){
		   $("#resultadoBusca").css("font-size","14px"); 
	   };
	
});
</script>
<div id="container" class="menubusquedas">
<section id="reportesprincipalpantalla">
    <h1 id="item_name">Reportes de salidas</h1>
    <h3 id="item_name">(Salidas de producto)</h3>	
    <hr>
    
    <div class="row-fluid">
		<div class="span4">
			<div class="widget-box" >
				<div class="widget-header">
					<h4>Total Salidas Por D&iacute;a</h4>
					<span class="widget-toolbar"  numwidget="1">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 1">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorDia.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Total Salidas Por Fechas</h4>
					<span class="widget-toolbar" numwidget="2">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 2">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorFechas.jsp" %>
						</div>
					</div>
				</div>
			</div>	
		</div>
		
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Total Salidas Por Fechas y Cliente</h4>
					<span class="widget-toolbar" numwidget="3">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 3">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorFechasCliente.jsp" %>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</div>
	<div class="row-fluid">	
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Total Detalle de Salidas por Fechas y Cliente</h4>
					<span class="widget-toolbar" numwidget="4">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 4">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorFechasClienteDetalle.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Reporte Salidas Por Camara</h4>
					<span class="widget-toolbar" numwidget="5">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 5">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorCamara.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Arrastre de Saldos</h4>
					<span class="widget-toolbar" numwidget="6">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 6">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaArrastre.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Total Salidas por Fechas Concentrado</h4>
					<span class="widget-toolbar" numwidget="7">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 7">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorFechasConcentrado.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Consulta por folio de Salida</h4>
					<span class="widget-toolbar" numwidget="8">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 8">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaFolio.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="span4">
			<div class="widget-box">
				<div class="widget-header">
					<h4>Salidas BACHOCO</h4>
					<span class="widget-toolbar" numwidget="9">
						<a href="#" data-action="collapse">
							<i class="icon-chevron-up"></i>
						</a>
					</span>
				</div>
				<div class="widget-body 9">
					<div style="display: block;" class="widget-body-inner">
						<div class="widget-main">
							<%@ include file="salidaPorFechasClienteBachoco.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



  <!--inline scripts related to this page-->
 <br>   
 </section>
</div>
<a class="btn btn-primary btn-small otraBusqueda" href="#" >
	Otra busqueda
	<i class="icon-exchange"></i>	
</a>
<br><br>
<div id="resultadoBusca">
</div>
<div id="bootbox-options"></div>
				