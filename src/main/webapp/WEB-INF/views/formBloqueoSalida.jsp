<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authentication property="authorities" var="roles" scope="page" />
<c:forEach var="role" items="${roles}">
    <c:set var="rolef" value="${role}"/>
</c:forEach>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/entrada.css" />
<style>
  .bloqueforma{
    display:block;
    width: 300px;
    float: left;;
  }
</style>
<script>
  $(document).ready(function(){
      $(".confirmar").click(function(){
        idCliente=$("#idCliente").val();
        causa=$("#causa").val();
        insertaModificacion(idCliente,causa);
      });
      insertaModificacion = function(idCliente,causa){
        $.ajax("cambioEstatus",{
          "type":"POST",
          "success":function(result){
            bootbox.alert("Se ha cambiado estatus exitosamente !!! :)",function(){
              window.location.href=window.location;

            });
          },
          "error":function(result){
            bootbox.alert("Ocurri\u00F3 un erro an enviar la informaci\u00F3n");
          },
          "data": {idCliente:idCliente,causa:causa},
          "async":true
        });
      };

      $(".consultar").click(function(){
          $.ajax("consultarBloqueados",{
              "type": "POST",
              "beforeSend": function () {
                  $("#resultado").css("font-size", "14px");
                  $("#resultado").html('<img src="resources/img/loader.GIF"/>');
              },
              "success": function(result){
                  $("#resultado").html(result);
              },
              "error": function (result) {
                  bootbox.alert("No se pueden recuperar los clientes bloqueados");
              },
              "data": {},
              "async": true
          });
      });
  });
</script>
<section class="container">
<h1>BLOQUEO DE SALIDAS</h1>
<c:if test="${(rolef eq 'ROLE_ADMIN') || (rolef eq 'ROLE_FACTURACION')}">
    <div class="bloqueforma">
        <div class="form-group">
          <label>ID CLIENTE</label>
          <select id="idCliente" name="idCliente" value='' class="form-control" aria-required="true">
            <option value="" > <fmt:message key="entradas.select"/> </option>
            <c:forEach items="${clientes}" var="client">
              <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
            </c:forEach>
          </select>
        </div>

    </div>
    <div class="bloqueforma">

        <div class="form-group">
          <label>ESTATUS</label>
          <select class="form-control" id="causa" name="causa" aria-required="true">
            <option value=""><fmt:message key="entradas.select"/></option>
            <option value="APROBADA">PERMISOS PARA REALIZR SALIDAS</option>
            <option value="DENEGADA">BLOQUEO DE SALIDAS DE PRODUCTO</option>
          </select>
        </div>
    </div>
  <button class="btn btn-primary btn-small confirmar" type="button">
    <i class="icon-edit  bigger-125"></i>
    ACEPTAR
  </button>
</c:if>
    <button class="btn btn-primary btn-small consultar" type="button">
        <i class="icon-edit  bigger-125"></i>
        Consultar clientes bloqueados
    </button>
  </section>
<div id="resultado"></div>