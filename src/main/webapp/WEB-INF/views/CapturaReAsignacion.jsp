<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/loader-barra.css" />
<script>
  $(document).ready(function() {
    $(".loader").hide();

    $(".ingresarReAsignacion").click(function(){
      idProspecto = $(".idProspecto").val();
      fecha=$("#FECHA").val();
      idEjecutivo=$("#idEjecutivo").val();

      if(idEjecutivo==""){
        alert("Favor de seleccionar un Ejecutivo")
        $("#idEjecutivo").focus();
        return false;
      }
      registraReAsignacionF(idProspecto,fecha,idEjecutivo);
      bootbox.hideAll();
    });

    registraReAsignacionF = function(idProspecto,fecha,idEjecutivo){

      $.ajax("registraReAsignacion", {
        "type": "POST",

        "success": function(result){
          //$("#CapSeg").css("font-size","14px");
          //$("#detCliente").html(result);
          bootbox.alert("Informaci\u00F3n guardada satisfactoriamente",function(){
            window.location.href=window.location;

          });
          return false;
        },
        "error": function(result) {
          bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n")
        },
        "data": {idProspecto:idProspecto,fecha:fecha,idEjecutivo:idEjecutivo},
        "async": true
      });


    };
  });
</script>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Realiza Re-Asignaci&oacute;n</strong>
  </div>
</div>
<div class="bloquedata" style="display: inline-block">
    <div class="group-forma">
      <label>Fecha</label>

      <input type='text' placeholder="FECHA" id="FECHA" name='FECHA' value='${now}' class="form-control" disabled>

      <input type='hidden' id="CLAVE" name='CLAVE' value="${claveUsuario}">
      <input type='hidden' id="EVENTO" name='EVENTO' value="Solicitud de Autorizacion">

    </div>
    <div class="group-forma">
      <label>Ejecutivo a Re-Asignar</label>
      <select id="idEjecutivo" name='idEjecutivo' class="idEjecutivo" required>
        <option value="">Selecciona un Ejecutivo</option>
        <c:forEach items="${ejecutivos}" var="e">
          <option value="${e.id}">${e.nombreEjecutivo}</option>
        </c:forEach>
      </select>

    </div>
    <input type='hidden' class="idProspecto" name='idProspecto' id="idProspecto" value='${idProspecto}'>
  &nbsp;&nbsp;
  <div class="group-forma">
      <button class="btn btn-primary btn-small ingresarReAsignacion" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Realizar Reasignaci&oacute;n
      </button>
    </div>


</div>
<div class="loader">

</div>
