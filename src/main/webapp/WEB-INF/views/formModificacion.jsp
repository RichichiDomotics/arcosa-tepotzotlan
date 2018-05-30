<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
      $(".ingresarModificacion").click(function(){
        folio=$("#folio").val();
        nombre=$("#nombre").val();
        area=$("#area").val();
        fecha=$("#fecha").val();
        hora=$("#hora").val();
        idCliente=$("#idCliente").val();
        cambio=$("#cambio").val();
        causa=$("#causa").val();
        consecutivo=$("#consecutivo").val();
        salida=$("#salida").val();
        insertaModificacion(folio,nombre,area,fecha,hora,idCliente,cambio,causa,consecutivo,salida);
      });
      insertaModificacion = function(folio,nombre,area,fecha,hora,idCliente,cambio,causa,consecutivo,salida){
        $.ajax("insertarModificacion",{
          "type":"POST",
          "success":function(result){
            bootbox.alert("Informaci\u00F3n guardada satisfactoriamente",function(){
              window.location.href=window.location;

            });
          },
          "error":function(result){
            bootbox.alert("Ocurri\u00F3 un error an enviar la informaci\u00F3n");
          },
          "data": {folio:folio,nombre:nombre,area:area,fecha:fecha,hora:hora,idCliente:idCliente,cambio:cambio,causa:causa,consecutivo:consecutivo,salida:salida},
          "async":true
        });
      };
  });
</script>
<section class="container">
<h1>SOLICITUD DE CAMBIOS AL SISTEMA</h1>
    <div class="bloqueforma">

        <div class="form-group">
          <label>FOLIO</label>
          <input type="text" class="form-control" placeholder="FOLIO" id="folio" name="folio" value="${folio}" disabled>
        </div>


      <div class="form-group">
        <label>NOMBRE DEL SOLICITANTE <strong>(completo)</strong></label>
        <input type="text" class="form-control" placeholder="NOMBRE" id="nombre" name="nombre" value="" aria-required="true">
      </div>


        <div class="form-group">
          <label>AREA SOLICITANTE</label>
          <input type="text" class="form-control" placeholder="AREA" id="area" name="area" value="SERVICIOS A CLIENTES" disabled>
        </div>


        <div class="form-group">
          <label>FECHA</label>
          <input type="text" class="form-control" placeholder="FECHA" id="fecha" name="fecha" value="${now}" disabled>
        </div>


        <div class="form-group">
          <label>HORA</label>
          <input type="text" class="form-control" placeholder="HORA" id="hora" name="hora" value="${time}" disabled>
        </div>


        <div class="form-group">
          <label>ID CLIENTE</label>
          <select id="idCliente" name="idCliente" value='' class="form-control" aria-required="true">
            <option value="" > <fmt:message key="entradas.select"/> </option>
            <c:forEach items="${clientes}" var="client">
              <option value="${client.idCliente}"> <c:out value="${client.idCliente} ${client.nombreCliente}"/></option>
            </c:forEach>
          </select>
        </div>


        <div class="form-group">
          <label>CAMBIO SOLICITADO</label>
           <textarea placeholder="CAMBIO SOLICITADO" id="cambio" name='cambio' data-maxlength="200"
                     style="width: 667px; height: 156px;" class="form-control limited"></textarea>
        </div>

    </div>
    <div class="bloqueforma">

        <div class="form-group">
          <label>CAUSA</label>
          <select class="form-control" id="causa" name="causa" aria-required="true">
            <option value=""><fmt:message key="entradas.select"/></option>
            <option value="CANCELACION DE LA SALIDA">CANCELACION DE LA SALIDA</option>
            <option value="EL CLIENTE NO REQUIERE EL PRODUCTO">EL CLIENTE NO REQUIERE EL PRODUCTO</option>
            <option value="SE LLEVA MENOS PRODUCTO">SE LLEVA MENOS PRODUCTO</option>
            <option value="NO ES PRODUCTO DESEADO">NO ES PRODUCTO DESEADO</option>
            <option value="ERROR DE ALMACENISTA">ERROR DE ALMACENISTA</option>
            <option value="ERROR DE MAQUILA">ERROR DE MAQUILA</option>
            <option value="ERROR DE OPERADOR">ERROR DE OPERADOR</option>
            <option value="ERROR DE SISTEMA">ERROR DE SISTEMA</option>
            <option value="SOLICITUD DEL CLIENTE">NO SE PRESENTO EL CLIENTE</option>
            <option value="FALTA DE ESPACIO EN CAMARAS">FALTA DE ESPACIO EN CAMARAS</option>
            <option value="PRODUCTO DAÑADO">PRODUCTO DA&Ntilde;ADO</option>
            <option value="TEMPERATURA FUERA DE RANGO">TEMPERATURA FUERA DE RANGO</option>
            <option value="INGRESO FUERA DE HORARIO">INGRESO FUERA DE HORARIO</option>
            <option value="NO SE PRESENTO EL CLIENTE">NO SE PRESENTO EL CLIENTE</option>
            <option value="OTROS">OTROS</option>
          </select>
        </div>


        <div class="form-group">
          <label>RD AFECTADO</label>
          <input type="text" class="form-control" placeholder="RD AFECTADO" id="consecutivo" name="consecutivo" value="" aria-required="true">
        </div>


        <div class="form-group">
          <label>SALIDA AFECTADA</label>
          <input type="text" class="form-control" placeholder="SALIDA AFECTADA" id="salida" name="salida" value="" aria-required="true">
        </div>

      <div class="group-forma">
        <button class="btn btn-primary btn-small ingresarModificacion" type="submit">
          <i class="icon-edit  bigger-125"></i>
          Solicitar
        </button>
      </div>
    </div>
  </section>