	<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />

<script>
 $(document).ready(function(){
     
   $(".limpiaCampo").click(function(){
	   limpiaCampos();  
   });

   $(".enviaregistrodetalle").click(function(){
	   //alert("cache el submit");

		   var options = {
					target:    '#tablaresultadodetalle',   // target element(s) to be updated with server response
					url:       'insDetalle',
					beforeSubmit:  showRequest,  // pre-submit callback
					success:       showResponse,  // post-submit callback

					// other available options:
					//url:       url         // override for form's 'action' attribute
					//type:      type        // 'get' or 'post', override for form's 'method' attribute
					//dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
					//clearForm: false,        // clear all form fields after successful submit
					//resetForm: false        // reset the form after successful submit

					// $.ajax options can be used here too, for example:
					//timeout:   3000
				};

				// bind form using 'ajaxForm'
				$('#detallesRd').ajaxForm(options);
				$('#detallesRd').submit();

	   return false;
   });
   
   showRequest = function(){
	  $("#tablaresultadodetalle").css("color","#000");
	  $("#tablaresultadodetalle").css("font-size","35px");  
      $('#tablaresultadodetalle').html("Registrando Detalle...<br><br><br>");  
   };
   
   showResponse = function(){
	   $("#tablaresultadodetalle").css("font-size","14px"); 
   };
   
   limpiaCampos = function(){
	   $("#PRODUCTO").val("");
	   $("#LOTE").val("");
	   $("#CANTIDAD").val("");
	   $("#CADUCIDAD").val("");
	   $("#PESOU").val("");
	   $("#EMBALAJE").val("");
	   $("#MARCA").val("");
	   $("#BULTOS").val("");
	   $("#PRODUCTO").focus();
   }
   
   $("#tunelsino").change(function(){
	  var tunelsinovar = $("#tunelsino").val();
	   if(tunelsinovar=="SI"){
		   /*$("#tunelocamara").html('<label for="tunel">TUNEL</label>'+
			    					'<select id="tunel" name="tunel" class="form-control" required>'+
			    	   				'<option value="">SELECCIONA UN TUNEL</option>'+
			    	   				'<option value="1">1</option>'+
			    	   				'<option value="2">2</option>'+
			    	   				'</select>');*/
		   
		   $("#tunelocamara").html('<label for="tunel">TUNEL</label>'+
					'<select id="camara" name="camara" class="form-control" required>'+
	   				'<option value="">SELECCIONA UN TUNEL</option>'+
	   				'<option value="T1">TUNEL 1</option>'+
            '<option value="T2">TUNEL 2</option>'+
            '<option value="T3">TUNEL 3</option>'+
	   				'</select>');
	   }
	   
	   if(tunelsinovar=="NO"){
		   $("#tunelocamara").html('<label for="camara">CAMARA</label>'+
									'<select id="camara" name="camara" class="form-control" required>'+
	   								'<option value="">SELECCIONA UNA CAMARA</option>'+
	   								/*'<option value="1">1</option>'+
	   								'<option value="2">2</option>'+*/

									   '<option value="0">0</option>'+
									   '<option value="1">1</option>'+
									   '<option value="2">2</option>'+
									   '<option value="3">3</option>'+
									   '<option value="4">4</option>'+
									   '<option value="5">5</option>'+
									   '<option value="6">6</option>'+
									   '<option value="7">7</option>'+
									   '<option value="8">8</option>'+
									   '<option value="9">9</option>'+
	   								'</select>');
	   }
		
	   if(tunelsinovar==""){
		   $("#tunelocamara").html('<label for="camaratunel">(TUNEL O CAMARA)</label>'+
									'<select id=camaratunel name="camaratunel" class="form-control" required>'+
    	   							'<option value="">SELECCIONA SI ENTRA A TUNEL O NO</option>'+
    								'</select>');
	   }
	   /*if(tunelsinovar==""){
		   $("#tunelocamara").html('<label for="TUNEL">(TUNEL O CAMARA)</label>'+
			    					'<select id=camaratunel name="camaratunel" value='' class="form-control" required>'+
			    	   				'<option value="">SELECCIONA SI ENTRA A TUNEL O NO</option>'+
			    	   				'<option value="">SELECCIONA SI ENTRA A TUNEL O NO</option>'+
			    					'</select>');
	   }*/
   });
   
 });  
</script>
<section class="container">
<div id="contenidocapturaingresos">
<h1 id="item_name">Captura Ingresos</h1>
<form:form method="post"  commandName="detallesRd" role="form" action="insDetalle">
    <form:input type="hidden" path="idCliente" value="${regEntradas.idCliente}"/>
    <form:input type="hidden" path="consecutivo" value="${regEntradas.consecutivo}"/>
    <form:input type="hidden" path="idIngresoVehiculo" value="${regEntradas.idIngresoVehiculo}"/>

