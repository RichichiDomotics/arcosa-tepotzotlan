<%--
  Created by IntelliJ IDEA.
  User: jolvera
  Date: 03/09/2014
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<script>
    $(document).ready(function() {
        $(".salidaVehiculobtn").click(function(){
            idVehiculo = $(this).attr("idVehiculo");
            tipoEntradaSalida = $(this).attr("tipoEntradaSalida");
            fleje = "";
            if(tipoEntradaSalida=="1112"){
                if($("#"+idVehiculo).val()==""){
                    alert("favor de ingresar el fleje de salida");
                    $("#"+idVehiculo).focus();
                    return false;
                }else{
                    fleje = $("#"+idVehiculo).val();
                }
            }

            cambiaFleje(idVehiculo,fleje);
            //consultaProspectoId = $(this).attr("consultaProspectoId")
            //agregarCliente(idProspecto,consultaProspectoId);
        });

        cambiaFleje = function(idVehiculo,fleje){
            $.ajax("ae_SalidaAutoriza?idVehiculo="+idVehiculo+"&fleje="+fleje, {
                "type": "GET",
                "beforeSend": function() {
                    $("#buscaProspectodiv").css("font-size","27px");
                    $("#buscaProspectodiv").html('Cargando ...');
                },
                "success": function(result){
                    $("#buscaProspectodiv").css("font-size","14px");
                    $("#buscaProspectodiv").html(result);
                    return false;
                },
                "error": function(result) {
                    alert("Error al recuperar la información", result);
                    //showAlert("Error al recuperar la información", result);
                },
                "data": {},
                "async": true
            });
        }

    });

</script>
<div id="buscaProspectodiv">
<body>
<h1 id="item_name">Salida de Vehiculos</h1>
<h3 id="item_name">Registro de salida</h3>
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
  <thead>
    <tr role="row">
        <th>Tipo Vehiculo</th>
        <th>Placas</th>
        <th>Cliente</th>
        <th>Nombre Operador</th>
        <th>Empresa</th>
        <td>Fleje Salida</td>
        <th></th>
    </tr>
  </thead>  
    <c:set var="contador" value="0" />
    <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
    <c:forEach items="${vehiculos}" var="v">
            <td>${(v[1]).descripcion}</td>
            <td>${(v[0]).placasVehiculo}</td>
            <td>${(v[0]).idCliente} ${(v[2]).nombreCliente}</td>
            <td>${(v[0]).nombreOperador}</td>
            <td>${(v[0]).nombreCiaTransporte}</td>
            <c:set var="tipoRec" value="${v[0].idTipoRecibo}"/>
            <c:if test="${tipoRec >= 1112}">
                <td><input type="text" id="${v[0].idIngresoVehiculo}" name="${v[0].idIngresoVehiculo}" value=""></td>
            </c:if>
            <c:if test="${tipoRec <= 1111}">
                <td></td>
            </c:if>
            <td>
                <button class="btn btn-minier btn-primary salidaVehiculobtn" tipoEntradaSalida="${v[0].idTipoRecibo}" idVehiculo="${v[0].idIngresoVehiculo}">Dar Salida</button>
                <!--a href="ae_SalidaAutoriza?idVehiculo=${v[0].idIngresoVehiculo}"><b>DAR SALIDA</b></a-->
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</div>
</html>

