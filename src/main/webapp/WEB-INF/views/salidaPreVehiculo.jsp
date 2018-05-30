<%--
  Created by IntelliJ IDEA.
  User: jolvera
  Date: 03/09/2014
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    $(document).ready(function(){
        $(".preVehiculo").click(function(){
            idVehiculo = $(this).attr("idvehiculo");
            //alert(idVehiculo);
            $.ajax("SalidaPreAutoriza", {
                "type": "POST", //usualmente post o get
                "success": function(result) {
                    bootbox.alert("Se ha dado Salida al Vehiculo correctamente",function(){
                        window.location.href=window.location;
                    });
                },
                "error": function(result) {
                    bootbox.alert("Error al realizar la salida del vehiculo :" + result);
                },
                "data": {idVehiculo : idVehiculo},
                "async": true
            });
            return false;
        });
    });
</script>
<h1 id="item_name">Salida de Vehiculo</h1>
<br>
<section class="grupodedatos">
    <table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
            <thead>
            <tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">TIPO DE MANIOBRA</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Placas</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cliente</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Nombre Operador</th>

            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled"></th>
        </tr>
        </thead>
        <c:set var="contador" value="0" />
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
        <c:forEach items="${vehiculos}" var="v">
                <td class="center  sorting_1">${(v[0]).maniobraCargaDescarga}</td>
                <td class="center  sorting_1">${(v[0]).placasVehiculo}</td>
                <td class="center  sorting_1">${(v[0]).idCliente} ${(v[1]).nombreCliente}</td>
                <td class="center  sorting_1">${(v[0]).nombreOperador}</td>

                <td><a href="#" class="preVehiculo" idvehiculo="${v[0].idPreVehiculo}"><b>DAR SALIDA</b></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