<section class="grupodedatosread">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="recibo_entrada">RECIBO DE ENTRADA </label>
    	<div class="alert-info"  id="recibo_entrada">${consecutivoObtenido}</div>
  	</div>
  	<div class="form-group">
    	<label for="chofer">CHOFER</label>
    	<div class="alert-info"  id="chofer">${vistaIngresoDetalle.nombreOperador}</div>
  	</div>
  	<div class="form-group">
    	<label for="THERMOKING">* TEMPERATURA THERMOKING</label>
    	<form:input type='text' path="thermoking" placeholder="TEMPERATURA THERMOKING" id="THERMOKING" name='THERMOKING' value='' class="form-control"/>
    	<form:errors path="thermoking" cssClass="error"/>
  	</div>
  	<div class="form-group">
    	<label for="TEMPERATURA_ANDEN">TEMPERATURA EN EL ANDEN</label>
    	<form:input type="text" path="temperaturaAnden" placeholder="TEMPERATURA_ANDEN" id="TEMPERATURA_ANDEN" name='TEMPERATURA_ANDEN' class="form-control"/>
        <form:errors path="temperaturaAnden" cssClass="error"/>
    </div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="cliente">CLIENTE</label>
    	<div class="alert-info-cliente"  id="cliente">${vistaIngresoDetalle.nombreCliente}</div>
  	</div>
  	<div class="form-group">
    	<label for="cliente">TRANSPORTE</label>
    	<div class="alert-info"  id="cliente">${vistaIngresoDetalle.transporte}</div>
  	</div>
  	<div class="form-group">
    	<label for="tunelsino">ENTRADA A TUNEL</label>
    	<select id="tunelsino" name="tunelsino" class="form-control">
    		<option value="" >SELECCIONA SI O NO</option>
    		<option value="SI" >SI</option>
    		<option value="NO" >NO</option>
    	</select>
    	
  	</div>
  	<div class="form-group">
  	    <div id="tunelocamara">
    	<label for="camaratunel">(TUNEL O CAMARA)</label>
    	<select id=camaratunel name="camaratunel" class="form-control" required>
    	   <option value="">SELECCIONA SI ENTRA A TUNEL O NO</option>
    	</select>
    	</div>
  	</div>
  </div>
  <div class="bloquedata">
  	
  	
  </div>
  </section>
  <div><h3 class="subtituloregdetalle">Registro de detalle del recibo de entrada:</h3></div>
  <section class="grupodedatosread">
  <div class="bloquedata">	
  	<div class="form-group">
    	<label for="RENGLON">RENGLON</label>
    	<input type='text' id="RENGLONVISTA" name='RENGLONVISTA' class="form-control" disabled="disabled"/>
    	<form:input type='hidden' path="renglon" placeholder="RENGLON" id="RENGLON" name='RENGLON' class="form-control" required="required"/>
        <form:errors path="renglon" cssClass="error"/>
  	</div>
  	<div class="form-group">
    	<label for="PRODUCTO">PRODUCTO</label>
    	<form:input type='text' path="descripcion" placeholder="PRODUCTO" id="descripcion" name='descripcion' value='' class="form-control" required="required"/>
  	</div>
  	<div class="form-group">
    	<label for="LOTE">LOTE</label>
    	<form:input type='text' path="lote" placeholder="LOTE" id="LOTE" name='LOTE' value='' class="form-control" required="required"/>
  	</div>
  	<div class="form-group">
    	<label for="EMBALAJE">EMBALAJE</label>
    	<form:select path="embalaje" id="embalaje" name="embalaje" class="form-control" required="required">
    		<option value="" >SELECCIONA EMBALAJE</option>
    		<c:forEach items="${embalajes}" var="emb">
    		   <option value="${emb.descripcion}" >${emb.descripcion}</option>
    		</c:forEach>
    	</form:select>
    	<form:errors path="embalaje" cssClass="error"/>
  	</div>
  	<div class="form-group">
    	<label for="MARCA">MARCA O CODIGO</label>
    	<form:input type='text' path="marca" placeholder="MARCA O CODIGO" id="MARCA" name='MARCA' value='' class="form-control" required="required"/>
    	<form:errors path="marca" cssClass="error"/>
  	</div>
  	<div class="form-group">
    	<label for="MARCA">NUMERO PEDIMENTO</label>
    	<form:input type='text' path="impedimento" placeholder="NUMERO PEDIMENTO" id="impedimento" name='impedimento' value='' class="form-control" required="required"/>
    	<form:errors path="impedimento" cssClass="error"/>
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="CANTIDAD">* CANTIDAD</label>
    	<form:input type='text' path="cantidad" placeholder="CANTIDAD" id="CANTIDAD" name='CANTIDAD' value='' class="form-control" required="required"/>
    	<form:errors path="cantidad" cssClass="error"/>
  	</div>
  	<div class="form-group">
    	<label for="CADUCIDAD">CADUCIDAD</label>
    	<form:input type='text' path="caducidad" placeholder="(dd/mm/aaaa)" id="CADUCIDAD" name='CADUCIDAD' value='' class="form-control" required="required"/>
    	<form:errors path="caducidad" cssClass="error"/>
  	</div>
  	<div class="form-group">
    	<label for="PESOU">PESO BRUTO</label>
    	<form:input type='text' path="pesou" placeholder="PESO UNITARIO" id="PESOU" size="8" maxlength="8" name='PESOU' value='' class="form-control" required="required"/>
  	</div>
  	<!--div class="form-group">
    	<label for="BULTOS">BULTOS</label>
    	<form:input type='text' path="bultos" placeholder="BULTOS" id="BULTOS" name='BULTOS' value='' class="form-control" required="required"/>
    	<form:errors path="bultos" cssClass="error"/>
  	</div-->
  	<div class="form-group">
    	<label for="observaciones">OBSERVACIONES</label>
			<form:input path="observaciones" type='text' placeholder="OBSERVACIONES" id="observaciones" name='observaciones' value='' size="75" maxlength="50" class="form-control observaciones"/>
    	<form:errors path="observaciones" cssClass="error"/>
  	</div>
  	
  	<div class="form-group">
    	<label for="PESOU">CUOTA DE COBRO</label>
    	<form:select path="claveProducto" id="claveProducto" name="claveProducto" class="form-control" required="required">
    		<option value="" >SELECCIONA PRODUCTO</option>
    		<c:forEach items="${Convenios}" var="cnv">
    		   <option value="${cnv.claveProducto}" >${cnv.producto} (${cnv.periodo} d&iacute;as)</option>
    		</c:forEach>
    	</form:select>
  	</div>
	  <div class="form-group">
		  <label for="pesoBruto">PESO NETO</label>
		  <form:input path="pesoBruto" type='text' placeholder="PESO NETO" id="pesoBruto" name='pesoBruto' value='' size="8" maxlength="8" class="form-control observaciones"/>
		  <form:errors path="pesoBruto" cssClass="error"/>
	  </div>

  </div>
  <div class="bloquedata">
	  <div class="form-group">
		  <label for="valorTotal">VALOR TOTAL</label>
		  <form:input path="valorTotal" type='text' placeholder="VALOR TOTAL" id="valorTotal" name='valorTotal' value='' size="75" maxlength="50" class="form-control observaciones"/>
		  <form:errors path="valorTotal" cssClass="error"/>
	  </div>
	  <div class="form-group">
		  <button class="btn btn-primary btn-small registradetalleboton enviaregistrodetalle" type="button">
			  <i class="icon-edit  bigger-125"></i>
			  Captura Rengl&oacute;n
		  </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <button class="btn btn-primary btn-small registradetalleboton limpiaCampo" type="button">
			  <i class="icon-edit  bigger-125"></i>
			  Limpia Campos
		  </button>
	  </div>
  </div>
  </section>
  </form:form>
