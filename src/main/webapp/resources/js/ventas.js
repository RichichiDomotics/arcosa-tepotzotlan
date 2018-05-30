/**
 * Created by Richard on 07/07/2015.
 */
$(document).ready(function(){
    $(".consultaVentas").click(function(){
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
        $.ajax("getVentas", {
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
    $(".consultaVentasMes").click(function(){
        fechaMes = $("#fecini").val();
        fechaMes2= fechaMes.split("/");
        mes="no hay mes";
        switch (fechaMes2[0]) {
            case "01":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Enero";
                break;
            case "02":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "28/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Febrero";
                break;
            case "03":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Marzo";
                break;
            case "04":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "30/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Abril";
                break;
            case "05":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Mayo";
                break;
            case "06":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "30/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Junio";
                break;
            case "07":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Julio";
                break;
            case "08":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Agosto";
                break;
            case "09":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "30/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Septiembre";
                break;
            case "10":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Octubre";
                break;
            case "11":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "30/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Noviembre";
                break;
            case "12":
                fecini = "01/"+fechaMes2[0]+"/"+fechaMes2[1];
                fecfin = "31/"+fechaMes2[0]+"/"+fechaMes2[1];
                mes = "Diciembre";
                break;
        }

        if(fechaMes==""){
            alert("Favor de ingresar un mes");
            $("#fecini").focus();
            return false;
        }
        $.ajax("getVentasxMes", {
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
            "data": {fecini:fecini, fecfin:fecfin, mes:mes},
            "async": true
        });
    });
    $(".consultaVentasIdEjecutivo").click(function(){
        fecini = $("#fecini").val();
        fecfin = $("#fecfin").val();
        idEjecutivo=$("#idEjecutivo").val();
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
        $.ajax("getVentasIdEjecutivo", {
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
            "data": {fecini:fecini, fecfin:fecfin, idEjecutivo:idEjecutivo},
            "async": true
        });
    });
});