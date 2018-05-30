$(document).ready(function(){
    $(".consultaposicion").click(function(){
        consecutivo=$(this).attr("consecutivo");
        if(!$("#CapSeg").length){
            $("#supdiv").append('<div id="CapSeg"></div>')
        }
        $.ajax("tablaRecUbicacion2",{
            "type": "POST",
            "beforeSend": function() {
                $("#CapSeg").css("font-size","27px");
                $("#CapSeg").html('Cargando ...');
            },
            "success": function(result){
                bootbox.hideAll();
                $("#CapSeg").css("font-size","12px");
                //$("#detCliente").html(result);
                bootbox.dialog($("#CapSeg").html(result), [{
                        "label" : "Cerrar",
                        "class" : "btn-small btn-primary",
                        "callback": function() {
                            $("#CapSeg").html("");
                        }
                    }]
                ).css({"width":"1000px","heith":"auto","left":"450px","overflow-y": "hidden"});

                return false;
            },
            "error": function(result) {
                alert("Error al recuperar la información", result);
                $("#CapSeg").html("");
            },
            "data": {consecutivo:consecutivo},
            "async": false
        });
    });
});