<script>var conteo=1;</script>
</div>
    <div id="contenidoconsulta">
    <div id="tablaresultadodetalle">
      <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
              <th>Renglon</th>
              <th>Producto</th>
              <th>Marca o Codigo</th>
              <th>Lote</th>
              <th>Embalaje</th>
              <th>Cantidad</th>
              <th>Peso U</th>
              <th>Caducidad</th>
           </tr>
         </thead>  
        <c:set var="sumatotal" value="0"/> 
        <c:set var="sumapesou" value="0"/> 
        <c:set var="sumatotalT" value="0"/> 
        <c:forEach items="${regEntradasAlmacenadas}" var="det">
			<tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
				<td><fmt:formatNumber value="${det.renglon}" maxFractionDigits="0"/></td>
				<td>${det.descripcion}</td>
				<td>${det.marca}</td>
				<td>${det.lote}</td>
				<td>${det.embalaje}</td>
				<td><fmt:formatNumber value="${det.cantidad}" maxFractionDigits="0"/></td>
				<td><fmt:formatNumber value="${det.pesou}" maxFractionDigits="2"/></td>
				<td>${det.caducidad}</td>
			</tr>
			</tbody>
			<script>conteo++;</script>
			<c:set var="sumatotal" value="${sumatotal+det.cantidad}"/>
			<c:set var="sumapesou" value="${sumapesou+det.pesou}"/>
			<c:set var="sumatotalT" value="${sumatotalT+(det.cantidad*det.pesou)}"/>
		</c:forEach>
		  <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd" style="color:#000">
		  <tr>
			  <td colspan="4"></td>
			  <td class="center  sorting_1">TOTAL :</td>
			  <td class="center  sorting_1"><fmt:formatNumber value="${sumatotal}" maxFractionDigits="0"/></td>
			  <td class="center  sorting_1"><fmt:formatNumber value="${sumatotalT}" maxFractionDigits="2"/> (KGS)</td>
			  <!-- <c:set var="sumatotalT" value="${sumatotal*sumapesou}"/>
			  <td class="center  sorting_1"><fmt:formatNumber value="${sumatotalT}" maxFractionDigits="2"/></td-->
			  <td></td>
		  </tr>
		  </tbody>
       </table>
    </div>
     <br>
