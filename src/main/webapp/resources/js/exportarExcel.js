$(document).ready(function() {
    $(".component").css("font-size","13px");
    $(".botonExcel").click(function(event) {
        $("#datos_a_enviar").val( $("<div>").append( $(".Exportar_a_Excel").eq(0).clone()).html());
        $("#FormularioExportacion").attr('action',"http://"+window.location.hostname+":8087/ficheroExcel.php");
        //$("#FormularioExportacion").attr('action',"http://"+window.location.hostname+"/Ace/ficheroExcel.php");
        $("#FormularioExportacion").submit();
    });
});