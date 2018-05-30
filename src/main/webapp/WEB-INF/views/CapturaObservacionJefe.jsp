<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/loader-barra.css" />
<script>
  $(document).ready(function() {
    $(".loader").hide();

    $(".ingresarSegumiento").click(function(){
      idSeguimiento = $(".idSeguimiento").val();
      idProspecto = $("#idProspecto").val();
      fecha=$("#FECHA").val();
      clave=$("#CLAVE").val();
      observacion=$("#observacion").val();
      registraObservacion(idSeguimiento,fecha,clave,observacion,idProspecto);
      bootbox.hideAll();
    });

    registraObservacion = function(idSeguimiento,fecha,clave,observacion,idProspecto){

      $.ajax("RegistraObservacionJefe", {
        "type": "POST",

        "success": function(result){
          //$("#CapSeg").css("font-size","14px");
          //$("#detCliente").html(result);
          bootbox.alert("Informaci\u00F3n guardada satisfactoriamente",function(){
            bootbox.hideAll();

          });
          return false;
        },
        "error": function(result) {
          bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n"+result.error())
        },
        "data": {idSeguimiento:idSeguimiento,fecha:fecha,clave:clave,observacion:observacion,idProspecto:idProspecto,idEjecutivo:-1},
        "async": true
      });

      $( ".group-forma-observaciones" ).toggle();


    };

    $( ".group-forma-observaciones" ).toggle();

    visualizaObserva = function(){
      if($(".group-forma-observaciones").is(":visible")){
        $(".veObservacion").html("<i class='icon-eye-open'> Ver Observaciones anteriores");
      }else{
        $(".veObservacion").html("<i class='icon-eye-close'> Ocultar Observaciones anteriores");
      }
      $(".group-forma-observaciones").is(":visible")
      $(".group-forma-observaciones").toggle();
    }

  });
</script>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Capturar Observaci&oacute;n</strong>
  </div>
</div>
<div class="contenido-p">
  <div class="bloquedataObs">
    <a href="javascript:visualizaObserva();" class="btn btn-mini btn-success veObservacion"><i class="icon-eye-open"></i> Ver Observaciones anteriores</a>
    <div class="group-forma-observaciones">
      <table aria-describedby="sample-table-2_info" class="table table-striped table-bordered table-hover dataTable" id="tablaResultado">
        <thead>
        <tr role="row">
          <th align="center">Fecha</th>
          <th align="center" >Observo</th>
          <th align="center" >Observaciones</th>
        </tr>
        </thead>
        <c:forEach items="${observaciones}" var="c">
          <tbody aria-relevant="all" aria-live="polite" role="alert">
          <tr class="odd">
            <td align="center">${c.fecha}</td>
            <td align="center">${c.claveUsuario}</td>
            <td align="right" >${c.observacion}</td>
          </tr>
          </tbody>
        </c:forEach>
      </table>
    </div>
  </div>
<div class="bloquedataObs">
  <div class="group-forma">
    <label>Fecha</label>

    <input type='text' placeholder="FECHA" id="FECHA" name='FECHA' value='${now}' class="form-control" disabled>

    <input type='hidden' id="CLAVE" name='CLAVE' value="${claveUsuario}">


  </div>
  <div class="group-forma">
    <label>Observaciones</label>

      <textarea placeholder="Observaciones" id="observacion" name='observacion' data-maxlength="200"
                style="width: 667px; height: 156px;" class="form-control limited"></textarea>

  </div>
  <input type='hidden' class="idProspecto" name='idProspecto' id='idProspecto' value='${idProspecto}'>
  <input type='hidden' class="idSeguimiento" name='idSeguimiento' id='idSeguimiento' value='${idSeguimiento}'>
  &nbsp;&nbsp;
  <div class="group-forma">
    <button class="btn btn-primary btn-small ingresarSegumiento" type="submit">
      <i class="icon-edit  bigger-125"></i>
      Guardar
    </button>
  </div>

</div>
</div>
<div class="loader">

</div>
<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 6/26/2015
  Time: 2:51 AM
  To change this template use File | Settings | File Templates.
--%>