<form:form method="post" id="detallesRd" commandName="detallesRd" role="form" action="insDetalleFin">
	<input type="hidden" path="bandera" name="bandera" id="bandera" value="0"/>
    <form:input type="hidden" path="idIngresoVehiculo" value="${regEntradas.idIngresoVehiculo}"/>
	
    <section class="grupodedatos">
    <div class="bloquedata">
    	<div class="form-group">
   		<label>CARACTERISTICAS:</label>
    		<form:input path="posiciones" id="carac1" type='text'  placeholder="POSICIONES OCUPADAS" name='tempThermo' value='' size="5" maxlength="3" class="form-control"/>
    		<form:input path="estibas" id="carac2" type='text' placeholder="NIVEL DE ESTIBA" name='tempAnden' value='' size="5" maxlength="3" class="form-control"/>
    		<form:input path="altura" id="carac3" type='text' placeholder="ALTURA DE TARIMA" name='tempThermo' value='' size="5" maxlength="3" class="form-control"/>
    	</div>
    	<div class="form-group">
            <label>UBICACI&Oacute;N:</label>
           	<form:input path="calle" id="calle" type='text' placeholder="PUERTA" name='calle' value='' size="3" maxlength="100" class="form-control"/>
           	<form:input path="fila" id="fila" type='text' placeholder="FILA" name='fila' value='' size="3" maxlength="100" class="form-control"/>
        </div>
        <div class="form-group">
    		<label for="idAlmacenamiento">ALMACENAMIENTO:</label>
    		<form:select id="idAlmacenamiento" name="idAlmacenamiento" value='' class="form-control" path="almaen">
    			<option value=-1 > <fmt:message key="entradas.select"/> </option>
    			<option value="RACK">RACK</option>
    			<option value="CONVV">CONVV</option>
    			<option value="TARIMA">TARIMA</option>
    			<option value="PANADEROS">PANADEROS</option>
    		</form:select>
    		<label for="incidenciaAnden">INCIDENCIA EN EL ANDEN</label>
			<form:input path="incidenciaAnden" type='text' placeholder="INCIDENCIA AL INGRESO" id="incidenciaAnden" name='incidenciaAnden' value='' size="75" maxlength="60" class="form-control"/>
    	</div>
    </div>
    <div class="bloquedata">
    	<div class="form-group">
                <label>LIBRE DE PLAGAS:<br></label>
                <form:select path="plaga" class="form-control">
                    <option value="SI" >SI</option>
                    <option value="NO" >NO</option>
                </form:select>
            </div>
            <div class="form-group">
                <label>VEHICULO EN BUEN ESTADO:<br></label>
                <form:select path="vehiculoBuenEstado" class="form-control">
                   <option value="SI">SI</option>
                   <option value="NO">NO</option>
                </form:select>
            </div>
    	<div class="form-group">
        	<label>LIBRE DE VIDRIOS:</label>
        	<form:select path="libreVidrios" class="form-control">
                   <option value="SI">SI</option>
                   <option value="NO">NO</option>
            </form:select>
        </div>
        <div class="form-group">
                <label>VEHICULO LIMPIO:</label>
                <form:select path="vehiculoLimpio" class="form-control">
                   <option value="SI">SI</option>
                   <option value="NO">NO</option>
                </form:select>
            </div>
    </div>
        
    </section>

    <!-- input type="submit" class="btn-warning btn-lg terminacaptura" value="Terminar Captura"-->
    
    <button class="btn btn-primary btn-small" type="submit">
		<i class="icon-edit  bigger-125"></i>
		Terminar Captura
	</button>
</form:form>
</div>
<script>
   $("#RENGLONVISTA").val(conteo);
   $("#RENGLON").val(conteo);
</script>
</section>
