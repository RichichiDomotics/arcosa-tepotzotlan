$(document).ready(function(){
    $(".consultaFacturas").click(function(){
        //var list = new Array();
        fechaini=$("#fecini").val();
        fechafin=$("#fecfin").val();
        //alert(entregado);
        var list = "";
        $("#selecttodo").prop('checked', false);
        $("input[type=checkbox]:checked").each(function(){
            //cada elemento seleccionado
            //list.push($(this).val());
            //list=list+","+$(this).val();
            list+=$(this).val()+",";
        });
        if(list.length==0){
            alert("Favor de seleccionar un cliente.");
            return false;
        }else{

            if(!$("#CapSeg").length){
                $("#supdiv").append('<div id="CapSeg"></div>')
            }
            $.ajax("crearFacturas", {
                "type": "POST", //usualmente post o get
                "beforeSend": function() {
                    var loader = $('<div align="center">Cargando...<br><img src="resources/img/loader.GIF"/></div>')
                    var alto=$(window).height();
                    bootbox.dialog(loader).css({"width": "220px","left":"875px","margin-top":(alto/2)-30+"px"});
                },
                "success": function(result) {
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
                    ).css({"width":"1250px","heith":"auto","left":"450px","overflow-y": "hidden"});

                    return false;
                },
                "error": function(result) {
                    bootbox.alert("Error al recuperar la información", result);
                    $("#CapSeg").html("");
                },
                "data": { listaids: list, fechaini: fechaini, fechafin: fechafin},
                "async": true
            });
        }
        return false;

    });

    $(".consultaPreFacturas").click(function(){
        fechaini=$("#fecini").val();
        fechafin=$("#fecfin").val();
        idcliente=$("#idCliente").val();

        $.ajax("preFacturas", {
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
            "data": {fechaini:fechaini, fechafin:fechafin, idcliente:idcliente},
            "async": true
        });
    });

});
