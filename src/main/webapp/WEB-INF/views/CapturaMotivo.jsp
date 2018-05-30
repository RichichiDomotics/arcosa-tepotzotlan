<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/loader-barra.css" />
<script>
  $(document).ready(function() {
    $(".loader").hide();

    $(".ingresarSegumientoReactiva").click(function(){
      idProspecto = $(".idProspecto").val();
      fecha=$("#FECHA").val();
      clave=$("#CLAVE").val();
      motivo=$("#segui").val();
      evento=$("#EVENTO").val();
      registraMotivoReactiva(idProspecto,fecha,clave,motivo,evento);
      bootbox.hideAll();
    });

    registraMotivoReactiva = function(idProspecto,fecha,clave,motivo,evento){

      $.ajax("ae_RegistraMotivo", {
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
        "data": {idProspecto:idProspecto,fecha:fecha,clave:clave,motivo:motivo,evento:evento},
        "async": true
      });


    };
  });
</script>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Solicitar Motivo de Autorizaci&oacute;n</strong>
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
      <label>Motivo de Solicitud de Autorizaci&oacute;n</label>

      <textarea placeholder="motivo" id="segui" name='segui' data-maxlength="200"
                style="width: 667px; height: 156px;" class="form-control limited"></textarea>

    </div>
    <input type='hidden' class="idProspecto" name='idProspecto' value='${idProspecto}'>
  &nbsp;&nbsp;
  <div class="group-forma">
      <button class="btn btn-primary btn-small ingresarSegumientoReactiva" type="submit">
        <i class="icon-edit  bigger-125"></i>
        Solicitar
      </button>
    </div>


</div>
<div class="loader">

</div>
