<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title><fmt:message key="title"/></title>
</head>
<body>
<div id="contenidoconsulta">
<h1 class="titulotabla alert-success"><fmt:message key="impresion.formato.heading"/></h1>
<!--h3><fmt:message key="consultas.ingreso.subtitle"/></h3-->
<section class="grupodedatosread">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="numerorecibo">NUMERO DE RECIBO</label>
    	<div class="alert-info"  id="numerorecibo">${detalle.numRecibo}</div>
  	</div>
  </div>
  <div class="bloquedata">	
  	<div class="form-group">
    	<label for="puerta">PUERTA</label>
    	<div class="alert-info"  id="puerta">${detalle.nombrePuerta}</div>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="fechaingreso">FECHA DE INGRESO</label>
    	<div class="alert-info"  id="fechaingreso">${detalle.fechaEntrada}</div>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="cliente">CLIENTE</label>
    	<div class="alert-info"  id="cliente">${detalle.nombreCliente}</div>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="operador">OPERADOR</label>
    	<div class="alert-info"  id="operador">${detalle.nombreOperador}</div>
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="planta">PLANTA</label>
    	<div class="alert-info"  id="planta">${detalle.nombrePlanta}</div>
  	</div>
  </div>
  
  </section>


</body>
<h3 class="tituloinicio2"><fmt:message key="impresion.formato.subtitle1"/></h3>
<form:form method="post" commandName="formEntradaImpresion" role="form">
<section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="temperatura">TEMPERATURA:</label>
    	<input id="temp1" type='text' placeholder="THERMOKING °C" name='tempThermo' value='' size="5" maxlength="3" class="form-control">
    	<input id="temp2" type='text' placeholder="EN ANDEN °C" name='tempAnden' value='' size="5" maxlength="3" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="rdTunel">ENTRA A TUNEL:<br></label>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="si">SI</label><input type="radio" name="rdTunel" value="">
  	</div>
  
  	<div class="form-group">
    	<label for="no">NO</label><input type="radio" name="rdTunel" value="">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="camara">CAMARA:</label>
    	<input id="camaraInicial" type='text' placeholder="INICIAL" name='camaraInicial' value='' size="5" maxlength="3" class="form-control">
    	<input id="camaraFinal" type='text' placeholder="FINAL" name='camaraFinal' value='' size="5" maxlength="3" class="form-control">
  	</div>
  </div>
  
</section>
</form:form>

<h3 class="tituloinicio2"><fmt:message key="impresion.formato.subtitle2"/></h3>
<form:form method="post" commandName="formEntradaProductos" role="form">
<section class="grupodedatos">
 <div class="bloquedata">
  	<div class="form-group">
    	<label for="cantidad">CANTIDAD:</label>
    	<input id="cantidad" type='text' placeholder="CANTIDAD" name='cantidad' value='' size="10" maxlength="10" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="embalaje">EMBALAJE:</label>
    	<input id="embalaje" type='text' placeholder="EMBALAJE" name='embalaje' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="producto">PRODUCTO:</label>
    	<input id="producto" type='text' placeholder="PRODUCTO" name='producto' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="caducidad">CADUCIDAD:</label>
    	<input id="caducidad" type='text' placeholder="CADUCIDAD" name='caducidad' value='' size="10" maxlength="10" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="marca">MARCA:</label>
    	<input id="marca" type='text' placeholder="MARCA" name='marca' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="lote">LOTE:</label>
    	<input id="lote" type='text' placeholder="LOTE" name='lote' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="peso">PESO U:</label>
    	<input id="peso" type='text' placeholder="PESO " name='peso' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="bultos">BULTOS:</label>
    	<input id="bultos" type='text' placeholder="BULTOS" name='bultos' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
</section>
</form:form>
<div class="sectionboton">
        <button class="btn btn-primary btn-sm" type="submit">Agregar Producto</button>
</div>
<h3 class="tituloinicio2"><fmt:message key="impresion.formato.subtitle3"/></h3>
<form:form method="post" commandName="formEntradaOtros" role="form">
<section class="grupodedatos">
 <div class="bloquedata">
  	<div class="form-group">
    	<label for="observaciones">OBSERVACIONES:</label>
    	<input id="observaciones" type='text' placeholder="OBSERVACIONES" name='observaciones' value='' size="10" maxlength="10" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="total">TOTAL:</label>
    	<input id="total" type='text' placeholder="CANTIDAD TOTAL" name='total' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="nombreValidador">NOMBRE VALIDADOR:</label>
    	<input id="nombreValidador" type='text' placeholder="NOMBRE VALIDADOR" name='nombreValidador' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="nombreAlmacenista">NOMBRE ALMACENISTA:</label>
    	<input id="nombreAlmacenista" type='text' placeholder="NOMBRE ALMACENISTA" name='nombreAlmacenista' value='' size="10" maxlength="255" class="form-control">
  	</div>
  </div>
  
</section>

<section class="grupodedatos">
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="caracteristicas">CARACTERISTICAS:</label>
    	<input id="carac1" type='text' placeholder="POSICIONES OCUPADAS" name='tempThermo' value='' size="5" maxlength="3" class="form-control">
    	<input id="carac2" type='text' placeholder="NIVEL DE ESTIBA" name='tempAnden' value='' size="5" maxlength="3" class="form-control">
    	<input id="carac3" type='text' placeholder="ALTURA DE TARIMA" name='tempThermo' value='' size="5" maxlength="3" class="form-control">
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
   	 <label for="idAlmacenamiento">ALMACENAMIENTO:</label>
    	<select id="idAlmacenamiento" name="idAlmacenamiento" value='' class="form-control">
    		<option value=-1 > <fmt:message key="entradas.select"/> </option>
        	<option value="RACK">RACK</option>
        	<option value="CONVV">CONVV</option>
        	<option value="TARIMA">TARIMA</option>
    	</select>
  	</div>
  </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="rdPlagas">LIBRE DE PLAGAS:<br></label>
  		<label for="si">SI</label><input type="radio" name="rdPlagas" value="">
  		<label for="no">NO</label><input type="radio" name="rdPlagas" value="">
  	</div>
  	<div class="form-group">
    	<label for="rdVidrios">LIBRE DE VIDRIOS:<br></label>
  		<label for="si">SI</label><input type="radio" name="rdVidrios" value="">
  		<label for="no">NO</label><input type="radio" name="rdVidrios" value="">
  	</div>
  	 </div>
  <div class="bloquedata">
  	<div class="form-group">
    	<label for="rdLimpio">VEHICULO LIMPIO:<br></label>
  		<label for="si">SI</label><input type="radio" name="rdLimpio" value="">
  		<label for="no">NO</label><input type="radio" name="rdLimpio" value="">
  	</div>
  	<div class="form-group">
    	<label for="rdEstado">VEHICULO EN BUEN ESTADO:<br></label>
  		<label for="si">SI</label><input type="radio" name="rdEstado" value="">
  		<label for="no">NO</label><input type="radio" name="rdEstado" value="">
  	</div>
  </div>
  
  
</section>
</form:form>

<div class="sectionboton">
        <button class="btn btn-primary btn-sm" type="submit">Imprimir Formato</button>
</div>
</div>
</html>