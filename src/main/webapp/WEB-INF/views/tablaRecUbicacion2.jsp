<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="resources/js/salidas.js"></script>
<script>
    $(document).ready(function(){

        $(".btnEditaRegistro").click(function(){
            idRegistro = $(this).attr("idRegistro");
            formaActualizaUbicacion(idRegistro);
            return false;
        });

        $(".btnBorraRegistro").click(function(){
            idRegistro = $(this).attr("idRegistro");
           // $("#dialog-confirm").html("Esta seguro de borrar este registro?");
            bootbox.confirm("Esta seguro de borrar este registro?", function(result) {
                if(result) {
                    borraRegistro(idRegistro);
                }
            });
            return false;
        });

        borraRegistro = function(idRegistro){
            $.ajax("borraUbicacion", {
                "type": "POST",
                "beforeSend": function() {
                   // $("#detCliente").css("font-size","27px");
                   // $("#detCliente").html('Cargando ...');
                },
                "success": function(result){
                    bootbox.alert("El registro ha sido borrado!");
                    $(".buscaUbicacionDet").click();
                    return false;
                },
                "error": function(result) {
                    bootbox.alert("Error al borrar el Registro!");
                    //alert("Error al borrar el Registro", result);
                    $(".buscaUbicacionDet").click();
                },
                "data": {idUbica:idRegistro},
                "async": true
            });


        };


        formaActualizaUbicacion = function(idRegistro){
            if(!$("#detCliente").length){
                $("#dialog-confirm").append('<div id="detCliente"></div>')
            }
            $.ajax("formaAcutalizaUbicacion", {
                "type": "POST",
                "beforeSend": function() {
                    $("#detCliente").css("font-size","17px");
                    $("#detCliente").html('Cargando ...');
                },
                "success": function(result){
                    $("#detCliente").css("font-size","14px");
                    //$("#detCliente").html(result);
                    bootbox.dialog($("#detCliente").html(result), [{
                                "label" : "Cerrar",
                                "class" : "btn-small btn-primary",
                                "callback": function() {
                                    $(".buscaUbicacionDet").click();
                                }
                            }]
                    ).css({"resize":"none","width":"900px","heith":"auto","left":"450px"});
                    return false;
                },
                "error": function(result) {
                    alert("Error al recuperar la información", result);
                    $("#detCliente").html("");
                },
                "data": {idUbica:idRegistro},
                "async": true
            });


        };

    });

</script>
<link rel="stylesheet" type="text/css"	href="resources/bootstrap/css/capturaDetalle.css" />
<c:if test="${ubicaciones=='[]'}">NO HAY REGISTROS CON LAS CONDICIONES DE BUSQUEDA INGRESADAS</c:if>
<c:if test="${ubicaciones!='[]'}">
<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
        <thead>
        	<tr role="row">
              <th>CAMARA</th>
              <th>LADO</th>
              <th>PASILLO</th>
              <th>FILA O CALLE</th>
              <th>SUPERFICIE</th>
              <th>POSICION</th>
              <th>TAMA&Ntilde;O TARIMA</th>
              <th>RD</th>
              <!--th>Modificar</th-->
           </tr>
          </thead>
    <c:forEach items="${ubicaciones}" var="det">
        <tbody aria-relevant="all" aria-live="polite" role="alert"><tr class="odd">
            <td><fmt:formatNumber value="${det[0]}" maxFractionDigits="0"/></td>
            <td>${det[1]}</td>
            <td>${det[2]}</td>
            <td><fmt:formatNumber value="${det[3]}" maxFractionDigits="0"/></td>
            <td><fmt:formatNumber value="${det[4]}" maxFractionDigits="0"/></td>
            <td><fmt:formatNumber value="${det[5]}" maxFractionDigits="0"/></td>
            <td>${det[6]}</td>
            <td>${det[7]}</td>
            <!--td>
                <button class="btn btn-mini btn-info btnEditaRegistro" idRegistro="${det[10]}">
                    <i class="icon-edit bigger-120"></i>
                </button>

                <button class="btn btn-mini btn-danger btnBorraRegistro" idRegistro="${det[10]}">
                    <i class="icon-trash bigger-120"></i>
                </button>
            </td-->

        </tr>
        </tbody>
    </c:forEach>
</table>
</c:if>