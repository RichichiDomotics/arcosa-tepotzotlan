<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/clientesCaptura.css" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/loader-barra.css" />
<script>
  $(document).ready(function() {
    $(".loader").hide();

    $(".ingresarMensaje").click(function(){
      idProspecto = $(".idProspecto").val();
      fecHoraEnviado=$("#fecHoraEnviado").val();
      usuarioEnvia=$("#usuarioEnvia").val();
      usuarioRecibe=$("#usuarioRecibe").val();
      mensaje=$("#mensaje").val();

      registraMensaje(idProspecto,fecHoraEnviado,usuarioEnvia,usuarioRecibe,mensaje);
      bootbox.hideAll();
    });

    registraMensaje = function(idProspecto,fecHoraEnviado,usuarioEnvia,usuarioRecibe,mensaje){

      $.ajax("registraMensaje", {
        "type": "POST",

        "success": function(result){
          //$("#CapSeg").css("font-size","14px");
          //$("#detCliente").html(result);
          bootbox.alert("Mensaje Enviado Satisfactoriamente",function(){
            window.location.href=window.location;

          });
          return false;
        },
        "error": function(result) {
          bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n")
        },
        "data": {idProspecto:idProspecto,fecHoraEnviado:fecHoraEnviado,usuarioEnvia:usuarioEnvia,usuarioRecibe:usuarioRecibe,mensaje:mensaje},
        "async": true
      });


    };
  });
</script>
<div class="modal-header no-padding">
  <div class="table-header">
    <button type="button" class="close" data-dismiss="modal">&times;</button>
    <strong>Enviar Mensaje</strong>
  </div>
</div>
<div class="bloquedata" style="display: inline-block">
  <div class="group-forma">
    <label>Fecha</label>

    <input type='text' placeholder="FECHA DE ENVIO" id="fecHoraEnviado" name='fecHoraEnviado' value='${now}' class="form-control" disabled>

    <input type='hidden' id="usuarioEnvia" name='usuarioEnvia' value="${claveUsuario}">

  </div>
  <div class="group-forma">
    <label>Enviar Mensaje</label>

      <textarea placeholder="mensaje" id="mensaje" name='mensaje' data-maxlength="200"
                style="width: 667px; height: 156px;" class="form-control limited"></textarea>

  </div>
  <select id="usuarioRecibe" name="usuarioRecibe">
      <option value="">Selecciona Ejecutivo</option>
      <c:forEach items="${ejecutivos}" var="e">
        <option value="${e.claveEjecutivo}">${e.nombreEjecutivo}</option>
      </c:forEach>
  </select>
  <input type='hidden' class="idProspecto" name='idProspecto' value='${idProspecto}'>
  &nbsp;&nbsp;
  <div class="group-forma">
    <button class="btn btn-primary btn-small ingresarMensaje" type="submit">
      <i class="icon-comment bigger-125"></i>
      Enviar Mensaje
    </button>
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
