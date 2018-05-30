$(document).ready(function(){
    $(".RealizaSalida").click(function(){
        //var list = new Array();
        idCliente = $("#idCliente").val();
        numeroRd = $("#numeroRd").val();
        producto = $("#producto").val();
        caducidad = $("#caducidad").val();
        entregado = $("#entregado").val();
        //alert(entregado);
        var list = "";
        var listacantidad = "";
        $("#selecttodo").prop('checked', false);
        $("input[type=checkbox]:checked").each(function(){
            //cada elemento seleccionado
            //list.push($(this).val());
            list=list+","+$(this).val();
            listacantidad = listacantidad+","+$("."+$(this).val()).val();
            console.log("*****************"+listacantidad);
        });
        if(list.length==0){
            alert("Favor de seleccionar una salida.");
            return false;
        }else{
            /*for ( var i = 0; i < list.length; i = i + 1 ) {
             alert(list[i]);
             console.log( list[i] );
             }*/
            //HACE EL LLAMADO DE AJAX PARA ENVIAR LOS IDS DE LOS REGISTROS DE DETALLE RD QUE SERAN ANEXADOS A LA SALIDA
            $.ajax("realizaSalida", {
                "type": "POST", //usualmente post o get
                "beforeSend": function() {
                    $("#consultadetallesRd").css("color","#000");
                    $("#consultadetallesRd").css("font-size","45px");
                    $("#consultadetallesRd").html('Procesando Salida ...');
                },
                "success": function(result) {
                    $("#consultadetallesRd").css("font-size","0.875em");
                    $("#consultadetallesRd").html(result);
                },
                "error": function(result) {
                    alert("Error al realizar la salida", result);
                    //showAlert("Error al recuperar la informaci�n", result);
                },
                "data": { listaids: list, listacantidad: listacantidad, idCliente:idCliente, numeroRd:numeroRd, producto:producto, caducidad:caducidad,entregado : entregado},
                "async": true
            });
        }
        return false;

    });

    $(".campoInventario").change(function(){
        var nombreIdCampo = $(this).attr("campo");
        var valorFijo = $("#"+nombreIdCampo).val();
        var valorcambiado = $(this).val();
        if(parseFloat(valorcambiado)>parseFloat(valorFijo)){
            alert("El valor no puede ser mayor que lo existente en inventario");
            $(this).val(valorFijo);
            $(this).focus();
            return false;
        }
    });

    $(".terminasalida").click(function(){
        idCliente = $("#idCliente").val();
        $.ajax("terminaSalida", {
            "type": "POST", //usualmente post o get
            "beforeSend": function() {
                $("#terminandoSalida").css("color","#000");
                $("#terminandoSalida").css("font-size","45px");
                $("#terminandoSalida").html('Terminando Salida ...');
            },
            "success": function(result) {
                /* $("#consultadetallesRd").css("font-size","0.875em");
                 $("#consultadetallesRd").html("");*/
                var bandera=$("#bandera").val();
                alert("La salida ha sido concluida");
                $(location).attr('href','/almacen/formRetemes?bandera='+bandera);
            },
            "error": function(result) {
                alert("Error al Terminar la salida", result);
                $(location).attr('href','alm_consultaSalidas');
            },
            "data": { idCliente: idCliente},
            "async": true
        });
    });
});
