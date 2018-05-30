<%@ include file="/WEB-INF/views/include.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/loader-barra.css" />
<script>
  $(document).ready(function() {
    $(".loader").hide();
    usuarioRecibe = $("#usuarioRecibe").val();
    usuarioEnvia = $("#usuarioEnvia").val();
    $(".regresarMensaje").click(function(){
      enviaMensajeRes(usuarioRecibe,usuarioEnvia);
    });


    enviaMensajeRes = function(usuarioRecibe,usuarioEnvia){
      if(!$("#CapSeg").length){
        $("#mensajeForma").append('<div id="CapSeg"></div>')
      }else{
        $("#CapSeg").html("");
      }

      $.ajax("ContestaMensaje", {
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
                      $(location).attr('href','/home');
                      $("#CapSeg").html("");
                    }
                  }]
          ).css({"width":"800px","heith":"400px","left":"550px","overflow-y": "hidden"});

          return false;
        },
        "error": function(result) {
          alert("Error al recuperar la información", result);
          $("#CapSeg").html("");
        },
        "data": {idProspecto:0,usuarioRecibe:usuarioRecibe,usuarioEnvia:usuarioEnvia},
        "async": true
      });
    }
  });
</script>

<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Mensaje</strong>
  </div>
</div>
<div class="bloquedata" style="display: inline-block">
  <div class="group-forma">
    <label>Enviado:</label>
    <fmt:parseDate value="${mensajeRec.getFecHoraEnviado()}" var="parsedEmpDate" pattern="yyyy-MM-dd HH:mm:ss" />
    <input type='text' id="fecHoraEnviado" name='fecHoraEnviado' value="<fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${parsedEmpDate}" />" readonly>
    <label>Envio : </label>
    <input type='text' id="usuarioEnviaOr" name='usuarioEnviaOr' value="${mensajeRec.getUsuarioEnvia()}" readonly>
    <input type='hidden' id="usuarioRecibe" name='usuarioRecibe' value="${mensajeRec.getUsuarioEnvia()}" readonly>
    <input type='hidden' id="usuarioEnvia" name='usuarioEnvia' value="${mensajeRec.getUsuarioRecibe()}" readonly>
  </div>
  <div class="group-forma">
    <label>Mensaje</label>

      <textarea placeholder="mensaje" id="mensaje" name='mensaje' data-maxlength="200"
                style="width: 667px; height: 156px;" class="form-control limited" readonly>${mensajeRec.getMensaje()}</textarea>

  </div>
  &nbsp;&nbsp;
  <div class="group-forma">
    <button class="btn btn-primary btn-small regresarMensaje" type="submit">
      <i class="icon-comment bigger-125"></i>
      Contestar Mensaje
    </button>
  </div>
</div>
<div class="loader">

</div>

