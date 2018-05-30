$(document).ready(function(){
    $(".ingresarModificacion").click(function(){
        idmodificacion = $(this).attr("idmodificacion");
        consecutivo=$(this).attr("consecutivo");
        salida=$(this).attr("salida")
        veCapturaObservacion(idmodificacion,consecutivo,salida);
    });
    veCapturaObservacion = function(idmodificacion,consecutivo,salida){
        if(!$("#CapSeg").length){
            $("#supdiv").append('<div id="CapSeg"></div>')
        }
        $.ajax("autorizarModificacion", {
            "type": "POST",
            "beforeSend": function() {
                $("#CapSeg").css("font-size","27px");
                $("#CapSeg").html('Cargando ...');
            },
            "success": function(result){
                $("#CapSeg").css("font-size","12px");
                //$("#detCliente").html(result);
                bootbox.dialog($("#CapSeg").html(result), [{
                        "label" : "Cerrar",
                        "class" : "btn-small btn-primary",
                        "callback": function() {
                            $("#CapSeg").html("");
                        }
                    }]
                ).css({"width":"1000px","heith":"800px","left":"550px","overflow-y": "hidden"});

                return false;
            },
            "error": function(result) {
                alert("Error al recuperar la información", result);
                $("#CapSeg").html("");
            },
            "data": {idmodificacion:idmodificacion,consecutivo:consecutivo,salida:salida},
            "async": true
        });


    };

    $(".consultaModificaciones").click(function(){
        fecini = $("#fecini").val();
        fecfin = $("#fecfin").val();
        if(fecini==""){
            alert("Favor de ingresar una fecha de inicio");
            $("#fecini").focus();
            return false;
        }

        if(fecfin==""){
            alert("Favor de ingresar una fecha final");
            $("#fecfin").focus();
            return false;
        }
        $.ajax("getModificaciones", {
            "type": "POST",
            "beforeSend": function() {
                $("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html('<img src="resources/img/loader.GIF"/>');
            },
            "success": function(result){
                //$("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html(result);
                return false;
            },
            "error": function(result) {
                alert("Error al recuperar la información", result);
            },
            "data": {fecini:fecini, fecfin:fecfin},
            "async": true
        });
    });
});