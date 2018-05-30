$(document).ready(function() {

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


        };

    });