/**
 * Created by Ricardo on 21/09/2015.
 */
$(document).ready(function(){
    $(".ingresarNota").click(function(){
        folio=$("#folio").val();
        nota=$("#nota").val();
        fecha=$("#fecha").val();
        idCliente=$("#idCliente").val();
        nofactura=$("#nofactura").val();
        facturaa=$("#facturaa").val();
        fechafactura=$("#fechafactura").val();
        fecharefactura=$("#fecharefactura").val();
        motivo=$("#motivo").val();
        concepto=$("#concepto").val();
        subtotal=$("#subtotal").val();
        camara=$("#camara").val();
        autoriza=$("#autoriza").val();
        realiza=$("#realiza").val();
        observaciones=$("#observaciones").val();

        $.ajax("insertaNota", {
            "type": "POST",
            "beforeSend": function() {
                $("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html('Cargando ...');
            },
            "success": function(result){
                $("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html(result);
                valorNota=parseInt(nota)+parseInt(1);
                $("#nota").val(valorNota);
                return false;
            },
            "error": function(result) {
                alert("Error al recuperar la información", result);
            },
            "data": {folio:folio,nota:nota,fecha:fecha,idCliente:idCliente,nofactura:nofactura,facturaa:facturaa,fechafactura:fechafactura,fecharefactura:fecharefactura,motivo:motivo,concepto:concepto,subtotal:subtotal,camara:camara,autoriza:autoriza,realiza:realiza,observaciones:observaciones},
            "async": true
        });
    });
    $(".finNota").click(function(){
        window.location.href=window.location;
    });
});