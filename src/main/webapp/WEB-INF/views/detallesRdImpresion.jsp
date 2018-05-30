<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    $(document).ready(function () {
        $(".RealizaImpresion").click(function () {
            //var list = new Array();
            //idCliente = $("#idCliente").val();
            folioSalida = $("#folioSalida").val();
            //producto = $("#producto").val();
            //caducidad = $("#caducidad").val();
            var list = "";
            var consecutivolist = "";
            //var listacantidad = "";
            $("#selecttodo").prop('checked', false);
            $("input[type=checkbox]:checked").each(function () {
                //cada elemento seleccionado
                //list.push($(this).val());
                list = list + "," + $(this).val();
                consecutivolist = consecutivolist + "," + $(this).attr("consecutivo");
                //listacantidad = listacantidad+","+$("."+$(this).val()).val();
                console.log("*****************" + consecutivolist);
            });
            if (list.length == 0) {
                alert("Favor de seleccionar una salida.");
                return false;
            } else {
                //HACE EL LLAMADO DE AJAX PARA ENVIAR LOS IDS DE LA SALIDA A IMPRIMIR
                $.ajax("imprimirSalida", {
                    "type": "POST", //usualmente post o get
                    "beforeSend": function () {
                        $("#consultadetallesRd").css("color", "#000");
                        $("#consultadetallesRd").css("font-size", "45px");
                        $("#consultadetallesRd").html('Procesando Impresion Salida ...');
                    },
                    "success": function (result) {
                    	$("#bootbox-options").html("");
                        $("#impresionpdfsalida").html("");
                        $("#consultadetallesRd").css("font-size", "14px");
                        $("#consultadetallesRd").html(result);
                        var element = list.split(',');
                        jQuery.each(element, function (i, val) {
                            if (val != "") {
                            	$("#bootbox-options").append("<a target='_blank' href='resources/pdf/salida" + val + ".pdf'>" + "salida" + val + ".pdf" + "</a><br/>");
                                /*$("#bootbox-options").append("<a target='_blank' href='http://localhost/Ace/pdf/salida" + val + ".pdf'>" + "salida" + val + ".pdf" + "</a><br/>");*/
                            }
                        });
                        bootbox.dialog("Imprime <br> "+$("#bootbox-options").html(), [{
    						"label" : "Cerrar",
    						"class" : "btn-small btn-primary",
    						"callback": function() {
    							$("#bootbox-options").html("");
    						}
    						}]
                        );
                    },
                    "error": function (result) {
                        alert("Error al realizar la salida", result);
                        //showAlert("Error al recuperar la informaci�n", result);
                    },
                    "data": { listaids: list, folioSalida: folioSalida},
                    "async": true
                });
            }
            return false;

        });
    });
</script>
<div id="contenidoconsultadetallerd">
    <!-- h1 class="titulotabla alert-success"><fmt:message key="consultas.ingreso.heading"/></h1-->
    <table class="table table-striped table-bordered table-hover dataTable">
        <thead>
        <tr role="row">
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
            	<input type="checkbox" name="selecttodo" id="selecttodo"
                                                 onclick="javascript:selectall()"/><span class="lbl"> Selecciona todos</span>
            </th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Folio Salida</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Numero RD</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Id Cliente</th>
            <th aria-label="" style="width: 58px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">Cliente</th>
        </tr>
        </thead>
        <c:forEach items="${detalle}" var="e">
            <tr>
                <td class="center  sorting_1">
                    <input type="checkbox" id="idSalida" name="idSalida" value="${e.folioSalida}"
                           consecutivo="${e.consecutivo}"><span class="lbl"></span>
                </td>
                <td class="center  sorting_1">${e.folioSalida}</td>
                <td class="center  sorting_1">${e.consecutivo}</td>
                <td class="center  sorting_1">${e.idCliente}</td>
                <td class="center  sorting_1">${e.nomCliente}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" align="center">
            	<button class="btn btn-primary btn-small RealizaImpresion" type="button">
					<i class="icon-edit  bigger-125"></i>
					Realizar Impresion
				</button>		
			</td>
        </tr>
    </table>
    <br>
    <!-- /form:form-->
    <!--a href="<c:url value="home"/>">Home</a-->
</div>