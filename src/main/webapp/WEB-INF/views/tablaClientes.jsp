<%@ include file="/WEB-INF/views/include.jsp" %>
<script type="text/javascript" src="resources/js/listadoClientes.js"></script>
<script>
    $(document).ready(function() {
        var oTable1 = $('#sample-table-2').dataTable();
        /*   $(".convierteClientebtn").click(function(){
         idProspecto = $(this).attr("idProspecto");
         consultaProspectoId = $(this).attr("consultaProspectoId")
         agregarCliente(idProspecto,consultaProspectoId);
         });

         agregarCliente = function(idProspecto,razonSocial){
         $.ajax("consultaProspectoId", {
         "type": "POST",
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
         "data": {id:idProspecto,razonSocial:razonSocial},
         "async": true
         });
         }

         $(".veDetalleClientebtn").click(function(){
         idCliente = $(this).attr("idCliente");
         nomCliente  = $(this).attr("nomCliente");
         veDetalleCliente(idCliente,nomCliente);
         });


         veDetalleCliente = function(idCliente,nomCliente){
         if(!$("#detCliente").length){
         $("#supdiv").append('<div id="detCliente"></div>')
         }
         $.ajax("consultaDetalleCliente", {
         "type": "POST",
         "beforeSend": function() {
         $("#detCliente").css("font-size","27px");
         $("#detCliente").html('Cargando ...');
         },
         "success": function(result){
         $("#detCliente").css("font-size","14px");
         //$("#detCliente").html(result);
         bootbox.dialog($("#detCliente").html(result), [{
         "label" : "Cerrar",
         "class" : "btn-small btn-primary",
         "callback": function() {
         $("#detCliente").html("");
         }
         }]
         ).css({"width":"1100px","heith":"auto","left":"450px"});
         return false;
         },
         "error": function(result) {
         alert("Error al recuperar la información", result);
         $("#detCliente").html("");
         },
         "data": {idCliente:idCliente,nombreCliente:nomCliente},
         "async": true
         });


         };*/

    });

</script>
<h1>Listado de Clientes</h1>

<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
    <thead>
    <tr role="row">
        <th>
            DETALLE
        </th>
        <th>CLIENTE</th>
        <th>RFC</th>
        <th>DIRECCION</th>
        <th>CONTACTO</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
    </tr>
    </thead>
    <tbody aria-relevant="all" aria-live="polite" role="alert">
    <c:forEach items="${clientes}" var="c">
        <tr class="odd">
            <td align="center" >
                <!--${c.idEjecutivo}        ${idEjecutivo}-->

                <button class="btn btn-minier btn-primary veDetalleClientebtn" idEjecutivo="${c.idEjecutivo}" idCliente="${c.idCliente}" nomCliente="${c.nombreCliente}" >Cliente</button>

            </td>
            <td>${c.idCliente} ${c.nombreCliente}</td>
            <td>${c.rfc}</td>
            <td>${c.calle} ${c.colonia} ${c.ciudad} ${c.idEstado} ${c.cp}</td>
            <td>${c.contacto}</td>
            <td>${c.email}</td>
            <td>${c.telefono1}</td>
        </tr>
    </c:forEach>
    </tbody>
    <!--tr>
 <td colspan="2" align="center"><input type="submit" value="Consultar"></td>
 <td colspan="2" align="center"><input type="submit" value="Modificar"></td>
 <td colspan="2" align="center"><input type="submit" value="Eliminar"></td>
 </tr-->
</table>
<div id="supdiv"><div id="detCliente"></div></div>
