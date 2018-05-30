<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<script type="text/javascript" src="resources/js/retemes.js"></script>
<script type="text/javascript">
  <!--
  $(document).ready(function(){
    if($("#ID_CLIENTE").val()!=""){
      $("#ID_CLIENTE").change();
    }
    $(".finRetemesTIF").click(function(){
      var bandera=$("#bandera").val();
      if(bandera==2){
        location.href = window.location;
      }else{
        location.href = "terminaCapturaRetemeTIF?bandera="+bandera;
      }
    });

  });
  //-->
</script>
<section class="container">
  <input type="hidden" path="bandera" name="bandera" id="bandera" value="${bandera}"/>
  <h1 id="item_name"><fmt:message key="retemes.heading"/></h1>
  <h3 id="item_name"><fmt:message key="retemes.subtitle"/></h3>
  <form:form method="post" action="consultaRetemes">
  <section class="grupodedatosRetemes">
    <div class="bloquedata">
      <div class="form-group">
        <label id="FECHA">FECHA</label>
        <fmt:parseDate value="${now}" var="parsedEmpDate" pattern="dd/MM/yyyy" />
        <input type='text' placeholder="FECHA" id="FECHA" name='FECHA' value='<fmt:formatDate pattern="dd/MM/yyyy" value="${parsedEmpDate}" />' class="form-control" disabled>
      </div>
      <div class="form-group">
        <label for="ID_CLIENTE">CLIENTE</label>
        <select id="ID_CLIENTE" name="ID_CLIENTE" class="form-control" required>
          <option value="" > <fmt:message key="entradas.select"/> </option>
          <c:forEach items="${clientes}" var="client">
            <option value="${client.idCliente}"
                    <c:if test="${cliente == client.idCliente}">
                      selected
                    </c:if>
                    > <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
          </c:forEach>
        </select>
      </div>
      <div class="form-group">
        <label>RECIBO O SALIDA</label>
        <input type='text' placeholder="Recibo o Salida" id="CONSECUTIVO" name='CONSECUTIVO' value='${consecutivo}' maxlength="10" class="form-control" required>
      </div>
      <div class="form-group">
        <label>REFERENCIA CARTA DE SALIDA</label>
        <input type='text' placeholder="Referencia carta de salida" id="REFEREN" name='REFEREN' value='' maxlength="255" class="form-control">
      </div>
      <div class="form-group">
        <label>CAMARA</label>
        <select id="CAMARA" name="CAMARA" required class="form-control">
          <c:if test="${camara == ''}">
            <option value="" > <fmt:message key="entradas.select"/> </option>
          </c:if>
          <c:if test="${camara != '' }">
            <option value="${camara}" >${camara}</option>
          </c:if>
          <c:forEach items="${camaras}" var="camara">
            <option value="${camara.idCatalogo}"> <c:out value="${camara.descripcion}"/></option>
          </c:forEach>
        </select>
      </div>
      <div class="form-group">
        <label>PERSONA QUE REALIZA EL RETEME</label>
        <input type='text' placeholder="Referencia carta de salida" id="FIRMA_RETEME" name='FIRMA_RETEME' value='${user}' disabled class="form-control">
      </div>
    </div>
    <div class="bloquedata">
      <div class="form-group">
        <label>FOLIO RETEME</label>
        <input type='text' placeholder="Folio Reteme" id="FOLIORETEME" name='FOLIORETEME' value='${folioRetemes}' maxlength="10" class="form-control" disabled required>
      </div>
      <div class="form-group">
        <label>MANIOBRA ESPECIAL	            UNICAMENTE CARGOS AL CLIENTE</label>
        <div id="comboServicios">
          <label>ELIJA LA MANIOBRA ESPECIAL</label>
          <select id="TIPOSERVICIO" name="TIPOSERVICIO" required class="form-control">
            <option value="" > <fmt:message key="entradas.select"/> </option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label>KILOS O PIEZAS U HORAS EXTRAS</label>
        <input type='text' placeholder="Kilos o piezas u horas extras" id="KILOS" name='KILOS' maxlength="50" value='' class="form-control" required>
      </div>
      <!--div class="form-group">
		<label>ALTURA DE LAS TARIMAS</label>
    	<select id="ALTURA" name="ALTURA" required class="form-control">
        	<option value="" > <fmt:message key="entradas.select"/> </option>
        	<option value="0-1.5" >0-1.5</option>
        	<option value="1.51-1.8" >1.51-1.8</option>
        	<option value="1.81-2" >1.81-2</option> 
        </select>
  	</div-->
      <div class="form-group">
        <label>OBSERVACIONES</label>
        <textarea placeholder="OBSERVACIONES" id="OBSERV" name='OBSERV' class="form-control"></textarea>
      </div>
      <div class="form-group">
        <br>
        <button class="btn btn-primary btn-small insertaRetemesTIF" type="button">
          <i class="icon-edit  bigger-125"></i>
          Inserta Reteme
        </button>
        <button class="btn btn-primary btn-small finRetemesTIF" type="button">
          <i class="icon-edit  bigger-125"></i>
          Finaliza Captura
        </button>

      </div>
    </div>
    <input type='hidden' name='fechaEntrada' value='${fecha}'>
    <input type='hidden' name='horaEntrada' value='${hora}'>
    </form:form>
    <br><br>
    <div class="bloquedataResultado">
      <div id="buscaretemes"></div>
    </div>

  </section>
