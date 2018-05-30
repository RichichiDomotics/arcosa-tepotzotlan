/**
 * Created by Richard on 24/07/2015.
 */
$(document).ready(function(){
    $(".factura").click(function(){
        factura1=$(this).attr("factura");
        idCliente=$(this).attr("idCliente");
        factura(factura1,idCliente);
    });

    factura = function(factura1,idCliente){
        if($("#CapSeg").length){
            $("#supdiv").append('<div id="CapSeg"></div>')
        }

        bootbox.dialog($("#CapSeg").html("<iframe src='http://"+window.location.hostname+":8081/pdf/"+factura1+"_"+idCliente+".pdf'" +
        //bootbox.dialog($("#CapSeg").html("<iframe src='http://"+window.location.hostname+":8081/Ace/pdf/"+factura1+"_"+idCliente+".pdf'" +
                "width='100%' height='900px' frameborder='no' tabindex='0'>" +
                "</iframe>"), [{
                "label" : "Cerrar",
                "class" : "btn-small btn-primary",
                "callback": function() {
                    $("#CapSeg").html("");
                }
            }]
        ).css({"width":"1000px","height":"auto","left":"450px","overflow-y": "hidden"});
    };
});
